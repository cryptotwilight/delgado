package crypto.delgado.blockchain;

import java.util.List; 

import org.web3j.protocol.Web3j;
import org.web3j.protocol.http.HttpService;

import crypto.delgado.pojo.DealSheet;

public class EthereumManager {

	private String contractAddress; 
	private Web3j web3;
	
	public EthereumManager(String _network, String _infuraKey,  String _contractAddress) {
		web3 = Web3j.build(new HttpService("https://"+_network+".infura.io/v3/"+_infuraKey));	
	} 
	
	
	public void publishDealSheets(List<DealSheet> dealSheets) {
		
		
		
	}
	
}
