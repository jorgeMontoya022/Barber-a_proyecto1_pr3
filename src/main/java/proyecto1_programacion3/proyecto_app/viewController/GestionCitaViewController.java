package proyecto1_programacion3.proyecto_app.viewController;

import java.net.URL;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Locale;
import java.util.ResourceBundle;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import proyecto1_programacion3.proyecto_app.controller.GestionCitaController;
import proyecto1_programacion3.proyecto_app.dto.GestionCitaDto;
import proyecto1_programacion3.proyecto_app.model.Barbero;
import proyecto1_programacion3.proyecto_app.model.Cliente;
import proyecto1_programacion3.proyecto_app.model.GestionCita;
import proyecto1_programacion3.proyecto_app.model.TipoServicio;

public class GestionCitaViewController {
    GestionCitaController gestionCitaController;
    ObservableList<GestionCitaDto> listaGestionCitas = FXCollections.observableArrayList();
    FilteredList<GestionCitaDto> gestionCitaFilteredList;
    GestionCitaDto gestionCitaSeleccionada;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private DatePicker DpFechaCita;

    @FXML
    private ComboBox<Barbero> cbBarbero;

    @FXML
    private ComboBox<Cliente> cbCliente;

    @FXML
    private ComboBox<TipoServicio> cbTipoCita;

    @FXML
    private Button onActualizarCliente;

    @FXML
    private Button onAgendarCita;


    @FXML
    private Button onEliminarCliente;

    @FXML
    private Button onLimpiar;

    @FXML
    private TableView<GestionCitaDto> tableCitas;

    @FXML
    private TableColumn<GestionCitaDto, String> tcFecha;

    @FXML
    private TableColumn<GestionCitaDto, String> tcHora;

    @FXML
    private TableColumn<GestionCitaDto, String> tcNombreBarbero;

    @FXML
    private TableColumn<GestionCitaDto, String> tcNombreCliente;

    @FXML
    private TableColumn<GestionCitaDto, String> tcTelefono;

    @FXML
    private TableColumn<GestionCitaDto, String> tcTipoCita;

    @FXML
    private TableColumn<GestionCitaDto, String> tcValor;

    @FXML
    private TextField txtHoraCita;


    @FXML
    void onActualizar(ActionEvent event) {

    }

    @FXML
    void onAgendarCita(ActionEvent event) {
        agendarCita();


    }


    @FXML
    void onEliminar(ActionEvent event) {

    }

    @FXML
    void onLimpiarDatos(ActionEvent event) {
        limpiarDatos();

    }

