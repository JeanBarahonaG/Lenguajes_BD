package Conexiones;

import Clases.Clientes;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import oracle.jdbc.OracleTypes;

public class ClienteBD {

    public ArrayList<Clientes> ListClientes() {
        ArrayList<Clientes> cliente = new ArrayList();
        try {
            Connection cnx = ConexionOracle.getConnection();
            CallableStatement cstmt = cnx.prepareCall("{call listar_Cliente(?)}");
            cstmt.registerOutParameter(1, OracleTypes.CURSOR);
            cstmt.execute();

            ResultSet rs = (ResultSet) cstmt.getObject(1);
            while (rs.next()) {
                Clientes c = new Clientes();
                c.setId(rs.getInt("ID"));
                c.setNombre(rs.getString("NOMBRE"));
                c.setTelefono(rs.getString("TELEFONO"));
                c.setMetodo_pago(rs.getString("METODO_PAGO"));
                c.setDireccion(rs.getString("DIRECCION"));
                c.setEmail(rs.getString("EMAIL"));

                cliente.add(c);
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
            System.err.println("Error de listado");
        }
        return cliente;
    }

    public void insertarCliente(Clientes cliente) {
        try {
            Connection cnx = ConexionOracle.getConnection();
            CallableStatement cst = cnx.prepareCall("{call insertar_Cliente(?,?,?,?,?,?)}");
            cst.setInt(1, cliente.getId());
            cst.setString(2, cliente.getNombre());
            cst.setString(3, cliente.getTelefono());
            cst.setString(4, cliente.getMetodo_pago());
            cst.setString(5, cliente.getDireccion());
            cst.setString(6, cliente.getEmail());

            cst.execute();
            System.out.println("Añadido correctamente");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
            System.err.println("Error en insertar");
        }
    }

    public void eliminarClientePorID(int idCliente) {
        try {
            Connection cnx = ConexionOracle.getConnection();
            CallableStatement cst = cnx.prepareCall("{call eliminar_Cliente(?)}");
            cst.setInt(1, idCliente);
            cst.execute();
            System.out.println("Proveedor con ID " + idCliente + " eliminado correctamente");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
            System.err.println("Error al eliminar el proveedor con ID " + idCliente);
        }
    }

    public void modificarCliente(Clientes cliente) {
        try {
            Connection cnx = ConexionOracle.getConnection();
            CallableStatement cst = cnx.prepareCall("{call modificar_Cliente(?, ?, ?, ?, ?, ?)}");
            cst.setInt(1, cliente.getId());
            cst.setString(2, cliente.getNombre());
            cst.setString(3, cliente.getTelefono());
            cst.setString(4, cliente.getMetodo_pago());
            cst.setString(5, cliente.getDireccion());
            cst.setString(6, cliente.getEmail());
            cst.execute();
            System.out.println("actualizada");
            cnx.close();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    public Clientes buscarClientePorID(int idCliente) {
        Clientes ClienteEncontrado = null;

        try {
            Connection cnx = ConexionOracle.getConnection();
            CallableStatement cstmt = cnx.prepareCall("{call consultar_Cliente_Por_ID(?, ?)}");
            cstmt.setInt(1, idCliente);
            cstmt.registerOutParameter(2, OracleTypes.CURSOR);
            cstmt.execute();
            ResultSet rs = (ResultSet) cstmt.getObject(2);

            if (rs.next()) {
                ClienteEncontrado = new Clientes();
                ClienteEncontrado.setId(rs.getInt("ID"));
                ClienteEncontrado.setNombre(rs.getString("NOMBRE"));
                ClienteEncontrado.setTelefono(rs.getString("TELEFONO"));
                ClienteEncontrado.setMetodo_pago(rs.getString("METODO_PAGO"));
                ClienteEncontrado.setDireccion(rs.getString("DIRECCION"));
                ClienteEncontrado.setEmail(rs.getString("EMAIL"));
            }

            cnx.close();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

        return ClienteEncontrado;
    }

}
