package ejercicio1;

import ejercicio4.Estudiante;
import ejercicio4.GestionEstudiantes;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Pruebas unitarias para la clase GestionEstudiantes
 */
public class Ejercicio4Test {

    private GestionEstudiantes gestion;

    @BeforeEach
    public void crearDatosPrueba() {
        // Se crea una lista de estudiantes con 3 estudiantes para las pruebas unitarias de la clase GestionEstudiantes
        ArrayList<Estudiante> estudiantes = new ArrayList<>();
        estudiantes.add(new Estudiante("123", "Carlos", LocalDate.of(2000, 1, 20), 4.5f));
        estudiantes.add(new Estudiante("234", "Luis", LocalDate.of(2000, 2, 20), 3.5f));
        estudiantes.add(new Estudiante("345", "Ana", LocalDate.of(1999, 9, 17), 4.0f));

        // Se crea una instancia de GestionEstudiantes con la lista de estudiantes
        gestion = new GestionEstudiantes();
        gestion.setEstudiantes(estudiantes);
    }

    @Test
    public void crearTest(){

        // Se crea un nuevo estudiante
        Estudiante estudiante4 = new Estudiante(
                "456",
                "Pepito",
                LocalDate.of(2000, 1, 20),
                4.5f
        );

        // Se espera que no se lance ninguna excepción
        assertDoesNotThrow( () -> {
            gestion.agregar(estudiante4);
        } );

        // Se espera que el estudiante haya sido agregado
        Estudiante estudianteAgregado = gestion.obtener("456");
        assertNotNull(estudianteAgregado);

    }

    @Test
    public void obtenerTest(){
        // Se obtiene un estudiante con ID 123
        Estudiante estudiante = gestion.obtener("123");

        // Se espera que el estudiante no sea nulo y que tenga los datos correctos
        assertNotNull(estudiante);
        assertEquals("Carlos", estudiante.getNombre());
        assertEquals(LocalDate.of(2000, 1, 20), estudiante.getFechaNacimiento());
        assertEquals(4.5f, estudiante.getNota());
    }

    @Test
    public void actualizarTest(){
        // Se crea un nuevo estudiante con los datos actualizados
        Estudiante estudiante = new Estudiante(
                "123",
                "Carlos Andrés",
                LocalDate.of(2000, 1, 21),
                4.6f
        );

        // Se espera que no se lance ninguna excepción
        assertDoesNotThrow( () -> {
            gestion.actualizar(estudiante);
        } );

        // Se obtiene el estudiante actualizado
        Estudiante estudianteActualizado = gestion.obtener("123");

        // Se espera que el estudiante no sea nulo y que tenga los datos actualizados
        assertNotNull(estudianteActualizado);
        assertEquals("Carlos Andrés", estudianteActualizado.getNombre());
        assertEquals(LocalDate.of(2000, 1, 21), estudianteActualizado.getFechaNacimiento());
        assertEquals(4.6f, estudianteActualizado.getNota());
    }

    @Test
    public void eliminarTest(){
        // Se espera que no se lance ninguna excepción al eliminar el estudiante con ID 123
        assertDoesNotThrow( () -> {
            gestion.eliminar("123");
        } );

        // Se espera que el estudiante con ID 123 no exista
        Estudiante estudiante = gestion.obtener("123");
        assertNull(estudiante);
    }

}
