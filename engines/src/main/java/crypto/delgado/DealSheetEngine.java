package crypto.delgado;

import java.util.List; 

import crypto.delgado.pojo.DealSheet;
import crypto.delgado.protocol.ProtocolMarketsCollector;
import crypto.delgado.blockchain.EthereumManager;
import crypto.delgado.db.DBManager;
import crypto.delgado.storage.IPFSManager;

public class DealSheetEngine {

	public static void main(String[] args) {
		String network = "rinkeby";
		String infuraKey = "";
		String contractAddress = "";
		String uri = "/ipfs.io/ipfs/tcp";
		int port = 5001;
		try {
			EthereumManager ethereum = new EthereumManager(network, infuraKey, contractAddress);
			IPFSManager ipfs = new IPFSManager(uri, port);
			String tempPath = "temp/";
			DBManager db = DBManager.getInstance(); 
			
			DealSheetPublisher publisher = new DealSheetPublisher(ipfs, ethereum, tempPath);
			DealSheetBuilder builder = new DealSheetBuilder(db);
			List<ProtocolMarketsCollector> collectors = getCollectors();
						
			SourceDataETL etl = new SourceDataETL(db, collectors );
			
			// pull source data and populate
			etl.populateSourceData(); 
			
			// build dealsheets											
			List<DealSheet> dealSheets = builder.buildDeals(); 
			
			// post dealsheets to market place & ipfs			
			publisher.publishDeals(dealSheets);
		
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}

	private static List<ProtocolMarketsCollector>  getCollectors() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
