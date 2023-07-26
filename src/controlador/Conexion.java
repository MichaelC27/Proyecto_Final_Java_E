/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import java.sql.*;


/**
 *
 * @author dbpan
 */
/*
public class Conexion {
import java.util.logging.Level;
import java.util.logging.Logger;
    String bd = "db_planilla";
    String url = "jdbc:mysql://localhost:3306/";
    String user = "root";
    String password = "";
    String driver = "com.mysql.jdbc.Driver";
    Connection cx;

    public Conexion() {
    }

    public Connection conectar() {
        try {
            Class.forName(driver);
            cx = DriverManager.getConnection(url + bd, user, password);
            System.out.println("SE CONECTO A BD " + bd);
        } catch (ClassNotFoundException | SQLException ex) {
            System.out.println("NO SE CONECTO A BD " + bd);
            Logger.getLogger (Conexion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return cx;
    }
    public void desconectar (){
        try {
            cx.close();
        } catch (SQLException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public static void main(String[] args){
        Conexion conexion = new Conexion();
        conexion.conectar();
    }
}

*/
public class Conexion {
    public static Connection getConnection(){
        Connection con;
        try{
            try{
               Class.forName("com.mysql.jdbc.Driver");
            }catch(ClassNotFoundException e){
                System.out.println(e.getMessage());
                return null;
            }
            con = DriverManager.getConnection("jdbc:mysql://localhost/db_planilla","root","");
            return con;
        }catch(SQLException e){
            System.out.println(e.getMessage());
            return null;
        }
    }
}
