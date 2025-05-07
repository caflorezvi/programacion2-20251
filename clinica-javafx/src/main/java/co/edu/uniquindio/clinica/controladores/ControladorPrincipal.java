package co.edu.uniquindio.clinica.controladores;

import co.edu.uniquindio.clinica.servicio.ClinicaServicio;
import javafx.scene.control.Alert;
import lombok.Getter;

public class ControladorPrincipal {

    private static ControladorPrincipal instancia;
    @Getter
    private final ClinicaServicio clinica;

    private ControladorPrincipal() {
        this.clinica = new ClinicaServicio();
    }

    public static ControladorPrincipal getInstancia() {
        if (instancia == null) {
            instancia = new ControladorPrincipal();
        }
        return instancia;
    }

    public void mostrarAlerta(String mensaje, Alert.AlertType tipo){
        Alert alert = new Alert(tipo);
        alert.setTitle("Información");
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.show();
    }

}
