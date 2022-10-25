package com.example.uni;

public class Usuario {

    String nombre;
    String apellido;
    String cedula;
    String telefono;
    String rol;
    String tVehiculo;
    String placa;
    String Entrada;
    String Salida;
    String Estado;
    String Admin;

    public Usuario(String nombre, String apellido, String cedula, String telefono, String rol,
                   String tVehiculo, String placa, String Entrada, String Salida, String Estado,
                   String Admin) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.cedula = cedula;
        this.telefono = telefono;
        this.rol = rol;
        this.tVehiculo = tVehiculo;
        this.placa = placa;
        this.Entrada = Entrada;
        this.Salida = Salida;
        this.Estado = Estado;
        this.Admin = Admin;
    }

}
