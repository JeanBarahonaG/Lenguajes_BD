package Conexiones;

import Clases.Encargados_Bodega;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import oracle.jdbc.OracleTypes;

/**
 *
 * @author Jeanca Barahona
 */
public class Encargado_BodegaBD {

    public ArrayList<Encargados_Bodega> ListEncargado() {
        ArrayList<Encargados_Bodega> encargado = new ArrayList();
        try {

            Connection cnx = ConexionOracle.getConnection();
            CallableStatement cstmt = cnx.prepareCall("{call listar_EncargadoB(?)}");
            cstmt.registerOutParameter(1, OracleTypes.CURSOR);
            cstmt.execute();

            ResultSet rs = (ResultSet) cstmt.getObject(1);

            while (rs.next()) {
                Encargados_Bodega eb = new Encargados_Bodega();
                eb.setId(rs.getInt("ID"));
                eb.setNombre(rs.getString("NOMBRE"));
                eb.setTelefono(rs.getString("TELEFONO"));
                eb.setDireccion(rs.getString("DIRECCION"));
                eb.setEmail(rs.getString("EMAIL"));
                encargado.add(eb);
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
            System.err.println("Error de listado");
        }

        return encargado;
    }

    public void insertarEncargado(Encargados_Bodega EB) {
        try {
            Connection cnx = ConexionOracle.getConnection();
            CallableStatement cst = cnx.prepareCall("{call insertar_EncargadoB(?,?,?,?,?)}");
            cst.setInt(1, EB.getId());
            cst.setString(2, EB.getNombre());
            cst.setString(3, EB.getTelefono());
            cst.setString(4, EB.getDireccion());
            cst.setString(5, EB.getEmail());
            cst.execute();
            System.out.println("Añadido correctamente");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
            System.err.println("Error en insertar");
        }
    }

    public void eliminarEncargadoBPorID(int idEncargadoB) {
        try {
            Connection cnx = ConexionOracle.getConnection();
            CallableStatement cst = cnx.prepareCall("{call eliminar_EncargadoB(?)}");
            cst.setInt(1, idEncargadoB);
            cst.execute();
            System.out.println("Proveedor con ID " + idEncargadoB + " eliminado correctamente");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
            System.err.println("Error al eliminar el proveedor con ID " + idEncargadoB);
        }
    }

    public void modificarEncargados_Bodega(Encargados_Bodega EB) {
        try {
            Connection cnx = ConexionOracle.getConnection();
            CallableStatement cst = cnx.prepareCall("{call modificar_EncargadoB(?, ?, ?, ?, ?)}");
            cst.setInt(1, EB.getId());
            cst.setString(2, EB.getNombre());
            cst.setString(3, EB.getTelefono());
            cst.setString(4, EB.getDireccion());
            cst.setString(5, EB.getEmail());
            cst.execute();
            System.out.println("actualizada");
            cnx.close();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    public Encargados_Bodega buscarEncargadoBPorID(int idEncargadoB) {
        Encargados_Bodega Encargados_BodegaEncontrado = null;

        try {
            Connection cnx = ConexionOracle.getConnection();
            CallableStatement cstmt = cnx.prepareCall("{call consultar_EncargadoB_Por_ID(?, ?)}");
            cstmt.setInt(1, idEncargadoB);
            cstmt.registerOutParameter(2, OracleTypes.CURSOR);
            cstmt.execute();
            ResultSet rs = (ResultSet) cstmt.getObject(2);

            if (rs.next()) {
                Encargados_BodegaEncontrado = new Encargados_Bodega();
                Encargados_BodegaEncontrado.setId(rs.getInt("ID"));
                Encargados_BodegaEncontrado.setNombre(rs.getString("NOMBRE"));
                Encargados_BodegaEncontrado.setTelefono(rs.getString("TELEFONO"));
                Encargados_BodegaEncontrado.setDireccion(rs.getString("DIRECCION"));
                Encargados_BodegaEncontrado.setEmail(rs.getString("EMAIL"));
            }

            cnx.close();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

        return Encargados_BodegaEncontrado;
    }

}
