/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author dbpan
 */
public class MostrarPlanilla {

    private int id_planilla;
    private String fecha_creacion;
    private int total_empleados;
    private double total_salario_bruto;
    private double total_seguro_social;
    private double total_salario_neto;

    public MostrarPlanilla() {
    }

    public MostrarPlanilla(int id_planilla, String fecha_creacion, int total_empleados, double total_salario_bruto, double total_seguro_social, double total_salario_neto) {
        this.id_planilla = id_planilla;
        this.fecha_creacion = fecha_creacion;
        this.total_empleados = total_empleados;
        this.total_salario_bruto = total_salario_bruto;
        this.total_seguro_social = total_seguro_social;
        this.total_salario_neto = total_salario_neto;
    }

    public int getId_planilla() {
        return id_planilla;
    }

    public void setId_planilla(int id_planilla) {
        this.id_planilla = id_planilla;
    }

    public String getFecha_creacion() {
        return fecha_creacion;
    }

    public void setFecha_creacion(String fecha_creacion) {
        this.fecha_creacion = fecha_creacion;
    }

    public int getTotal_empleados() {
        return total_empleados;
    }

    public void setTotal_empleados(int total_empleados) {
        this.total_empleados = total_empleados;
    }

    public double getTotal_salario_bruto() {
        return total_salario_bruto;
    }

    public void setTotal_salario_bruto(double total_salario_bruto) {
        this.total_salario_bruto = total_salario_bruto;
    }

    public double getTotal_seguro_social() {
        return total_seguro_social;
    }

    public void setTotal_seguro_social(double total_seguro_social) {
        this.total_seguro_social = total_seguro_social;
    }

    public double getTotal_salario_neto() {
        return total_salario_neto;
    }

    public void setTotal_salario_neto(double total_salario_neto) {
        this.total_salario_neto = total_salario_neto;
    }

    /*
    public ArrayList<MostrarPlanilla> mostrar_planillas() {
        try {
            ArrayList<MostrarPlanilla> lista_planillas = new ArrayList<MostrarPlanilla>();
            PreparedStatement ps = Conexion.getConnection().prepareStatement("call sp_select_tbl_planilla");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int id_planilla = rs.getInt(1);
                String fecha_creacion = rs.getString(2);
                MostrarPlanilla obj_planilla = new MostrarPlanilla(id_planilla, fecha_creacion);
                lista_planillas.add(obj_planilla);
            }
            return lista_planillas;
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
            return null;
        }
    }*/
    public ArrayList<MostrarPlanilla> mostrar_planillas() {
        try {
            ArrayList<MostrarPlanilla> lista_planillas = new ArrayList<>();
            PreparedStatement ps = Conexion.getConnection().prepareCall("{ CALL sp_select_tbl_planilla_inner_tbl_detalle_planilla() }");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int id_planilla = rs.getInt(1);
                String fecha_generacion = rs.getString(2);
                // Obtener los otros campos sumados y contados si los necesitas
                int total_empleados = rs.getInt(3);
                double total_salario_bruto = rs.getDouble(4);
                double total_seguro_social = rs.getDouble(5);
                double total_salario_neto = rs.getDouble(6);

                MostrarPlanilla obj_planilla = new MostrarPlanilla(id_planilla, fecha_generacion, total_empleados, total_salario_bruto, total_seguro_social, total_salario_neto);
                // Puedes establecer los otros campos sumados y contados en el objeto MostrarPlanilla si los necesitas
                lista_planillas.add(obj_planilla);
            }
            return lista_planillas;
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
            return null;
        }
    }

}
