package proyecto1_programacion3.proyecto_app.factory;

import proyecto1_programacion3.proyecto_app.model.Barberia;
import proyecto1_programacion3.proyecto_app.model.Barbero;
import proyecto1_programacion3.proyecto_app.model.Cliente;
import proyecto1_programacion3.proyecto_app.model.builder.BarberoBuilder;
import proyecto1_programacion3.proyecto_app.model.builder.ClienteBuilder;

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
        Barbero barbero1 = new BarberoBuilder()
                .nombre("Felipe Monrroy")
                .cedula("45789100")
                .build();

        Cliente cliente1 = new ClienteBuilder()
                .nombre("Juan Luis Aguirre")
                .cedula("15467537")
                .correo("juanli86@gmail.com")
                .telefono("315-123-4567")
                .barbero(barbero1)
                .build();
        barbero1.setCliente(cliente1);

        Barbero barbero2 = new BarberoBuilder()
                .nombre("Andrés Pérez")
                .cedula("49827365")
                .build();
        Cliente cliente2 = new ClienteBuilder()
                .nombre("Carlos Gómez")
                .cedula("76492034")
                .correo("carlosgomez@example.com")
                .telefono("300-765-4321")
                .barbero(barbero2)
                .build();
        barbero2.setCliente(cliente2);

        Barbero barbero3 = new BarberoBuilder()
                .nombre("Jorge Martínez")
                .cedula("37582910")
                .build();
        Cliente cliente3 = new ClienteBuilder()
                .nombre("Pedro José Martínez")
                .cedula("23894716")
                .correo("pedromartinez@example.com")
                .telefono("317-890-1234")
                .barbero(barbero3)
                .build();
        barbero3.setCliente(cliente3);

        Barbero barbero4 = new BarberoBuilder()
                .nombre("Miguel Ángel Martínez")
                .cedula("59237418")
                .build();
        Cliente cliente4 = new ClienteBuilder()
                .nombre("Sergio Moreno")
                .cedula("19283746")
                .correo("sergiomoreno@example.com")
                .telefono("311-234-5678")
                .barbero(barbero4)
                .build();
        barbero4.setCliente(cliente4);

        Barbero barbero5 = new BarberoBuilder()
                .nombre("Ricardo Gómez")
                .cedula("48392017")
                .build();
        Cliente cliente5 = new ClienteBuilder()
                .nombre("Natalia Rodríguez")
                .cedula("47382910")
                .correo("natalia.rodriguez@example.com")
                .telefono("312-345-6789")
                .barbero(barbero5)
                .build();
        barbero5.setCliente(cliente5);

        Barbero barbero6 = new BarberoBuilder()
                .nombre("Luis Fernando Sánchez")
                .cedula("49283710")
                .build();
        Cliente cliente6 = new ClienteBuilder()
                .nombre("Luis Fernando Gómez")
                .cedula("98374521")
                .correo("luis.gomez@example.com")
                .telefono("318-765-4321")
                .barbero(barbero6)
                .build();
        barbero6.setCliente(cliente6);

        Barbero barbero7 = new BarberoBuilder()
                .nombre("Antonio Torres")
                .cedula("58392047")
                .build();
        Cliente cliente7 = new ClienteBuilder()
                .nombre("Jorge Salazar")
                .cedula("28475639")
                .correo("jorge.salazar@example.com")
                .telefono("315-678-9012")
                .barbero(barbero7)
                .build();
        barbero7.setCliente(cliente7);

        Barbero barbero8 = new BarberoBuilder()
                .nombre("David Guzmán")
                .cedula("49384720")
                .build();
        Cliente cliente8 = new ClienteBuilder()
                .nombre("Andrés Morales")
                .cedula("73829164")
                .correo("andres.morales@example.com")
                .telefono("300-456-7890")
                .barbero(barbero8)
                .build();
        barbero8.setCliente(cliente8);

        Barbero barbero9 = new BarberoBuilder()
                .nombre("Tomás Díaz")
                .cedula("49385672")
                .build();
        Cliente cliente9 = new ClienteBuilder()
                .nombre("Daniel Arévalo")
                .cedula("91827364")
                .correo("daniel.arevalo@example.com")
                .telefono("317-890-5678")
                .barbero(barbero9)
                .build();
        barbero9.setCliente(cliente9);

        Barbero barbero10 = new BarberoBuilder()
                .nombre("Víctor Gómez")
                .cedula("49382765")
                .build();
        Cliente cliente10 = new ClienteBuilder()
                .nombre("Felipe Silva")
                .cedula("62738491")
                .correo("felipe.silva@example.com")
                .telefono("311-789-0123")
                .barbero(barbero10)
                .build();
        barbero10.setCliente(cliente10);

        barberia.getListaClientes().add(cliente1);
        barberia.getListaClientes().add(cliente2);
        barberia.getListaClientes().add(cliente3);
        barberia.getListaClientes().add(cliente4);
        barberia.getListaClientes().add(cliente5);
        barberia.getListaClientes().add(cliente6);
        barberia.getListaClientes().add(cliente7);
        barberia.getListaClientes().add(cliente8);
        barberia.getListaClientes().add(cliente9);
        barberia.getListaClientes().add(cliente10);

    }



    public List<Cliente> getListaClientes() {
        return barberia.getListaClientes();
    }

    public boolean crearCliente(Cliente cliente) {
      return barberia.crearCliente(cliente);
    }

    public boolean eliminarCliente(Cliente clienteSeleccionado) {
        return barberia.eliminarCliente(clienteSeleccionado);
    }

    public boolean actualizarCliente(Cliente clienteSeleccionado, Cliente clienteActualizado) {
        return barberia.actualizarCliente(clienteSeleccionado, clienteActualizado);
    }
}
