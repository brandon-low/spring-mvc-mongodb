package test;


import static org.junit.Assert.*;

import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

//import org.springframework.boot.test.mock.mockito.MockBean;

import org.springframework.test.context.ContextConfiguration;
//import org.springframework.stereotype.Controller;
import org.springframework.test.context.junit4.SpringRunner;

import example.bean.Statement;

import static org.assertj.core.api.Assertions.assertThat;



@RunWith(SpringRunner.class)
//@DataMongoTest
@ContextConfiguration(classes = {SpringMongoDbConfig.class})
public class StatementServiceTest {
	 private static final Logger logger = LoggerFactory.getLogger(StatementServiceTest.class);
	
	@Autowired
    private StatementService service;
	//private static ExampleService service = new ExampleServiceImpl();
	
	//@MockBean  // if we don't have repository 
	@Autowired
	private StatementRepository repository;
	
	static final int NUMBER_OF_TEST_DATA = 3;

	 @Before
	 public void init() {
		 repository.deleteAll();
	}

	private Statement populate(Statement e) {
		if (e == null) {
			e = new Statement();
		}
		e.setCreateUser("Brandon");
		e.setCreateTimestamp(new Date(System.currentTimeMillis()));
		e.setUpdateUser("Brandon");
		e.setUpdateTimestamp(new Date(System.currentTimeMillis()));
			
		return e;
	}
	
	
	@Test
	public void testCreateTestCases() {
		Statement e1 = populate(null);
		try {
			
			e1.setTitle("Document With collections of facts");
			e1.setText("Am i a liar?");
			
		
			service.create(e1);	
		} catch (Exception e) {
			logger.error(e.getMessage());
			assertFalse(e.getMessage(), true);
		}
		
		logger.debug("Root Ducument created");
		try {
			Statement e = service.getStatementById(e1.getId());
			if (e == null) {
				throw new Exception("Error id" + e1.getId() +"not found");
			}
			
		} catch (Exception e) {
			logger.error(e.getMessage());
			assertFalse(e.getMessage(), true);
		}
		
		
		assertNull(null);
	}

}
