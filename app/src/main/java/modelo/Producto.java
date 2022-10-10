package modelo;

import java.io.Serializable;

import modelo.Oferta;

public class Producto implements Serializable {

    private String nombre;
    private String precio;
    private Oferta oferta;
    private String descripcion;
    private int id;
    private int idRelacion;


    // Se debe dejar para que la clase AgregarProducto lo utilice
    public Producto(String nombre,  int id) {
        this.nombre = nombre;
        this.precio = "0.0";
        this.oferta = null;
        this.descripcion = "Sin descripcion";
        this.id = id;
        this.idRelacion = -1;
    }

    public Producto(String nombre, String precio, int id, String descripcion, int idRelacion){
        this.nombre = nombre;
        this.precio = precio;
        this.oferta = null;
        this.descripcion = descripcion;
        this.id = id;
        this.idRelacion = idRelacion;


    }


    public String getDescripcion() {
        return this.descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdRelacion() {
        return idRelacion;
    }

    public void setIdRelacion(int idRelacion) {
        this.idRelacion = idRelacion;
    }

    public boolean tieneOferta(){
        if(this.oferta != null) return true;
        return false;
    }

    public String tiempoRestanteOferta(){
        if(tieneOferta()){
            this.oferta.getTiempoRestante();
        }
        return "";
    }

    public void crearOferta(String precioOferta, String tiempoRestante){
        this.oferta = new Oferta(precioOferta,tiempoRestante,"");
    }

    public String precioOferta(){
        if(tieneOferta()){
            return this.oferta.getPrecioOferta();
        }
        return "";
    }







}

