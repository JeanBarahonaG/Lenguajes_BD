/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Conexiones;

import Clases.Encargados_Bodega;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author Jeanca Barahona
 */
public class Encargado_BodegaBD {

    public ArrayList<Encargados_Bodega> ListEncargado() {
        ArrayList<Encargados_Bodega> encargado = new ArrayList();
        try {

            Connection cnx = ConexionOracle.getConnection();
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery("SELECT ID,NOMBRE,TELEFONO,DIRECCION,EMAIL   " + "   FROM ENCARGADO_BODEGA ORDER BY 2");

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

    public void insertarEncargado(Encargados_Bodega encargado) {

        try {
            Connection cnx = ConexionOracle.getConnection();
            PreparedStatement pst = cnx.prepareStatement("INSERT INTO ENCARGADO_BODEGA(ID,NOMBRE,TELEFONO,DIRECCION,EMAIL)"
                    + "  VALUES(?,?,?,?,?)");
            pst.setInt(1, encargado.getId());
            pst.setString(2, encargado.getNombre());
            pst.setString(3, encargado.getTelefono());
            pst.setString(4, encargado.getDireccion());
            pst.setString(5, encargado.getEmail());
            pst.executeUpdate();
            System.out.println("Añadido correctamente");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
            System.err.println("Error en insertar");
        }

    }

}
