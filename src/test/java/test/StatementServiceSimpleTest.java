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

import static org.assertj.core.api.Assertions.assertThat;



@RunWith(SpringRunner.class)
//@DataMongoTest
@ContextConfiguration(classes = {SpringMongoDbConfig.class})
public class StatementServiceSimpleTest {
	 private static final Logger logger = LoggerFactory.getLogger(StatementServiceSimpleTest.class);
	
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
		for (int i = 0; i <NUMBER_OF_TEST_DATA; i++) {
			try {
				Statement entity = populate(null);
				
				entity.setText("Lie " + (i+1) + "my arse is to big?");
				service.create(entity);
				
			} catch (Exception e) {
				logger.error(e.getMessage());
			}
		}
	 }

	private Statement populate(Statement e) {
		if (e == null) {
			e = new Statement();
		}
		e.setTitle("Title Test: Who is the liar?" );
		e.setCreateUser("Brandon");
		e.setCreateTimestamp(new Date(System.currentTimeMillis()));
		e.setUpdateUser("Brandon");
		e.setUpdateTimestamp(new Date(System.currentTimeMillis()));
			
		return e;
	}
	
	//@Test
    public void countAllTestData() {

        List<Statement> entities = repository.findAll();
        assertEquals(NUMBER_OF_TEST_DATA, entities.size());
    }
	
	
	@Test
	public void testCreate() {
		Statement entity = populate(null);
		try {
				entity.setText("Liar liar here i come");
				service.create(entity);
		} catch (Exception e) {
			logger.error(e.getMessage());
			assertFalse(e.getMessage(), true);
		}
		assertThat(entity.getId()).isNotNull();
	}

}
