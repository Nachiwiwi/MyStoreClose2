package modelo;

import java.io.Serializable;

public class Oferta implements Serializable {


    //private String precioOferta;
    // private Date fechaInico;
    // private Date fechaTermino;
    private String tiempoRestante;
    private String id;
    private String minimarketOferta;




    private int idRelacion;
    private int idProducto;
    private String nombreProducto;
    private String nombreLocal;
    private String precioOriginal;
    private String precioOferta;

    // Constructor

    public Oferta(String precioOferta,String tiempoRestante,String id) {
        this.precioOferta = precioOferta;
        this.tiempoRestante = tiempoRestante;
        this.id = id;
    }
    public Oferta(int idRelacion, int idProducto, String nombreProducto, String nombreLocal, String precioOriginal, String precioOferta){
        this.idRelacion = idRelacion;
        this.idProducto = idProducto;
        this.nombreProducto = nombreProducto;
        this.nombreLocal = nombreLocal;
        this.precioOriginal = precioOriginal;
        this.precioOferta = precioOferta;
    }
    // Setters y Getters


    public String getMinimarketOferta() {
        return minimarketOferta;
    }

    public void setMinimarketOferta(String minimarketOferta) {
        this.minimarketOferta = minimarketOferta;
    }

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    public String getNombreLocal() {
        return nombreLocal;
    }

    public void setNombreLocal(String nombreLocal) {
        this.nombreLocal = nombreLocal;
    }

    public String getPrecioOriginal() {
        return precioOriginal;
    }

    public void setPrecioOriginal(String precioOriginal) {
        this.precioOriginal = precioOriginal;
    }
    public int getIdRelacion() {
        return idRelacion;
    }

    public void setIdRelacion(int idRelacion) {
        this.idRelacion = idRelacion;
    }
    public String getPrecioOferta() {
        return precioOferta;
    }

    public void setPrecioOferta(String precioOferta) {
        this.precioOferta = precioOferta;
    }

    public String getTiempoRestante() {
        return tiempoRestante;
    }

    public void setTiempoRestante(String tiempoRestante) {
        this.tiempoRestante = tiempoRestante;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


}

