package proyecto1_programacion3.proyecto_app.model.services;

import proyecto1_programacion3.proyecto_app.model.GestionCita;

public interface IGestionCita {
    boolean agregarCita(GestionCita nuevaCita);
    boolean eliminarCita(GestionCita gestionCita);
    boolean actualizarCita(GestionCita gestionCita, GestionCita gestionCitaActualizada);
}
