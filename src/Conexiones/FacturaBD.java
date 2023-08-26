package Conexiones;

import Clases.Facturas;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import oracle.jdbc.OracleTypes;

public class FacturaBD {

    public ArrayList<Facturas> ListFactura() {
        ArrayList<Facturas> factura = new ArrayList();
        try {
            Connection cnx = ConexionOracle.getConnection();
            CallableStatement cstmt = cnx.prepareCall("{call listar_Factura(?)}");
            cstmt.registerOutParameter(1, OracleTypes.CURSOR);
            cstmt.execute();

            ResultSet rs = (ResultSet) cstmt.getObject(1);
            while (rs.next()) {
                Facturas pv = new Facturas();
                pv.setId(rs.getInt("ID"));
                pv.setFecha(rs.getString("fecha"));
                pv.setCantidad(rs.getInt("cantidad"));
                pv.setTotal(rs.getInt("total"));
                pv.setMetodo_Pago(rs.getString("metodo_pago"));
                factura.add(pv);
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
            System.err.println("Error de listado");
        }
        return factura;
    }

    public void insertarFactura(Facturas factura) {
        try {
            Connection cnx = ConexionOracle.getConnection();
            CallableStatement cst = cnx.prepareCall("{call insertar_Factura(?,?,?,?,?)}");
            cst.setInt(1, factura.getId());
            cst.setString(2, factura.getFecha());
            cst.setInt(3, factura.getCantidad());
            cst.setInt(4, factura.getTotal());
            cst.setString(5, factura.getMetodo_Pago());
            cst.execute();
            System.out.println("Añadido correctamente");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
            System.err.println("Error en insertar");
        }
    }

    public void eliminarFacturaPorID(int idFactura) {
        try {
            Connection cnx = ConexionOracle.getConnection();
            CallableStatement cst = cnx.prepareCall("{call eliminar_Factura(?)}");
            cst.setInt(1, idFactura);
            cst.execute();
            System.out.println("Proveedor con ID " + idFactura + " eliminado correctamente");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
            System.err.println("Error al eliminar el proveedor con ID " + idFactura);
        }
    }

    public void modificarFactura(Facturas factura) {
        try {
            Connection cnx = ConexionOracle.getConnection();
            CallableStatement cst = cnx.prepareCall("{call modificar_Factura(?, ?, ?, ?, ?)}");
            cst.setInt(1, factura.getId());
            cst.setString(2, factura.getFecha());
            cst.setInt(3, factura.getCantidad());
            cst.setInt(4, factura.getTotal());
            cst.setString(5, factura.getMetodo_Pago());
            cst.execute();
            System.out.println("actualizada");
            cnx.close();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
    
        public Facturas buscarFacturaPorID(int idFactura) {
        Facturas FacturaEncontrado = null;

        try {
            Connection cnx = ConexionOracle.getConnection();
            CallableStatement cstmt = cnx.prepareCall("{call consultar_Factura_Por_ID(?, ?)}");
            cstmt.setInt(1, idFactura);
            cstmt.registerOutParameter(2, OracleTypes.CURSOR);
            cstmt.execute();
            ResultSet rs = (ResultSet) cstmt.getObject(2);

            if (rs.next()) {
                FacturaEncontrado = new Facturas();
                FacturaEncontrado.setId(rs.getInt("ID"));
                FacturaEncontrado.setFecha(rs.getString("fecha"));
                FacturaEncontrado.setCantidad(rs.getInt("cantidad"));
                FacturaEncontrado.setCantidad(rs.getInt("total"));
                FacturaEncontrado.setMetodo_Pago("metodo_pago");
            }

            cnx.close();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

        return FacturaEncontrado;
    }

}
