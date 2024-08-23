package proyecto1_programacion3.proyecto_app.model.services;

import proyecto1_programacion3.proyecto_app.model.Cliente;

public interface IGestionCliente {
     boolean crearCliente(Cliente cliente);
     boolean eliminarCliente(Cliente cliente);
     boolean actualizarCliente(Cliente clienteSeleccionado, Cliente clienteActualizado);

}
