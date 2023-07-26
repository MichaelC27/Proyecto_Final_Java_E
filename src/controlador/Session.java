/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author dbpan
 */
public class Session {

    
    private static Session instance = null;
    private String userid;
    private String cedula;
    private String nombre;
    private String apellido;

    // Constructor privado para evitar instanciación directa desde fuera de la clase
    private Session() {
    }

    // Método estático para obtener la instancia única de Session
    public static Session getInstance() {
        if (instance == null) {
            instance = new Session();
        }
        return instance;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
        buscar();
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public Session buscar() {
        //System.out.println("Usuario desde session" + this.userid);
        try {
            PreparedStatement ps = Conexion.getConnection().prepareStatement("call sp_select_dato_usuario_login(?)");
            ps.setString(1, this.userid);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                this.cedula = rs.getString(1);
                this.nombre = rs.getString(2);
                this.apellido = rs.getString(3);

                return this;
            }
            return null;
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
            return null;
        }
    }
    
}
