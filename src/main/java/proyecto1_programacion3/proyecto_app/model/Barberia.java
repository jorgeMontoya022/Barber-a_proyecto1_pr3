package proyecto1_programacion3.proyecto_app.model;

import java.util.ArrayList;
import java.util.List;

public class Barberia {
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
}
