package proyecto1_programacion3.proyecto_app.controller;

import proyecto1_programacion3.proyecto_app.dto.GestionCitaDto;
import proyecto1_programacion3.proyecto_app.factory.ModelFactory;
import proyecto1_programacion3.proyecto_app.model.Barbero;
import proyecto1_programacion3.proyecto_app.model.Cliente;
import proyecto1_programacion3.proyecto_app.model.GestionCita;

import java.util.List;

public class GestionCitaController {
    ModelFactory modelFactory;

    public GestionCitaController(){
        modelFactory = ModelFactory.getInstance();
    }

    public List<GestionCitaDto> getListaGestionCitas() {
        return modelFactory.getListaGestionCitas();

    }

    public List<Cliente> getListaClientes() {
        return modelFactory.getListaClientes();
    }

    public List<Barbero> getListaBarberos() {
        return modelFactory.getListaBarberos();
    }

    public boolean agregarCita(GestionCita nuevaCita) {
        return modelFactory.agregarCita(nuevaCita);
    }

    public boolean eliminarCita(GestionCita gestionCita) {
        return modelFactory.eliminarCita(gestionCita);
    }

    public boolean actualizarCita(GestionCita gestionCita, GestionCita gestionCitaActualizada) {
        return modelFactory.actualizarCita(gestionCita, gestionCitaActualizada);
    }
}
