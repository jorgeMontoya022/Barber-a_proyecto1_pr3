package proyecto1_programacion3.proyecto_app.factory;

import proyecto1_programacion3.proyecto_app.model.Barberia;
import proyecto1_programacion3.proyecto_app.model.Cliente;

import java.util.List;

public class ModelFactory {
    private static ModelFactory modelFactory;
    private Barberia barberia;

    private ModelFactory(){
        barberia = new Barberia();
        initializeData();
    }

    public static ModelFactory getInstance() {
        if (modelFactory == null) {
            modelFactory = new ModelFactory();
        }
        return modelFactory;
    }

    private void initializeData() {
        initCliente();
    }

    private void initCliente() {
        Cliente cliente1 = new Cliente();
        cliente1.setNombre("Juana Villegas");
        cliente1.setCedula("342254526");
        cliente1.setCorreo("juana@gmail.com");
        cliente1.setTelefono("321-232-3454");

        barberia.getListaClientes().add(cliente1);
    }



    public List<Cliente> getListaClientes() {
        return barberia.getListaClientes();
    }

    public boolean crearCliente(Cliente cliente) {
      return   barberia.crearCliente(cliente);
    }
}
