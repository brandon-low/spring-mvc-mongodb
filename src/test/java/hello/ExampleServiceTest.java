package hello;

import static org.junit.Assert.*;

import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;

//import org.springframework.boot.test.mock.mockito.MockBean;

import org.springframework.test.context.ContextConfiguration;
//import org.springframework.stereotype.Controller;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

//import junit.framework.AssertionFailedError;


@RunWith(SpringRunner.class)
//@DataMongoTest
@ContextConfiguration(classes = {SpringMongoDbConfig.class})
public class ExampleServiceTest {
	 
	
	@Autowired
    private ExampleService service;
	
	
	//@MockBean
	@Autowired
	private ExampleRepository repository;
	
	private void createTestData() {
		try {
			Example entity = populate(new Example());
			service.create(entity);
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
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
			assertFalse(e.getMessage(), true);
		}
		assertThat(entity.getId()).isNotNull();
	}

}
