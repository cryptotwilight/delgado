package crypto.delgado;

import crypto.delgado.storage.IPFSManager;
import crypto.delgado.util.DealSheetConverter;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import crypto.delgado.blockchain.EthereumManager;
import crypto.delgado.pojo.DealSheet; 

public class DealSheetPublisher {

		private IPFSManager ipfs; 
		private EthereumManager ethereum; 
		private String tempPath;
		
		public DealSheetPublisher(IPFSManager _ipfs, EthereumManager _ethereum, String _tempPath) {
			ipfs = _ipfs; 
			ethereum = _ethereum;
			tempPath = _tempPath; 
		}
		
		public HashMap<Integer, String> publishDeals(List<DealSheet> dealSheets) throws Exception {
			HashMap<Integer, String> dealSheetCids = new HashMap<Integer, String>();
			DealSheetConverter dsc = new DealSheetConverter();
			
			// publish to ipfs and get the CID
			for(DealSheet d : dealSheets) {
				String cid = ipfs.upload(dsc.dealSheetToFile(d, tempPath +"/"+new Date().getTime()+".json"));
				d.cid = cid;
				dealSheetCids.put(d.sheetId, cid);				 
			}
			// publish to blockchain 
			ethereum.publishDealSheets(dealSheets);										
			return dealSheetCids; 
		}
		
}
