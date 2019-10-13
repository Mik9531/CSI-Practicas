package es.uca.gii.csi18.lara.test;

import static org.junit.jupiter.api.Assertions.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import es.uca.gii.csi18.lara.data.Autor;
import es.uca.gii.csi18.lara.data.Data;
import es.uca.gii.csi18.lara.data.Reliquia;

class ReliquiaTest {

    static Scanner sc = new Scanner(System.in);

    @BeforeAll
    static void setUpBeforeClass() throws Exception {
        Data.LoadDriver();
    }

    @Test
    public void testConstructor() throws Exception {

        Connection con = null;
        ResultSet rs = null;

        try {
            con = Data.Connection();
            rs = con.createStatement()
                    .executeQuery("SELECT Id, Peso, Nombre FROM reliquia WHERE Id = 1;");
            Reliquia prueba = new Reliquia(1);

            if (rs.next()) {
                assertEquals(prueba.getId(), rs.getInt("Id"));
                assertEquals(prueba.getNombre(), rs.getString("Nombre"));
                assertEquals(prueba.getPeso(), rs.getInt("Peso"));
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

    public void testCreate() throws Exception {

        Autor autor = new Autor(1);
        Reliquia prueba = Reliquia.Create(30, "Create", autor);
        Connection con = null;
        ResultSet rs = null;

        try {
            con = Data.Connection();
            rs = con.createStatement().executeQuery(
                    "SELECT Id, Id_Autor, Peso, Nombre FROM reliquia ORDER BY Id desc"
                    + " LIMIT 1;");

            if (rs.next()) {
                assertEquals(prueba.getId(), rs.getInt("Id"));
                assertEquals(prueba.getAutor().getId(), rs.getInt("Id_Autor"));
                assertEquals(prueba.getPeso(), rs.getInt("Peso"));
                assertEquals(prueba.getNombre(), rs.getString("Nombre"));
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

    @Test
    public void testSelect() throws Exception {

        Connection con = null;
        ResultSet rs = null;

        ArrayList<Reliquia> reliquia = Reliquia.Select(null, "pruebafinal", "");
        try {
            con = Data.Connection();
            rs = con.createStatement().executeQuery(
                    "SELECT Id, Peso, Nombre FROM reliquia WHERE Nombre"
                    + " LIKE 'pruebafinal'");
            int i = 0;
            while (rs.next()) {
                assertEquals(rs.getInt("Id"), reliquia.get(i).getId());
                assertEquals(rs.getInt("Id_Autor"), reliquia.get(i).getAutor().getId());
                assertEquals(rs.getInt("Peso"), reliquia.get(i).getPeso());
                assertEquals(rs.getString("Nombre"), reliquia.get(i).getNombre());
                i++;
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

    @Test
    public void testUpdate() throws Exception {

        Connection con = null;
        ResultSet rs = null;

        Autor autor = new Autor(1);
        Reliquia reliquia = Reliquia.Create(50, "Create", autor);
        reliquia.setPeso(20);
        reliquia.setNombre("Update");
        reliquia.Update();

        try {

            con = Data.Connection();
            rs = con.createStatement()
                    .executeQuery("SELECT Id, Id_Autor, Peso, Nombre FROM reliquia "
                            + "WHERE Id =" + reliquia.getId());

            while (rs.next()) {
                assertEquals(autor.getId(), rs.getInt("Id_Autor"));
                assertEquals(reliquia.getPeso(), rs.getInt("Peso"));
                assertEquals(reliquia.getNombre(), rs.getString("Nombre"));

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

    @Test
    public void testDelete() throws Exception {
        Connection con = null;
        ResultSet rs = null;
        Reliquia reliquia = new Reliquia(2);

        try {

            reliquia.Delete();
            con = Data.Connection();
            rs = con.createStatement().executeQuery("SELECT Id, Peso, Nombre " 
                    + "FROM reliquia");

            while (rs.next()) {
                assertNotSame(rs.getInt("Id"), reliquia.getId());
            }

            assertEquals(true, reliquia.getIsDeleted());

        } catch (SQLException ee) {
            throw ee;
        } finally {
            if (rs != null)
                rs.close();
            if (con != null)
                con.close();
        }
    }
}
