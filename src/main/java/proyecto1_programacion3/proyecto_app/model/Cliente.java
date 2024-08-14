package proyecto1_programacion3.proyecto_app.model;

public class Cliente extends Persona {
    private String correo;
    private String telefono;
    private Barbero barbero;

    public Cliente(String nombre, String cedula) {
        super(nombre, cedula);
    }

    public Cliente(String nombre, String cedula, String correo, String telefono, Barbero barbero) {
        super(nombre, cedula);
        this.correo = correo;
        this.telefono = telefono;
        this.barbero = barbero;
    }


    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public Barbero getBarbero() {
        return barbero;
    }

    public void setBarbero(Barbero barbero) {
        this.barbero = barbero;
    }

    @Override
    public String toString() {
        return getNombre();
    }
}
