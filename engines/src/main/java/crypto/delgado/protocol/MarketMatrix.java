package crypto.delgado.protocol;

import java.util.List;

public interface MarketMatrix {

	public List<Market> getSupplyMarkets(); 
	
	public List<Market> getBorrowMarkets();
	
}
