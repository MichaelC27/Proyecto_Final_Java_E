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
public class AdicionEmpleadoPlanilla {

   // CrearPlanilla obj_crear = new CrearPlanilla();
    private int id_planilla;
    private String cedula_empleado;
    private double horas_trabajadas;
    private double salario_por_hora;
    private double salario_bruto;
    private double seguro_social;
    private double seguro_educativo;
    private double salario_neto;

    public AdicionEmpleadoPlanilla() {
    }

    public AdicionEmpleadoPlanilla(int id_planilla, String cedula_empleado, double horas_trabajadas, double salario_por_hora, double salario_bruto, double seguro_social, double seguro_educativo, double salario_neto) {
        this.id_planilla = id_planilla;
        this.cedula_empleado = cedula_empleado;
        this.horas_trabajadas = horas_trabajadas;
        this.salario_por_hora = salario_por_hora;
        this.salario_bruto = salario_bruto;
        this.seguro_social = seguro_social;
        this.seguro_educativo = seguro_educativo;
        this.salario_neto = salario_neto;
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

    public double getSalario_bruto() {
        return salario_bruto;
    }

    public void setSalario_bruto(double salario_bruto) {
        this.salario_bruto = salario_bruto;
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

    public double getSalario_neto() {
        return salario_neto;
    }

    public void setSalario_neto(double salario_neto) {
        this.salario_neto = salario_neto;
    }

    // Método para calcular el Salario Bruto
    public double SalarioBruto() {
        salario_bruto = horas_trabajadas * salario_por_hora;
        return salario_bruto;
    }

    // Método para calcular el Seguro Social (SS)
    public double SeguroSocial() {
        seguro_social = salario_bruto * 0.0975; // 9.75%
        return seguro_social;
    }

    // Método para calcular el Seguro Educativo (SE)
    public double SeguroEducativo() {
        seguro_educativo = salario_bruto * 0.0125; // 1.25%
        return seguro_educativo;
    }

    // Método para calcular el Salario Neto
    public double SalarioNeto() {
        salario_neto = salario_bruto - seguro_social - seguro_educativo;
        return salario_neto;
    }

    // Método para obtener el último ID de la tabla tbl_planilla
    public int obtenerUltimoID() {
        int ultimoID=-1;

        try {
            // Establecer la conexión a la base de datos
            PreparedStatement ps = Conexion.getConnection().prepareStatement("CALL sp_select_ultimo_id_tbl_planilla()");

            ResultSet rs = ps.executeQuery();

            // Obtener el resultado del procedimiento almacenado
            if (rs.next()) {
                ultimoID = rs.getInt("ultimo_id");
            }

            // Cerrar el ResultSet y el PreparedStatement
            rs.close();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {

        }

        return ultimoID;
    }
// Elimina la llamada a obtenerUltimoID() y modifica el método insertar para recibir el ID de la planilla


    public int insertar() {
        int id =obtenerUltimoID();
        System.out.println("EL Id "+this.id_planilla);
        try {
            PreparedStatement ps = Conexion.getConnection().prepareStatement("CALL sp_insert_tbl_detalle_planilla(?, ?, ?, ?, ?, ?, ?, ?)");
            ps.setInt(1, id); // Usamos el ID de planilla pasado como parámetro
            ps.setString(2, this.cedula_empleado);
            ps.setDouble(3, this.horas_trabajadas);
            ps.setDouble(4, this.salario_por_hora);
            ps.setDouble(5, Math.round(SalarioBruto()));
            ps.setDouble(6, Math.round(SeguroSocial()));
            ps.setDouble(7, Math.round(SeguroEducativo()));
            ps.setDouble(8, Math.round(SalarioNeto()));

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                int id_planilla = rs.getInt("respuesta"); // Obtener el valor de id_planilla desde el ResultSet
                System.out.println("ID de la planilla generada: " + id_planilla);
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
