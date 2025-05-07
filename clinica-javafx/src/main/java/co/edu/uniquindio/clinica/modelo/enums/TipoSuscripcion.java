package co.edu.uniquindio.clinica.modelo.enums;

public enum TipoSuscripcion {
    BASICA("Básica"),
    PREMIUM("Premium");

    private final String nombre;

    TipoSuscripcion(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }
}
