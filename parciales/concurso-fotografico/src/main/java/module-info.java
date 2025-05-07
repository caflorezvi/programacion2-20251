module co.edu.uniquindio.concurso {
    requires javafx.controls;
    requires javafx.fxml;
    requires static lombok;

    opens co.edu.uniquindio.concurso to javafx.fxml;
    exports co.edu.uniquindio.concurso;
    exports co.edu.uniquindio.concurso.modelo;
    exports co.edu.uniquindio.concurso.controladores;
    opens co.edu.uniquindio.concurso.controladores to javafx.fxml;
}