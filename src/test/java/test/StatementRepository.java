package test;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface StatementRepository extends MongoRepository<Statement, String> {

	List<Statement> findAll();

    //Statement findBy_id(String id);

    @Query("{ name : ?0 }")
	Statement findByName(String name);

}
