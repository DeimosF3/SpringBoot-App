package com.ilerna.clientes.service;
import com.ilerna.clientes.database.Conexion;
import com.ilerna.clientes.entity.Cliente;
import com.ilerna.clientes.entity.Producto;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class GestorProductos {
    Conexion c = new Conexion();
    public void alta(Producto e) throws SQLException {
        Statement consulta = c.conectar().createStatement();
        // Conversión de Date a String. tratamiento de la fecha para que sea aceptada por MySQL
        //SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        //String strFechaFormateada = sdf.format(p.getFechaNacimiento());
        // estring de inserción
        String cadena = "INSERT INTO producto(nombre, precio) VALUES ('"
                + e.getNombre() + "','"
                + e.getPrecio() + "');";
        //System.out.println(cadena);
        consulta.executeUpdate(cadena);
        consulta.close();
    }
    public List<Producto> listar() throws SQLException {
        Statement consulta = c.conectar().createStatement();
        ResultSet rs = consulta.executeQuery("SELECT * FROM producto");
        List<Producto> lista = new ArrayList<>();

        while (rs.next()) {
     Producto e = new Producto(
                    rs.getInt("id"),
                    rs.getString("nombre"),
                    rs.getString("precio")
            );
            lista.add(e);
        }
        rs.close();
        consulta.close();
        return lista;
    }
    public Producto buscar(int id) throws SQLException {
        Statement consulta = c.conectar().createStatement();
        ResultSet rs = consulta.executeQuery("SELECT * FROM producto WHERE id = " + id);
        Producto e = null;
        if (rs.next()) {
            e = new Producto(
                    rs.getInt("id"),
                    rs.getString("nombre"),
                    rs.getString("precio")

            );
        }
        rs.close();
        consulta.close();
        return e;
    }
    public void modificar(Producto p) throws SQLException {
        Statement consulta = c.conectar().createStatement();
        // Conversión de Date a String. tratamiento de la fecha para que sea aceptada por MySQL
        //SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        //String strFechaFormateada = sdf.format(p.getFechaNacimiento());
        // String de actualización
        String cadena = "UPDATE producto SET "
                + "nombre = '" + p.getNombre() + "', "
                + "precio = '" + p.getPrecio() + "', "
                + "WHERE id = " + p.getId();
        // System.out.println(cadena);
        consulta.executeUpdate(cadena);
        consulta.close();
    }
    public void eliminar(int id) throws SQLException {
        Statement consulta = c.conectar().createStatement();
        consulta.executeUpdate("DELETE FROM producto WHERE id = " + id);
        consulta.close();
    }


}
