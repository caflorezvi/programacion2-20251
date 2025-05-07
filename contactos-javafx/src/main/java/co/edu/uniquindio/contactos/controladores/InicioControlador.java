package co.edu.uniquindio.contactos.controladores;

import co.edu.uniquindio.contactos.modelo.Contacto;
import co.edu.uniquindio.contactos.modelo.AgendaContactos;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Window;

import java.io.File;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class InicioControlador implements Initializable {

    @FXML
    public ImageView fotoContacto;

    @FXML
    private TextField txtNombre;

    @FXML
    private TextField txtApellido;

    @FXML
    private TextField txtTelefono;

    @FXML
    private TextField txtEmail;

    @FXML
    private DatePicker txtFechaCumple;

    @FXML
    private TableView<Contacto> tablaContactos;

    @FXML
    private TableColumn<Contacto, String> colFoto;

    @FXML
    private TableColumn<Contacto, String> colNombre;

    @FXML
    private TableColumn<Contacto, String> colApellido;

    @FXML
    private TableColumn<Contacto, String> colFecha;

    @FXML
    private TableColumn<Contacto, String> colTelefono;

    @FXML
    private TableColumn<Contacto, String> colEmail;

    private final AgendaContactos agendaContactos; //Instancia de la clase principal

    private Contacto contactoSeleccionado; //Contacto seleccionado de la tabla

    private ObservableList<Contacto> contactosObservable; //Lista observable de contactos

    @FXML
    private ComboBox<String> cmbBuscarPor;

    @FXML
    private TextField txtBuscar;

    private String imagenSeleccionada;

    public InicioControlador() {
        agendaContactos = new AgendaContactos();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //Asignar las propiedades de la nota a las columnas de la tabla
        colNombre.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getNombre()));
        colApellido.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getApellido()));
        colEmail.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getEmail()));
        colFecha.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getFechaNacimiento().toString()));
        colTelefono.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getTelefono()));
        colFoto.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getUrlFoto()));

        // Cargar la imagen en la tabla
        colFoto.setCellFactory(param -> new TableCell<Contacto, String>() {
                    private final ImageView imageView = new ImageView();

                    @Override
                    protected void updateItem(String imageUrl, boolean empty) {
                        super.updateItem(imageUrl, empty);
                        if (empty || imageUrl == null) {
                            setGraphic(null);
                        } else {
                            // Cargar la imagen directamente, puede bloquear la interfaz si es lenta
                            Image image = new Image( imageUrl, 50, 50, true, true); // Ajusta el tamaño
                            imageView.setImage(image);
                            setGraphic(imageView);
                        }
                    }
                });

        //Inicializar el combobox de busqueda
        cmbBuscarPor.getItems().addAll("Buscar por...", "Nombre", "Telefono", "Ninguno");
        cmbBuscarPor.setValue("Buscar por...");

        //Inicializar lista observable y cargar las notas
        contactosObservable = FXCollections.observableArrayList();
        cargarContactos();

        //Evento click en la tabla
        tablaContactos.setOnMouseClicked(e -> {
            //Obtener la nota seleccionada
            contactoSeleccionado = tablaContactos.getSelectionModel().getSelectedItem();

            if(contactoSeleccionado != null){
                txtNombre.setText(contactoSeleccionado.getNombre());
                txtApellido.setText(contactoSeleccionado.getApellido());
                txtFechaCumple.setValue(contactoSeleccionado.getFechaNacimiento());
                txtEmail.setText(contactoSeleccionado.getEmail());
                txtTelefono.setText(contactoSeleccionado.getTelefono());

                imagenSeleccionada = contactoSeleccionado.getUrlFoto();
                Image imagen = new Image(contactoSeleccionado.getUrlFoto());
                fotoContacto.setImage(imagen);
            }

        });
    }

    public void crearContacto(ActionEvent e){
        try {
            agendaContactos.agregarContacto(
                    txtNombre.getText(),
                    txtApellido.getText(),
                    txtTelefono.getText(),
                    txtEmail.getText(),
                    imagenSeleccionada,
                    txtFechaCumple.getValue()
            );

            limpiarCampos();
            actualizarTabla( agendaContactos.listarContactos() );
            mostrarAlerta("Contacto creado correctamente", Alert.AlertType.INFORMATION);
        }catch (Exception ex){
            mostrarAlerta(ex.getMessage(), Alert.AlertType.ERROR);
        }

    }

    public void eliminarContacto(ActionEvent e){

        if(contactoSeleccionado != null) {
            try {
                agendaContactos.eliminarContacto(contactoSeleccionado.getId());

                limpiarCampos();
                actualizarTabla( agendaContactos.listarContactos() );
                mostrarAlerta("Contacto eliminada correctamente", Alert.AlertType.INFORMATION);
            } catch (Exception ex) {
                mostrarAlerta(ex.getMessage(), Alert.AlertType.ERROR);
            }

        }else{
            mostrarAlerta("Debe seleccionar un contacto de la tabla", Alert.AlertType.WARNING);
        }
    }

    public void editarContacto(ActionEvent e) {

        if(contactoSeleccionado != null) {
            try {
                agendaContactos.actualizarContacto(
                        contactoSeleccionado.getId(),
                        txtNombre.getText(),
                        txtApellido.getText(),
                        txtTelefono.getText(),
                        txtEmail.getText(),
                        imagenSeleccionada,
                        txtFechaCumple.getValue()
                );

                limpiarCampos();
                actualizarTabla( agendaContactos.listarContactos() );
                mostrarAlerta("Contacto actualizado correctamente", Alert.AlertType.INFORMATION);
            } catch (Exception ex) {
                mostrarAlerta(ex.getMessage(), Alert.AlertType.ERROR);
            }
        }else{
            mostrarAlerta("Debe seleccionar un contacto de la tabla", Alert.AlertType.WARNING);
        }
    }

    private void cargarContactos() {
        contactosObservable.setAll(agendaContactos.listarContactos());
        tablaContactos.setItems(contactosObservable);
    }

    public void actualizarTabla(List<Contacto> contactos) {
        contactosObservable.setAll(contactos);
    }

    /**
     * Muestra una alerta en pantalla
     * @param mensaje Mensaje a mostrar
     * @param tipo Tipo de alerta
     */
    private void mostrarAlerta(String mensaje, Alert.AlertType tipo){
        Alert alert = new Alert(tipo);
        alert.setTitle("Información");
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.show();
    }

    /**
     * Limpia los campos de texto del formulario
     */
    private void limpiarCampos() {
        txtNombre.clear();
        txtApellido.clear();
        txtTelefono.clear();
        txtEmail.clear();
        imagenSeleccionada = null;
        txtFechaCumple.setValue(null);
        fotoContacto.setImage(null);
    }

    public void buscarContacto(ActionEvent actionEvent) {
        String opcion = cmbBuscarPor.getValue();
        String valor = txtBuscar.getText();

        if(valor == null || valor.isEmpty()){
            mostrarAlerta("Debe ingresar un valor para buscar", Alert.AlertType.WARNING);
            return;
        }

        switch (opcion) {
            case "Nombre", "Telefono" -> actualizarTabla( agendaContactos.buscarContacto(valor, opcion) );
            case "Ninguno" -> {
                txtBuscar.clear();
                cargarContactos();
            }

        }
    }

    public void seleccionarImagen(ActionEvent actionEvent) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Seleccionar Imagen");
        fileChooser.getExtensionFilters().add(
                new FileChooser.ExtensionFilter("Imágenes", "*.png", "*.jpg", "*.jpeg", "*.gif")
        );

        Window window = fotoContacto.getScene().getWindow();
        File archivo = fileChooser.showOpenDialog(window);

        if (archivo != null) {
            Image imagen = new Image(archivo.toURI().toString());
            imagenSeleccionada = archivo.toURI().toString();
            fotoContacto.setImage(imagen);
        }
    }
}
