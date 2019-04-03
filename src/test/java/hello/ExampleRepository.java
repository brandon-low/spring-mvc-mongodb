package hello;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface ExampleRepository extends MongoRepository<Example, Long> {

	List<Example> findAll();

    Example findById(String id);

    @Query("{ name : ?0 }")
	Example findByName(String name);

}