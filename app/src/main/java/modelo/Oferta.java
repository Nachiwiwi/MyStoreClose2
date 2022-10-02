package modelo;

public class Oferta {
    private String precioOferta;
    // private Date fechaInico;
    // private Date fechaTermino;
    private String tiempoRestante;
    private String id;

    public Oferta(String precioOferta,String tiempoRestante,String id) {
        this.precioOferta = precioOferta;
        this.tiempoRestante = tiempoRestante;
        this.id = id;
    }
}

