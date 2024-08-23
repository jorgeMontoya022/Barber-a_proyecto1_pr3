package proyecto1_programacion3.proyecto_app.model;

import proyecto1_programacion3.proyecto_app.model.services.IGestionBarbero;
import proyecto1_programacion3.proyecto_app.model.services.IGestionCita;
import proyecto1_programacion3.proyecto_app.model.services.IGestionCliente;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class Barberia implements IGestionCliente, IGestionBarbero, IGestionCita {
    private List<Cliente> listaClientes = new ArrayList<>();
    private List<Barbero> listaBarberos = new ArrayList<>();
    private List<GestionCita> listaGestionCitas = new ArrayList<>();

    public Barberia() {

    }

    public List<Cliente> getListaClientes() {
        return listaClientes;
    }

    public void setListaClientes(List<Cliente> listaClientes) {
        this.listaClientes = listaClientes;
    }

    public List<Barbero> getListaBarberos() {
        return listaBarberos;
    }

    public void setListaBarberos(List<Barbero> listaBarberos) {
        this.listaBarberos = listaBarberos;
    }

    public List<GestionCita> getListaGestionCitas() {
        return listaGestionCitas;
    }

    public void setListaGestionCitas(List<GestionCita> listaGestionCitas) {
        this.listaGestionCitas = listaGestionCitas;
    }

    @Override
    public boolean crearCliente(Cliente cliente) {
        Cliente clienteEncontrado = buscarCliente(cliente.getCedula());
        if (clienteEncontrado == null) {
            getListaClientes().add(cliente);
            return true;
        }
        return false;
    }

    private Cliente buscarCliente(String cedula) {
        for (Cliente cliente : getListaClientes()) {
            if (cliente.getCedula().equalsIgnoreCase(cedula)) {
                return cliente;
            }
        }
        return null;
    }

    @Override
    public boolean eliminarCliente(Cliente clienteSeleccionado) {
        if (clienteSeleccionado != null) {
            int index = getListaClientes().indexOf(clienteSeleccionado);
            if (index != -1) {
                getListaClientes().remove(index);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean actualizarCliente(Cliente clienteSeleccionado, Cliente clienteActualizado) {
        int index = getListaClientes().indexOf(clienteSeleccionado);
        if (index != -1) {
            getListaClientes().set(index, clienteActualizado);
            return true;
        }
        return false;
    }
    @Override
    public boolean agregarCita(GestionCita nuevaCita) {
        if (nuevaCita != null && !citaExiste(nuevaCita.getFechaCita(), nuevaCita.getHoraCita(), nuevaCita.getBarbero())) {
            getListaGestionCitas().add(nuevaCita);
            return true;
        }
        return false;
    }

    private boolean citaExiste(LocalDate fechaCita, LocalTime horaCita, Barbero barbero) {
        for (GestionCita gestionCita : getListaGestionCitas()) {
            if (gestionCita.getFechaCita().equals(fechaCita) && gestionCita.getHoraCita().equals(horaCita) && gestionCita.getBarbero().equals(barbero)) {
                return true;
            }
        }
        return false;
    }
    @Override
    public boolean eliminarCita(GestionCita gestionCita) {
        if (gestionCita != null) {
            for (GestionCita cita : getListaGestionCitas()) {
                if (cita.getCliente().equals(gestionCita.getCliente())) {
                    return true;
                }
            }

        }

        return false;
    }
    @Override
    public boolean actualizarCita(GestionCita gestionCita, GestionCita gestionCitaActualizada) {

        for (int i = 0; i < getListaGestionCitas().size(); i++) {
            GestionCita actualCita = getListaGestionCitas().get(i);
            if (actualCita.getCliente().getNombre().equals(gestionCita.getCliente().getNombre())) {
                getListaGestionCitas().set(i, gestionCitaActualizada);
                return true;
            }
        }
        return false;
    }
   @Override
    public boolean crearBarbero(Barbero barbero) {
        Barbero barberoEncontrado = buscarBarbero(barbero.getCedula());
        if (barberoEncontrado != null) {
            return false;

        } else {
            getListaBarberos().add(barbero);
            return true;
        }
    }

    private Barbero buscarBarbero(String cedula) {
        for (Barbero barbero : getListaBarberos()) {
            if (barbero.getCedula().equalsIgnoreCase(cedula)) {
                return barbero;
            }
        }
        return null;
    }
    @Override
    public boolean eliminarBarbero(Barbero barberoSeleccionado) {
        if (barberoSeleccionado != null) {
            int index = getListaBarberos().indexOf(barberoSeleccionado);
            if (index != -1) {
                getListaBarberos().remove(index);
                return true;
            }
        }
        return false;
    }
    @Override
    public boolean actualizarBarbero(Barbero barberoSeleccionado, Barbero barberoActualizado) {
        int index = getListaBarberos().indexOf(barberoSeleccionado);
        if (index != -1) {
            getListaBarberos().set(index, barberoActualizado);
            return true;
        }
        return false;
    }
}
