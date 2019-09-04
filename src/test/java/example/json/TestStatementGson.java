package example.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import java.io.FileReader;
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
	
	private static int STATEMENT_SIZE = 5;
	private static int STATEMENT_DEPTH = 3;
	
	private Statement giveMeOne() {
		Statement s = new Statement();
		s.populateTestData();
		return s;
	}
	private Statement widthRecursion(int size, Statement previous) {
		if (size != 0) {
			Statement current = giveMeOne();
			current.setText("Width : " + size + "text");
			current.setTitle("Width : " + size + "Title");
			previous.addStatements(current);
			depthRecursion(STATEMENT_DEPTH, current);
			return widthRecursion(size -1, previous);
		}else {
			return previous;
		}
	}
	
	private Statement depthWidthRecursion(int size, int depth, Statement previous) {
		if (size != 0) {
			Statement current = giveMeOne();
			current.setText("Depth :" + depth + " width=" +size+ " Text");
			current.setTitle("Depth :" + depth + "width="+size + " Title");
			previous.addStatements(current);
			return depthWidthRecursion(size-1, depth, previous);
		} else {
			return previous;
		}
	}
	
	private  Statement depthRecursion(int depth, Statement previous) {
		if (depth != 0) {
			
				Statement current = giveMeOne();
				current.setText("Depth :" + depth + " Text");
				current.setTitle("Depth :" + depth +" Title");
				previous.addStatements(current);
				return depthRecursion (depth-1, current);	
			
		} else {
			return previous;
		}
	}
	
	private Statement populate() {
		Statement s = giveMeOne();
		s.setText("Root Documents");
		s.setTitle("Root Document");
		
		widthRecursion(STATEMENT_SIZE, s);
		//recursion (STATEMENT_DEPTH, STATEMENT_SIZE, s);
		
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
