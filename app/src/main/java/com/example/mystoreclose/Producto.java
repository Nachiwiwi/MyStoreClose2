package com.example.mystoreclose;

public class Producto {
    //private imagen ???
    //se utilizara public de forma provicional
    public String nombre;
    public String precio;
    public String precioOferta;

    public Producto(String nombre, String precio, String precioOferta) {
        this.nombre = nombre;
        this.precio = precio;
        this.precioOferta = precioOferta;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}


