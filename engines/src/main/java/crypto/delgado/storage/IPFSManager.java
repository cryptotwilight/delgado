package crypto.delgado.storage;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import io.ipfs.api.IPFS;
import io.ipfs.api.MerkleNode;
import io.ipfs.api.NamedStreamable;
import io.ipfs.multihash.Multihash;

public class IPFSManager {

	private String connectString; 
	
	public IPFSManager(String uri, int port) {
		connectString = uri +"/"+port; 
	}
	
	private IPFS getIPFS() throws IOException {
		IPFS ipfs = new IPFS(connectString);
		ipfs.refs.local();
		return ipfs; 
	}
	
	public String upload(String path) throws Exception {
		return upload(new File(path));		
	}
	
	public String upload(File file) throws Exception {		
		NamedStreamable.FileWrapper wfile = new NamedStreamable.FileWrapper(file);
		IPFS ipfs = getIPFS();
		MerkleNode addResult = ipfs.add(wfile).get(0);
		return addResult.name.get();		
	}
	
	public String downloadFile(String cid, String savePath, String extension) throws Exception {
		
		Multihash filePointer = Multihash.fromBase58(cid);
		IPFS ipfs = getIPFS();
		byte[] fileContents = ipfs.cat(filePointer);
		String filename = savePath+"/"+cid+"."+extension;
		File file = new File(filename);
		write(file, fileContents);
		return filename; 
		
	}
	
	private void write(File file, byte[] fileContents)  throws Exception  {
		
		FileOutputStream fos = null;
		BufferedOutputStream bos = null; 
		
		try {
			
			fos = new FileOutputStream(file);
			bos = new BufferedOutputStream(fos, 1024);
			bos.write(fileContents);
			
		}
		finally {
	
			close(bos);
			close(fos);
		}
	}
	
	
	private void close(final OutputStream o) throws Exception {
		if(o != null) {
			o.close();
		}
	}
	
}
