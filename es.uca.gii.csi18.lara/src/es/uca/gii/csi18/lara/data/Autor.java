package es.uca.gii.csi18.lara.data;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Autor {
    private int _iId;
    private String _sNombre;
    
    void setNombre(String sNombre) {
        _sNombre = sNombre; 
    }
    
    public String getNombre() {
        return _sNombre;
    }
    
    public int getId() {
        return _iId;
    }
    
    public Autor(int iId) throws Exception {
            Connection con = null;
            ResultSet rs = null;

            try {
                con = Data.Connection();
                rs = con.createStatement()
                     .executeQuery("SELECT Id, Nombre FROM autor WHERE Id =" + iId);
                while (rs.next()) {
                    _iId = rs.getInt("Id");
                    _sNombre = rs.getString("Nombre");
                }
            } catch (SQLException ee) {
                throw ee;
            } finally {
                if (rs != null)
                    rs.close();
                if (con != null)
                    con.close();
            }
    }
    
    public static ArrayList<Autor> Select()
            throws Exception {

        Connection con = null;
        ResultSet rs = null;
        ArrayList<Autor> Autor = new ArrayList<Autor>();

        try {
            con = Data.Connection();
            rs = con.createStatement().executeQuery(
                    "SELECT Id, Nombre FROM autor ORDER BY Nombre");

            while (rs.next()) {
                Autor.add(new Autor(rs.getInt("Id")));
            }
            return Autor;
            
        } catch (SQLException ee) {
            throw ee;
        } finally {
            if (rs != null) rs.close();
            if (con != null) con.close();
        }
    }
    
    public String toString() {
        return _sNombre;
    }

}
