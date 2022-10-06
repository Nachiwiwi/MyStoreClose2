package modelo;

import modelo.Oferta;

public class Producto {

    private String nombre;
    private String precio;
    private Oferta oferta;
    private String id;

    public Producto(String nombre, String precio, String oferta) {
        this.nombre = nombre;
        this.precio = precio;
        this.oferta = null;
        this.id = "0";
    }

    public Producto(String nombre, String precio) {
        this.nombre = nombre;
        this.precio = precio;
        this.oferta = null;
        this.id = "0";
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

    public void setPrecio(String precio) {
        this.precio = precio;
    }

    public Oferta getOferta() {
        return oferta;
    }

    public void setOferta(Oferta oferta) {
        this.oferta = oferta;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }





}


