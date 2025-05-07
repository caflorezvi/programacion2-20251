package co.edu.uniquindio.contactos.modelo;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class AgendaContactos {

    private final List<Contacto> contactos;

    public AgendaContactos() {
        contactos = new ArrayList<>();
        crearDatosPrueba();
    }

    private void crearDatosPrueba() {
        try {
            agregarContacto("Juan", "Perez", "55555", "juan@email.com", "https://w7.pngwing.com/pngs/81/570/png-transparent-profile-logo-computer-icons-user-user-blue-heroes-logo-thumbnail.png", LocalDate.of(2000, 10, 10));
            agregarContacto("Carlos", "Florez", "33333", "carlos@email.com", "https://w7.pngwing.com/pngs/81/570/png-transparent-profile-logo-computer-icons-user-user-blue-heroes-logo-thumbnail.png", LocalDate.of(1999, 8, 12));
            agregarContacto("Luisa", "Perez", "44444", "luisa@email.com", "https://w7.pngwing.com/pngs/81/570/png-transparent-profile-logo-computer-icons-user-user-blue-heroes-logo-thumbnail.png", LocalDate.of(2002, 2, 20));

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void agregarContacto(String nombre, String apellido, String telefono, String email, String urlFoto, LocalDate fechaNacimiento) throws Exception {

        if(nombre.isEmpty() || telefono.isEmpty() || urlFoto.isEmpty())
            throw new Exception("Todos los campos son obligatorios");

        Contacto contacto = Contacto.builder()
                .id(UUID.randomUUID().toString()) //Genera un id aleatorio
                .nombre(nombre)
                .apellido(apellido)
                .fechaNacimiento(fechaNacimiento)
                .email(email)
                .urlFoto(urlFoto)
                .telefono(telefono).build();

        contactos.add(contacto);
    }

    public void eliminarContacto(String id) throws Exception{
        int posNota = obtenerContacto(id);

        if(posNota == -1){
            throw new Exception("No existe el id proporcionado");
        }

        contactos.remove( contactos.get(posNota) );
    }

    public void actualizarContacto(String id, String nombre, String apellido, String telefono, String email, String urlFoto, LocalDate fechaNacimiento) throws Exception{

        if(nombre.isEmpty() || apellido.isEmpty() || telefono.isEmpty())
            throw new Exception("Todos los campos son obligatorios");


        int posNota = obtenerContacto(id);

        if(posNota == -1){
            throw new Exception("No existe el id proporcionado");
        }

        Contacto contactoGuardado = contactos.get(posNota);
        contactoGuardado.setNombre(nombre);
        contactoGuardado.setApellido(apellido);
        contactoGuardado.setTelefono(telefono);
        contactoGuardado.setEmail(email);
        contactoGuardado.setUrlFoto(urlFoto);
        contactoGuardado.setFechaNacimiento(fechaNacimiento);

        //Actualiza la nota en la lista de notas
        contactos.set(posNota, contactoGuardado);
    }

    /**
     * Obtiene la posición de una nota en la lista de notas
     * @param id Id de la nota a buscar
     * @return Posición de la nota en la lista
     */
    private int obtenerContacto(String id){

        for (int i = 0; i < contactos.size(); i++) {
            if( contactos.get(i).getId().equals(id) ){
                return i;
            }
        }

        return -1;
    }

    public List<Contacto> listarContactos() {
        return contactos;
    }

    public List<Contacto> buscarContacto(String valor, String opcion) {

        return switch (opcion){
            case "Telefono" -> buscarPorTelefono(valor);
            case "Nombre" -> buscarPorNombre(valor);
            default -> new ArrayList<>();
        };

    }

    private List<Contacto> buscarPorTelefono(String text) {
        return contactos.stream().filter(c -> c.getTelefono().equalsIgnoreCase(text)).toList();
    }

    private List<Contacto> buscarPorNombre(String text) {
        return contactos.stream().filter(c -> c.getNombre().equalsIgnoreCase(text)).toList();
    }
}
