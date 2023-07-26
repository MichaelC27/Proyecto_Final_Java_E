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
public class CrearPlanilla {

    private String cedula;
    private String fecha_creacion;

    AdicionEmpleadoPlanilla obj = new AdicionEmpleadoPlanilla();
    public CrearPlanilla() {
    }

    public CrearPlanilla(String cedula, String fecha_creacion) {
        this.cedula = cedula;
        this.fecha_creacion = fecha_creacion;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getFecha_creacion() {
        return fecha_creacion;
    }

    public void setFecha_creacion(String fecha_creacion) {
        this.fecha_creacion = fecha_creacion;
    }

    
    public int insertar() {
    try {
        PreparedStatement ps = Conexion.getConnection().prepareStatement("CALL sp_insert_tbl_planilla(?, ?)");
        ps.setString(1, this.cedula);
        ps.setString(2, this.fecha_creacion);

        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            int id_planilla = rs.getInt("respuesta");
            obj.setId_planilla(id_planilla); 
            System.out.println("ID de la planilla generada: " + id_planilla); // Mostrar el ID de la planilla generado
            return id_planilla;
        } else {
            return 0; // Si no hay resultados en el ResultSet, devuelve 0 o algún otro valor predeterminado.
        }
    } catch (SQLException e) {
        System.out.println("Error: " + e.getMessage());
        return 0; // En caso de excepción, también se puede devolver 0 o algún otro valor predeterminado.
    }
}
    


}