    @FXML
    void initialize() {
        gestionCitaController = new GestionCitaController();
        initView();
        cargarDatos();

        cbTipoCita.valueProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                double valor = newValue.getValor();
            }
        });


    }


    private void initView() {
        initDataBinding();
        getListaGestionCitas();
        tableCitas.getItems().clear();
        tableCitas.setItems(listaGestionCitas);
        listenerSelection();
    }


    private void initDataBinding() {
        tcNombreCliente.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().nombreCliente()));
        tcTelefono.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().telefonoCliente()));
        tcNombreBarbero.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().nombreBarbero()));
        tcTipoCita.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().tipoCita()));
        tcValor.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().valor()));
        tcFecha.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().fechaCita()));
        tcHora.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().horaCita()));

    }

    private void getListaGestionCitas() {
        listaGestionCitas.addAll(gestionCitaController.getListaGestionCitas());
    }

    private void listenerSelection() {
        tableCitas.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            gestionCitaSeleccionada = newSelection;
            mostrarInformacion(gestionCitaSeleccionada);
        });
    }

    private void mostrarInformacion(GestionCitaDto gestionCitaSeleccionada) {
        if (gestionCitaSeleccionada != null) {
            cbCliente.setItems(FXCollections.observableArrayList(gestionCitaController.getListaClientes()));
            cbBarbero.setItems(FXCollections.observableArrayList(gestionCitaController.getListaBarberos()));
            cbTipoCita.setItems(FXCollections.observableArrayList(TipoServicio.values()));
            DpFechaCita.setValue(LocalDate.parse(gestionCitaSeleccionada.fechaCita()));
            txtHoraCita.setText(gestionCitaSeleccionada.horaCita());

            // Selección del cliente
            cbCliente.getSelectionModel().select(
                    cbCliente.getItems().stream()
                            .filter(cliente -> cliente.getNombre().equals(gestionCitaSeleccionada.nombreCliente()))
                            .findFirst()
                            .orElse(null)
            );


            // Selección del barbero
            cbBarbero.getSelectionModel().select(
                    cbBarbero.getItems().stream()
                            .filter(barbero -> barbero.getNombre().equals(gestionCitaSeleccionada.nombreBarbero()))
                            .findFirst()
                            .orElse(null)
            );

            // Selección del tipo de cita
            cbTipoCita.getSelectionModel().select(
                    cbTipoCita.getItems().stream()
                            .filter(tipoServicio -> tipoServicio.name().equals(gestionCitaSeleccionada.tipoCita()))
                            .findFirst()
                            .orElse(null)
            );

        }
    }

    private void cargarDatos() {
        cbCliente.setItems(FXCollections.observableArrayList(gestionCitaController.getListaClientes()));
        cbBarbero.setItems(FXCollections.observableArrayList(gestionCitaController.getListaBarberos()));
        cbTipoCita.setItems(FXCollections.observableArrayList(TipoServicio.values()));
    }

    private void agendarCita() {
        if (validarFormulario()) {
            GestionCita nuevaCita = buildDataGestionCita();
            boolean suceso = gestionCitaController.agregarCita(nuevaCita);
            if (suceso) {
                mostrarMensaje("Éxito", "Cita Agregada", "La cita se ha agregado exitosamente.", Alert.AlertType.INFORMATION);
                limpiarDatos();
                refrescarTabla();
            } else {
                mostrarMensaje("Error", "Cita no Agregada", "Esa hora ya está agendada", Alert.AlertType.ERROR);
            }
        } else {
            mostrarMensaje("Error", "Datos invalidos", "Por favor complete todos los campos requeridos con datos válidos.", Alert.AlertType.ERROR);
        }
    }

    private GestionCita buildDataGestionCita() {
        Cliente cliente = cbCliente.getSelectionModel().getSelectedItem();
        Barbero barbero = cbBarbero.getSelectionModel().getSelectedItem();
        TipoServicio tipoServicio = cbTipoCita.getSelectionModel().getSelectedItem();
        LocalDate fechaCita = DpFechaCita.getValue();
        LocalTime horaCita = LocalTime.parse(txtHoraCita.getText());
        double valorCita = tipoServicio != null ? tipoServicio.getValor() : 0.0;
        return new GestionCita(barbero, cliente, tipoServicio, fechaCita, horaCita, valorCita);


    }

    private boolean validarFormulario() {
        if (cbCliente.getSelectionModel().getSelectedItem() == null || cbBarbero.getSelectionModel().getSelectedItem() == null ||
                cbTipoCita.getSelectionModel().getSelectedItem() == null || txtHoraCita.getText().isEmpty() || DpFechaCita.getValue() == null) {
            return false;
        }
        return true;
    }

    private void refrescarTabla() {
        listaGestionCitas.clear();
        tableCitas.setItems(listaGestionCitas);
        getListaGestionCitas();
        cargarDatos();
    }

    private void limpiarDatos() {
        cbTipoCita.getSelectionModel().clearSelection();
        cbBarbero.getSelectionModel().clearSelection();
        cbCliente.getSelectionModel().clearSelection();
        DpFechaCita.setValue(null);
        txtHoraCita.setText("");
    }

    private void mostrarMensaje(String title, String header, String content, Alert.AlertType alertType) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }


}
