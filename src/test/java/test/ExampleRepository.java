package test;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface ExampleRepository extends MongoRepository<Example, String> {

    @Query("{ name : ?0 }")
	Example findByName(String name);

}