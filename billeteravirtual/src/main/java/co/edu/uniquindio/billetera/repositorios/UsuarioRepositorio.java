package co.edu.uniquindio.billetera.repositorios;

import co.edu.uniquindio.billetera.modelo.entidades.Usuario;
import java.util.ArrayList;
import java.util.List;

public class UsuarioRepositorio {
    private final List<Usuario> usuarios;

    public UsuarioRepositorio() {
        usuarios = new ArrayList<>();
    }

    public void agregar(Usuario usuario) {
        usuarios.add(usuario);
    }

    public void eliminar(Usuario usuario) {
        usuarios.remove(usuario);
    }

    public void actualizar(Usuario usuario) {
        usuarios.set(usuarios.indexOf(usuario), usuario);
    }

    public Usuario buscarPorId(String id) {
        return usuarios.stream()
                .filter(u -> u.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    public List<Usuario> listarTodos() {
        return usuarios;
    }
}
