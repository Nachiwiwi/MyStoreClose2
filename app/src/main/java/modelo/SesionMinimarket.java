package modelo;

public class SesionMinimarket {
    private String Nombre_local,ContraseñaDueño,MailDueño,Nombredueño,Rut_empresa,Direccion,Nombre_empresa;
    private int IdMarket;
    double Longitud,Latitud;

    public String getNombre_local() {
        return Nombre_local;
    }

    public void setNombre_local(String nombre_local) {
        Nombre_local = nombre_local;
    }

    public String getContraseñaDueño() {
        return ContraseñaDueño;
    }

    public void setContraseñaDueño(String contraseñaDueño) {
        ContraseñaDueño = contraseñaDueño;
    }

    public String getMailDueño() {
        return MailDueño;
    }

    public void setMailDueño(String mailDueño) {
        MailDueño = mailDueño;
    }

    public String getNombredueño() {
        return Nombredueño;
    }

    public void setNombredueño(String nombredueño) {
        Nombredueño = nombredueño;
    }

    public String getRut_empresa() {
        return Rut_empresa;
    }

    public void setRut_empresa(String rut_empresa) {
        Rut_empresa = rut_empresa;
    }

    public String getDireccion() {
        return Direccion;
    }

    public void setDireccion(String direccion) {
        Direccion = direccion;
    }

    public String getNombre_empresa() {
        return Nombre_empresa;
    }

    public void setNombre_empresa(String nombre_empresa) {
        Nombre_empresa = nombre_empresa;
    }

    public int getIdMarket() {
        return IdMarket;
    }

    public void setIdMarket(int idMarket) {
        IdMarket = idMarket;
    }

    public double getLongitud() {
        return Longitud;
    }

    public void setLongitud(double longitud) {
        Longitud = longitud;
    }

    public double getLatitud() {
        return Latitud;
    }

    public void setLatitud(double latitud) {
        Latitud = latitud;
    }
}
