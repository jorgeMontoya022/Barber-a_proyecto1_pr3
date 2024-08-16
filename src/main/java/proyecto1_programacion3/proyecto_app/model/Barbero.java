package proyecto1_programacion3.proyecto_app.model;

public class Barbero extends Persona {

    private Cliente cliente;
    public Barbero(String nombre, String cedula, Cliente cliente) {
        super(nombre, cedula);
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    @Override
    public String toString() {
        return getNombre();
    }


}
