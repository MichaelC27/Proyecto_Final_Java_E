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
public class Empleado {
    private String cedula;
    private String nombre;
    private String apellido;
    private String fecha_nacimiento;
    private String direccion;
    private String numero_telefono;
    private String fecha_creacion;
    private String fecha_modificacion;

    public Empleado() {
    }

    public Empleado(String cedula, String nombre, String apellido, String fecha_nacimiento, String direccion, String numero_telefono, String fecha_creacion, String fecha_modificacion) {
        this.cedula = cedula;
        this.nombre = nombre;
        this.apellido = apellido;
        this.fecha_nacimiento = fecha_nacimiento;
        this.direccion = direccion;
        this.numero_telefono = numero_telefono;
        this.fecha_creacion = fecha_creacion;
        this.fecha_modificacion = fecha_modificacion;
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

    public String getFecha_nacimiento() {
        return fecha_nacimiento;
    }

    public void setFecha_nacimiento(String fecha_nacimiento) {
        this.fecha_nacimiento = fecha_nacimiento;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getNumero_telefono() {
        return numero_telefono;
    }

    public void setNumero_telefono(String numero_telefono) {
        this.numero_telefono = numero_telefono;
    }

    public String getFecha_creacion() {
        return fecha_creacion;
    }

    public void setFecha_creacion(String fecha_creacion) {
        this.fecha_creacion = fecha_creacion;
    }

    public String getFecha_modificacion() {
        return fecha_modificacion;
    }

    public void setFecha_modificacion(String fecha_modificacion) {
        this.fecha_modificacion = fecha_modificacion;
    }

    
    //METODO PARA INSERTAR
   public int insertar() {
        try {
            PreparedStatement ps = Conexion.getConnection().prepareStatement("call sp_insert_tbl_empleado(?,?,?,?,?,?)");
            ps.setString(1, this.cedula);
            ps.setString(2, this.nombre);
            ps.setString(3, this.apellido);
            ps.setString(4, this.fecha_nacimiento);
            ps.setString(5, this.direccion);
            ps.setString(6, this.numero_telefono);

            ResultSet rs = ps.executeQuery();
            System.out.println(rs.getString("respuesta"));
            if (rs.next()) {
                return Integer.parseInt(rs.getString("respuesta"));
            } else {
                return 0;
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
            return 0;
        }

    }
   
   
   
    //METODO PARA MODIFICAR
    public int modificar() {
        try {
            PreparedStatement ps = Conexion.getConnection().prepareStatement("call sp_update_tbl_empleado(?,?,?,?,?,?)");
            ps.setString(1, this.cedula);
            ps.setString(2, this.nombre);
            ps.setString(3, this.apellido);
            ps.setString(4, this.fecha_nacimiento);
            ps.setString(5, this.direccion);
            ps.setString(6, this.numero_telefono);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return Integer.parseInt(rs.getString("respuesta"));
            } else {
                return 0;
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
            return 0;
        }

    }


      //METODO PARA BUSCAR POR CEDULA
    
    public Empleado buscar() {
        System.out.println(this.cedula);
        try {
            PreparedStatement ps = Conexion.getConnection().prepareStatement("call sp_select_tbl_empleado_por_cedula(?)");
            ps.setString(1, this.cedula);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                this.cedula = rs.getString(1);
                this.nombre = rs.getString(2);
                this.apellido = rs.getString(3);
                this.fecha_nacimiento = rs.getString(4);
                this.direccion = rs.getString(5);
                this.numero_telefono = rs.getString(6);
                return this;
            }
            return null;
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
            return null;
        }
    }

}

    