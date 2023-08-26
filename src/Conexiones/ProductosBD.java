package Conexiones;

import Clases.Producto;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import oracle.jdbc.OracleTypes;

public class ProductosBD {

    public ArrayList<Producto> ListProducto() {
        ArrayList<Producto> producto = new ArrayList();
        try {
            Connection cnx = ConexionOracle.getConnection();
            CallableStatement cstmt = cnx.prepareCall("{call listar_Productos(?)}");
            cstmt.registerOutParameter(1, OracleTypes.CURSOR);
            cstmt.execute();

            ResultSet rs = (ResultSet) cstmt.getObject(1);
            while (rs.next()) {
                Producto p = new Producto();
                p.setId(rs.getInt("id"));
                p.setNombre(rs.getString("nombre"));
                p.setDescripcion(rs.getString("descripcion"));
                p.setPrecio_venta(rs.getInt("precio_venta"));
                p.setCantidad(rs.getInt("cantidad"));
                p.setCategoria(rs.getString("categoria"));
                p.setPrecio_compra(rs.getInt("precio_compra"));
                producto.add(p);
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
            System.err.println("Error de listado");
        }
        return producto;
    }

    public void insertarProducto(Producto producto) {
        try {
            Connection cnx = ConexionOracle.getConnection();
            CallableStatement cst = cnx.prepareCall("{call insertar_Productos(?,?,?,?,?,?,?)}");
            cst.setInt(1, producto.getId());
            cst.setString(2, producto.getNombre());
            cst.setString(3, producto.getDescripcion());
            cst.setInt(4, producto.getPrecio_venta());
            cst.setInt(5, producto.getCantidad());
            cst.setString(6, producto.getCategoria());
            cst.setInt(7, producto.getPrecio_compra());
            cst.execute();
            System.out.println("Añadido correctamente");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
            System.err.println("Error en insertar");
        }
    }

    public void eliminarProductoPorID(int idProducto) {
        try {
            Connection cnx = ConexionOracle.getConnection();
            CallableStatement cst = cnx.prepareCall("{call eliminar_Productos(?)}");
            cst.setInt(1, idProducto);
            cst.execute();
            System.out.println("Proveedor con ID " + idProducto + " eliminado correctamente");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
            System.err.println("Error al eliminar el proveedor con ID " + idProducto);
        }
    }

    public void modificarProducto(Producto producto) {
        try {
            Connection cnx = ConexionOracle.getConnection();
            CallableStatement cst = cnx.prepareCall("{call modificar_Productos(?, ?, ?, ?, ?, ?,?)}");
            cst.setInt(1, producto.getId());
            cst.setString(2, producto.getNombre());
            cst.setString(3, producto.getDescripcion());
            cst.setInt(4, producto.getPrecio_venta());
            cst.setInt(5, producto.getCantidad());
            cst.setString(6, producto.getCategoria());
            cst.setInt(7, producto.getPrecio_compra());
            cst.execute();
            System.out.println("actualizada");
            cnx.close();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    public Producto buscarProductoPorID(int idProducto) {
        Producto productoEncontrado = null;

        try {
            Connection cnx = ConexionOracle.getConnection();
            CallableStatement cstmt = cnx.prepareCall("{call consultar_Productos_Por_ID(?, ?)}");
            cstmt.setInt(1, idProducto);
            cstmt.registerOutParameter(2, OracleTypes.CURSOR);
            cstmt.execute();
            ResultSet rs = (ResultSet) cstmt.getObject(2);

            if (rs.next()) {
                productoEncontrado = new Producto();
                productoEncontrado.setId(rs.getInt("id"));
                productoEncontrado.setNombre(rs.getString("nombre"));
                productoEncontrado.setDescripcion(rs.getString("descripcion"));
                productoEncontrado.setPrecio_venta(rs.getInt("precio_venta"));
                productoEncontrado.setCantidad(rs.getInt("cantidad"));
                productoEncontrado.setCategoria(rs.getString("categoria"));
                productoEncontrado.setPrecio_compra(rs.getInt("precio_compra"));
            }

            cnx.close();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

        return productoEncontrado;
    }

}
