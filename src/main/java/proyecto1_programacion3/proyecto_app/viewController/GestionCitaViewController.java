package proyecto1_programacion3.proyecto_app.viewController;

import java.net.URL;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Locale;
import java.util.Optional;
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

    /**
     * Maneja el evento de clic en el botón de actualizar.
     * Llama al método para actualizar la cita seleccionada.
     */
    @FXML
    void onActualizar(ActionEvent event) {
        actualizarCita();
    }

    /**
     * Maneja el evento de clic en el botón de agendar cita.
     * Llama al método para agregar una nueva cita.
     */
    @FXML
    void onAgendarCita(ActionEvent event) {
        agendarCita();
    }

    /**
     * Maneja el evento de clic en el botón de eliminar cita.
     * Llama al método para eliminar la cita seleccionada.
     */
    @FXML
    void onEliminar(ActionEvent event) {
        eliminarGestionCita();
    }

    /**
     * Maneja el evento de clic en el botón de limpiar.
     * Llama al método para limpiar los campos de entrada.
     */
    @FXML
    void onLimpiarDatos(ActionEvent event) {
        limpiarDatos();
    }

    /**
     * Inicializa el controlador de la vista de gestión de citas.
     * Configura la vista y carga los datos necesarios para los campos.
     */
    @FXML
    void initialize() {
        gestionCitaController = new GestionCitaController();
        initView();
        cargarDatos();

        // Configura el listener para el ComboBox de tipo de cita para obtener el valor del servicio.
        cbTipoCita.valueProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                double valor = newValue.getValor();
            }
        });
    }

    /**
     * Inicializa la vista configurando el enlace de datos y cargando la lista de citas.
     * Configura la tabla y el comportamiento de selección.
     */
    private void initView() {
        initDataBinding();
        getListaGestionCitas();
        tableCitas.getItems().clear();
        tableCitas.setItems(listaGestionCitas);
        listenerSelection();
    }

    /**
     * Configura el enlace de datos para las columnas de la tabla de gestión de citas.
     * Asocia las propiedades del DTO de gestión de citas con las columnas de la tabla.
     */
    private void initDataBinding() {
        tcNombreCliente.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().nombreCliente()));
        tcTelefono.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().telefonoCliente()));
        tcNombreBarbero.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().nombreBarbero()));
        tcTipoCita.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().tipoCita()));
        tcValor.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().valor()));
        tcFecha.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().fechaCita()));
        tcHora.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().horaCita()));
    }

    /**
     * Obtiene la lista de citas de la base de datos y la agrega a la lista observable.
     */
    private void getListaGestionCitas() {
        listaGestionCitas.addAll(gestionCitaController.getListaGestionCitas());
    }

    /**
     * Configura el oyente para el modelo de selección en la tabla de citas.
     * Muestra la información de la cita seleccionada en los campos de entrada.
     */
    private void listenerSelection() {
        tableCitas.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            gestionCitaSeleccionada = newSelection;
            mostrarInformacion(gestionCitaSeleccionada);
        });
    }

    /**
     * Muestra la información de la cita seleccionada en los campos de entrada.
     * @param gestionCitaSeleccionada Cita cuyo detalle se mostrará.
     */
    private void mostrarInformacion(GestionCitaDto gestionCitaSeleccionada) {
        if (gestionCitaSeleccionada != null) {
            // Carga los datos en los ComboBoxes y otros campos de entrada.
            cbCliente.setItems(FXCollections.observableArrayList(gestionCitaController.getListaClientes()));
            cbBarbero.setItems(FXCollections.observableArrayList(gestionCitaController.getListaBarberos()));
            cbTipoCita.setItems(FXCollections.observableArrayList(TipoServicio.values()));
            DpFechaCita.setValue(LocalDate.parse(gestionCitaSeleccionada.fechaCita()));
            txtHoraCita.setText(gestionCitaSeleccionada.horaCita());

            // Selecciona el cliente en el ComboBox
            cbCliente.getSelectionModel().select(
                    cbCliente.getItems().stream()
                            .filter(cliente -> cliente.getNombre().equals(gestionCitaSeleccionada.nombreCliente()))
                            .findFirst()
                            .orElse(null)
            );

            // Selecciona el barbero en el ComboBox
            cbBarbero.getSelectionModel().select(
                    cbBarbero.getItems().stream()
                            .filter(barbero -> barbero.getNombre().equals(gestionCitaSeleccionada.nombreBarbero()))
                            .findFirst()
                            .orElse(null)
            );

            // Selecciona el tipo de cita en el ComboBox
            cbTipoCita.getSelectionModel().select(
                    cbTipoCita.getItems().stream()
                            .filter(tipoServicio -> tipoServicio.name().equals(gestionCitaSeleccionada.tipoCita()))
                            .findFirst()
                            .orElse(null)
            );
        }
    }

    /**
     * Carga los datos necesarios en los ComboBoxes.
     * Configura los ComboBoxes para clientes, barberos y tipos de cita.
     */
    private void cargarDatos() {
        cbCliente.setItems(FXCollections.observableArrayList(gestionCitaController.getListaClientes()));
        cbBarbero.setItems(FXCollections.observableArrayList(gestionCitaController.getListaBarberos()));
        cbTipoCita.setItems(FXCollections.observableArrayList(TipoServicio.values()));
    }

    /**
     * Agrega una nueva cita si el formulario es válido.
     * Muestra mensajes de éxito o error según el resultado de la operación.
     */
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

    /**
     * Elimina la cita seleccionada si hay una seleccionada y el usuario confirma la eliminación.
     * Muestra mensajes de éxito o error según el resultado de la operación.
     */
    private void eliminarGestionCita() {
        if (gestionCitaSeleccionada != null) {
            boolean confirmacion = mostrarMensajeConfirmacion("¿Está seguro de eliminar la cita?");
            if (confirmacion) {
                GestionCita gestionCita = buildDataGestionCita();
                boolean suceso = gestionCitaController.eliminarCita(gestionCita);
                if (suceso) {
                    listaGestionCitas.remove(gestionCitaSeleccionada);
                    mostrarMensaje("Notificación Gestión Cita", "Cita eliminada con éxito", "La cita fue eliminada exitosamente", Alert.AlertType.INFORMATION);
                    limpiarDatos();
                } else {
                    mostrarMensaje("Notificación Gestión Cita", "Error al eliminar", "No se pudo eliminar la cita", Alert.AlertType.ERROR);
                }
            }
        } else {
            mostrarMensaje("Notificación Gestión Cita", "Ninguna cita Seleccionada", "Debe seleccionar una cita para eliminar", Alert.AlertType.WARNING);
        }
    }

    /**
     * Actualiza la cita seleccionada con los nuevos datos si hay una cita seleccionada.
     * Muestra mensajes de éxito o error según el resultado de la operación.
     */
    private void actualizarCita() {
        if (gestionCitaSeleccionada != null) {
            GestionCita gestionCita = gestionCitaSeleccionada.toGestionCita();
            GestionCita gestionCitaActualizada = buildDataGestionCita();
            boolean suceso = gestionCitaController.actualizarCita(gestionCita, gestionCitaActualizada);
            if (suceso) {
                int index = listaGestionCitas.indexOf(gestionCitaSeleccionada);
                if (index != -1) {
                    GestionCitaDto updateDto = GestionCitaDto.fromGestionCita(gestionCitaActualizada);
                    listaGestionCitas.set(index, updateDto);
                    refrescarTabla();
                }
                mostrarMensaje("Notificación Cita", "Cita actualizada", "La cita ha sido actualizada con éxito", Alert.AlertType.INFORMATION);
                limpiarDatos();
            } else {
                mostrarMensaje("Error", "Actualización fallida", "No se pudo actualizar la cita.", Alert.AlertType.ERROR);
            }
        } else {
            mostrarMensaje("Error", "Selección requerida", "Debe seleccionar una cita para actualizar.", Alert.AlertType.WARNING);
        }
    }

    /**
     * Construye un objeto GestionCita a partir de los datos ingresados en los campos de entrada.
     * @return Un nuevo objeto GestionCita con los datos proporcionados.
     */
    private GestionCita buildDataGestionCita() {
        Cliente cliente = cbCliente.getSelectionModel().getSelectedItem();
        Barbero barbero = cbBarbero.getSelectionModel().getSelectedItem();
        TipoServicio tipoServicio = cbTipoCita.getSelectionModel().getSelectedItem();
        LocalDate fechaCita = DpFechaCita.getValue();
        LocalTime horaCita = LocalTime.parse(txtHoraCita.getText());
        double valorCita = tipoServicio != null ? tipoServicio.getValor() : 0.0;
        return new GestionCita(barbero, cliente, tipoServicio, fechaCita, horaCita, valorCita);
    }

    /**
     * Valida que todos los campos del formulario estén completos y correctos.
     * @return true si todos los campos están correctamente completados; de lo contrario, false.
     */
    private boolean validarFormulario() {
        return cbCliente.getSelectionModel().getSelectedItem() != null &&
                cbBarbero.getSelectionModel().getSelectedItem() != null &&
                cbTipoCita.getSelectionModel().getSelectedItem() != null &&
                !txtHoraCita.getText().isEmpty() &&
                DpFechaCita.getValue() != null;
    }

    /**
     * Refresca la tabla de citas y carga los datos actualizados.
     */
    private void refrescarTabla() {
        listaGestionCitas.clear();
        tableCitas.setItems(listaGestionCitas);
        getListaGestionCitas();
        cargarDatos();
    }

    /**
     * Limpia todos los campos de entrada en la vista.
     */
    private void limpiarDatos() {
        cbTipoCita.getSelectionModel().clearSelection();
        cbBarbero.getSelectionModel().clearSelection();
        cbCliente.getSelectionModel().clearSelection();
        DpFechaCita.setValue(null);
        txtHoraCita.setText("");
    }

    /**
     * Muestra un cuadro de diálogo de confirmación y devuelve el resultado.
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
     * @param title Título del mensaje.
     * @param header Encabezado del mensaje.
     * @param content Contenido del mensaje.
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
