package proyecto1_programacion3.proyecto_app.builder;

import proyecto1_programacion3.proyecto_app.model.Barbero;
import proyecto1_programacion3.proyecto_app.model.Cliente;
import proyecto1_programacion3.proyecto_app.builder.services.IBuilder;

public class BarberoBuilder implements IBuilder {

    private String nombre;
    private String cedula;

    private Cliente cliente;

    public BarberoBuilder nombre(String nombre){
        this.nombre = nombre;
        return this;
    }

    public BarberoBuilder cedula(String cedula) {
        this.cedula = cedula;
        return this;
    }

    public BarberoBuilder cliente(Cliente cliente) {
        this.cliente = cliente;
        return this;
    }
    @Override
    public Barbero build() {
        return new Barbero(nombre, cedula, cliente);
    }
}
