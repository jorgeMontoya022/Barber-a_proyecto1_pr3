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
import proyecto1_programacion3.proyecto_app.model.builder.BarberoBuilder;

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


    private void initView() {
        initDataBinding();
        getListaBarberos();
        tablaBarbero.getItems().clear();
        tablaBarbero.setItems(filteredListBarbero);
        listenerSelection();
    }


    private void initDataBinding() {
        tcNombreBarbero.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getNombre()));
        tcCedulaBarbero.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getCedula()));
    }

    private void getListaBarberos() {
        listaBarberos.addAll(barberoController.getListaBarberos());
        filteredListBarbero = new FilteredList<>(listaBarberos, p -> true);
    }

    private void setupFilter() {
        txtFilterBarbero.textProperty().addListener((observable, oldValue, newValue) -> {
            List<Barbero> originalList = barberoController.getListaBarberos();
            ObservableList<Barbero> filteredList = filtrarList(originalList, newValue);
            tablaBarbero.setItems(filteredList);
        });
    }

    private ObservableList<Barbero> filtrarList(List<Barbero> list, String sarchText) {
        List<Barbero> filteredList = new ArrayList<>();
        for (Barbero barbero : list) {
            if (buscarBarbero(barbero, sarchText)) filteredList.add(barbero);
            ;
        }
        return FXCollections.observableList(filteredList);
    }

    private boolean buscarBarbero(Barbero barbero, String sarchText) {
        return (barbero.getCedula().toLowerCase().contains(sarchText.toLowerCase()));
    }

    private void listenerSelection() {
        tablaBarbero.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            barberoSeleccionado = newSelection;
            mostrarInformacion(barberoSeleccionado);
        });
    }

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
                limpiarDatos();
                deseleccionarBarbero();

            } else {
                mostrarMensaje("Error", "Creación fallida",
                        "No se pudo crear el barbero.", Alert.AlertType.ERROR);
            }
        }else{
            mostrarMensaje("Error", "Datos invalidos",
                    "Por favor, ingrese datos validos", Alert.AlertType.WARNING);
        }
    }

    private void eliminarBarbero(Barbero barberoSeleccionado) {
        if (barberoSeleccionado != null) {
            if (mostrarMensajeConfirmacion("¿Está seguro de eliminar este barbero?")){
                if(barberoController.eliminarBarbero(barberoSeleccionado)){
                    int index = listaBarberos.indexOf(barberoSeleccionado);
                    listaBarberos.remove(index);
                    mostrarMensaje("Notificación", "Cliente eliminado", "El cliente ha sido eliminado con éxito", Alert.AlertType.INFORMATION);
                    limpiarDatos();
                    deseleccionarBarbero();
                } else {
                    mostrarMensaje("Error", "Eliminación fallida", "No se pudo eliminar el barbero.", Alert.AlertType.ERROR);
                }
            }
        } else{
            mostrarMensaje("Advertencia", "Selección requerida", "Debe seleccionar un barbero para eliminar.", Alert.AlertType.WARNING);
        }
    }

    private void actualizarBarbero() {
        if (barberoSeleccionado !=null) {
            Barbero barberoActualizado = buildDataBarbero();
            boolean resultado = barberoController.actualizarBarbero(barberoSeleccionado, barberoActualizado);
            if (resultado) {
                actualizarListaBarbero(barberoSeleccionado, barberoActualizado);
                mostrarMensaje("Notificación Barbero", "Barbero actualizado",
                        "El barbero ha sido actualizado con éxito", Alert.AlertType.INFORMATION);
                limpiarDatos();
            }else{
                mostrarMensaje("Error", "Actualización fallida",
                        "No se pudo actualizar el barbero.", Alert.AlertType.ERROR);
            }
        }else{
            mostrarMensaje("Error", "Selección requerida",
                    "Debe seleccionar un barbero para actualizar.", Alert.AlertType.WARNING);
        }
    }

    private void actualizarListaBarbero(Barbero barberoSeleccionado, Barbero barberoActualizado) {
        int index = listaBarberos.indexOf(barberoSeleccionado);
        if (index != -1) {
            listaBarberos.set(index, barberoActualizado);
        }

    }

    private boolean mostrarMensajeConfirmacion(String message) {
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
    private Barbero buildDataBarbero() {
        return new BarberoBuilder()
                .nombre(txtNombreBarbero.getText())
                .cedula(txtCedulaBarbero.getText())
                .build();
    }

    private boolean validarFormulario() {
        return !txtNombreBarbero.getText().isEmpty()
                && !txtCedulaBarbero.getText().isEmpty();
    }
    private void deseleccionarBarbero() {
        tablaBarbero.getSelectionModel().clearSelection();
        barberoSeleccionado = null;
    }


    private void limpiarDatos() {
        txtNombreBarbero.setText("");
        txtCedulaBarbero.setText("");
    }

}