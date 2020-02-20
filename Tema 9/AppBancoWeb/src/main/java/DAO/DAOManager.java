/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 *
 * @author carlos
 */
public class DAOManager {

    private  Connection conn;
    private final String URL;
    private final String USER;
    private final String PASS;
    private static DAOManager singlenton; //Atributo estatico que guarda una referencia al DAO

    private DAOManager() throws FileNotFoundException, IOException { //Constructor privado para que no se pueda llamar las veces que se quiera
        this.conn = null;
        Properties properties = new Properties();
        properties.load(new FileReader("/Users/carlos/Documents/proyectosjava/APPBancoWeb/AppBancoWeb/src/main/webapp/config/appbancoweb.properties"));
        String url = properties.getProperty("url_bd");
        String user = properties.getProperty("user_bd");
        String pass = properties.getProperty("pass_bd");
        URL = url;
        USER = user;
        PASS = pass;
        /*this.URL = "jdbc:mysql://localhost:3306/banco?autoReconnect=true&useSSL=false";
        this.USER = "root"; //Usuario de la BBDD
        this.PASS = "1234"; //Clave de la BBDD*/
    }

    public static DAOManager getSinglentonInstance() throws IOException{ //Metodo que devuelve el DAO, si el atributo estatico ya ha sido
                                                      // inicializado lo devuelve
        if (singlenton == null) {
            singlenton = new DAOManager();
            return singlenton;
        }else return singlenton; //Aqui devuelvo el que ya está creado
    }

    public Connection getConn() {
        return conn;
    }

    public void open() throws Exception {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); //Cargo el driver de conexión jdbc
            conn = DriverManager.getConnection(URL, USER, PASS); //Uso la clase DriverManager para crear la conexión
        } catch (ClassNotFoundException | SQLException e) {
            throw e;
        }
    }

    public void close() throws SQLException {
        try
        {
            if(this.conn!=null)
                this.conn.close();
        }
        catch(SQLException e) { throw e; }
    }

}
