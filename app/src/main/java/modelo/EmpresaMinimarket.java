package modelo;

public class EmpresaMinimarket {
    private String nombreEmpresa;
    private String nombreMinimarket;
    private String id;
    private Direccion direccion;
    private String rutEmpresa;
    private String contraseña;
    private String corrreo;
    private String longitud;
    private String latitud;

    public EmpresaMinimarket(String nombreEmpresa, String nombreMinimarket, String id, Direccion direccion, String rutEmpresa, String contraseña, String corrreo, String longitud, String latitud) {
        this.nombreEmpresa = nombreEmpresa;
        this.nombreMinimarket = nombreMinimarket;
        this.id = id;
        this.direccion = direccion;
        this.rutEmpresa = rutEmpresa;
        this.contraseña = contraseña;
        this.corrreo = corrreo;
        this.longitud = longitud;
        this.latitud = latitud;
    }

    public EmpresaMinimarket(String nombreEmpresa, String nombreMinimarket,String rutEmpresa,String direccion,String correo){
        this.nombreEmpresa = nombreEmpresa;
        this.nombreMinimarket = nombreMinimarket;
        this.rutEmpresa = rutEmpresa;
        this.longitud = direccion; //cambiar
        this.corrreo = corrreo;
    }
    public String getNombreEmpresa() {
        return nombreEmpresa;
    }

    public void setNombreEmpresa(String nombreEmpresa) {
        this.nombreEmpresa = nombreEmpresa;
    }

    public String getNombreMinimarket() {
        return nombreMinimarket;
    }

    public void setNombreMinimarket(String nombreMinimarket) {
        this.nombreMinimarket = nombreMinimarket;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRutEmpresa() {
        return rutEmpresa;
    }

    public void setRutEmpresa(String rutEmpresa) {
        this.rutEmpresa = rutEmpresa;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public String getCorrreo() {
        return corrreo;
    }

    public void setCorrreo(String corrreo) {
        this.corrreo = corrreo;
    }

    public String getLongitud() {
        return longitud;
    }

    public void setLongitud(String longitud) {
        this.longitud = longitud;
    }

    public String getLatitud() {
        return latitud;
    }

    public void setLatitud(String latitud) {
        this.latitud = latitud;
    }





    //private Direccion direccion;

}
