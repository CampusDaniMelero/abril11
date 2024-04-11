package com.softtek.stream;

import com.softtek.modelo.Empleado;
import com.softtek.persistencia.AccesoEmpleados;

import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class mainCRUD {
    //Vamos a establecer el CRUD
    public static void main(String[] args) {
        AccesoEmpleados accesoEmpleados = new AccesoEmpleados();
        Scanner scanner = new Scanner(System.in);

        int opcion;
        do {
            System.out.println("Menú:");
            System.out.println("1. Leer todos los empleados");
            System.out.println("2. Crear un nuevo empleado");
            System.out.println("3. Actualizar un empleado");
            System.out.println("4. Eliminar un empleado");
            System.out.println("5. Salir");
            System.out.print("Elija una opción: ");

            try {
                opcion = scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Error: entrada no válida. Ingrese un número válido.");
                scanner.nextLine(); // Limpiar la entrada no válida
                opcion = 0; // O cualquier otro valor que desees para indicar una opción no válida
            }

            switch (opcion) {
                case 1:
                    // Leer todos los empleados
                    List<Empleado> empleados = accesoEmpleados.obtenerTodosLosEmpleados();
                    System.out.println("Lista de empleados:");
                    for (Empleado empleado : empleados) {
                        System.out.println(empleado);
                        accesoEmpleados.cerrarConexion(); // Cerrar la conexión al finalizar el programa

                    }
                    break;
                case 2:
                    List<Empleado> addEmpleados = accesoEmpleados.crearEmpleado();
                    if (addEmpleados != null) {
                        System.out.println("Empleado insertado correctamente.");
                        System.out.println("Lista de empleados:");
                        for (Empleado empleado : addEmpleados) {
                            System.out.println(empleado);
                            accesoEmpleados.cerrarConexion(); // Cerrar la conexión al finalizar el programa

                        }
                    }
                    break;
                case 3:
                    // Implementar la actualización de un empleado
                    accesoEmpleados.cerrarConexion(); // Cerrar la conexión al finalizar el programa

                    break;
                case 4:
                    // Implementar la eliminación de un empleado
                    accesoEmpleados.cerrarConexion(); // Cerrar la conexión al finalizar el programa

                    break;
                case 5:
                    System.out.println("Saliendo...");
                    accesoEmpleados.cerrarConexion(); // Cerrar la conexión al finalizar el programa

                    break;
                default:
                    System.out.println("Opción no válida. Intente de nuevo.");
                    break;
            }
        } while (opcion != 5);

        scanner.close(); // Cerrar el Scanner al salir del bucle del menú
    }
}