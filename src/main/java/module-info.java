module proyecto1_programacion3.proyecto_app {
    requires javafx.controls;
    requires javafx.fxml;


    opens proyecto1_programacion3.proyecto_app to javafx.fxml;
    exports proyecto1_programacion3.proyecto_app;

    opens proyecto1_programacion3.proyecto_app.viewController;
    exports proyecto1_programacion3.proyecto_app.viewController;
}