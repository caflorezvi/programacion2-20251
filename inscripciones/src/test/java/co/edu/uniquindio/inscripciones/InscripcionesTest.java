package co.edu.uniquindio.inscripciones;

import co.edu.uniquindio.inscripciones.modelo.Inscripciones;
import co.edu.uniquindio.inscripciones.enums.TipoCarrera;
import co.edu.uniquindio.inscripciones.vo.RespuestaBusqueda;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class InscripcionesTest {

    @Test
    public void registrarAspiranteValidoTest(){

        Inscripciones inscripciones = new Inscripciones();

        assertDoesNotThrow(() -> {
            inscripciones.registrarAspirante(
                    "123456789",
                    "Juan Perez",
                    LocalDate.of(2000, 1, 1),
                    120,
                    TipoCarrera.ING_SISTEMAS
            );
        });

    }

    @Test
    public void registrarAspiranteInvalidoTest(){

        Inscripciones inscripciones = new Inscripciones();

        assertThrows(Exception.class, () -> {
            inscripciones.registrarAspirante(
                    "",
                    "Juan Perez",
                    LocalDate.of(2000, 1, 1),
                    120,
                    TipoCarrera.ING_SISTEMAS
            );
        });

    }

    @Test
    public void buscarAspiranteTest() throws Exception{

        Inscripciones inscripciones = new Inscripciones();

        inscripciones.registrarAspirante(
                "12345",
                "Juan Perez",
                LocalDate.of(2000, 1, 1),
                190,
                TipoCarrera.ING_SISTEMAS
        );

        inscripciones.registrarAspirante(
                "67890",
                "Juan Perez",
                LocalDate.of(2000, 1, 1),
                100,
                TipoCarrera.ING_SISTEMAS
        );

        RespuestaBusqueda rb = inscripciones.buscar(TipoCarrera.ING_SISTEMAS, "12345");
        assertEquals(1, rb.getPosicion());
        assertEquals("Admitido", rb.getLista());

    }

}
