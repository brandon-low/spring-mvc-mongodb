package test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

@Service
public class StatementServiceImpl implements StatementService {
	
    private static final Logger logger = LoggerFactory.getLogger(StatementServiceImpl.class);

	@Autowired
	StatementRepository repo;
	
	public void create(Statement stat) {
		repo.save(stat);
	}

	@Override
	public void update(Statement stat) {	
		Optional<Statement> optStat = repo.findById(stat.getId());
		if (logger.isInfoEnabled())
			logger.info("update from " + optStat + " to " + stat);
		if (optStat.isPresent()) {
			Statement statInDb = optStat.get();
			BeanUtils.copyProperties(stat, statInDb, new String[] {"id"});
			repo.save(statInDb);
		} else {
			repo.save(stat);
		}
	}
	public void delete(String id) {
		Optional<Statement> optStat = repo.findById(id);
		if (optStat.isPresent()) {
			repo.delete(optStat.get());
		}
       
	}
	
	public List<Statement> getAllStatements() {
		return repo.findAll();

	}
	
	@Override
	public Statement getStatementById(String id) {
		Optional<Statement> optStat = repo.findById(id);
		if (optStat.isPresent()) {
			return optStat.get();
		}
       return null;
	}

	
	

}