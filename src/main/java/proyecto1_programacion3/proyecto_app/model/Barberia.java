package proyecto1_programacion3.proyecto_app.model;

import proyecto1_programacion3.proyecto_app.services.IGestionCliente;

import java.util.ArrayList;
import java.util.List;

public class Barberia implements IGestionCliente {
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
        if(clienteEncontrado == null) {
            getListaClientes().add(cliente);
            return true;
        }
        return false;
    }

    private Cliente buscarCliente(String cedula) {
        for (Cliente cliente: getListaClientes()) {
            if(cliente.getCedula().equalsIgnoreCase(cedula)) {
                return cliente;
            }
        }
        return null;
    }

    @Override
    public boolean eliminarCliente(Cliente clienteSeleccionado) {
        if(clienteSeleccionado != null){
            int index = getListaClientes().indexOf(clienteSeleccionado);
            if (index !=-1) {
                getListaClientes().remove(index);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean actualizarCliente(Cliente clienteSeleccionado, Cliente clienteActualizado) {
        int index = getListaClientes().indexOf(clienteSeleccionado);
        if(index!=-1){
            getListaClientes().set(index, clienteActualizado);
            return true;
        }
        return false;
    }
}
