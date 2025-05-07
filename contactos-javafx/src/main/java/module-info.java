module co.edu.uniquindio.contactos {
    requires javafx.controls;
    requires javafx.fxml;
    requires static lombok;

    opens co.edu.uniquindio.contactos to javafx.fxml;
    exports co.edu.uniquindio.contactos;

    exports co.edu.uniquindio.contactos.controladores;
    opens co.edu.uniquindio.contactos.controladores to javafx.fxml;
}