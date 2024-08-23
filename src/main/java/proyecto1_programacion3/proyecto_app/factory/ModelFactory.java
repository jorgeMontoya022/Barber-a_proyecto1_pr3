package proyecto1_programacion3.proyecto_app.factory;

import proyecto1_programacion3.proyecto_app.dto.GestionCitaDto;
import proyecto1_programacion3.proyecto_app.model.*;
import proyecto1_programacion3.proyecto_app.builder.BarberoBuilder;
import proyecto1_programacion3.proyecto_app.builder.ClienteBuilder;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class ModelFactory {
    // Instancia única de la fábrica, siguiendo el patrón Singleton
    private static ModelFactory modelFactory;
    private Barberia barberia;

    // Constructor privado para evitar la instanciación externa y garantizar el patrón Singleton
    private ModelFactory() {
        barberia = new Barberia();
        initializeData(); // Inicializa los datos simulados para la barbería
    }

    // Método para obtener la única instancia de la fábrica
    // Esto es necesario para garantizar que solo haya una instancia de ModelFactory en todo el sistema
    public static ModelFactory getInstance() {
        if (modelFactory == null) {
            modelFactory = new ModelFactory();
        }
        return modelFactory;
    }

    // Método privado para inicializar datos de ejemplo
    // Se invoca en el constructor para prellenar la barbería con datos simulados
    private void initializeData() {
        initCliente();
    }

    // Método privado para inicializar clientes y barberos simulados
    // Esto es útil para probar la funcionalidad sin necesidad de un backend completo
    private void initCliente() {
        // Se crean varios barberos y clientes usando el patrón Builder para mayor flexibilidad
        Barbero barbero1 = new BarberoBuilder()
                .nombre("Felipe Monrroy")
                .cedula("45789100")
                .build();

        Cliente cliente1 = new ClienteBuilder()
                .nombre("Juan Luis Aguirre")
                .cedula("15467537")
                .correo("juanli86@gmail.com")
                .telefono("315-123-4567")
                .build();

        Barbero barbero2 = new BarberoBuilder()
                .nombre("Andrés Pérez")
                .cedula("49827365")
                .build();

        Cliente cliente2 = new ClienteBuilder()
                .nombre("Carlos Gómez")
                .cedula("76492034")
                .correo("carlosgomez@example.com")
                .telefono("300-765-4321")
                .build();

        // Se crean más barberos y clientes de manera similar para poblar la lista
        Barbero barbero3 = new BarberoBuilder()
                .nombre("Jorge Martínez")
                .cedula("37582910")
                .build();
        Cliente cliente3 = new ClienteBuilder()
                .nombre("Pedro José Martínez")
                .cedula("23894716")
                .correo("pedromartinez@example.com")
                .telefono("317-890-1234")
                .build();

        Barbero barbero4 = new BarberoBuilder()
                .nombre("Miguel Ángel Martínez")
                .cedula("59237418")
                .build();
        Cliente cliente4 = new ClienteBuilder()
                .nombre("Sergio Moreno")
                .cedula("19283746")
                .correo("sergiomoreno@example.com")
                .telefono("311-234-5678")
                .build();

        Barbero barbero5 = new BarberoBuilder()
                .nombre("Ricardo Gómez")
                .cedula("48392017")
                .build();
        Cliente cliente5 = new ClienteBuilder()
                .nombre("Natalia Rodríguez")
                .cedula("47382910")
                .correo("natalia.rodriguez@example.com")
                .telefono("312-345-6789")
                .build();

        Barbero barbero6 = new BarberoBuilder()
                .nombre("Luis Fernando Sánchez")
                .cedula("49283710")
                .build();
        Cliente cliente6 = new ClienteBuilder()
                .nombre("Luis Fernando Gómez")
                .cedula("98374521")
                .correo("luis.gomez@example.com")
                .telefono("318-765-4321")
                .build();

        Barbero barbero7 = new BarberoBuilder()
                .nombre("Antonio Torres")
                .cedula("58392047")
                .build();
        Cliente cliente7 = new ClienteBuilder()
                .nombre("Jorge Salazar")
                .cedula("28475639")
                .correo("jorge.salazar@example.com")
                .telefono("315-678-9012")
                .build();

        Barbero barbero8 = new BarberoBuilder()
                .nombre("David Guzmán")
                .cedula("49384720")
                .build();
        Cliente cliente8 = new ClienteBuilder()
                .nombre("Andrés Morales")
                .cedula("73829164")
                .correo("andres.morales@example.com")
                .telefono("300-456-7890")
                .build();

        Barbero barbero9 = new BarberoBuilder()
                .nombre("Tomás Díaz")
                .cedula("49385672")
                .build();
        Cliente cliente9 = new ClienteBuilder()
                .nombre("Daniel Arévalo")
                .cedula("91827364")
                .correo("daniel.arevalo@example.com")
                .telefono("317-890-5678")
                .build();

        Barbero barbero10 = new BarberoBuilder()
                .nombre("Víctor Gómez")
                .cedula("49382765")
                .build();

        Cliente cliente10 = new ClienteBuilder()
                .nombre("Felipe Silva")
                .cedula("62738491")
                .correo("felipe.silva@example.com")
                .telefono("311-789-0123")
                .build();

        // Se crean varias citas gestionadas y se les asignan los clientes y barberos creados
        GestionCita gestionCita1 = new GestionCita();
        gestionCita1.setCliente(cliente1);
        gestionCita1.setBarbero(barbero1);
        gestionCita1.setFechaCita(LocalDate.of(2024, 8, 03));
        gestionCita1.setTipoServicio(TipoServicio.BARBA);
        gestionCita1.setValorCita(TipoServicio.BARBA.getValor());
        gestionCita1.setHoraCita(LocalTime.of(03, 20));

        GestionCita gestionCita2 = new GestionCita();
        gestionCita2.setCliente(cliente2);
        gestionCita2.setBarbero(barbero2);
        gestionCita2.setFechaCita(LocalDate.of(2024, 8, 03));
        gestionCita2.setTipoServicio(TipoServicio.CABELLO);
        gestionCita2.setValorCita(TipoServicio.CABELLO.getValor());
        gestionCita2.setHoraCita(LocalTime.of(04, 30));

        GestionCita gestionCita3 = new GestionCita();
        gestionCita3.setCliente(cliente3);
        gestionCita3.setBarbero(barbero3);
        gestionCita3.setFechaCita(LocalDate.of(2024, 8, 05));
        gestionCita3.setTipoServicio(TipoServicio.CABELLO);
        gestionCita3.setValorCita(TipoServicio.CABELLO.getValor());
        gestionCita3.setHoraCita(LocalTime.of(02, 30));

        GestionCita gestionCita4 = new GestionCita();
        gestionCita4.setCliente(cliente4);
        gestionCita4.setBarbero(barbero4);
        gestionCita4.setFechaCita(LocalDate.of(2024, 8, 05));
        gestionCita4.setTipoServicio(TipoServicio.MASCARILLA);
        gestionCita4.setValorCita(TipoServicio.MASCARILLA.getValor());
        gestionCita4.setHoraCita(LocalTime.of(03, 30));

        GestionCita gestionCita5 = new GestionCita();
        gestionCita5.setCliente(cliente5);
        gestionCita5.setBarbero(barbero5);
        gestionCita5.setFechaCita(LocalDate.of(2024, 8, 05));
        gestionCita5.setTipoServicio(TipoServicio.CEJAS);
        gestionCita5.setValorCita(TipoServicio.CEJAS.getValor());
        gestionCita5.setHoraCita(LocalTime.of(05, 30));

        // Se agregan las citas gestionadas a la lista de la barbería
        barberia.getListaGestionCitas().add(gestionCita1);
        barberia.getListaGestionCitas().add(gestionCita2);
        barberia.getListaGestionCitas().add(gestionCita3);
        barberia.getListaGestionCitas().add(gestionCita4);
        barberia.getListaGestionCitas().add(gestionCita5);

        // Se agrega la lista de clientes a la barbería
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

        // Se agrega la lista de barberos a la barbería
        barberia.getListaBarberos().add(barbero1);
        barberia.getListaBarberos().add(barbero2);
        barberia.getListaBarberos().add(barbero3);
        barberia.getListaBarberos().add(barbero4);
        barberia.getListaBarberos().add(barbero5);
        barberia.getListaBarberos().add(barbero6);
        barberia.getListaBarberos().add(barbero7);
        barberia.getListaBarberos().add(barbero8);
        barberia.getListaBarberos().add(barbero9);
        barberia.getListaBarberos().add(barbero10);
    }

    // Método para obtener la lista de clientes
    public List<Cliente> getListaClientes() {
        return barberia.getListaClientes();
    }

    // Método para crear un nuevo cliente en la barbería
    public boolean crearCliente(Cliente cliente) {
        return barberia.crearCliente(cliente);
    }

    // Método para eliminar un cliente de la barbería
    public boolean eliminarCliente(Cliente clienteSeleccionado) {
        return barberia.eliminarCliente(clienteSeleccionado);
    }

    // Método para actualizar los datos de un cliente
    public boolean actualizarCliente(Cliente clienteSeleccionado, Cliente clienteActualizado) {
        return barberia.actualizarCliente(clienteSeleccionado, clienteActualizado);
    }

    // Método para obtener la lista de citas gestionadas, transformadas a DTOs
    public List<GestionCitaDto> getListaGestionCitas() {
        List<GestionCita> listaGestionCitas = barberia.getListaGestionCitas();
        List<GestionCitaDto> listaGestionCitasDto = new ArrayList<>();

        for (GestionCita gestionCita : listaGestionCitas) {
            listaGestionCitasDto.add(buildGestionCitaDto(gestionCita));
        }
        return listaGestionCitasDto;
    }

    // Método privado para convertir una cita gestionada en un DTO
    private GestionCitaDto buildGestionCitaDto(GestionCita gestionCita) {
        return new GestionCitaDto(
                gestionCita.getCliente().getNombre(),
                gestionCita.getCliente().getTelefono(),
                gestionCita.getBarbero().getNombre(),
                gestionCita.getFechaCita().toString(),
                gestionCita.getHoraCita().toString(),
                String.valueOf(gestionCita.getValorCita()),
                gestionCita.getTipoServicio().toString()
        );
    }

    // Método para obtener la lista de barberos
    public List<Barbero> getListaBarberos() {
        return barberia.getListaBarberos();
    }

    // Método para agregar una nueva cita gestionada
    public boolean agregarCita(GestionCita nuevaCita) {
        return barberia.agregarCita(nuevaCita);
    }

    // Método para eliminar una cita gestionada existente
    public boolean eliminarCita(GestionCita gestionCita) {
        return barberia.eliminarCita(gestionCita);
    }

    // Método para actualizar una cita gestionada existente
    public boolean actualizarCita(GestionCita gestionCita, GestionCita gestionCitaActualizada) {
        return barberia.actualizarCita(gestionCita, gestionCitaActualizada);
    }

    // Métodos para la gestión de barberos (crear, eliminar, actualizar)
    public boolean crearBarbero(Barbero barbero) {
        return barberia.crearBarbero(barbero);
    }

    public boolean eliminarBarbero(Barbero barberoSeleccionado) {
        return barberia.eliminarBarbero(barberoSeleccionado);
    }

    public boolean actualizarBarbero(Barbero barberoSeleccionado, Barbero barberoActualizado) {
        return barberia.actualizarBarbero(barberoSeleccionado, barberoActualizado);
    }
}
