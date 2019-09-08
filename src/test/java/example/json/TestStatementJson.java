package example.json;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import static org.junit.Assert.assertTrue;
import org.junit.Test;
import java.io.IOException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import example.bean.Statement;
public class TestStatementJson {
	
	private static final Logger logger = LoggerFactory.getLogger(TestStatementJson.class);
	
	private static int STATEMENT_SIZE = 5;
	private static int STATEMENT_DEPTH = 3;
	
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
			Statement e = populate();
			
			ObjectMapper mapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);;

			// Convert object to JSON string and save into a file directly
			//mapper.writeValue(new File("D:\\staff.json"), staff);

			// Convert object to JSON string
			String jsonInString = mapper.writeValueAsString(e);
			//System.out.println(jsonInString);

			// Convert object to JSON string and pretty print
			String prettyPrintJsonString = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(e);
			logger.debug( prettyPrintJsonString);
				
			Statement e1 = mapper.readValue(jsonInString, Statement.class);
		
			logger.debug("*******************************");
			logger.debug(e1.toString());


		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		assertTrue("Done Good", true);
	}


}
