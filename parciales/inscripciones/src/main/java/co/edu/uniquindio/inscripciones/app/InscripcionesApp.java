package co.edu.uniquindio.inscripciones.app;

import co.edu.uniquindio.inscripciones.modelo.Inscripciones;
import co.edu.uniquindio.inscripciones.enums.TipoCarrera;

import java.time.LocalDate;

public class InscripcionesApp {

    public static void main(String[] args) throws Exception{

        Inscripciones inscripciones = new Inscripciones();

        inscripciones.registrarAspirante("123", "Juan", LocalDate.of(1992, 10, 10),  100, TipoCarrera.ING_SISTEMAS);
        inscripciones.registrarAspirante("124", "Maria", LocalDate.of(1992, 10, 10),  150, TipoCarrera.ING_SISTEMAS);
        inscripciones.registrarAspirante("125", "Lucas", LocalDate.of(1992, 10, 10),  390, TipoCarrera.ING_SISTEMAS);
        inscripciones.registrarAspirante("126", "Martha", LocalDate.of(1992, 10, 10),  200, TipoCarrera.ING_SISTEMAS);
        inscripciones.registrarAspirante("127", "Pedro", LocalDate.of(1992, 10, 10),  250, TipoCarrera.ING_SISTEMAS);
        inscripciones.registrarAspirante("128", "Pepe", LocalDate.of(1992, 10, 10),  290, TipoCarrera.ING_SISTEMAS);

        System.out.println( inscripciones.getIngSistemas().getListaAdmitidos() );
        System.out.println( inscripciones.getIngSistemas().getListaEspera() );

        System.out.println( inscripciones.buscar(TipoCarrera.ING_SISTEMAS, "125") );

    }

}
