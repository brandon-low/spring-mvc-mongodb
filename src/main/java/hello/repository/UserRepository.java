package hello.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import hello.entity.User;

@Repository
public interface UserRepository extends MongoRepository<User, Long> {

	List<User> findAll();

    User findById(String id);

    @Query("{ username : ?0 }")
	User findByUsername(String username);
    
    @Query("{ accountNonLocked : ?0 }")
    List<User> findAllUserAccountNonLocked(boolean accountNonLocked);

}

