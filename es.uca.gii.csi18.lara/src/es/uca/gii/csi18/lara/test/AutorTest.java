package es.uca.gii.csi18.lara.test;

import static org.junit.jupiter.api.Assertions.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import es.uca.gii.csi18.lara.data.Data;
import es.uca.gii.csi18.lara.data.Autor;

class AutorTest {

    @BeforeAll
    static void setUpBeforeClass() throws Exception {}

    @Test
    public void testConstructor() throws Exception {

        Connection con = null;
        ResultSet rs = null;

        try {
            con = Data.Connection();
            rs = con.createStatement()
                    .executeQuery("SELECT Id, Nombre FROM autor WHERE Id = 1;");
            Autor prueba = new Autor(1);

            if (rs.next()) {
                assertEquals(prueba.getId(), rs.getInt("Id"));
                assertEquals(prueba.getNombre(), rs.getString("Nombre"));

            }
        } catch (SQLException ee) {
            throw ee;
        } finally {
            if (rs != null) rs.close();
            if (con != null) con.close();
        }
    }
    
    @Test
    public void testSelect() throws Exception {

        Connection con = null;
        ResultSet rs = null;

        ArrayList<Autor> autor = Autor.Select();
        try {
            con = Data.Connection();
            rs = con.createStatement()
                    .executeQuery("SELECT Id, Nombre FROM autor ORDER BY Nombre");
            int i = 0;
            while (rs.next()) {
                assertEquals(rs.getInt("Id"), autor.get(i).getId());
                assertEquals(rs.getString("Nombre"), autor.get(i).getNombre());
                i++;
            }
        } catch (SQLException ee) {
            throw ee;
        } finally {
            if (rs != null) rs.close();
            if (con != null) con.close();
        }
    }

}
