package Conexiones;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConexionOracle {

    public static Connection getConnection() {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            String myDB = "jdbc:oracle:thin:@localhost:1521:xe";
            String usuario = "cursosabado";
            String password = "123456";
            //Connection cnx = DriverManager.getConnection(myDB, usuario, password);
            System.out.println("Conexion Hecha");
            return DriverManager.getConnection(myDB, usuario, password);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ConexionOracle.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }
}
