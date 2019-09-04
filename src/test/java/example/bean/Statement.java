package example.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "statements")
public class Statement implements Serializable {
	
	private static final long serialVersionUID =    129348938L; 
	
	private static int PERCENTAGE_UNANSWER 	= -1;
	private static int PERCENTAGE_UNKNOWN 	= 50;
	private static int PERCENTAGE_TRUE 		= 100;
	private static int PERCENTAGE_FALSE 	= 0;
	
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
	private int				percentage;
	private List<String>	reasons;
	private List<Statement> statements;
	private List<Statement> brokenDownStatements;
	private boolean			isHidden;
	
	private String 			createUser;
	private String 			updateUser;
	private Date 			createTimestamp;
	private Date 			updateTimestamp;
	
	public Statement() {
		super();
		initData();
		// TODO Auto-generated constructor stub
	}
	
	private void initData() {
		this.valueMethod = ValueMethod.TRUEFALSE;
		this.setValueMethod(ValueMethod.TRUEFALSE);
		this.setValueType(ValueType.UNANSWER);
		this.setPercentage( PERCENTAGE_UNANSWER );
		this.setHidden(true);	
	}
	public boolean isHidden() {
		return isHidden;
	}

	public void setHidden(boolean isHidden) {
		this.isHidden = isHidden;
	}

	public void populateTestData() {
		this.setCreateUser("Brandon Low");
		this.setCreateTimestamp(new Date(System.currentTimeMillis()));
		this.setUpdateUser("Brandon Low");
		this.setUpdateTimestamp(new Date(System.currentTimeMillis()));
	}
	
	public void addReasons(String str) {
		if (this.getReasons() == null) {
			this.setReasons(new ArrayList<String>());
		}
		this.getReasons().add(str);
	}
	
	public void addStatements(Statement e) {
		if (this.getStatements() == null) {
			this.setStatements(new ArrayList<Statement>());
		}
		this.getStatements().add(e);
	}
	public void addBrokenDownStatements(Statement e) {
		if (this.getBrokenDownStatements() == null) {
			this.setBrokenDownStatements(new ArrayList<Statement>());
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
		// default to true false
		if (this.getValueMethod() == null ){
			this.setValueMethod(ValueMethod.TRUEFALSE);
			this.setValueType(ValueType.UNANSWER);
			this.setPercentage( PERCENTAGE_UNANSWER );
		}
		if (this.getValueType() == null) {
			this.setValueType(ValueType.UNANSWER);
			this.setPercentage( PERCENTAGE_UNANSWER );
		}
		if (this.getValueMethod() == ValueMethod.TRUEFALSE) {
			switch (this.getValueType()) {
				case VALUE_TRUE: 
					return PERCENTAGE_TRUE;
				case VALUE_FALSE: 
					return PERCENTAGE_FALSE;
				case UNANSWER: 
					return PERCENTAGE_UNANSWER;
				default : 
					return PERCENTAGE_UNKNOWN;
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
