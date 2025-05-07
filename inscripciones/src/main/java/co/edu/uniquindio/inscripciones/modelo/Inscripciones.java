package co.edu.uniquindio.inscripciones.modelo;

import co.edu.uniquindio.inscripciones.enums.TipoCarrera;
import co.edu.uniquindio.inscripciones.vo.RespuestaBusqueda;
import lombok.Getter;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
public class Inscripciones {

    private final Carrera ingSistemas;
    private final Carrera ingCivil;
    private final Carrera ingElectronica;

    public Inscripciones() {
        this.ingSistemas = new Carrera(TipoCarrera.ING_SISTEMAS, 120);
        this.ingCivil = new Carrera(TipoCarrera.ING_CIVIL, 110);
        this.ingElectronica = new Carrera(TipoCarrera.ING_ELECTRONICA, 100);
    }

    public void registrarAspirante(String numeroIdentificacion, String nombre, LocalDate fechaNacimiento, int puntaje, TipoCarrera carrera) throws Exception{
        if (numeroIdentificacion == null || numeroIdentificacion.isBlank()){
            throw new Exception("El número de identificación no puede ser nulo o vacío");
        }

        if (nombre == null || nombre.isBlank()){
            throw new Exception("El nombre no puede ser nulo o vacío");
        }

        if (fechaNacimiento == null){
            throw new Exception("La fecha de nacimiento no puede ser nula");
        }

        if (puntaje < 0){
            throw new Exception("El puntaje no puede ser negativo");
        }

        if (carrera == null){
            throw new Exception("La carrera no puede ser nula");
        }

        Aspirante aspirante = Aspirante.builder()
                .numeroIdentificacion(numeroIdentificacion)
                .nombre(nombre)
                .fechaNacimiento(fechaNacimiento)
                .puntaje(puntaje)
                .build();

        switch (carrera) {
            case ING_SISTEMAS -> ingSistemas.guardarAspirante(aspirante);
            case ING_CIVIL -> ingCivil.guardarAspirante(aspirante);
            case ING_ELECTRONICA -> ingElectronica.guardarAspirante(aspirante);
        }

    }

    public RespuestaBusqueda buscar(TipoCarrera carrera, String numeroIdentificacion) throws Exception{
        return switch (carrera) {
            case ING_SISTEMAS -> ingSistemas.buscarEnListas(numeroIdentificacion);
            case ING_CIVIL -> ingCivil.buscarEnListas(numeroIdentificacion);
            case ING_ELECTRONICA -> ingElectronica.buscarEnListas(numeroIdentificacion);
        };
    }

    public List<Carrera> obtenerCarreraMasCompetitiva(){
        List<Carrera> respuesta = new ArrayList<>();
        respuesta.add(ingSistemas);
        respuesta.add(ingCivil);
        respuesta.add(ingElectronica);

        respuesta.sort((c1, c2) -> Float.compare(c1.obtenerDiferenciaPuntajes(), c2.obtenerDiferenciaPuntajes()));

        return respuesta;

    }


}
