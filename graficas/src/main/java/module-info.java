module co.edu.uniquindio.graficas {
    requires javafx.controls;
    requires javafx.fxml;


    opens co.edu.uniquindio.graficas to javafx.fxml;
    exports co.edu.uniquindio.graficas;
}