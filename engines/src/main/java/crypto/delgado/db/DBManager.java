package crypto.delgado.db;

import java.sql.Date;
import java.util.Properties;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;

public class DBManager {

	public static final String DB_PROPERTY_SERVER		= "mongo-server";
	public static final String DB_PROPERTY_PORT			= "mongo-port";
	public static final String DB_PROPERTY_USER			= "mongo-user";
	public static final String DB_PROPERTY_PASSWORD		= "mongo-password";
	public static final String DB_PROPERTY_DATABASE		= "mongo-database";
	public static final String DB_PROPERTY_AUTH_SOURCE	= "mongo-auth-source";
	
	private MongoDatabase mdb = null; 
	
	private static DBManager dbm;
	
	private DBManager( MongoDatabase _mdb) {		
		 mdb = _mdb;
	}
	
	private static Properties getDefaultProperties() {
		Properties properties = new Properties(); 
		properties.setProperty(DB_PROPERTY_DATABASE, 	"test");
		properties.setProperty(DB_PROPERTY_PORT, 		"7");
		properties.setProperty(DB_PROPERTY_USER, 		"x");
		properties.setProperty(DB_PROPERTY_PASSWORD, 	"x");
		properties.setProperty(DB_PROPERTY_SERVER, 		"localhost");
		properties.setProperty(DB_PROPERTY_AUTH_SOURCE, "test");
		
		return properties; 
	}
	
	public static DBManager getInstance() throws Exception {		
			return getInstance(getDefaultProperties());
	
	}
	
	public static DBManager getInstance(Properties properties) throws Exception {		
		if (dbm == null) {
			
			
			MongoClient client = MongoClients.create("mongodb://"+properties.getProperty(DB_PROPERTY_USER)+":"+properties.getProperty(DB_PROPERTY_PASSWORD)+"@"+properties.getProperty(DB_PROPERTY_SERVER)+":"+properties.getProperty(DB_PROPERTY_PORT)+"/?authSource="+properties.getProperty(DB_PROPERTY_AUTH_SOURCE));
			
			MongoDatabase mongodb = client.getDatabase(properties.getProperty(DB_PROPERTY_DATABASE)); 
			dbm = new DBManager(mongodb);
			
		}
		return dbm; 
	}
	
	public DBManager() {
		
	}
	
	public void storeProtocolData() {
		
	}
	
	public String loadProtocolData (String protocol, Date startDate, Date endDate) {
		
		return null; 
	}

	
}
