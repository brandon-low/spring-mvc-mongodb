package test;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hello.entity.Address;
import hello.entity.User;


@Service
public class ExampleServiceImpl implements ExampleService {
	  private static final Logger logger = LoggerFactory.getLogger(ExampleServiceImpl.class);


		@Autowired
		ExampleRepository repository;

		public void insert(Example example) {
			logger.info("save example="+ example);
			if (example != null) {
				example.setCreateTimestamp(new Date(System.currentTimeMillis()));
				example.setUpdateTimestamp(new Date(System.currentTimeMillis()));
			}
			repository.insert(example);
		}
		public void update(Example example) {
			logger.info("save example="+ example);
			 example.setUpdateTimestamp(new Date(System.currentTimeMillis()));
			 repository.save(example);
		}
		
		public void deleteById(String id) {
				repository.deleteById(id);			
		}
		
		public List<Example> findAll() {
			return repository.findAll();
		}
		public void deleteAll() {
			repository.deleteAll();
		}
		

}
