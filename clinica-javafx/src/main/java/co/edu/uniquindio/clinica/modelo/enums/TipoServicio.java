package co.edu.uniquindio.clinica.modelo.enums;

import lombok.Getter;

@Getter
public enum TipoServicio {
    CONSULTA_GENERAL("Consulta general", 60000),
    CARDIOLOGIA("Cardiología", 95000),
    OFTALMOLOGIA("Oftalmología", 85000),
    PSICOLOGIA("Psicología", 90000),
    ODONTOLOGIA("Odontología", 80000);

    private final String nombre;
    private final float precio;

    TipoServicio(String nombre, float precio) {
        this.nombre = nombre;
        this.precio = precio;
    }

}
