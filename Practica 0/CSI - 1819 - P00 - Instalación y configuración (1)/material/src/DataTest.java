package es.uca.gii.csi18.check.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import es.uca.gii.csi18.check.data.Data;

class DataTest {

	@BeforeAll
	static void setUpBeforeClass() throws Exception { Data.LoadDriver(); }

	@Test
	void testDbAccess() throws Exception {
		
	    Connection con = null;
	    ResultSet rs = null;
	    
	    try {  	
	        con = Data.Connection();
	        rs = con.createStatement().executeQuery("SELECT Host, User FROM user;");
	        
	        int i = 0;	        
	        while (rs.next()) {	        	
	        	System.out.println(rs.getString("Host") + " " + rs.getString("User"));
	        	i++;
	        }
	        
	        assertEquals(3, i);
	    }
	    catch (SQLException ee) { throw ee; }
	    finally {
	    	if (rs != null) rs.close();
	    	if (con != null) con.close();
	    }
	}
}