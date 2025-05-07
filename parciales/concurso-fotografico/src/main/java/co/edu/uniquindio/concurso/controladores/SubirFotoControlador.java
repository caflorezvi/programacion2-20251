package co.edu.uniquindio.concurso.controladores;

import co.edu.uniquindio.concurso.modelo.Concurso;
import co.edu.uniquindio.concurso.modelo.ConcursoFotografico;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class SubirFotoControlador implements Initializable {

    private final ConcursoFotografico concursoFotografico;

    @FXML
    private ComboBox<Concurso> comboConcurso;

    @FXML
    private ComboBox<String> comboCategoria;

    @FXML
    private TextField cedulaFotografo;

    @FXML
    private TextField nombreFotografo;

    public SubirFotoControlador() {
        this.concursoFotografico = ConcursoFotografico.getInstancia();
    }

    public void registrarFoto(ActionEvent actionEvent) {

        try {

            String idConcurso = comboConcurso.getValue().getId();
            String categoriaF = comboCategoria.getValue();
            String cedulaF = cedulaFotografo.getText();
            String nombreF = nombreFotografo.getText();

            concursoFotografico.agregarFoto(
                    idConcurso,
                    categoriaF,
                    cedulaF,
                    nombreF
            );

            mostrarAlerta("Foto registrada correctamente", Alert.AlertType.INFORMATION);
        } catch (Exception e) {
            mostrarAlerta(e.getMessage(), Alert.AlertType.ERROR);
        }

    }

    public void mostrarAlerta(String mensaje, Alert.AlertType tipo){
        Alert alert = new Alert(tipo);
        alert.setTitle("Información");
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.show();
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        comboCategoria.setItems(FXCollections.observableList( concursoFotografico.obtenerCategorias() ));
        comboConcurso.setItems(FXCollections.observableList( concursoFotografico.obtenerConcursos() ));

    }
}
