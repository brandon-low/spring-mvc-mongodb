package hello.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import hello.entity.Address;

@Repository
public interface AddressRepository extends MongoRepository<Address, Long>, AddressRepositoryCustom {

	List<Address> findAll();

    Address findBy_id(String id);

    @Query("{ name : ?0 }")
	Address findByName(String name);

}
