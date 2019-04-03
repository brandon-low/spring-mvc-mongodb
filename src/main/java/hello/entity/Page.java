package hello.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Page implements Serializable {	

	private static final long serialVersionUID = 1L;

	private static final Logger log = LoggerFactory.getLogger( Page.class);

	private static final int DEFAULT_PAGE		= 1;
	private static final int DEFAULT_PAGE_SIZE 	= 10;
	
	
	private int 	pageNumber		= DEFAULT_PAGE;
	private int 	pageSize 		= DEFAULT_PAGE_SIZE;
	
	
	private int 	rowCount		= 0;	// how many rows we have in the database
	private List 	collections;			// collection of data
	
	/**
	 * search stuff. one is criterias the other method is form object. Could just be a bean
	 */
	private ArrayList<QueryParam> criterias;
	private Object 	form;	// form to search
	
	public Object getForm() {
		return form;
	}

	public void setForm(Object form) {
		this.form = form;
	}

	
	public class QueryParam {
		private String key;
		private String operator;
		private Object value;
		
		public QueryParam(String key, String operator, Object value) {
			super();
			this.key = key;
			this.operator = operator;
			this.value = value;
		}


		public QueryParam(String key, Object value) {
			super();
			this.key = key;
			this.value = value;
		}

		public String getOperator() {
			return operator;
		}


		public void setOperator(String operator) {
			this.operator = operator;
		}


		public String getKey() {
			return key;
		}

		public void setKey(String key) {
			this.key = key;
		}

		public Object getValue() {
			return value;
		}

		public void setValue(Object value) {
			this.value = value;
		}
	}
	
	public void addCriteria(String key, Object value) {
		if (getCriterias() == null) {
			setCriterias(new ArrayList<QueryParam>());
		}
		getCriterias().add(new QueryParam(key, value));
	}
	
	public String getCriteriaKey(int index) {
		return getCriterias().get(index).getKey();
	}
	public Object getCriteriaValue(int index) {
		return getCriterias().get(index).getValue();
	}
	
	private ArrayList<QueryParam> getCriterias() {
		if (criterias == null) {
			criterias =new ArrayList<QueryParam>();
		}
		return criterias;
	}

	private void setCriterias(ArrayList<QueryParam> criterias) {
		this.criterias = criterias;
	}
	
	public String getMongoDbQueryString() {
		StringBuffer sb = new StringBuffer();
		for (QueryParam param : getCriterias()) {
			if (sb.length() > 0) {
				sb.append(", ");
			}
			switch (param.getOperator().toUpperCase()) {
				case "EQ":
				case "=" :
		        	  sb.append(param.getKey() + " : { $eq :" + param.getValue().toString() + "}");
		        	  break;
		          case "NE":
		        	  sb.append(param.getKey() + " : { $ne :" + param.getValue().toString() + "}");
		        	  break;
		          case "GT":
		        	  sb.append(param.getKey() + " : { $gt :" + param.getValue().toString() + "}");
		        	  break;
		          case "GTE":
		        	  sb.append(param.getKey() + " : { $gte :" + param.getValue().toString() + "}");
		        	  break;
		          case "LT":
		        	  sb.append(param.getKey() + " : { $lt :" + param.getValue().toString() + "}");
		        	  break;
		            case "LTE":
		            	sb.append(param.getKey() + " : { $lte :" + param.getValue().toString() + "}");
			        	break;
		          case "IN":
		        	  sb.append(param.getKey() + " : { $in :" + param.getValue().toString() + "}");
		        	  break;
		          case "NIN":
		        	  sb.append(param.getKey() + " : { $nin :" + param.getValue().toString() + "}");
		        	  break;
		          default:
	          		break;
	          }
			
		}
		
		if (sb.length() > 0 ) {
			return "{ " + sb.toString() + " }";
		}
	
		
		return null;
	}

	private void setPageNumber(int pageNumber){
		this.pageNumber 		= pageNumber;
	}

	private void setPageSize(int pageSize){
		this.pageSize 	= pageSize;
	}
	
	/*
	 * Default Constructor.
	 */			
	public Page() {	}
	public Page(int pageSize) {
		setPageSize(pageSize);		
	}

	public Page(int pageNumber, int pageSize) {
		setPageNumber(pageNumber);
		setPageSize(pageSize);		
	}
	
	/**
	 * Use This in JPA model
	 * @param pageNumber
	 * @param pageSize
	 * @param rowCount
	 * @param collections
	 */
	public Page(int pageNumber, int pageSize, long rowCount, List collections) {
		setPageNumber(pageNumber);
		setPageSize(pageSize);		
		setRowCount(rowCount);
		setCollections(collections);
	}
	
	/**
	 * Use this in JPA Model
	 * @param pageNumber
	 * @param rowCount
	 * @param collections
	 */
	public Page(int pageNumber,  long rowCount, List collections) {
		setPageNumber(pageNumber);
		setRowCount(rowCount);
		setCollections(collections);
	}
	

	/**
	 * This will be used to get the offset position of the cursor
	 * in mongodb we use skip(offset).limit(pagesize)
	 * @return
	 */
	public int getOffsetPosition() {
		return (pageNumber - 1) * pageSize;
	}
	
	
	public int getPageSize() {
		if (this.pageSize <= 0) {
			this.pageSize = DEFAULT_PAGE_SIZE;
		}
		return this.pageSize;
	}

	public void setRowCount(long rowCount) {
		this.rowCount = new Long(rowCount).intValue();
	}
	
	public void setRowCount(int rowCount) {
		this.rowCount = rowCount;
	}
	
	public long getRowCount() {
		return this.rowCount;
	}
	
	/*
	 * Set the collections into page object
	 */
	public void setCollections(List collections) {
		this.collections = collections;
	}
	
	/**
	 *	This returns the query results.. which is pageSize+1
	 *	collection is pageSize + 1 to page it
	 */
	public List getCollections() {
		return collections;
	}
	
	/**
	 * Main function used to display the collection
	 * @return
	 */
	public List getList() {
		return isNextPage() ? collections.subList(0, pageSize) : collections;
	}
	
	/**
	 * test results greater than 0
	 * @return 
	 */
	public boolean hasList() {
		return (collections != null && collections.size() > 0);
	}	
	
	/**
	 * test collection size to see if it is end of page
	 * @return
	 */
	public boolean isEndOfPage() {	
		if (collections != null)
			return (collections.size() < pageSize);
		if (pageNumber > 0) return false;
		
		return false;
	}
	
	public boolean isNextPage() {
		return collections.size() > pageSize;
	}

	public boolean isPreviousPage() {
		return pageNumber > 0;
	}

	
	public boolean hasNextPage() {
		if (log.isDebugEnabled()) {
			log.debug("hasNextPage numberOfPage=" + getLastPage() + " > pageNumber=" +pageNumber );			
		}
		return ( getLastPage() > pageNumber);
	}
	
	public boolean hasPreviousPage() {
		return this.pageNumber > 0;
	}
	
	public int getPageNumber() {
		if (this.pageNumber <= 0) {
			this.pageNumber = DEFAULT_PAGE;
		}
		return this.pageNumber;
	}

	public int getCurrentPage() {
		return getPageNumber();
	}
	
	/**
	 * return index of the last page
	 * @return int
	 */
	public int getLastPage() {
		return getNumberOfPages();
	}
	
	/**
	 * unknown returns -1
	 * @return 
	 */
	public int getNumberOfPages() {		
		try {
			int r = rowCount % pageSize;
			int d = rowCount / pageSize;			
			return (r > 0) ? ( d + 1) : d;
		} catch (Exception e) {
			
		}
		return -1;		
	}
	
	
	
	/** Code clean up */
	
	/**
	 * The collection will store pageSize + 1. It is used to see if we have another page.
	 * @return
	 */
	/*
	public int getMaxResultSize() {
		return getPageSize() + 1;
	}
	
	  public int getLastPageNumber() {
		return getTotalPage();
	}
	    */
	   
}
