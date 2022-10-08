package modelo;

import java.io.Serializable;

public class Direccion implements Serializable {
    String calle;
    String numero;
    String latitud;
    String longitud;

    public Direccion(){

    }

    public Direccion(String calle, String numero, String latitud, String longitud){
        this.calle = calle;
        this.numero = numero;
        this.latitud = latitud;
        this.longitud = longitud;

    }

    public String getCalle() {
        return calle;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getLatitud() {
        return latitud;
    }

    public void setLatitud(String latitud) {
        this.latitud = latitud;
    }

    public String getLongitud() {
        return longitud;
    }

    public void setLongitud(String longitud) {
        this.longitud = longitud;
    }


}
