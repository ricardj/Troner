package Model;

import java.sql.*;

/**
 * Clse que permite conectarnos a la base de datos mediante querys.
 * @author grupoC6
 */
public class ConectorDB {
    static String userName;
    static String password;
    static String db;
    static int port;
    static String url = "jdbc:mysql://localhost";
    static Connection conn;
    static Statement s;

    /**
     * Constructor de la clase {@code ConectorDB} que reune la información para conectarse a la base de datos.
     * @param usr usuario de la base de datos
     * @param pass contraseña para la base de datos
     * @param db nombre de la base de datos
     * @param port puerto de conexión a la base de datos
     */
    public ConectorDB(String usr, String pass, String db, int port) {
        ConectorDB.userName = usr;
        ConectorDB.password = pass;
        ConectorDB.db = db;
        ConectorDB.port = port;
        ConectorDB.url += ":"+port+"/";
        ConectorDB.url += db;
    }

    /**
     * Realiza la conexión a la base de datos.
     */
    public void connect() {
        try {
            Class.forName("com.mysql.jdbc.Connection");
            conn = (Connection) DriverManager.getConnection(url, userName, password);
            if (conn != null) {
                System.out.println("Conexió a base de dades "+url+" ... Ok");
            }
        }
        catch(SQLException ex) {
            System.out.println("Problema al connecta-nos a la BBDD --> "+url);
        }
        catch(ClassNotFoundException ex) {
            ex.printStackTrace();
            //System.out.println(ex);
        }

    }

    /**
     * Envia a la base de datos querys del tipo INSERT INTO --
     * @param query query a insertar
     */
    public void insertQuery(String query){
        try {
            s =(Statement) conn.createStatement();
            s.executeUpdate(query);

        } catch (SQLException ex) {
            System.out.println("Problema al Inserir --> " + ex.getSQLState());
            ex.printStackTrace();
        }
    }

    /**
     * Envia querys a la base de datos del tipo UPDATE --
     * @param query query a ejecutar
     */
    public void updateQuery(String query){
        try {
            s =(Statement) conn.createStatement();
            s.executeUpdate(query);

        } catch (SQLException ex) {
            System.out.println("Problema al Modificar --> " + ex.getSQLState());
        }
    }

    /**
     * Envia querys a la base de datos del tipo DELETE --
     * @param query query a ejecutar
     */
    public void deleteQuery(String query){
        try {
            s =(Statement) conn.createStatement();
            s.executeUpdate(query);

        } catch (SQLException ex) {
            System.out.println("Problema al Eliminar --> " + ex.getSQLState());
            ex.printStackTrace();
        }

    }

    /**
     * Envia querys a la base de datos del tipo SELECT --
     * @param query query a ejecutar.
     * @return retorna toda la información que retorna la query.
     */
    public ResultSet selectQuery(String query){
        ResultSet rs = null;
        try {
            s =(Statement) conn.createStatement();
            rs = s.executeQuery (query);

        } catch (SQLException ex) {
            System.out.println("Problema al Recuperar les dades --> " + ex.getSQLState());
            ex.printStackTrace();
        }
        return rs;
    }

    /**
     * Realiza la desconexión de la base de datos.
     */
    public void disconnect(){
        try {
            conn.close();
        } catch (SQLException e) {
            System.out.println("Problema al tancar la connexió --> " + e.getSQLState());
        }
    }

}