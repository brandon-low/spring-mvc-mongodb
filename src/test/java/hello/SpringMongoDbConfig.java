package hello;

import com.mongodb.MongoClient;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
 
@EnableMongoRepositories
public class SpringMongoDbConfig {
 
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