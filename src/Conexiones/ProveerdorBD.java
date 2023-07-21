package Conexiones;

import Clases.Proveerdor;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.util.ArrayList;

public class ProveerdorBD {

    public ArrayList<Proveerdor> ListProveedor() {
        ArrayList<Proveerdor> proveedor = new ArrayList();
        try {

            Connection cnx = ConexionOracle.getConnection();
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery("SELECT ID,MARCA,CIUDAD,NOMBRE,EMAIL,TELEFONO   " + "   FROM PROVEEDOR ORDER BY 2 ");

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
            PreparedStatement pst = cnx.prepareStatement("INSERT INTO PROVEEDOR(ID,MARCA,CIUDAD,NOMBRE,EMAIL,TELEFONO)" 
            + "  VALUES(?,?,?,?,?,?)");
            pst.setInt(1, proveedor.getId());
            pst.setString(2, proveedor.getMarca());
            pst.setString(3, proveedor.getCiudad());
            pst.setString(4, proveedor.getNombre());
            pst.setString(5, proveedor.getEmail());
            pst.setString(6, proveedor.getTelefono());
            pst.executeUpdate();
            System.out.println("Añadido correctamente");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
            System.err.println("Error en insertar");
        }
    }
    
//        public void modificarProveedor(Proveerdor proveedor) {
//        try {
//            Connection cnx = ConexionOracle.getConnection();
//            PreparedStatement pst = cnx.prepareStatement("UPDATE PROVEEDOR SET MARCA = ?, CIUDAD = ?, NOMBRE = ?, EMAIL = ?, TELEFONO = ?" 
//            + "  WHERE ID = ?");
//            
//            pst.setString(1, proveedor.getMarca());
//            pst.setString(2, proveedor.getCiudad());
//            pst.setString(3, proveedor.getNombre());
//            pst.setString(4, proveedor.getEmail());
//            pst.setString(5, proveedor.getTelefono());
//            pst.setInt(6, proveedor.getId());
//            pst.execute();
//            System.out.println("Modificado correctamente");
//        } catch (SQLException ex) {
//            System.err.println(ex.getMessage());
//            System.err.println("Error en insertar");
//        }
//    }

}
