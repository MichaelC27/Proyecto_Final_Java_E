/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import com.mysql.jdbc.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


/**
 *
 * @author dbpan
 */
public class MostrarDetallePlanilla {

    private int id_detalle_planilla;
    private int id_planilla;
    private String cedula_empleado;
    private String nombre_empleado;
    private String apellido_empleado;
    private double horas_trabajadas;
    private double salario_por_hora;
    private double seguro_social;
    private double seguro_educativo;
    private double salario_bruto;
    private double salario_neto;
    private String fecha_creacion;
    private double total_salario_bruto;
    private double total_salario_neto;

    public MostrarDetallePlanilla() {
    }

    public MostrarDetallePlanilla(int id_detalle_planilla, int id_planilla, String cedula_empleado, String nombre_empleado, String apellido_empleado, double horas_trabajadas, double salario_por_hora, double seguro_social, double seguro_educativo, double salario_bruto, double salario_neto, String fecha_creacion, double total_salario_bruto, double total_salario_neto) {
        this.id_detalle_planilla = id_detalle_planilla;
        this.id_planilla = id_planilla;
        this.cedula_empleado = cedula_empleado;
        this.nombre_empleado = nombre_empleado;
        this.apellido_empleado = apellido_empleado;
        this.horas_trabajadas = horas_trabajadas;
        this.salario_por_hora = salario_por_hora;
        this.seguro_social = seguro_social;
        this.seguro_educativo = seguro_educativo;
        this.salario_bruto = salario_bruto;
        this.salario_neto = salario_neto;
        this.fecha_creacion = fecha_creacion;
        this.total_salario_bruto = total_salario_bruto;
        this.total_salario_neto = total_salario_neto;
    }

    public int getId_detalle_planilla() {
        return id_detalle_planilla;
    }

    public void setId_detalle_planilla(int id_detalle_planilla) {
        this.id_detalle_planilla = id_detalle_planilla;
    }

    public int getId_planilla() {
        return id_planilla;
    }

    public void setId_planilla(int id_planilla) {
        this.id_planilla = id_planilla;
    }

    public String getCedula_empleado() {
        return cedula_empleado;
    }

    public void setCedula_empleado(String cedula_empleado) {
        this.cedula_empleado = cedula_empleado;
    }

    public String getNombre_empleado() {
        return nombre_empleado;
    }

    public void setNombre_empleado(String nombre_empleado) {
        this.nombre_empleado = nombre_empleado;
    }

    public String getApellido_empleado() {
        return apellido_empleado;
    }

    public void setApellido_empleado(String apellido_empleado) {
        this.apellido_empleado = apellido_empleado;
    }

    public double getHoras_trabajadas() {
        return horas_trabajadas;
    }

    public void setHoras_trabajadas(double horas_trabajadas) {
        this.horas_trabajadas = horas_trabajadas;
    }

    public double getSalario_por_hora() {
        return salario_por_hora;
    }

    public void setSalario_por_hora(double salario_por_hora) {
        this.salario_por_hora = salario_por_hora;
    }

    public double getSeguro_social() {
        return seguro_social;
    }

    public void setSeguro_social(double seguro_social) {
        this.seguro_social = seguro_social;
    }

    public double getSeguro_educativo() {
        return seguro_educativo;
    }

    public void setSeguro_educativo(double seguro_educativo) {
        this.seguro_educativo = seguro_educativo;
    }

    public double getSalario_bruto() {
        return salario_bruto;
    }

    public void setSalario_bruto(double salario_bruto) {
        this.salario_bruto = salario_bruto;
    }

    public double getSalario_neto() {
        return salario_neto;
    }

    public void setSalario_neto(double salario_neto) {
        this.salario_neto = salario_neto;
    }

    public String getFecha_creacion() {
        return fecha_creacion;
    }

    public void setFecha_creacion(String fecha_creacion) {
        this.fecha_creacion = fecha_creacion;
    }

    public double getTotal_salario_bruto() {
        return total_salario_bruto;
    }

    public void setTotal_salario_bruto(double total_salario_bruto) {
        this.total_salario_bruto = total_salario_bruto;
    }

    public double getTotal_salario_neto() {
        return total_salario_neto;
    }

    public void setTotal_salario_neto(double total_salario_neto) {
        this.total_salario_neto = total_salario_neto;
    }

    public ArrayList<MostrarDetallePlanilla> mostrar_detalle_planillas(int idPlanilla) {
        try {
            ArrayList<MostrarDetallePlanilla> lista_detalle_planillas = new ArrayList<>();
            PreparedStatement ps = Conexion.getConnection().prepareCall("{ CALL sp_select_tbl_detalle_planilla(?) }");
            ps.setInt(1, idPlanilla); // Pasar el valor de idPlanilla al procedimiento almacenado
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int id_detalle_planilla = rs.getInt(1);
                int id_planilla = rs.getInt(2);
                String cedula_empleado = rs.getString(3);
                String nombre_empleado = rs.getString(4);
                String apellido_empleado = rs.getString(5);
                double horas_trabajadas = rs.getDouble(6);
                double salario_por_hora = rs.getDouble(7);
                double seguro_social = rs.getDouble(8);
                double seguro_educativo = rs.getDouble(9);
                double salario_bruto = rs.getDouble(10);
                double salario_neto = rs.getDouble(11);
                String fecha_generacion = rs.getString(12);
               
                MostrarDetallePlanilla obj_detalle_planilla = new MostrarDetallePlanilla(
                        id_detalle_planilla,
                        id_planilla,
                        cedula_empleado,
                        nombre_empleado,
                        apellido_empleado,
                        horas_trabajadas,
                        salario_por_hora,
                        seguro_social,
                        seguro_educativo,
                        salario_bruto,
                        salario_neto,
                        fecha_generacion,
                        total_salario_bruto,
                        total_salario_neto
                );

                lista_detalle_planillas.add(obj_detalle_planilla);
            }
            return lista_detalle_planillas;
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
            return null;
        }
    }
/*public double obtenerTotalSalarioNeto(int idPlanilla) {
    try {
        double totalSalarioNeto = 0;
        // Llamar al procedimiento almacenado para obtener el total del salario neto
        PreparedStatement ps = Conexion.getConnection().prepareCall("{ CALL sp_obtener_total_salario_neto_tbl_detalle_planilla(?) }");
        ps.setInt(1, idPlanilla); // Pasar el valor de idPlanilla como parámetro
       
        ResultSet rs = ps.executeQuery();
        // Obtener el total del salario neto del conjunto de resultados
        if (rs.next()) {
            totalSalarioNeto = rs.getDouble(1);
        }
        System.out.println(totalSalarioNeto);
        return totalSalarioNeto;
    } catch (SQLException e) {
        System.out.println("Error: " + e.getMessage());
        return 0;
    }
}*/




}
