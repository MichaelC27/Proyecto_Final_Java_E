/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

/**
 *
 * @author dbpan
 */
import java.util.Base64;
import java.util.ArrayList;
import java.sql.*;
import java.util.Base64;
public class Login {
    private String userid;
    private String contrasena;

    public Login() {
    }

    public Login(String userid, String contrasena) {
        this.userid = userid;
        this.contrasena = contrasena;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }
    
    
     // METODO PARA EL INICIO DE SESION
    public boolean iniciarSesion(String usuario, String contrasena) {
    try {
        PreparedStatement ps = Conexion.getConnection().prepareStatement("CALL sp_login(?, ?)");
        ps.setString(1, usuario);
        ps.setString(2, Base64.getEncoder().encodeToString(contrasena.getBytes()));

        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            // Verificar si el valor de usuarioEncontrado es no nulo para determinar si las credenciales son válidas
            String usuarioEncontrado = rs.getString("usuario");
            return usuarioEncontrado != null;
        } else {
            return false;
        }

    } catch (SQLException e) {
        System.out.println("Error: " + e.getMessage());
        return false;
    }
}
}
