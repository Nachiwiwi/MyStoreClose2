package modelo;

import java.io.Serializable;

public class Direccion implements Serializable {

    double latitud;
    double longitud;

    public Direccion(double latitud, double longitud){
        this.latitud = latitud;
        this.longitud = longitud;

    }


    public double getLatitud() {
        return latitud;
    }

    public void setLatitud(double latitud) {
        this.latitud = latitud;
    }

    public double getLongitud() {
        return longitud;
    }

    public void setLongitud(double longitud) {
        this.longitud = longitud;
    }


    public String getDistancia() {
        return "123";
    }
}
