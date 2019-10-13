package es.uca.gii.csi18.lara.data;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import es.uca.gii.csi18.lara.util.Config;

public class Data {
	
	public static String getPropertiesUrl() { return "./db.properties"; }
	
	public static Connection Connection() throws Exception {
		
		try {
			
			Properties properties = Config.Properties(getPropertiesUrl());
			
			return DriverManager.getConnection(
				properties.getProperty("jdbc.url"),
				properties.getProperty("jdbc.username"),
				properties.getProperty("jdbc.password"));
		} catch (Exception ee) { throw ee; }
	
	}

	public static void LoadDriver()	throws InstantiationException, IllegalAccessException,
		ClassNotFoundException, IOException {
			
		Class.forName(Config.Properties(Data.getPropertiesUrl()).getProperty(
				"jdbc.driverClassName")).newInstance();
	}
	
	public static int Boolean2Sql (boolean b) {
		
		if (b) return 1;
		else  return 0;
		
	}
	
	public static String String2Sql(
			String s, boolean bAddQuotes, boolean bAddWildcards) {
		
		char Char;
		String String = "";
	                
	    if(bAddQuotes){
	        Char = 39;
	        String=String + Char;               	
	    }
	    
	    if(bAddWildcards){
	        Char = 37;
	        String=String + Char;
	    }
	           
		 /* Guarda letra a letra. Si es una comilla 
		 /* (39 en ASCII) la guarda dos veces*/
	                
		for (int i=0; i<s.length(); i++) { 
			Char = s.charAt(i);
			String = String + Char;
			if (Char == 39) 
				String=String + Char; 
		}
	    
	    if(bAddWildcards){
	        Char = 37;
	        String=String + Char;
	    }
	    
	    if(bAddQuotes){
	        Char = 39;
	        String=String + Char;
	    }
	
	    return String;
	    
	}
	
	public static int LastId(Connection con) throws Exception {
	    Properties properties = Config.Properties(getPropertiesUrl());
        ResultSet rs = null;
        
		try {
		    rs = con.createStatement().executeQuery(properties.getProperty("jdbc.lastIdSentence"));
			if(rs.next()) return rs.getInt(1);
			else throw new Exception("No se ha podido encontrar la última inserción");
		}
		catch(SQLException ee) { throw ee;}
		finally {
		    // No cierra la conexión
			if (rs != null) rs.close();
		}
			
	}
}


