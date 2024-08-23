package proyecto1_programacion3.proyecto_app.viewController;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import proyecto1_programacion3.proyecto_app.controller.BarberoController;
import proyecto1_programacion3.proyecto_app.model.Barbero;
import proyecto1_programacion3.proyecto_app.builder.BarberoBuilder;

public class BarberoViewController {
    BarberoController barberoController;
    ObservableList<Barbero> listaBarberos = FXCollections.observableArrayList();
    FilteredList<Barbero> filteredListBarbero;
    Barbero barberoSeleccionado;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableView<Barbero> tablaBarbero;

    @FXML
    private TableColumn<Barbero, String> tcCedulaBarbero;

    @FXML
    private TableColumn<Barbero, String> tcNombreBarbero;

    @FXML
    private TextField txtCedulaBarbero;

    @FXML
    private TextField txtFilterBarbero;

    @FXML
    private TextField txtNombreBarbero;

    @FXML
    void onActualizar(ActionEvent event) {
        actualizarBarbero();

    }


    @FXML
    void onAgregar(ActionEvent event) {
        agregarBarbero();

    }


    @FXML
    void onEliminar(ActionEvent event) {
        eliminarBarbero(barberoSeleccionado);
    }


    @FXML
    void onLimpiar(ActionEvent event) {
        limpiarDatos();

    }


    @FXML
    void initialize() {
        barberoController = new BarberoController();
        initView();
        setupFilter();

    }


    /* Configura la vista inicial, estableciendo la tabla con los datos y añadiendo los listeners necesarios. */
    private void initView() {
        initDataBinding(); /* Vincula los datos de las columnas de la tabla con las propiedades del modelo de barbero. */
        getListaBarberos(); /* Obtiene la lista de barberos del controlador para poblar la tabla. */
        tablaBarbero.getItems().clear(); /* Limpia cualquier dato previo en la tabla, asegurando que se muestra la lista actualizada. */
        tablaBarbero.setItems(filteredListBarbero); /* Asigna la lista filtrada a la tabla para que se muestre en la UI. */
        listenerSelection(); /* Configura un listener para gestionar la selección de elementos en la tabla. */
    }


