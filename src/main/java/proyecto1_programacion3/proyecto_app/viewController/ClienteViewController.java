package proyecto1_programacion3.proyecto_app.viewController;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import proyecto1_programacion3.proyecto_app.controller.ClienteController;
import proyecto1_programacion3.proyecto_app.model.Cliente;
import proyecto1_programacion3.proyecto_app.builder.ClienteBuilder;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

public class ClienteViewController {
    ClienteController clienteController;
    ObservableList<Cliente> listaClientes = FXCollections.observableArrayList();
    FilteredList<Cliente> filteredListCliente;

    Cliente clienteSeleccionado;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableView<Cliente> tableCustomer;

    @FXML
    private TableColumn<Cliente, String> tcCedula;

    @FXML
    private TableColumn<Cliente, String> tcCorreo;

    @FXML
    private TableColumn<Cliente, String> tcNombre;

    @FXML
    private TableColumn<Cliente, String> tcTelefono;

    @FXML
    private TextField txtCedulaCliente;

    @FXML
    private TextField txtCorreoCliente;

    @FXML
    private TextField txtFilter;

    @FXML
    private TextField txtNombreCliente;

    @FXML
    private TextField txtTelefono;
    
    @FXML
    void onActualizar(ActionEvent event) {
        actualizarUsuario();
    }

    @FXML
    void onAgregar(ActionEvent event) {
        agregarUsuario();
    }

    @FXML
    void onLimpiar(ActionEvent event) {
        limpiarDatos();
    }


    @FXML
    void onEliminar(ActionEvent event) {
        eliminarCliente(clienteSeleccionado);
    }

    /**
     * Inicializa el controlador de la vista de cliente.
     * Configura la vista y el filtro para la lista de clientes.
     */
    @FXML
    void initialize() {
        clienteController = new ClienteController();
        initView();
        setupFilter();
    }

    /**
     * Inicializa la vista configurando el enlace de datos y cargando la lista de clientes.
     * Configura la tabla y el comportamiento de selección.
     */
    private void initView() {
        initDataBinding();
        getListaClientes();
        tableCustomer.getItems().clear();
        tableCustomer.setItems(filteredListCliente);
        listenerSelection();
    }

