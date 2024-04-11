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
            System.out.println("3. Editar un empleado");
            System.out.println("4. Eliminar un empleado");
            System.out.println("5. Salir");
            System.out.print("Elija una opción: ");

            try {
                opcion = scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Error: entrada no válida. Ingrese un número válido.");
                scanner.nextLine(); // Limpiar la entrada no válida
                opcion = 0; // O cualquier otro valor que desees para indicar una opción no válida
                continue; // Continuar con la siguiente iteración del bucle
            }

            switch (opcion) {
                case 1:
                    // Leer todos los empleados
                    List<Empleado> empleados = accesoEmpleados.obtenerTodosLosEmpleados();
                    System.out.println("Lista de empleados:");
                    for (Empleado empleado : empleados) {
                        System.out.println(empleado);
                    }
                    break;
                case 2:
                    List<Empleado> addEmpleados = accesoEmpleados.crearEmpleado();
                    if (addEmpleados != null) {
                        System.out.println("Empleado insertado correctamente:");
                        System.out.println(addEmpleados.get(addEmpleados.size() - 1)); // Mostrar el último empleado insertado
                    }
                    break;
                case 3:
                    // Editar un empleado
                    empleados = accesoEmpleados.obtenerTodosLosEmpleados();
                    System.out.println("Lista de empleados:");
                    for (int i = 0; i < empleados.size(); i++) {
                        System.out.println((i + 1) + ". " + empleados.get(i));
                    }
                    System.out.print("Seleccione el número del empleado que desea editar: ");
                    int indiceEmpleado = scanner.nextInt();
                    if (indiceEmpleado >= 1 && indiceEmpleado <= empleados.size()) {
                        Empleado empleadoAEditar = empleados.get(indiceEmpleado - 1);
                        System.out.println("Empleado seleccionado para editar: " + empleadoAEditar);
                        System.out.println("Ingrese los nuevos detalles para el empleado:");

                        // Solicitar al usuario los nuevos detalles para el empleado
                        System.out.print("Nuevo ID del empleado: ");
                        int nuevoIdEmpleado = scanner.nextInt();
                        scanner.nextLine(); // Limpiar el buffer
                        System.out.print("Nuevo nombre del empleado: ");
                        String nuevoNombre = scanner.nextLine();
                        System.out.print("Nuevos apellidos del empleado: ");
                        String nuevosApellidos = scanner.nextLine();

                        // Actualizar los detalles del empleado en la base de datos
                        empleadoAEditar.setIdEmpleado(nuevoIdEmpleado);
                        empleadoAEditar.setNombre(nuevoNombre);
                        empleadoAEditar.setApellidos(nuevosApellidos);
                        accesoEmpleados.actualizarEmpleado(empleadoAEditar);
                        System.out.println("Empleado actualizado correctamente.");
                    } else {
                        System.out.println("Número de empleado no válido. Intente de nuevo.");
                    }
                    break;
                case 4:
                    // Eliminar un empleado
                    List<Empleado> empleadosEliminar = accesoEmpleados.obtenerTodosLosEmpleados();
                    System.out.println("Lista de empleados:");
                    for (int i = 0; i < empleadosEliminar.size(); i++) {
                        System.out.println((i + 1) + ". " + empleadosEliminar.get(i));
                    }
                    System.out.print("Seleccione el número del empleado que desea eliminar: ");
                    int indiceEmpleadoEliminar = scanner.nextInt();
                    if (indiceEmpleadoEliminar >= 1 && indiceEmpleadoEliminar <= empleadosEliminar.size()) {
                        Empleado empleadoAEliminar = empleadosEliminar.get(indiceEmpleadoEliminar - 1);
                        System.out.println("Empleado seleccionado para eliminar: " + empleadoAEliminar);
                        System.out.println("¿Está seguro de que desea eliminar este empleado? (s/n)");
                        String confirmacion = scanner.next();
                        if (confirmacion.equalsIgnoreCase("s")) {
                            accesoEmpleados.eliminarEmpleado(empleadoAEliminar);
                            System.out.println("Empleado eliminado correctamente.");
                        } else {
                            System.out.println("Operación cancelada.");
                        }
                    } else {
                        System.out.println("Número de empleado no válido. Intente de nuevo.");
                    }
                    break;
                case 5:
                    System.out.println("Saliendo...");
                    break;
                default:
                    System.out.println("Opción no válida. Intente de nuevo.");
                    break;
            }

            // Cerrar la conexión, el Scanner y salir del programa después de ejecutar una acción
            accesoEmpleados.cerrarConexion();
            scanner.close();
            break; // Salir del bucle después de ejecutar una acción

        } while (opcion != 5);
    }
}
