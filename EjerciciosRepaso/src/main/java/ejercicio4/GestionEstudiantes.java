package ejercicio4;

import java.util.ArrayList;

/**
 * Clase que se encarga de gestionar los estudiantes. Permite agregar, eliminar, editar y obtener estudiantes de la lista.
 * Básicamente es la clase encargada de hacer el CRUD (Create, Read, Update, Delete) de los estudiantes.
 */
public class GestionEstudiantes {

    private ArrayList<Estudiante> estudiantes;

    public GestionEstudiantes(){
        this.estudiantes = new ArrayList<>();
    }

    /**
     * Método que permite agregar un estudiante a la lista de estudiantes.
     * @param estudiante Estudiante a agregar
     * @throws Exception
     */
    public void agregar(Estudiante estudiante) throws Exception{

        Estudiante estadianteBuscado = obtener(estudiante.getId());

        // Si el estudiante ya existe, lanzar una excepción
        if(estadianteBuscado!=null){
            throw new Exception("Ya existe un estudiante con el mismo ID");
        }else{
            estudiantes.add(estudiante);
        }

    }

    /**
     * Método que permite eliminar un estudiante de la lista de estudiantes.
     * @param id ID del estudiante a eliminar
     * @throws Exception
     */
    public void eliminar(String id) throws Exception{
        Estudiante estadianteBuscado = obtener(id);

        // Si el estudiante no existe, lanzar una excepción
        if(estadianteBuscado==null){
            throw new Exception("No existe un estudiante con el ID dado");
        }else{
            estudiantes.remove(estadianteBuscado);
        }
    }

    /**
     * Método que permite editar un estudiante de la lista de estudiantes.
     * @param nuevoEstudiante Estudiante con los datos actualizados
     */
    public void actualizar(Estudiante nuevoEstudiante) throws Exception{
        Estudiante estudianteBuscado = obtener(nuevoEstudiante.getId());

        // Si el estudiante no existe, lanzar una excepción
        if(estudianteBuscado!=null){
            estudianteBuscado.setNombre(nuevoEstudiante.getNombre());
            estudianteBuscado.setFechaNacimiento(nuevoEstudiante.getFechaNacimiento());
            estudianteBuscado.setNota(nuevoEstudiante.getNota());
        }else{
            throw new Exception("No existe un estudiante con el ID dado");
        }
    }

    /**
     * Método que permite obtener un estudiante de la lista de estudiantes.
     * @param id ID del estudiante a buscar
     * @return Estudiante encontrado o null si no se encuentra
     */
    public Estudiante obtener(String id){

        // Buscar el estudiante con el ID dado (se usa programación funcional)
        return estudiantes
                .stream()
                .filter(e -> e.getId().equals(id))
                .findFirst()
                .orElse(null);

    }

    public ArrayList<Estudiante> getEstudiantes(){
        return estudiantes;
    }

    public void setEstudiantes(ArrayList<Estudiante> estudiantes){
        this.estudiantes = estudiantes;
    }

    /**
     * Método que permite obtener la cantidad de estudiantes en la lista.
     * @return cantidad de estudiantes
     */
    public int obtenerCantidadEstudiantes(){
        return estudiantes.size();
    }

}
