package Conexiones;

import Clases.Vendedores;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import oracle.jdbc.OracleTypes;

public class VendedorBD {

    public ArrayList<Vendedores> ListVendedor() {
        ArrayList<Vendedores> vendedor = new ArrayList();
        try {
            Connection cnx = ConexionOracle.getConnection();
            CallableStatement cstmt = cnx.prepareCall("{call listar_Vendedor(?)}");
            cstmt.registerOutParameter(1, OracleTypes.CURSOR);
            cstmt.execute();

            ResultSet rs = (ResultSet) cstmt.getObject(1);
            while (rs.next()) {
                Vendedores v = new Vendedores();
                v.setId(rs.getInt("ID"));
                v.setNombre(rs.getString("NOMBRE"));
                v.setTelefono(rs.getString("TELEFONO"));
                v.setDireccion(rs.getNString("DIRECCION"));
                v.setEmail(rs.getString("EMAIL"));
                vendedor.add(v);
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
            System.err.println("Error de listado");
        }
        return vendedor;
    }

    public void insertarVendedor(Vendedores vendedor) {
        try {
            Connection cnx = ConexionOracle.getConnection();
            CallableStatement cst = cnx.prepareCall("{call insertar_Vendedor(?,?,?,?,?)}");
            cst.setInt(1, vendedor.getId());
            cst.setString(2, vendedor.getNombre());
            cst.setString(3, vendedor.getTelefono());
            cst.setString(4, vendedor.getDireccion());
            cst.setString(5, vendedor.getEmail());

            cst.execute();
            System.out.println("Añadido correctamente");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
            System.err.println("Error en insertar");
        }
    }

    public void eliminarVendedorPorID(int idVendedor) {
        try {
            Connection cnx = ConexionOracle.getConnection();
            CallableStatement cst = cnx.prepareCall("{call eliminar_Vendedor(?)}");
            cst.setInt(1, idVendedor);
            cst.execute();
            System.out.println("Proveedor con ID " + idVendedor + " eliminado correctamente");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
            System.err.println("Error al eliminar el proveedor con ID " + idVendedor);
        }
    }

    public void modificarVendedor(Vendedores vendedor) {
        try {
            Connection cnx = ConexionOracle.getConnection();
            CallableStatement cst = cnx.prepareCall("{call modificar_Vendedor(?, ?, ?, ?, ?)}");
            cst.setInt(1, vendedor.getId());
            cst.setString(2, vendedor.getNombre());
            cst.setString(3, vendedor.getTelefono());
            cst.setString(4, vendedor.getDireccion());
            cst.setString(5, vendedor.getEmail());
            cst.execute();
            System.out.println("actualizada");
            cnx.close();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    public Vendedores buscarVendedorPorID(int idVendedor) {
        Vendedores VendedoreEncontrado = null;

        try {
            Connection cnx = ConexionOracle.getConnection();
            CallableStatement cstmt = cnx.prepareCall("{call consultar_Vendedor_Por_ID(?, ?)}");
            cstmt.setInt(1, idVendedor);
            cstmt.registerOutParameter(2, OracleTypes.CURSOR);
            cstmt.execute();
            ResultSet rs = (ResultSet) cstmt.getObject(2);

            if (rs.next()) {
                VendedoreEncontrado = new Vendedores();
                VendedoreEncontrado.setId(rs.getInt("ID"));
                VendedoreEncontrado.setNombre(rs.getString("NOMBRE"));
                VendedoreEncontrado.setTelefono(rs.getString("TELEFONO"));
                VendedoreEncontrado.setDireccion(rs.getString("DIRECCION"));
                VendedoreEncontrado.setEmail(rs.getString("EMAIL"));
            }

            cnx.close();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

        return VendedoreEncontrado;
    }

}
