package fabflix;

import java.sql.*;
import javax.sql.*;

public class DBCP_DB {

	public static DataSource datasource = null;
	public static DataSource datasource_emp = null;
	private int pooledCount, nonPooledCount;
	
	public DBCP_DB(){}

	 public int getpooledCount() {
	        return pooledCount;
	    }
	 public void setpooledCount(int pooledCount_) {
	        this.pooledCount = pooledCount_;
	    }
	
	 public int getnonPooledCount() {
	        return nonPooledCount;
	    }
	 public void setnonPooledCount(int nonPooledCount_) {
	        this.nonPooledCount = nonPooledCount_;
	    }

	  public synchronized Connection getConnection(boolean pooledConnection, int s) throws SQLException {
	    int t;
		  t = s;
		  if (pooledConnection) {
	      pooledCount++;
			      if (t ==1 ){      
			      return datasource_emp.getConnection();	// Allocate and use a connection from the pool
			      }else {
			    	  
			    	  return datasource.getConnection();
			      }
	      }
	    
	   
	    else {
	      nonPooledCount++;
	      Connection con = null;
		try {
			con = DB.getConnection();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	      return con;						   //retun a newly created object
	    }

	    
	  //return datasource.getConnection();
	  }
	  
}
