package crypto.delgado;

import java.util.List;

import crypto.delgado.db.DBManager;
import crypto.delgado.protocol.ProtocolMarketsCollector;

public class SourceDataETL {

	private DBManager db; 
	private List<ProtocolMarketsCollector> collectors; 
	
	public SourceDataETL (DBManager _db, List<ProtocolMarketsCollector> _collectors) {
		db = _db;
		collectors = _collectors; 
	}
	
	public void populateSourceData() { 
		
		
		
	}
	
	
}
