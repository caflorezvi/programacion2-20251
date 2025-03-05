package co.edu.uniquindio.billetera.servicios;

import co.edu.uniquindio.billetera.modelo.entidades.Usuario;
import co.edu.uniquindio.billetera.repositorios.UsuarioRepositorio;

public class UsuarioServicio {

    private final UsuarioRepositorio usuarioRepositorio;

    public UsuarioServicio() {
        this.usuarioRepositorio = new UsuarioRepositorio();
    }

    public Usuario registrarUsuario(String id, String nombre, String direccion, String email, String password) throws Exception {
        if (id == null || id.isEmpty()) throw new Exception("El id es obligatorio");
        if (nombre == null || nombre.isEmpty()) throw new Exception("El nombre es obligatorio");
        if (direccion == null || direccion.isEmpty()) throw new Exception("La dirección es obligatoria");
        if (email == null || email.isEmpty()) throw new Exception("El email es obligatorio");
        if (password == null || password.isEmpty()) throw new Exception("La contraseña es obligatoria");
        if (usuarioRepositorio.buscarPorId(id) != null) throw new Exception("El usuario ya existe");

        Usuario usuario = new Usuario(id, nombre, direccion, email, password);
        usuarioRepositorio.agregar(usuario);

        return usuario;
    }

    public void eliminarUsuario(String id) throws Exception {
        Usuario usuario = buscarUsuario(id);
        usuarioRepositorio.eliminar(usuario);
    }

    public void actualizarUsuario(String id, String nombre, String direccion, String email, String password) throws Exception {
        Usuario usuario = buscarUsuario(id);
        usuario.setNombre(nombre);
        usuario.setDireccion(direccion);
        usuario.setEmail(email);
        usuario.setPassword(password);
        usuarioRepositorio.actualizar(usuario);
    }

    public Usuario buscarUsuario(String id) throws Exception{
        Usuario usuario = usuarioRepositorio.buscarPorId(id);
        if (usuario == null) throw new Exception("El usuario no existe");
        return usuario;
    }

    public Usuario buscarUsuario(String numeroIdentificacion, String password) throws Exception {
        Usuario usuario = buscarUsuario(numeroIdentificacion);

        if(usuario == null) throw new Exception("El usuario no existe");
        if(!usuario.getPassword().equals(password)) throw new Exception("La contraseña es incorrecta");

        return usuario;
    }
}
