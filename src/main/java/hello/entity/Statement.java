package hello.entity;

import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "statements")
public class Statement {
	
	public enum ValueType {
		UNANSWER, TRUE, FALSE, UNKNOWN
	};
	
	public enum ValueMethod {
		TRUEFALSE, PERCENTAGE
	}
	

	@Id
	private String id;
	
	private String 		title;
	private String 		text;
	private ValueMethod valueMethod;
	private ValueType	value ;
	private int 		percentage;
	private List<String>	reasons;
	private List<Statement> statements;
	private List<Statement> brokenDownStatements;
	
	private Date createTimestamp;
	private Date updateTimestamp;
	
	public Statement() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	
	public int getPercentage() {
		return percentage;
	}
	public void setPercentage(int percentage) {
		this.percentage = percentage;
	}
	public List<String> getReasons() {
		return reasons;
	}
	public void setReasons(List<String> reasons) {
		this.reasons = reasons;
	}
	public List<Statement> getStatements() {
		return statements;
	}
	public void setStatements(List<Statement> statements) {
		this.statements = statements;
	}
	public List<Statement> getBrokenDownStatements() {
		return brokenDownStatements;
	}
	public void setBrokenDownStatements(List<Statement> brokenDownStatements) {
		this.brokenDownStatements = brokenDownStatements;
	}
	public Date getCreateTimestamp() {
		return createTimestamp;
	}
	public void setCreateTimestamp(Date createTimestamp) {
		this.createTimestamp = createTimestamp;
	}
	public Date getUpdateTimestamp() {
		return updateTimestamp;
	}
	public void setUpdateTimestamp(Date updateTimestamp) {
		this.updateTimestamp = updateTimestamp;
	}
	
	

}
