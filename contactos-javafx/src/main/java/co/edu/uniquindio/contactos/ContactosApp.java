package co.edu.uniquindio.contactos;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ContactosApp extends Application {

    @Override
    public void start(Stage stage) throws Exception {

        FXMLLoader loader = new FXMLLoader(ContactosApp.class.getResource("/inicio.fxml"));
        Parent parent = loader.load();

        Scene scene = new Scene(parent, 1000, 460);
        stage.setScene(scene);
        stage.setTitle("UQ Contactos");
        //stage.setResizable(false);
        stage.show();

    }

    public static void main(String[] args) {
        launch(ContactosApp.class, args);
    }

}
