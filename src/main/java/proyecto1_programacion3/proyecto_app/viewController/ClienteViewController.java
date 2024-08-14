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
import proyecto1_programacion3.proyecto_app.model.builder.ClienteBuilder;

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

    }


    @FXML
    void initialize() {
        clienteController = new ClienteController();
        initView();
        setupFilter();

    }

    private void initView() {
        initDataBinding();
        getListaClientes();
        tableCustomer.getItems().clear();
        tableCustomer.setItems(filteredListCliente);
        listenerSelection();
    }

    private void initDataBinding() {
        tcNombre.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getNombre()));
        tcCedula.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getCedula()));
        tcCorreo.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getCorreo()));
        tcTelefono.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getTelefono()));
    }

    private void getListaClientes() {
        listaClientes.addAll(clienteController.getListaClientes());
        filteredListCliente = new FilteredList<>(listaClientes, p -> true);

    }

    private void setupFilter() {
        txtFilter.textProperty().addListener((observable, oldValue, newValue) -> {
            List<Cliente> originalList = clienteController.getListaClientes();
            ObservableList<Cliente> filteredList = filtrarLista(originalList, newValue);
            tableCustomer.setItems(filteredList);


        });
    }

    private ObservableList<Cliente> filtrarLista(List<Cliente> list, String searchText) {
        List<Cliente> filteredList = new ArrayList<>();
        for (Cliente cliente : list) {
            if (buscarCliente(cliente, searchText)) filteredList.add(cliente);

        }
        return FXCollections.observableList(filteredList);

    }

    private boolean buscarCliente(Cliente cliente, String searchText) {
        return (cliente.getCedula().toLowerCase().contains(searchText.toLowerCase()));
    }

    private void listenerSelection() {
        tableCustomer.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            clienteSeleccionado = newSelection;
            mostrarInformacion(clienteSeleccionado);


        });
    }

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
                mostrarMensaje("Error", "Creación fallida",
                        "No se pudo crear el cliente.", Alert.AlertType.ERROR);
            }
        } else {
            mostrarMensaje("Error", "Datos invalidos",
                    "Por favor, ingrese datos validos", Alert.AlertType.WARNING);
        }
    }

    private void deseleccionarCliente() {
        tableCustomer.getSelectionModel().clearSelection();
        clienteSeleccionado = null;
    }

    private void limpiarDatos() {
        txtNombreCliente.setText("");
        txtCedulaCliente.setText("");
        txtCorreoCliente.setText("");
        txtTelefono.setText("");
    }


    private Cliente buildDataCliente() {
        return new ClienteBuilder()
                .nombre(txtNombreCliente.getText())
                .cedula(txtCedulaCliente.getText())
                .correo(txtCorreoCliente.getText())
                .telefono(txtTelefono.getText())
                .build();
    }

    private boolean validarFormulario() {
        return !txtNombreCliente.getText().isEmpty()
                && !txtCedulaCliente.getText().isEmpty()
                && !txtCorreoCliente.getText().isEmpty()
                && !txtTelefono.getText().isEmpty();
    }

    private boolean mostrarMensajeConfirmacion(String message){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText(null);
        alert.setTitle("Confirmación");
        alert.setContentText(message);
        Optional<ButtonType> action = alert.showAndWait();
        if (action.get() == ButtonType.OK) {
            return true;
        } else {
            return false;
        }
    }

    private void mostrarMensaje(String title, String header, String content, Alert.AlertType alertType) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }



}
