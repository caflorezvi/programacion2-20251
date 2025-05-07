package co.edu.uniquindio.clinica.controladores;

import co.edu.uniquindio.clinica.modelo.Cita;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ListaCitasControlador implements Initializable{

    @FXML
    private TableView<Cita> tablaCitas;

    @FXML
    private TableColumn<Cita, String> colPaciente;

    @FXML
    private TableColumn<Cita, String> colFecha;

    @FXML
    private TableColumn<Cita, String> colServicio;

    private ObservableList<Cita> observableList;

    private final ControladorPrincipal controladorPrincipal;

    public ListaCitasControlador(){
        controladorPrincipal = ControladorPrincipal.getInstancia();
    }

    public void cancelarCita(ActionEvent actionEvent) {

        try{

            Cita cita = tablaCitas.getSelectionModel().getSelectedItem();
            if (cita != null) {
                controladorPrincipal.getClinica().cancelarCita(cita.getId());
                actualizarTabla();
                controladorPrincipal.mostrarAlerta("Cita cancelada correctamente", Alert.AlertType.INFORMATION);
            } else {
                controladorPrincipal.mostrarAlerta("Seleccione una cita para cancelar", Alert.AlertType.WARNING);
            }

        }catch (Exception e){
            controladorPrincipal.mostrarAlerta(e.getMessage(), Alert.AlertType.ERROR);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colPaciente.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getPaciente().toString()));
        colFecha.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getDia().toString()+" "+cellData.getValue().getHora().toString()));
        colServicio.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getServicio().getNombre().toString()));

        observableList = FXCollections.observableArrayList();
        observableList.setAll(new ArrayList<>());
        tablaCitas.setItems(observableList);

        actualizarTabla();
    }

    public void actualizarTabla() {
        observableList.setAll(controladorPrincipal.getClinica().listarCitas());
    }

}
