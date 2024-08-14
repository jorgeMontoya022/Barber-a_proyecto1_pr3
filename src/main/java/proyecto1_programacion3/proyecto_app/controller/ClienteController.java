package proyecto1_programacion3.proyecto_app.controller;

import proyecto1_programacion3.proyecto_app.factory.ModelFactory;
import proyecto1_programacion3.proyecto_app.model.Cliente;

import java.util.List;

public class ClienteController {
    ModelFactory modelFactory;

    public ClienteController() {
        modelFactory = ModelFactory.getInstance();
    }

    public List<Cliente> getListaClientes() {
        return modelFactory.getListaClientes();
    }

    public boolean crearCliente(Cliente cliente) {
        return modelFactory.crearCliente(cliente);
    }
}
