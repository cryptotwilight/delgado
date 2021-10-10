package crypto.delgado.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.List;

import com.google.gson.Gson;

import crypto.delgado.pojo.DealSheet;

public class DealSheetConverter {

	public File dealSheetToFile(DealSheet dealSheet, String path) throws Exception {
		String sheetData = new Gson().toJson(dealSheet);
		File file = new File(path);
		writeFile(file, sheetData);
		return file; 
	}
	
	public HashMap<String, Object[]> dealSheetListToArray(List<DealSheet> dealSheets){
		
		
		return null; 
	}
	
	private void writeFile(File file, String data) throws Exception {
		FileWriter fw = null; 
		BufferedWriter bw = null; 
		try {
			fw = new FileWriter(file);
			bw = new BufferedWriter(fw, 1024);
			bw.write(data);
		}
		finally {
			close(bw);
			close(fw);			
		}
	}
	
	private void close(Writer w) throws Exception {
		if(w != null) {
			w.close();
		}
	}
}
