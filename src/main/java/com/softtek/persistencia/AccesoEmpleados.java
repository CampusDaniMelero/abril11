package com.softtek.persistencia;

import com.softtek.modelo.Empleado;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AccesoEmpleados extends Conexion{

    private Connection conexion;

    public AccesoEmpleados() {
        Conexion con = new Conexion();
        try {
            con.abrirConexion();
            conexion = con.getMiConexion();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    // Método para crear un nuevo empleado
    public List<Empleado> crearEmpleado() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Ingrese los datos del nuevo empleado:");
        System.out.print("ID del empleado: ");
        int idEmpleado = scanner.nextInt();
        scanner.nextLine(); // Limpiar el buffer
        System.out.print("Nombre del empleado: ");
        String nombre = scanner.nextLine();
        System.out.print("Apellidos del empleado: ");
        String apellidos = scanner.nextLine();

        int jefe;
        do {
            System.out.print("ID del jefe (2 o 5): ");
            jefe = scanner.nextInt();
            // Verificar si el jefe es 2 o 5
            if (jefe != 2 && jefe != 5) {
                System.out.println("El ID del jefe debe ser 2 o 5. Intente de nuevo.");
            }
        } while (jefe != 2 && jefe != 5);

        try {
            String sql = "INSERT INTO employees (employee_id, first_name, last_name, reports_to) VALUES (?, ?, ?, ?)";
            PreparedStatement statement = conexion.prepareStatement(sql);
            statement.setInt(1, idEmpleado);
            statement.setString(2, nombre);
            statement.setString(3, apellidos);
            statement.setInt(4, jefe);
            statement.executeUpdate();
            statement.close();
            System.out.println("Empleado insertado correctamente.");

            // Obtener la lista actualizada de empleados después de la inserción
            List<Empleado> empleados = obtenerTodosLosEmpleados();
            return empleados;
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Error al insertar el empleado.");
        } finally {
            scanner.close();
        }
        return null; // En caso de error, devuelve null
    }
    // Método para obtener todos los empleados
    public List<Empleado> obtenerTodosLosEmpleados() {
        List<Empleado> empleados = new ArrayList<>();
        String sql = "SELECT * FROM employees";
        try {
            PreparedStatement statement = conexion.prepareStatement(sql);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                int idEmpleado = result.getInt("employee_id");
                String nombre = result.getString("first_name");
                String apellidos = result.getString("last_name");
                int jefe = result.getInt("reports_to");
                Empleado empleado = new Empleado(idEmpleado, nombre, apellidos, jefe);
                empleados.add(empleado);
            }
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return empleados;
    }

    // Otros métodos para actualizar y eliminar empleados

    // No necesitas cerrar la conexión aquí si ya tienes una clase que maneja la apertura y cierre de la conexión.

}