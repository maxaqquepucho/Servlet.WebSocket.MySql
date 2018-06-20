package com.colegio.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;



public class ConectarMysql {
	
	private Connection connection;
    private String url = "jdbc:mysql://localhost/websocket123";
    private String usuario = "root";
    private String contraseña = "";
    
    public Connection getConnection(){
        return connection;
    }
    
    public void setConnection(Connection connection){
        this.connection = connection;
    }
    	
    public void establecerConexion(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection =  (Connection) DriverManager.getConnection(url, usuario, contraseña);
            System.out.println("Conexion Exitosa");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ConectarMysql.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
        } catch (SQLException ex) {
            Logger.getLogger(ConectarMysql.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void cerrarConexion(){
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Conexion cerrarda");
        }
    }
}
