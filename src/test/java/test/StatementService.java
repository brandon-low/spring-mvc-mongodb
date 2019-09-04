package test;

import java.util.List;

import example.bean.Statement;


public interface StatementService {

	void create(Statement stat);
	void update(Statement stat);
	void delete(String id);

	Statement getStatementById(String id);
	List<Statement> getAllStatements();
}
