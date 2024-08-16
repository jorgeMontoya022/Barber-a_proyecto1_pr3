package proyecto1_programacion3.proyecto_app.viewController;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import proyecto1_programacion3.proyecto_app.model.Barbero;

public class BarberoViewController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableView<?> tablaBarbero;

    @FXML
    private TableColumn<?, ?> tcCedulaBarbero;

    @FXML
    private TableColumn<?,?> tcNombreBarbero;

    @FXML
    private TextField txtCedulaBarbero;

    @FXML
    private TextField txtFilterBarbero;

    @FXML
    private TextField txtNombreBarbero;

    @FXML
    void onActualizar(ActionEvent event) {

    }

    @FXML
    void onAgregar(ActionEvent event) {

    }

    @FXML
    void onEliminar(ActionEvent event) {

    }

    @FXML
    void onLimpiar(ActionEvent event) {

    }

    @FXML
    void initialize() {

    }

}