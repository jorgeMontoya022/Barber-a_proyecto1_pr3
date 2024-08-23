package proyecto1_programacion3.proyecto_app.model.services;

import proyecto1_programacion3.proyecto_app.model.Barbero;

public interface IGestionBarbero {
    boolean crearBarbero(Barbero barbero);
    boolean eliminarBarbero(Barbero barberoSeleccionado);
    boolean actualizarBarbero(Barbero barberoSeleccionado, Barbero barberoActualizado);
}
