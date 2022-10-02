package modelo;

import modelo.Oferta;

public class Producto {
    //private imagen ???
    //se utilizara public de forma provicional
    private String nombre;
    private String precio;
    private Oferta oferta;
    private String id;

    public Producto(String nombre, String precio, String oferta) {
        this.nombre = nombre;
        this.precio = precio;
        this.oferta = null;
        this.id = id;
    }


}


