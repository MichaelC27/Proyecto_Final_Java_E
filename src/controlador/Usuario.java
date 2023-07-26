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
import javax.swing.JOptionPane;
import java.sql.Types;


/**
 *
 * @author danis
 */
public class Usuario {

    private String cedula;
    private String usuario;
    private String password;
    private String nombre;
    private String apellido;
    private String correo;
    private String direccion;
    private String fecha_creacion;
    private String fecha_modificacion;

    public Usuario() {
    }

    public Usuario(String cedula, String usuario, String password, String nombre, String apellido, String correo, String direccion, String fecha_creacion, String fecha_modificacion) {
        this.cedula = cedula;
        this.usuario = usuario;
        this.password = password;
        this.nombre = nombre;
        this.apellido = apellido;
        this.correo = correo;
        this.direccion = direccion;
        this.fecha_creacion = fecha_creacion;
        this.fecha_modificacion = fecha_modificacion;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
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


    public int insertar() {
    // Verificar si la cédula ya existe
    try {
        CallableStatement csCedula = Conexion.getConnection().prepareCall("{ CALL sp_validar_cedula_tbl_usuario(?, ?) }");
        csCedula.setString(1, this.cedula);
        csCedula.registerOutParameter(2, Types.BIT);
        csCedula.execute();
        int existeCedula = csCedula.getInt(2); // Convertir el valor a entero
        if (existeCedula == 1) {
            // La cédula ya existe, mostrar mensaje de error
            JOptionPane.showMessageDialog(null, "La cédula ya existe. No se puede realizar la inserción.", "Error", JOptionPane.ERROR_MESSAGE);
            return 0;
        }
    } catch (SQLException e) {
        System.out.println("Error: " + e.getMessage());
        return 0;
    }

    // Verificar si el usuario ya existe
    try {
        CallableStatement csUsuario = Conexion.getConnection().prepareCall("{ CALL sp_validar_userid_tbl_usuario(?, ?) }");
        csUsuario.setString(1, this.usuario);
        csUsuario.registerOutParameter(2, Types.BIT);
        csUsuario.execute();
        int existeUsuario = csUsuario.getInt(2); // Convertir el valor a entero
        if (existeUsuario == 1) {
            // El usuario ya existe, mostrar mensaje de error
            JOptionPane.showMessageDialog(null, "El usuario ya existe. No se puede realizar la inserción.", "Error", JOptionPane.ERROR_MESSAGE);
            return 0;
        }
    } catch (SQLException e) {
        System.out.println("Error: " + e.getMessage());
        return 0;
    }

    // Si la cédula y el usuario no existen, proceder con la inserción
    try {
        PreparedStatement ps = Conexion.getConnection().prepareStatement("CALL sp_insert_tbl_usuario(?,?,?,?,?,?)");
        ps.setString(1, this.cedula);
        ps.setString(2, this.usuario);
        ps.setString(3, this.password = Base64.getEncoder().encodeToString(this.password.getBytes()));
        ps.setString(4, this.nombre);
        ps.setString(5, this.apellido);
        ps.setString(6, this.direccion);

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
    public Usuario buscar() {
        try {
            PreparedStatement ps = Conexion.getConnection().prepareStatement("call sp_select_tbl_usuario_por_cedula(?)");
            ps.setString(1, this.cedula);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                this.cedula = rs.getString(1);
                this.usuario = rs.getString(2);
                this.password = rs.getString(3);
                this.nombre = rs.getString(4);
                this.apellido = rs.getString(5);
                this.direccion = rs.getString(6);
                return this;
            }
            return null;
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
            return null;
        }
    }

    //METODO PARA MODIFICAR
    public int modificar() {
        try {
            PreparedStatement ps = Conexion.getConnection().prepareStatement("call sp_update_tbl_usuario(?,?,?,?,?,?)");
            ps.setString(1, this.cedula + "");
            ps.setString(2, this.usuario);
            ps.setString(3, this.password=Base64.getEncoder().encodeToString(this.password.getBytes()));
            ps.setString(4, this.nombre);
            ps.setString(5, this.apellido);
            ps.setString(6, this.direccion);

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

    //METODO PARA ELIMINAR
    public int eliminar() {
        try {
            PreparedStatement ps = Conexion.getConnection().prepareStatement("CALL sp_delete_tbl_usuario(?)");
            ps.setString(1, this.cedula);

            int filasAfectadas = ps.executeUpdate();
            System.out.println(filasAfectadas);
            return filasAfectadas;
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
            return 0;
        }
    }
    
public ArrayList<Usuario> mostrar_usuarios() {
    try {
        ArrayList<Usuario> lista_usuarios = new ArrayList<>();
        PreparedStatement ps = Conexion.getConnection().prepareCall("{ CALL sp_select_tbl_usuarios() }");
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            String cedula = rs.getString(1);
            String usuario = rs.getString(2);
            String password = rs.getString(3);
            String nombre = rs.getString(4);
            String apellido = rs.getString(5);
            String correo = rs.getString(6);
            String direccion = rs.getString(7);
            String fecha_creacion = rs.getString(8);

            Usuario usuarioObj = new Usuario(cedula, usuario, password, nombre, apellido, correo, direccion, fecha_creacion, fecha_modificacion);
            lista_usuarios.add(usuarioObj);
        }
        return lista_usuarios;
    } catch (SQLException e) {
        System.out.println("Error: " + e.getMessage());
        return null;
    }
}

}
