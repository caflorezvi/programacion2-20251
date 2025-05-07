package co.edu.uniquindio.concurso.modelo;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ConcursoFotografico {

    private final List<Concurso> concursos;
    private final List<Fotografo> fotografos;
    private static ConcursoFotografico instance;

    private ConcursoFotografico() {
        this.concursos = new ArrayList<>();
        this.fotografos = new ArrayList<>();
        crearConcursoPrueba();
    }

    public static ConcursoFotografico getInstancia() {
        if (instance == null) {
            instance = new ConcursoFotografico();
        }
        return instance;
    }

    public void agregarFoto(String idConcurso, String categoria, String cedula, String nombre) throws Exception {

        if (idConcurso == null) {
            throw new Exception("El ID del concurso no puede ser nulo.");
        }

        if (categoria == null || categoria.isBlank()) {
            throw new Exception("La categoría de la foto es obligatoria.");
        }

        if (cedula == null || cedula.isBlank()) {
            throw new Exception("La cédula del fotógrafo es obligatoria.");
        }

        if (nombre == null || nombre.isBlank()) {
            throw new Exception("El nombre del fotógrafo es obligatorio.");
        }

        Concurso concurso = obtenerConcurso(idConcurso);
        Fotografo fotografo = buscarFotografo(cedula);

        if (fotografo == null) {

            fotografo = Fotografo.builder()
                    .cedula(cedula)
                    .nombre(nombre)
                    .build();

            fotografos.add(fotografo);
        }

        Foto foto = Foto.builder()
                .categoria(categoria)
                .fotografo(fotografo)
                .build();

        concurso.agregarFoto(foto);
    }

    public Concurso obtenerConcurso(String idConcurso) throws Exception{
        Concurso concurso = concursos.stream()
                .filter(c -> c.getId().equals(idConcurso))
                .findFirst()
                .orElseThrow(() -> new Exception("Concurso no encontrado"));

        return concurso;
    }

    public Fotografo buscarFotografo(String cedula) {
        Fotografo fotografo = fotografos.stream()
                .filter(f -> f.getCedula().equals(cedula))
                .findFirst()
                .orElse(null);

        return fotografo;
    }

    public List<String> obtenerCategorias(){
        List<String> categorias = new ArrayList<>();
        categorias.add("Naturaleza");
        categorias.add("Retrato");
        categorias.add("Arquitectura");
        categorias.add("Deportes");
        return categorias;
    }

    public List<Concurso> obtenerConcursos(){
        return concursos;
    }

    private void crearConcursoPrueba(){
        Concurso concurso = Concurso.builder()
                .id(UUID.randomUUID().toString())
                .nombre("Concurso de Prueba")
                .categorias(obtenerCategorias())
                .build();
        concursos.add(concurso);
    }

    public void realizarConcurso(String idConcurso) throws Exception{
        Concurso concurso = obtenerConcurso(idConcurso);
        concurso.determinarGanadores();;
    }


}
