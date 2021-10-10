package crypto.delgado.pojo;

public class Deal {

	// yield from collateral 
	public double collateralAt; 
	// currency from which to borrow, to which collateral has been provided
	public String borrwoFrom;
	// rate at which the loan will be taken at 
	public double loanAt;
	// currency to swap to to provide liquidity
	public String swapTo; 
	// rate at which liquidity should yield for SwapTo
	public double lendAt; 
		
}
