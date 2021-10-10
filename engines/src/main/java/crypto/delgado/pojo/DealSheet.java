package crypto.delgado.pojo;

import java.util.Date;
import java.util.List;

public class DealSheet {

	public int sheetId; 
	public String cid; 
	public String protocol; 
	public Date createDate; 
	public double maxYield; 
	public double minYield; 
	public double maxBorrow; 
	public double minBorrow; 
	public double maxLiquidity; 
	public double minLiquidity;
	
	public int dealCount; 
	
	public List<Deal> deals; 
}
