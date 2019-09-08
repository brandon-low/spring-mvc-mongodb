package example.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;

import static org.junit.Assert.assertTrue;
import org.junit.Test;
import java.io.IOException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import example.bean.Statement;
public class TestStatementGson {
	
	private static final Logger logger = LoggerFactory.getLogger(TestStatementGson.class);
		
	private Statement giveMeOne() {
		Statement s = new Statement();
		s.populateTestData();
		return s;
	}
	
	private  Statement recursion(int depth, int size, Statement previous) {
		if (depth != 0) {
				for (int i = 0; i < size; i++) {
					Statement current = giveMeOne();
					current.setText("D(" + depth + ") W("+ i+ ")" + previous.getText());
					previous.addStatements(current);
					recursion(depth -1, size, current);
				}
				
				
				return previous;
			
		} else {
			return previous;
		}
	}
	
	private Statement populate() {
		Statement s = giveMeOne();
		s.setText("Root");
		s.setTitle("Root");
		
		
		recursion(2, 2, s);
		
		return s;
	}
	
	@Test
	public void test() {
		
		
		try {
			
			// Reader reader = new FileReader("c:\\projects\\staff.json")) {

		   // Convert JSON File to Java Object
		   //         Staff staff = gson.fromJson(reader, Staff.class);
			
			 // Convert JSON to JsonElement, and later to String
           // JsonElement json = gson.fromJson(reader, JsonElement.class);

		            
			Statement e = populate();
			
			Gson gson = new GsonBuilder().setPrettyPrinting().create();
			
			// Convert object to JSON string
			String jsonInString = gson.toJson(e);
			
			logger.debug( jsonInString );
				
			Statement e1 = gson.fromJson(jsonInString, Statement.class);
		
			logger.debug("*******************************");
			logger.debug(e1.toString());
			
			

		} catch (Exception e) {
			e.printStackTrace();
		}
		assertTrue("Done Good", true);
	}


}
