package proyecto1_programacion3.proyecto_app.controller;

import proyecto1_programacion3.proyecto_app.factory.ModelFactory;
import proyecto1_programacion3.proyecto_app.model.Barbero;

import java.util.List;

public class BarberoController {
    ModelFactory modelFactory;

    public BarberoController() {
        modelFactory = ModelFactory.getInstance();
    }

    public List<Barbero> getListaBarberos() {
        return modelFactory.getListaBarberos();
    }

    public boolean crearBarbero(Barbero barbero) {
        return modelFactory.crearBarbero(barbero);
    }

    public boolean eliminarBarbero(Barbero barberoSeleccionado) {
        return modelFactory.eliminarBarbero(barberoSeleccionado);
    }

    public boolean actualizarBarbero(Barbero barberoSeleccionado, Barbero barberoActualizado) {
        return modelFactory.actualizarBarbero(barberoSeleccionado, barberoActualizado);
    }
}
