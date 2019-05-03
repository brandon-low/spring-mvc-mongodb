package test;



import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;



/***
 * 
 * @author brandon
 *	Test Object
 */
@Document(collection = "Example")
public class Example {
	     /**
	      * The Field Id Is primary key
	     */
			@Id
	     private String Id;
			
		 // @Indexed(unique = true)
		 /*private String key;
			
	     public String getKey() {
			return key;
		}

		public void setKey(String key) {
			this.key = key;
		}
		*/

		private int version;
	     private String Name;
	     private long Status;
	     private String CreateUser;
	     private Date CreateTimestamp;
	     private String UpdateUser;
	     private Date UpdateTimestamp;

	    public Example() {
	    }

	    public Example(String Name, long Status, String CreateUser, Date CreateTimestamp, String UpdateUser, Date UpdateTimestamp) {
	       this.Name = Name;
	       this.Status = Status;
	       this.CreateUser = CreateUser;
	       this.CreateTimestamp = CreateTimestamp;
	       this.UpdateUser = UpdateUser;
	       this.UpdateTimestamp = UpdateTimestamp;
	    }
	   
	    /**       
	     *      * The Field Id Is primary key
	     */
		    public String getId() {
	        return this.Id;
	    }
	    
	    public void setId(String Id) {
	        this.Id = Id;
	    }

	    public int getVersion() {
	        return this.version;
	    }
	    
	    public void setVersion(int version) {
	        this.version = version;
	    }

	    
	     public String getName() {
	        return this.Name;
	    }
	    
	    public void setName(String Name) {
	        this.Name = Name;
	    }

	    
	      public long getStatus() {
	        return this.Status;
	    }
	    
	    public void setStatus(long Status) {
	        this.Status = Status;
	    }

	    public String getCreateUser() {
	        return this.CreateUser;
	    }
	    
	    public void setCreateUser(String CreateUser) {
	        this.CreateUser = CreateUser;
	    }
	    public Date getCreateTimestamp() {
	        return this.CreateTimestamp;
	    }
	    
	    public void setCreateTimestamp(Date CreateTimestamp) {
	        this.CreateTimestamp = CreateTimestamp;
	    }

	    
	    public String getUpdateUser() {
	        return this.UpdateUser;
	    }
	    
	    public void setUpdateUser(String UpdateUser) {
	        this.UpdateUser = UpdateUser;
	    }
	    
	    public Date getUpdateTimestamp() {
	        return this.UpdateTimestamp;
	    }
	    
	    public void setUpdateTimestamp(Date UpdateTimestamp) {
	        this.UpdateTimestamp = UpdateTimestamp;
	    }

	    /**
	     * toString
	     * @return String
	     */
	     public String toString() {
		  StringBuffer buffer = new StringBuffer();

	      buffer.append(getClass().getName()).append("@").append(Integer.toHexString(hashCode())).append(" [");
	      buffer.append("Id").append("='").append(getId()).append("' ");			
	      buffer.append("version").append("='").append(getVersion()).append("' ");			
	      buffer.append("Name").append("='").append(getName()).append("' ");			
	      buffer.append("Status").append("='").append(getStatus()).append("' ");			
	      buffer.append("CreateUser").append("='").append(getCreateUser()).append("' ");			
	      buffer.append("CreateTimestamp").append("='").append(getCreateTimestamp()).append("' ");			
	      buffer.append("UpdateUser").append("='").append(getUpdateUser()).append("' ");			
	      buffer.append("UpdateTimestamp").append("='").append(getUpdateTimestamp()).append("' ");			
	      buffer.append("]");
	      
	      return buffer.toString();
	     }

