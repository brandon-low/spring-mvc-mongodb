package example.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;

import java.io.BufferedReader;
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
public class TestStatementTestCaseGson {
	
	private static final Logger logger = LoggerFactory.getLogger(TestStatementTestCaseGson.class);
		
	private Statement giveMeOne() {
		Statement s = new Statement();
		//s.populateTestData();
		return s;
	}
	
	
	private Statement populateTestCase() {
		Statement root = giveMeOne();
		root.setText("Root Document");
	
		Statement f, e, q = null;
		
		
		/*** Barrister is a liar **/
		f = giveMeOne();
		root.addStatements(f);
		
		f.setText("Fact: Is the barrister a Liar?");
		f.setHidden(true);

		e= giveMeOne();
		e.setText("SubFact :  Was There a statment made and then changed their mind?");
		e.setHidden(true);
		
		q = giveMeOne();
		q.setText("Did you made a statment that the application is 2 years apart if it is challege it will easily win?");
		q.setHidden(false);
		e.addStatements(q);
		
		
		q = giveMeOne();
		q.setText("Did you prepared the application, asking the client's approval (cut short the time span by 2 years) ?");
		q.setHidden(false);
		e.addStatements(q);
		
		q = giveMeOne();
		q.setText("Did you later tell the lawyer that just pay L&R - do not go to court?");
		q.setHidden(false);
		e.addStatements(q);
		
		q = giveMeOne();
		q.setText("Are you a prefessional barrister who had been in this business for 30+ yrs?");
		q.setHidden(false);
		e.addStatements(q);
		
		q = giveMeOne();
		q.setText("Were you choosen for the job because of your years of experiences?");
		q.setHidden(false);
		e.addStatements(q);
			
		f.addStatements(e);
		
	
		/** Ex a liar **/
		f = giveMeOne();
		root.addStatements(f);
		f.setText("F[1] Ex Is a Liar?");
		f.setHidden(true);

		q = giveMeOne();
		q.setText("Did you swear by the bible to tell the whole truth in court?");
		q.setHidden(false);
		f.addStatements(q);
		
		q = giveMeOne();
		q.setText("Did you made an accusation of violence towards children?");
		q.setHidden(true);
		f.addStatements(q);
		
		q = giveMeOne();
		q.setText("Did you admit in court that children were very happy and well cared for by him?");
		q.setHidden(true);
		f.addStatements(q);
		
		q = giveMeOne();
		q.setText("S(1) Did you made an claim that you contributed to renovation of the kitchen?");
		q.setHidden(false);
		f.addStatements(q);
		
		q = giveMeOne();
		q.setText("(S2) Did you also changed the affidavites that the contribution is towards the shop at the final affidavits?");
		q.setHidden(false);
		f.addStatements(q);
		
		q = giveMeOne();
		q.setText("Do you agree that your agenda is not about money?");
		q.setHidden(false);
		
		Statement q1 = giveMeOne();
		q1.setHidden(true);
		q1.setText("Why is statement (S1 & S2) made?");
		
		f.addStatements(q);
		
		return root;
	}
	
	
	@Test
	public void testReadWrite() {
		String dir ="/home/brandon/temp/test.json";
		
		
		try {
			Statement e = populateTestCase();
			 	
			
			Gson gson = new GsonBuilder().setPrettyPrinting().disableHtmlEscaping().create();
			
			String jsonInString = gson.toJson(e);
			//gson.toJson (e, new FileWriter(dir));
			
			//write converted json data to a file named "CountryGSON.json"
			FileWriter writer = new FileWriter(dir);
			writer.write(jsonInString);
			writer.close();
				  
			BufferedReader br = new BufferedReader( new FileReader(dir));
		
			Statement fromFile = gson.fromJson(br, Statement.class);
			logger.debug("From File"+fromFile.toString());
			logger.debug(gson.toJson(fromFile) );
			
			assertTrue("Done Good", true);
		} catch (Exception e) {
			e.printStackTrace();
			assertTrue("Done Good", false);
		}
	}
	
	
	//@Test
	public void test() {
		
		try {
			
			 
			Statement e = populateTestCase();
			
			Gson gson = new GsonBuilder().setPrettyPrinting().disableHtmlEscaping().create();
			
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
