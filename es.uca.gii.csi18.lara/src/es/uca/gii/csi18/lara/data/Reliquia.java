package es.uca.gii.csi18.lara.data;

import java.util.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Reliquia {

    static Scanner sc = new Scanner(System.in);
    private int _iPeso, _iId;
    private String _sNombre;
    private boolean _bIsDeleted = false;
    private Autor _Autor;

    public Reliquia(int iId) throws Exception {
        Connection con = null;
        ResultSet rs = null;

        try {
            con = Data.Connection();
            rs = con.createStatement().executeQuery(
                    "SELECT Id, Id_Autor, Peso, Nombre FROM reliquia WHERE Id =" + iId);
            while (rs.next()) {
                _iId = rs.getInt("Id");
                _Autor = new Autor(rs.getInt("Id_Autor"));
                _iPeso = rs.getInt("Peso");
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

    public static Reliquia Create(int iPeso, String sNombre, Autor autor) 
            throws Exception {

        Connection con = null;
        sNombre = Data.String2Sql(sNombre, true, false);
        try {
            con = Data.Connection();
            con.createStatement()
                    .executeUpdate("INSERT INTO reliquia (Id_Autor, Peso, Nombre) "
                            + "VALUES(" + autor.getId() + ", " + iPeso + ", " 
                            + sNombre + ");");
            return new Reliquia(Data.LastId(con));
        } catch (SQLException ee) {
            throw ee;
        } finally {
            if (con != null)
                con.close();
        }

    }

    public static ArrayList<Reliquia> Select(Integer iPeso, String sNombre, String sAutor)
            throws Exception {

        Connection con = null;
        ResultSet rs = null;
        ArrayList<Reliquia> alReliquia = new ArrayList<Reliquia>();

        try {
            con = Data.Connection();
            rs = con.createStatement().executeQuery(
                    "SELECT reliquia.Id, reliquia.Id_Autor, reliquia.Peso, reliquia.Nombre "
                    + "FROM reliquia INNER JOIN autor ON autor.Id=reliquia.Id_Autor "
                    + Where(iPeso, sNombre, sAutor) + ";");

            while (rs.next()) {
                alReliquia.add(new Reliquia(rs.getInt("Id")));
            }
            return alReliquia;

        } catch (SQLException ee) {
            throw ee;
        } finally {
            if (rs != null)
                rs.close();
            if (con != null)
                con.close();
        }
    }

    public void Delete() throws Exception {

        Connection con = null;
        if (_bIsDeleted)
            throw new Exception("Ya está eliminado");
        else {
            try {
                con = Data.Connection();
                con.createStatement().executeUpdate("DELETE FROM reliquia WHERE Id = "
                        + _iId);
                _bIsDeleted = true;
            } catch (SQLException ee) {
                throw ee;
            } finally {
                if (con != null)
                    con.close();
            }
        }
    }

    public void Update() throws Exception {

        if (_bIsDeleted)
            throw new Exception("No puede actualizar una reliquia eliminada");
        else {
            Connection con = null;
            try {
                con = Data.Connection();
                con.createStatement()
                        .executeUpdate("UPDATE reliquia SET Id_Autor=" + _Autor.getId()
                        + ", Peso=" + _iPeso + ", Nombre = "
                        + Data.String2Sql(_sNombre, true, false)+ "WHERE Id = " + _iId);
            } catch (SQLException ee) {
                throw ee;
            } finally {
                if (con != null)
                    con.close();
            }
        }
    }

    private static String Where(Integer iPeso, String sNombre, String sAutor) {
        if (sNombre == "" && iPeso == null && sAutor == "") {
            return "";
        } else {
            String sInstruccion = "WHERE ";
            if (sAutor != "") sInstruccion += "autor.Nombre LIKE "
                        + Data.String2Sql(sAutor, true, true) + " AND";
            
            if (iPeso != null) sInstruccion += " reliquia.Peso = "
                        + iPeso + " AND";
            
            if (sNombre != "") sInstruccion += " reliquia.Nombre LIKE "
                        + Data.String2Sql(sNombre, true, true) + " AND";

            sInstruccion = sInstruccion.substring(0, sInstruccion.length() - 4);

            return sInstruccion;
        }
    }

    @Override
    public String toString() {
        return super.toString() + ":" + _iId + ":" + _iPeso + ":" + _sNombre + ":"
                + _Autor.getNombre();
    }

    /* Setters */

    public void setId(int iId) {
        _iId = iId;
    }

    public void setPeso(int iPeso) {
        _iPeso = iPeso;
    }

    public void setNombre(String sNombre) {
        _sNombre = sNombre;
    }

    public void setAutor(Autor Autor) {
        _Autor = Autor;
    }

    /* Getters */

    public int getId() {
        return _iId;
    }

    public Autor getAutor() {
        return _Autor;
    }

    public int getPeso() {
        return _iPeso;
    }

    public String getNombre() {
        return _sNombre;
    }

    public boolean getIsDeleted() {
        return _bIsDeleted;
    }
}
