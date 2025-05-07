package co.edu.uniquindio.clinica.controladores;

import co.edu.uniquindio.clinica.modelo.Factura;
import co.edu.uniquindio.clinica.modelo.enums.TipoServicio;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class AgendarCitaControlador implements Initializable{

    @FXML
    private TextField txtCedulaPaciente;

    @FXML
    private DatePicker dpDiaCita;

    @FXML
    private ComboBox<String> cbHoraCita;

    @FXML
    private ComboBox<TipoServicio> cbServicio;

    private final ControladorPrincipal controladorPrincipal;

    public AgendarCitaControlador(){
        controladorPrincipal = ControladorPrincipal.getInstancia();
    }

    public void registrarCita() {

        try {

            String cedulaPaciente = txtCedulaPaciente.getText();
            LocalDate fecha = dpDiaCita.getValue();
            String hora = cbHoraCita.getValue();
            TipoServicio servicio = cbServicio.getValue();

            Factura factura = controladorPrincipal.getClinica().agendarCita(cedulaPaciente, servicio, fecha, hora);
            limpiarFormularioCita();

            controladorPrincipal.mostrarAlerta(
                    "Cita agendada correctamente.\n\n" +
                    "Detalle factura:\n" +
                    "ID: "+factura.getId()+"\n" +
                    "Subtotal: $"+factura.getSubtotal()+"\n" +
                    "Total: $"+factura.getTotal(),
                    Alert.AlertType.INFORMATION
            );
        } catch (Exception ex) {
            controladorPrincipal.mostrarAlerta(ex.getMessage(), Alert.AlertType.ERROR);
        }
    }

    private void limpiarFormularioCita() {
        txtCedulaPaciente.setText("");
        dpDiaCita.setValue(null);
        cbHoraCita.setValue(null);
        cbServicio.setValue(null);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        cbHoraCita.setItems(FXCollections.observableArrayList(controladorPrincipal.getClinica().generarHorarios()));
        cbServicio.setItems(FXCollections.observableArrayList(TipoServicio.values()));
    }


}
