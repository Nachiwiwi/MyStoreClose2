package modelo;

public class Oferta {


    private String precioOferta;
    // private Date fechaInico;
    // private Date fechaTermino;
    private String tiempoRestante;
    private String id;

    // Constructor

    public Oferta(String precioOferta,String tiempoRestante,String id) {
        this.precioOferta = precioOferta;
        this.tiempoRestante = tiempoRestante;
        this.id = id;
    }

    // Setters y Getters

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