    /* Vincula los datos de las columnas de la tabla a las propiedades del modelo.
          Esto permite que las columnas de la tabla reflejen automáticamente los valores de las propiedades del modelo. */
    private void initDataBinding() {
        tcNombreBarbero.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getNombre()));
        tcCedulaBarbero.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getCedula()));
    }

    /* Obtiene la lista de barberos desde el controlador y la asigna a la lista observable.
          Esto asegura que la tabla muestre los datos más actualizados disponibles en el controlador. */
    private void getListaBarberos() {
        listaBarberos.addAll(barberoController.getListaBarberos());
        filteredListBarbero = new FilteredList<>(listaBarberos, p -> true);
    }

    /* Configura el filtro para la lista de barberos en la tabla basado en el texto ingresado.
       Permite que la tabla muestre solo los barberos que coincidan con el texto de búsqueda. */
    private void setupFilter() {
        txtFilterBarbero.textProperty().addListener((observable, oldValue, newValue) -> {
            List<Barbero> originalList = barberoController.getListaBarberos();
            ObservableList<Barbero> filteredList = filtrarList(originalList, newValue);
            tablaBarbero.setItems(filteredList);
        });
    }

    /* Filtra la lista de barberos según el texto de búsqueda.
       Devuelve una lista filtrada de barberos que coinciden con el criterio de búsqueda. */
    private ObservableList<Barbero> filtrarList(List<Barbero> list, String searchText) {
        List<Barbero> filteredList = new ArrayList<>();
        for (Barbero barbero : list) {
            if (buscarBarbero(barbero, searchText)) filteredList.add(barbero);
        }
        return FXCollections.observableList(filteredList);
    }

    /* Busca barberos que coincidan con el texto de búsqueda en su cédula.
       Este método es útil para filtrar la lista de barberos en la tabla. */
    private boolean buscarBarbero(Barbero barbero, String searchText) {
        return (barbero.getCedula().toLowerCase().contains(searchText.toLowerCase()));
    }

    /* Configura el listener para actualizar la vista cuando se selecciona un barbero en la tabla.
       Esto permite que los campos de texto muestren la información del barbero seleccionado. */
    private void listenerSelection() {
        tablaBarbero.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            barberoSeleccionado = newSelection;
            mostrarInformacion(barberoSeleccionado);
        });
    }

    /* Muestra la información del barbero seleccionado en los campos de texto.
          Esto facilita la edición y visualización de los datos del barbero seleccionado. */
    private void mostrarInformacion(Barbero barberoSeleccionado) {
        if (barberoSeleccionado != null) {
            txtNombreBarbero.setText(barberoSeleccionado.getNombre());
            txtCedulaBarbero.setText(barberoSeleccionado.getCedula());
        }
    }

    private void agregarBarbero() {
        if (validarFormulario()) {
            Barbero barbero = buildDataBarbero();
            if (barberoController.crearBarbero(barbero)) {
                listaBarberos.add(barbero);
                mostrarMensaje("Notificación Barbero", "Barbero creado", "El barbero ha sido agregado con éxito", Alert.AlertType.INFORMATION);
                limpiarDatos(); /* Limpia los campos de texto después de agregar un barbero. */
                deseleccionarBarbero(); /* Deselecciona cualquier barbero en la tabla para evitar confusiones. */
            } else {
                mostrarMensaje("Error", "Creación fallida", "No se pudo crear el barbero.", Alert.AlertType.ERROR);
            }
        } else {
            mostrarMensaje("Error", "Datos inválidos", "Por favor, ingrese datos válidos", Alert.AlertType.WARNING);
        }
    }

    private void eliminarBarbero(Barbero barberoSeleccionado) {
        if (barberoSeleccionado != null) {
            if (mostrarMensajeConfirmacion("¿Está seguro de eliminar este barbero?")) {
                if (barberoController.eliminarBarbero(barberoSeleccionado)) {
                    int index = listaBarberos.indexOf(barberoSeleccionado);
                    listaBarberos.remove(index);
                    mostrarMensaje("Notificación", "Cliente eliminado", "El cliente ha sido eliminado con éxito", Alert.AlertType.INFORMATION);
                    limpiarDatos();
                    deseleccionarBarbero();
                } else {
                    mostrarMensaje("Error", "Eliminación fallida", "No se pudo eliminar el barbero.", Alert.AlertType.ERROR);
                }
            }
        } else {
            mostrarMensaje("Advertencia", "Selección requerida", "Debe seleccionar un barbero para eliminar.", Alert.AlertType.WARNING);
        }
    }


    private void actualizarBarbero() {
        if (barberoSeleccionado != null) {
            Barbero barberoActualizado = buildDataBarbero();
            boolean resultado = barberoController.actualizarBarbero(barberoSeleccionado, barberoActualizado);
            if (resultado) {
                actualizarListaBarbero(barberoSeleccionado, barberoActualizado);
                mostrarMensaje("Notificación Barbero", "Barbero actualizado", "El barbero ha sido actualizado con éxito", Alert.AlertType.INFORMATION);
                limpiarDatos(); /* Limpia los campos de texto después de actualizar un barbero. */
            } else {
                mostrarMensaje("Error", "Actualización fallida", "No se pudo actualizar el barbero.", Alert.AlertType.ERROR);
            }
        } else {
            mostrarMensaje("Error", "Selección requerida", "Debe seleccionar un barbero para actualizar.", Alert.AlertType.WARNING);
        }
    }

    /* Actualiza la lista observable de barberos después de una actualización.
       Esto asegura que la tabla refleje la información más actualizada. */
    private void actualizarListaBarbero(Barbero barberoSeleccionado, Barbero barberoActualizado) {
        int index = listaBarberos.indexOf(barberoSeleccionado);
        if (index != -1) {
            listaBarberos.set(index, barberoActualizado);
        }
    }

    /* Muestra un cuadro de diálogo de confirmación y devuelve el resultado.
      Utilizado para confirmar acciones destructivas como la eliminación de un barbero. */
    private boolean mostrarMensajeConfirmacion(String message) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText(null);
        alert.setTitle("Confirmación");
        alert.setContentText(message);
        Optional<ButtonType> action = alert.showAndWait();
        return action.get() == ButtonType.OK;
    }


    /* Muestra un mensaje con el tipo de alerta especificado.
        Proporciona retroalimentación al usuario sobre el resultado de las acciones (éxito, error, advertencia). */
    private void mostrarMensaje(String title, String header, String content, Alert.AlertType alertType) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }

    /* Construye un objeto Barbero con los datos ingresados en los campos de texto.
       Se utiliza para crear o actualizar un barbero en la base de datos. */
    private Barbero buildDataBarbero() {
        return new BarberoBuilder()
                .nombre(txtNombreBarbero.getText())
                .cedula(txtCedulaBarbero.getText())
                .build();
    }

    /* Valida los datos ingresados en el formulario.
        Verifica que los campos no estén vacíos antes de agregar o actualizar un barbero. */
    private boolean validarFormulario() {
        return !txtNombreBarbero.getText().isEmpty() && !txtCedulaBarbero.getText().isEmpty();
    }


    /* Deselecciona cualquier barbero en la tabla.
        Esto es útil para evitar que se muestre información de un barbero previamente seleccionado después de realizar una acción. */
    private void deseleccionarBarbero() {
        tablaBarbero.getSelectionModel().clearSelection();
    }


    /* Limpia los campos de entrada de texto.
       Restablece los campos para permitir la entrada de nuevos datos. */
    private void limpiarDatos() {
        txtNombreBarbero.clear();
        txtCedulaBarbero.clear();
    }

}