package test;

import static org.junit.Assert.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;

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
	 public static class SpringMongoDbConfig {
	  
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
	
	//@MockBean  // if we don't have repository 
	@Autowired
	private ExampleRepository repository;
	
	
	private Example populate(Example e) {
		if (e == null) {
			e = new Example();
		}
		e.setName("Big Boy " + System.currentTimeMillis());
		e.setStatus(System.currentTimeMillis());
		e.setCreateUser("Brandon");
		e.setCreateTimestamp(new Date(System.currentTimeMillis()));
		e.setUpdateUser("Brandon");
		e.setUpdateTimestamp(new Date(System.currentTimeMillis()));
			
		return e;
	}
	
	
	@Test
	public void testInsertFindUpdateDelete() {
		Example e1 = populate(new Example());
		
		try {
			repository.insert(e1);		
		} catch (Exception e) {
			logger.error(e.getMessage());
			assertFalse(e.getMessage(), true);
		}
		assertThat(e1.getId()).isNotNull();
			
				
		Example e2 = null;
		try {
			logger.debug("About to load entity by id=" + e1.getId());
			Optional<Example> opt = repository.findById(e1.getId());
			if (opt.isPresent()) {
				e2=opt.get();
			}
			assertThat(e1.getId().equals(e2.getId()));
		} catch (Exception e) {
				logger.error(e.getMessage());
				assertFalse(e.getMessage(), true);
		}
		assertThat(e2 != null);

		try {
			repository.save(e2);
			
		} catch (Exception e) {
			logger.error(e.getMessage());
			assertFalse(e.getMessage(), true);
		}
		assertThat((e2!= null && e2.getId()!= null ));
		
		try {
			repository.delete(e2);
			
		} catch (Exception e) {
			logger.error(e.getMessage());
			assertFalse(e.getMessage(), true);
		}
		assertTrue("Test Insert Update ", true);
			
	}
		
	static final int NUMBER_OF_TEST_DATA = 6;


	@Test
    public void testFindAll() {
		
		// clear all the stuff
		repository.deleteAll();
		
		for (int i = 0; i <NUMBER_OF_TEST_DATA; i++) {
			try {
				Example entity = populate(new Example());
				repository.insert(entity);
				
			} catch (Exception e) {
				logger.error(e.getMessage());
			}
		}
			
        List<Example> e = repository.findAll();
        assertEquals(NUMBER_OF_TEST_DATA, e.size());
        
        // delete each
        for (int i = 0; i <e.size(); i++ ) {
        	Example e1 = e.get(i);
        	logger.debug("About to delete by id=" + e1.getId() + " obj=" + e1.toString());
        	try {
        		Optional<Example> e3 = repository.findById(e1.getId());
        		if (e3.isPresent()) {
        			logger.debug("Found and about to delete =" + e3.get());
        			repository.delete(e3.get());	
        		} else {
        			throw new Exception(e1.toString() + " Does not exist");
        		}
        		
        		
        	} catch (Exception ex) {
        		logger.error(ex.getMessage());
    			assertFalse(ex.getMessage(), true);
        	}
        }
        
    }
	
	
	@Test
	public void testInsertUpdate() {
		Example e1 = populate(new Example());
		
		try {
			repository.insert(e1);
			
		} catch (Exception e) {
			logger.error(e.getMessage());
			assertFalse(e.getMessage(), true);
		}
		assertThat(e1.getId()).isNotNull();
		
		Example e2 = null;
		try {
			logger.debug("About to load entity by id=" + e1.getId());
			Optional<Example> opt = repository.findById(e1.getId());
			e2 = opt.get();
			
			assertThat(e1.getId().equals(e2.getId()));
		} catch (Exception e) {
			logger.error(e.getMessage());
			assertFalse(e.getMessage(), true);
		}
		assertThat(e2 != null);

		e2.setUpdateUser("YiYong");
		e2.setName("changed");
		e2.setUpdateTimestamp(new Date(System.currentTimeMillis()));
		
		
		try {
			repository.save(e2);
			
		} catch (Exception e) {
			logger.error(e.getMessage());
			assertFalse(e.getMessage(), true);
		}
		assertThat(e1.getId()).isNotNull();
		
		Example e3 = null;
		try {
			logger.debug("About to load entity by id=" + e2.getId());
			Optional<Example> opt = repository.findById(e2.getId());
			e3 = opt.get();
			
			assertThat(e2.getId().equals(e3.getId()));
		} catch (Exception e) {
			logger.error(e.getMessage());
			assertFalse(e.getMessage(), true);
		}
		assertThat(e3 != null);
		
	}

}
