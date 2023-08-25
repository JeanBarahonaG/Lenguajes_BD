package Conexiones;

import Clases.Proveerdor;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import oracle.jdbc.OracleTypes;

public class ProveerdorBD {

    public ArrayList<Proveerdor> ListProveedor() {
        ArrayList<Proveerdor> proveedor = new ArrayList();
        try {
            Connection cnx = ConexionOracle.getConnection();
            CallableStatement cstmt = cnx.prepareCall("{call listar_proveedores(?)}");
            cstmt.registerOutParameter(1, OracleTypes.CURSOR);
            cstmt.execute();

            ResultSet rs = (ResultSet) cstmt.getObject(1);
            while (rs.next()) {
                Proveerdor pv = new Proveerdor();
                pv.setId(rs.getInt("ID"));
                pv.setMarca(rs.getString("MARCA"));
                pv.setCiudad(rs.getString("CIUDAD"));
                pv.setNombre(rs.getString("NOMBRE"));
                pv.setEmail(rs.getString("EMAIL"));
                pv.setTelefono(rs.getString("TELEFONO"));
                proveedor.add(pv);
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
            System.err.println("Error de listado");
        }
        return proveedor;
    }

    public void insertarProveedor(Proveerdor proveedor) {
        try {
            Connection cnx = ConexionOracle.getConnection();
            CallableStatement cst = cnx.prepareCall("{call insertar_proveedor(?,?,?,?,?,?)}");
            cst.setInt(1, proveedor.getId());
            cst.setString(2, proveedor.getMarca());
            cst.setString(3, proveedor.getCiudad());
            cst.setString(4, proveedor.getNombre());
            cst.setString(5, proveedor.getEmail());
            cst.setString(6, proveedor.getTelefono());
            cst.execute();
            System.out.println("Añadido correctamente");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
            System.err.println("Error en insertar");
        }
    }

//    public void insertarProveedor(Proveerdor proveedor) {
//        try {
//            Connection cnx = ConexionOracle.getConnection();
//            PreparedStatement pst = cnx.prepareStatement("INSERT INTO PROVEEDOR(ID,MARCA,CIUDAD,NOMBRE,EMAIL,TELEFONO)"
//                    + "  VALUES(?,?,?,?,?,?)");
//            pst.setInt(1, proveedor.getId());
//            pst.setString(2, proveedor.getMarca());
//            pst.setString(3, proveedor.getCiudad());
//            pst.setString(4, proveedor.getNombre());
//            pst.setString(5, proveedor.getEmail());
//            pst.setString(6, proveedor.getTelefono());
//            pst.executeUpdate();
//            System.out.println("Añadido correctamente");
//        } catch (SQLException ex) {
//            System.err.println(ex.getMessage());
//            System.err.println("Error en insertar");
//        }
//    }
    public void eliminarProveedorPorID(int idProveedor) {
        try {
            Connection cnx = ConexionOracle.getConnection();
            CallableStatement cst = cnx.prepareCall("{call eliminar_proveedor(?)}");
            cst.setInt(1, idProveedor);
            cst.execute();
            System.out.println("Proveedor con ID " + idProveedor + " eliminado correctamente");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
            System.err.println("Error al eliminar el proveedor con ID " + idProveedor);
        }
    }

//    public void eliminarProveedorPorID(int idProveedor) {
//        try {
//            Connection cnx = ConexionOracle.getConnection();
//            PreparedStatement pst = cnx.prepareStatement("DELETE FROM PROVEEDOR WHERE ID = ?");
//            pst.setInt(1, idProveedor);
//            int filasAfectadas = pst.executeUpdate();
//            if (filasAfectadas > 0) {
//                System.out.println("Proveedor con ID " + idProveedor + " eliminado correctamente");
//            } else {
//                System.out.println("No se encontró ningún proveedor con el ID " + idProveedor);
//            }
//        } catch (SQLException ex) {
//            System.err.println(ex.getMessage());
//            System.err.println("Error al eliminar el proveedor con ID " + idProveedor);
//        }
//    }
    public void modificarProveedor(Proveerdor proveedor) {
        try {
            Connection cnx = ConexionOracle.getConnection();
            CallableStatement cst = cnx.prepareCall("{call modificar_proveedor(?, ?, ?, ?, ?, ?)}");
            cst.setInt(1, proveedor.getId());
            cst.setString(2, proveedor.getMarca());
            cst.setString(3, proveedor.getCiudad());
            cst.setString(4, proveedor.getNombre());
            cst.setString(5, proveedor.getEmail());
            cst.setString(6, proveedor.getTelefono());
            cst.execute();
            System.out.println("actualizada");
            cnx.close();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

//    public void modificarProveedor(Proveerdor proveedor) {
//        try {
//            Connection cnx = ConexionOracle.getConnection();
//            PreparedStatement pst = cnx.prepareStatement("UPDATE PROVEEDOR SET MARCA = ?, CIUDAD = ?, NOMBRE = ?, EMAIL = ?, TELEFONO = ? WHERE ID = ?");
//            pst.setString(1, proveedor.getMarca());
//            pst.setString(2, proveedor.getCiudad());
//            pst.setString(3, proveedor.getNombre());
//            pst.setString(4, proveedor.getEmail());
//            pst.setString(5, proveedor.getTelefono());
//            pst.setInt(6, proveedor.getId());
//            int filasAfectadas = pst.executeUpdate();
//            if (filasAfectadas > 0) {
//                System.out.println("actualizada");
//            } else {
//
//                System.out.println("no se actualizo");
//            }
//            cnx.close();
//        } catch (SQLException ex) {
//
//            System.err.println(ex.getMessage());
//        }
//    }
    public Proveerdor buscarProveedorPorID(int idProveedor) {
        Proveerdor proveedorEncontrado = null;

        try {
            Connection cnx = ConexionOracle.getConnection();
            PreparedStatement pst = cnx.prepareStatement("SELECT ID, MARCA, CIUDAD, NOMBRE, EMAIL, TELEFONO "
                    + "FROM PROVEEDOR WHERE ID = ?");
            pst.setInt(1, idProveedor);
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                proveedorEncontrado = new Proveerdor();
                proveedorEncontrado.setId(rs.getInt("ID"));
                proveedorEncontrado.setMarca(rs.getString("MARCA"));
                proveedorEncontrado.setCiudad(rs.getString("CIUDAD"));
                proveedorEncontrado.setNombre(rs.getString("NOMBRE"));
                proveedorEncontrado.setEmail(rs.getString("EMAIL"));
                proveedorEncontrado.setTelefono(rs.getString("TELEFONO"));
            }

            cnx.close();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

        return proveedorEncontrado;
    }

}
