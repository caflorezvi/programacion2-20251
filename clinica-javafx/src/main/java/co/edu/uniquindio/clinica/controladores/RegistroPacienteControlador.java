package co.edu.uniquindio.clinica.controladores;

import co.edu.uniquindio.clinica.modelo.enums.TipoSuscripcion;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import java.net.URL;
import java.util.ResourceBundle;

public class RegistroPacienteControlador implements Initializable {

    @FXML
    public TextField txtIdentificacion;

    @FXML
    public TextField txtNombre;

    @FXML
    public TextField txtTelefono;

    @FXML
    public TextField txtCorreo;

    @FXML
    private ComboBox<TipoSuscripcion> cbSuscripcion;

    private final ControladorPrincipal controladorPrincipal;

    public RegistroPacienteControlador(){
        this.controladorPrincipal = ControladorPrincipal.getInstancia();
    }

    public void registrarPaciente(ActionEvent actionEvent) {

        try {
            String identificacion = txtIdentificacion.getText();
            String nombre = txtNombre.getText();
            String telefono = txtTelefono.getText();
            String correo = txtCorreo.getText();
            TipoSuscripcion suscripcion = cbSuscripcion.getValue();

            controladorPrincipal.getClinica().registrarPaciente(identificacion, nombre, telefono, correo, suscripcion);
            limpiarFormularioRegistro();
            controladorPrincipal.mostrarAlerta("Paciente registrado correctamente", Alert.AlertType.INFORMATION);
        }catch (Exception e){
            controladorPrincipal.mostrarAlerta(e.getMessage(), Alert.AlertType.ERROR);
        }
    }

    private void limpiarFormularioRegistro() {
        txtIdentificacion.setText("");
        txtNombre.setText("");
        txtTelefono.setText("");
        txtCorreo.setText("");
        cbSuscripcion.setValue(null);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        cbSuscripcion.getItems().addAll(TipoSuscripcion.values());
    }
}