    /**
     * Configura el enlace de datos para las columnas de la tabla de clientes.
     * Asocia las propiedades del modelo de cliente con las columnas de la tabla.
     */
    private void initDataBinding() {
        tcNombre.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getNombre()));
        tcCedula.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getCedula()));
        tcCorreo.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getCorreo()));
        tcTelefono.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getTelefono()));
    }

    /**
     * Obtiene la lista de clientes del controlador y la asigna a la lista observable.
     * Configura la lista filtrada para su uso en la vista.
     */
    private void getListaClientes() {
        listaClientes.addAll(clienteController.getListaClientes());
        filteredListCliente = new FilteredList<>(listaClientes, p -> true);
    }

    /**
     * Configura el filtro para la lista de clientes basado en el texto ingresado en el campo de filtro.
     * Actualiza la tabla con la lista filtrada según el texto de búsqueda.
     */
    private void setupFilter() {
        txtFilter.textProperty().addListener((observable, oldValue, newValue) -> {
            List<Cliente> originalList = clienteController.getListaClientes();
            ObservableList<Cliente> filteredList = filtrarLista(originalList, newValue);
            tableCustomer.setItems(filteredList);
        });
    }

    /**
     * Filtra la lista de clientes basada en el texto de búsqueda.
     *
     * @param list       Lista de clientes a filtrar.
     * @param searchText Texto de búsqueda.
     * @return Lista observable de clientes filtrados.
     */
    private ObservableList<Cliente> filtrarLista(List<Cliente> list, String searchText) {
        List<Cliente> filteredList = new ArrayList<>();
        for (Cliente cliente : list) {
            if (buscarCliente(cliente, searchText)) filteredList.add(cliente);
        }
        return FXCollections.observableList(filteredList);
    }

    /**
     * Verifica si un cliente cumple con el texto de búsqueda.
     *
     * @param cliente    Cliente a verificar.
     * @param searchText Texto de búsqueda.
     * @return true si el cliente cumple con el texto de búsqueda; de lo contrario, false.
     */
    private boolean buscarCliente(Cliente cliente, String searchText) {
        return (cliente.getCedula().toLowerCase().contains(searchText.toLowerCase()));
    }

    /**
     * Configura el oyente para el modelo de selección en la tabla de clientes.
     * Muestra la información del cliente seleccionado en los campos de texto.
     */
    private void listenerSelection() {
        tableCustomer.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            clienteSeleccionado = newSelection;
            mostrarInformacion(clienteSeleccionado);
        });
    }

    /**
     * Muestra la información del cliente seleccionado en los campos de texto.
     *
     * @param clienteSeleccionado Cliente cuyo detalle se mostrará.
     */
    private void mostrarInformacion(Cliente clienteSeleccionado) {
        if (clienteSeleccionado != null) {
            txtNombreCliente.setText(clienteSeleccionado.getNombre());
            txtCedulaCliente.setText(clienteSeleccionado.getCedula());
            txtCorreoCliente.setText(clienteSeleccionado.getCorreo());
            txtTelefono.setText(clienteSeleccionado.getTelefono());
        }
    }

    private void agregarUsuario() {
        if (validarFormulario()) {
            Cliente cliente = buildDataCliente();
            if (clienteController.crearCliente(cliente)) {
                listaClientes.add(cliente);
                mostrarMensaje("Notificación cliente", "Cliente creado", "El cliente ha sido agregado con éxito", Alert.AlertType.INFORMATION);
                limpiarDatos();
                deseleccionarCliente();
            } else {
                mostrarMensaje("Error", "Creación fallida", "No se pudo crear el cliente.", Alert.AlertType.ERROR);
            }
        } else {
            mostrarMensaje("Error", "Datos invalidos", "Por favor, ingrese datos válidos", Alert.AlertType.WARNING);
        }
    }

    private void eliminarCliente(Cliente clienteSeleccionado) {
        if (clienteSeleccionado != null) {
            if (mostrarMensajeConfirmacion("¿Está seguro que desea eliminar este cliente?")) {
                if (clienteController.eliminarCliente(clienteSeleccionado)) {
                    int index = listaClientes.indexOf(clienteSeleccionado);
                    listaClientes.remove(index);
                    mostrarMensaje("Notificación", "Cliente eliminado", "El cliente ha sido eliminado con éxito", Alert.AlertType.INFORMATION);
                    limpiarDatos();
                    deseleccionarCliente();
                } else {
                    mostrarMensaje("Error", "Eliminación fallida", "No se pudo eliminar el cliente.", Alert.AlertType.ERROR);
                }
            }
        } else {
            mostrarMensaje("Advertencia", "Selección requerida", "Debe seleccionar un cliente para eliminar.", Alert.AlertType.WARNING);
        }
    }

    private void actualizarUsuario() {
        if (clienteSeleccionado != null) {
            Cliente clienteActualizado = buildDataCliente();
            boolean resultado = clienteController.actualizarCliente(clienteSeleccionado, clienteActualizado);
            if (resultado) {
                actualizarListaCliente(clienteSeleccionado, clienteActualizado);
                mostrarMensaje("Notificación Cliente", "Cliente actualizado", "El cliente ha sido actualizado con éxito", Alert.AlertType.INFORMATION);
                limpiarDatos();
            } else {
                mostrarMensaje("Error", "Actualización fallida", "No se pudo actualizar el cliente.", Alert.AlertType.ERROR);
            }
        } else {
            mostrarMensaje("Error", "Selección requerida", "Debe seleccionar un cliente para actualizar.", Alert.AlertType.WARNING);
        }
    }

    /**
     * Actualiza la lista observable de clientes después de una actualización.
     *
     * @param clienteSeleccionado Cliente que se va a reemplazar.
     * @param clienteActualizado  Cliente con los nuevos datos.
     */
    private void actualizarListaCliente(Cliente clienteSeleccionado, Cliente clienteActualizado) {
        int index = listaClientes.indexOf(clienteSeleccionado);
        if (index != -1) {
            listaClientes.set(index, clienteActualizado);
        }
    }

    /**
     * Deselecciona el cliente actualmente seleccionado en la tabla.
     * Limpia la selección para evitar mostrar información de un cliente previamente seleccionado.
     */
    private void deseleccionarCliente() {
        tableCustomer.getSelectionModel().clearSelection();
        clienteSeleccionado = null;
    }

    /**
     * Limpia los campos de texto en la vista.
     * Resetea los campos para permitir la entrada de nuevos datos.
     */
    private void limpiarDatos() {
        txtNombreCliente.setText("");
        txtCedulaCliente.setText("");
        txtCorreoCliente.setText("");
        txtTelefono.setText("");
    }

    /**
     * Construye un objeto Cliente con los datos ingresados en los campos de texto.
     *
     * @return Cliente con los datos proporcionados.
     */
    private Cliente buildDataCliente() {
        return new ClienteBuilder()
                .nombre(txtNombreCliente.getText())
                .cedula(txtCedulaCliente.getText())
                .correo(txtCorreoCliente.getText())
                .telefono(txtTelefono.getText())
                .build();
    }

    /**
     * Valida los datos ingresados en el formulario.
     *
     * @return true si todos los campos están llenos; de lo contrario, false.
     */
    private boolean validarFormulario() {
        return !txtNombreCliente.getText().isEmpty()
                && !txtCedulaCliente.getText().isEmpty()
                && !txtCorreoCliente.getText().isEmpty()
                && !txtTelefono.getText().isEmpty();
    }

    /**
     * Muestra un cuadro de diálogo de confirmación y devuelve el resultado.
     *
     * @param message Mensaje a mostrar en el cuadro de diálogo.
     * @return true si el usuario confirma la acción; de lo contrario, false.
     */
    private boolean mostrarMensajeConfirmacion(String message) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText(null);
        alert.setTitle("Confirmación");
        alert.setContentText(message);
        Optional<ButtonType> action = alert.showAndWait();
        return action.get() == ButtonType.OK;
    }

    /**
     * Muestra un mensaje al usuario con el tipo de alerta especificado.
     *
     * @param title     Título del mensaje.
     * @param header    Encabezado del mensaje.
     * @param content   Contenido del mensaje.
     * @param alertType Tipo de alerta (información, error, advertencia).
     */
    private void mostrarMensaje(String title, String header, String content, Alert.AlertType alertType) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }
}
