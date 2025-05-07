package co.edu.uniquindio.clinica.controladores;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Tab;

import java.net.URL;
import java.util.ResourceBundle;

public class PanelControlador2 implements Initializable {

    @FXML
    private Tab tab1;

    @FXML
    private Tab tab2;

    @FXML
    private Tab tab3;

    @FXML
    private Tab tab4;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try{
            cargarTab(tab1, "/registroPaciente.fxml");
            cargarTab(tab2, "/listaPacientes.fxml");
            cargarTab(tab3, "/agendarCita.fxml");
            cargarTab(tab4, "/listaCitas.fxml");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void cargarTab(Tab tab, String fxmlFile) throws Exception{
        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));
        Parent content = loader.load();
        tab.setContent(content);
    }
}
