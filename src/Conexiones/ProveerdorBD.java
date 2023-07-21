package Conexiones;

import Clases.Proveerdor;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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

}
