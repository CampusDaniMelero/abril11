package com.softtek.modelo;

public class Empleado {
    private int idEmpleado;
    private String Nombre;
    private String Apellidos;
    private int jefe;

    @Override
    public String toString() {
        return "Empleado{" + "idEmpleado=" + idEmpleado + ", Nombre=" + Nombre + ", Apellidos=" + Apellidos + ", jefe=" + jefe + '}';
    }
    public Empleado(){

    }

    public Empleado(int idEmpleado, String Nombre, String Apellidos, int jefe) {
        this.idEmpleado = idEmpleado;
        this.Nombre = Nombre;
        this.Apellidos = Apellidos;
        this.jefe = jefe;
    }

    public int getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(int idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    public String getApellidos() {
        return Apellidos;
    }

    public void setApellidos(String Apellidos) {
        this.Apellidos = Apellidos;
    }

    public int getJefe() {
        return jefe;
    }

    public void setJefe(int jefe) {
        this.jefe = jefe;
    }



}
