package test;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;

import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
 
@EnableMongoRepositories
public class SpringMongoDbConfig {
 
	@Bean
	public MongoTemplate mongoTemplate() throws Exception {
		//MongoClient client = new MongoClient("localhost", 27017);
		String url ="mongodb://localhost:27017";
 		MongoClient client = MongoClients.create(url);
		return new MongoTemplate(client, "JUnitTestDb");
	}
	
	@Bean
    public ExampleService exampleService() {
        return new ExampleServiceImpl();
    }
	
	@Bean
    public StatementService statementService() {
        return new StatementServiceImpl();
    }
 
	
}