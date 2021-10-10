package crypto.delgado.protocol;

public interface ProtocolMarketsCollector {

	public MarketMatrix getMarkets() throws Exception;
	
	public String getProtocolName(); 
	
}
