package modelo;

public abstract class Usuario {
    private String clave;
    private String correo;

    public Usuario(String clave, String correo){
        this.clave = clave;
        this.correo = correo;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getClave(){
        return this.clave;
    }

    public void setCorreo(String correo){
        this.correo = correo;
    }

    public String getCorreo(){
        return this.correo;
    }

}

