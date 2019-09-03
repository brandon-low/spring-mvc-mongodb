package test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "statements")
public class Statement {
	
	public static enum ValueMethod {
		TRUEFALSE, PERCENTAGE
	}
	
	public static enum ValueType {
		UNANSWER, VALUE_TRUE, VALUE_FALSE, UNKNOWN
	}

	@Id
	private String 			id;
	private String 			title;
	private String 			text;
	private ValueMethod 	valueMethod;
	private ValueType		valueType;
	private int 			percentage;
	private List<String>	reasons;
	private List<Statement> statements;
	private List<Statement> brokenDownStatements;
	
	private String createUser;
	private String updateUser;
	
	private Date createTimestamp;
	private Date updateTimestamp;
	
	public Statement() {
		super();
		this.valueMethod = ValueMethod.TRUEFALSE;
		// TODO Auto-generated constructor stub
	}
	
	public void addReasons(String str) {
		if (this.getReasons() == null) {
			this.setReasons(new ArrayList());
		}
		this.getReasons().add(str);
	}
	
	public void addStatements(Statement e) {
		if (this.getStatements() == null) {
			this.setStatements(new ArrayList());
		}
		this.getStatements().add(e);
	}
	public void addBrokenDownStatements(Statement e) {
		if (this.getBrokenDownStatements() == null) {
			this.setBrokenDownStatements(new ArrayList());
		}
		this.getBrokenDownStatements().add(e);
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

	public int getValuedPercentage() {
		if (this.getValueMethod() == ValueMethod.TRUEFALSE) {
			switch (this.getValueType()) {
				case VALUE_TRUE: return 100;
				case VALUE_FALSE: return 0;
				default : return 50;
			}
		}
		return this.getPercentage();
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
	
	public ValueMethod getValueMethod() {
		return valueMethod;
	}

	public void setValueMethod(ValueMethod valueMethod) {
		this.valueMethod = valueMethod;
	}

	public ValueType getValueType() {
		return valueType;
	}

	public void setValueType(ValueType valueType) {
		this.valueType = valueType;
	}
	
	public String getCreateUser() {
		return createUser;
	}

	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}

	public String getUpdateUser() {
		return updateUser;
	}

	public void setUpdateUser(String updateUser) {
		this.updateUser = updateUser;
	}


}
