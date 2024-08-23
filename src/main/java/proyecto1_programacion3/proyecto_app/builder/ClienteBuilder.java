package proyecto1_programacion3.proyecto_app.builder;

import proyecto1_programacion3.proyecto_app.model.Barbero;
import proyecto1_programacion3.proyecto_app.model.Cliente;
import proyecto1_programacion3.proyecto_app.builder.services.IBuilder;

public class ClienteBuilder implements IBuilder {
    private String nombre;
    private String cedula;
    private String correo;
    private String telefono;
    private Barbero barbero;

    public ClienteBuilder nombre(String nombre) {
        this.nombre = nombre;
        return this;
    }

    public ClienteBuilder cedula(String cedula) {
        this.cedula = cedula;
        return this;
    }

    public ClienteBuilder correo(String correo) {
        this.correo = correo;
        return this;
    }

    public ClienteBuilder telefono(String telefono) {
        this.telefono = telefono;
        return this;
    }

    public ClienteBuilder barbero(Barbero barbero) {
        this.barbero = barbero;
        return this;
    }

    @Override
    public Cliente build() {
        return new Cliente(nombre, cedula, correo, telefono, barbero);
    }
}
