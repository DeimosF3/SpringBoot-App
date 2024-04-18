package com.ilerna.clientes.entity;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

public class Producto {
    private int id; // Autoincremental, no incluido en el constructor
    private String nombre;
    private String precio;
  

    public Producto() {
    }

    public Producto(String nombre, String precio) {
        this.nombre = nombre;
        this.precio = precio;
    }

    public Producto(int id, String nombre, String precio) {
        this.id = id;
        this.nombre = nombre;
        this.precio = precio;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPrecio() {
        return precio;
    }

    public void setPrecio(String apellido) {
        this.precio = precio;
    }



    @Override
    public String toString() {

        return String.format("%-4d %-20s %-20s",
                id,
                nombre,
                precio);
    }
}


