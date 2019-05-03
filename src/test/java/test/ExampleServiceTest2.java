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
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//import org.springframework.stereotype.Controller;
import org.springframework.test.context.junit4.SpringRunner;

import com.mongodb.MongoClient;

import hello.config.SpringSecurityConfig;

import static org.assertj.core.api.Assertions.assertThat;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

//import junit.framework.AssertionFailedError;

//@RunWith(SpringJUnit4ClassRunner.class)
@RunWith(SpringRunner.class)
//@DataMongoTest
@ContextConfiguration//(classes = {SpringMongoDbConfig.class})]
@ComponentScan({ "test.*" })
public class ExampleServiceTest2 {
	 private static final Logger logger = LoggerFactory.getLogger(ExampleServiceTest2.class);

	 @Autowired
	 private ApplicationContext applicationContext;
	 
	 @Configuration
	 @EnableMongoRepositories
	 static class SpringMongoDbConfig {
	  
	 	@Bean
	 	public MongoTemplate mongoTemplate() throws Exception {
	 		MongoClient client = new MongoClient("localhost", 27017);
	 		return new MongoTemplate(client, "JUnitTestDb");
	 	}
	 	
	 	@Bean
	     public ExampleService exampleService() {
	         return new ExampleServiceImpl();
	     }
	  
	 }
	 
	 
	@Autowired
    private ExampleService service;
	//private static ExampleService service = new ExampleServiceImpl();
	
	//@MockBean  // if we don't have repository 
	@Autowired
	private ExampleRepository repository;
	
	private void createTestData() {
		try {
			Example entity = populate(new Example());
			service.create(entity);
			
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
	}
	
	static final int NUMBER_OF_TEST_DATA = 6;

	 @Before
	 public void init() {
		 repository.deleteAll();
		for (int i = 0; i <NUMBER_OF_TEST_DATA; i++) {
			createTestData();
		}
	 }

	private Example populate(Example e) {
		if (e == null) {
			e = new Example();
		}
		//e.setKey(new Date(System.currentTimeMillis()).toString());
		e.setName("Big Boy " + System.currentTimeMillis());
		e.setStatus(System.currentTimeMillis());
		e.setCreateUser("Brandon");
		e.setCreateTimestamp(new Date(System.currentTimeMillis()));
		e.setCreateUser("Brandon");
		e.setUpdateTimestamp(new Date(System.currentTimeMillis()));
			
		return e;
	}
	
	@Test
    public void countAllTestData() {

        List<Example> entities = repository.findAll();
        assertEquals(NUMBER_OF_TEST_DATA, entities.size());
    }
	
	
	@Test
	public void testCreate() {
		Example entity = populate(new Example());
		
		try {
			service.create(entity);
			
		} catch (Exception e) {
			logger.error(e.getMessage());
			assertFalse(e.getMessage(), true);
		}
		assertThat(entity.getId()).isNotNull();
	}

}