		public boolean myEquals(Object other) {
			if ( (this == other ) ) return true;
			if ( (other == null ) ) return false;
			if ( !(other instanceof Example) ) return false;
			Example castOther = ( Example ) other;
			return ( (1 == 1)
				&& ( ( this.getId() == castOther.getId() )
					|| ( this.getId() != null &&  castOther.getId() != null && this.getId().equals(castOther.getId() ))
				)
				&& ( ( this.getVersion() == castOther.getVersion() )
				)
				&& ( ( this.getName() == castOther.getName() )
					|| ( this.getName() != null &&  castOther.getName() != null && this.getName().equals(castOther.getName() ))
				)
				&& ( ( this.getStatus() == castOther.getStatus() )
				)
				&& ( ( this.getCreateUser() == castOther.getCreateUser() )
					|| ( this.getCreateUser() != null &&  castOther.getCreateUser() != null && this.getCreateUser().equals(castOther.getCreateUser() ))
				)
				&& ( ( this.getCreateTimestamp() == castOther.getCreateTimestamp() )
					|| ( this.getCreateTimestamp() != null 
						&&  castOther.getCreateTimestamp() != null 
						&& this.getCreateTimestamp().equals(castOther.getCreateTimestamp() ) 
					) 
					|| ( this.getCreateTimestamp() != null 
						&&  castOther.getCreateTimestamp() != null 
						&& this.getCreateTimestamp().getTime() == castOther.getCreateTimestamp().getTime()
					) 
				)
				&& ( ( this.getUpdateUser() == castOther.getUpdateUser() )
					|| ( this.getUpdateUser() != null &&  castOther.getUpdateUser() != null && this.getUpdateUser().equals(castOther.getUpdateUser() ))
				)
				&& ( ( this.getUpdateTimestamp() == castOther.getUpdateTimestamp() )
					|| ( this.getUpdateTimestamp() != null 
						&&  castOther.getUpdateTimestamp() != null 
						&& this.getUpdateTimestamp().equals(castOther.getUpdateTimestamp() ) 
					) 
					|| ( this.getUpdateTimestamp() != null 
						&&  castOther.getUpdateTimestamp() != null 
						&& this.getUpdateTimestamp().getTime() == castOther.getUpdateTimestamp().getTime()
					) 
				)

					) ;		
		}

		public boolean equals(Object other) {
			if ( (this == other ) ) return true;
			if ( (other == null ) ) return false;
			if ( !(other instanceof Example) ) return false;
			Example castOther = ( Example ) other;         
			return (this.getVersion()==castOther.getVersion())
	 && ( (this.getName()==castOther.getName()) || ( this.getName()!=null && castOther.getName()!=null && this.getName().equals(castOther.getName()) ) )
	 && (this.getStatus()==castOther.getStatus())
	 && ( (this.getCreateUser()==castOther.getCreateUser()) || ( this.getCreateUser()!=null && castOther.getCreateUser()!=null && this.getCreateUser().equals(castOther.getCreateUser()) ) )
	 && ( (this.getCreateTimestamp()==castOther.getCreateTimestamp()) || ( this.getCreateTimestamp()!=null && castOther.getCreateTimestamp()!=null && this.getCreateTimestamp().equals(castOther.getCreateTimestamp()) ) )
	 && ( (this.getUpdateUser()==castOther.getUpdateUser()) || ( this.getUpdateUser()!=null && castOther.getUpdateUser()!=null && this.getUpdateUser().equals(castOther.getUpdateUser()) ) )
	 && ( (this.getUpdateTimestamp()==castOther.getUpdateTimestamp()) || ( this.getUpdateTimestamp()!=null && castOther.getUpdateTimestamp()!=null && this.getUpdateTimestamp().equals(castOther.getUpdateTimestamp()) ) );
		}
		
		public int hashCode() {
			int result = 17;         
			
			result = 37 * result + this.getVersion();
			result = 37 * result + ( getName() == null ? 0 : this.getName().hashCode() );
			result = 37 * result + (int) this.getStatus();
			result = 37 * result + ( getCreateUser() == null ? 0 : this.getCreateUser().hashCode() );
			result = 37 * result + ( getCreateTimestamp() == null ? 0 : this.getCreateTimestamp().hashCode() );
			result = 37 * result + ( getUpdateUser() == null ? 0 : this.getUpdateUser().hashCode() );
			result = 37 * result + ( getUpdateTimestamp() == null ? 0 : this.getUpdateTimestamp().hashCode() );
			return result;
		}


}
