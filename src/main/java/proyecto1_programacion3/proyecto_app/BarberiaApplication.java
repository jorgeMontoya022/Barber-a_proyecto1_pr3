package proyecto1_programacion3.proyecto_app;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class BarberiaApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(BarberiaApplication.class.getResource("Barberia.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("BARBERÍA VIBRAS");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}