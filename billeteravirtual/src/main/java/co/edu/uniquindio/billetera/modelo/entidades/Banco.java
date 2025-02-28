package co.edu.uniquindio.billetera.modelo.entidades;

import co.edu.uniquindio.billetera.config.Constantes;
import co.edu.uniquindio.billetera.modelo.enums.Categoria;
import co.edu.uniquindio.billetera.modelo.vo.PorcentajeGastosIngresos;
import co.edu.uniquindio.billetera.modelo.vo.SaldoTransaccionesBilletera;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Clase que representa un banco con usuarios y billeteras
 * @version 1.0
 * @author caflorezvi
 */
public class Banco {

    private List<Usuario> usuarios;
    private List<Billetera> billeteras;

    public Banco(){
        this.usuarios = new ArrayList<>();
        this.billeteras = new ArrayList<>();
    }

    /**
     * Permite registrar un usuario en el banco y crear su billetera
     * @param id identificación del usuario
     * @param nombre nombre del usuario
     * @param direccion dirección del usuario
     * @param email email del usuario
     * @param password contraseña del usuario
     * @throws Exception si el id, nombre, dirección, email o password son nulos o vacíos o si el usuario ya existe
     */
    public void registrarUsuario(String id, String nombre, String direccion, String email, String password) throws Exception{

        if(id == null || id.isEmpty()){
            throw new Exception("El id es obligatorio");
        }

        if(nombre == null || nombre.isEmpty()){
            throw new Exception("El nombre es obligatorio");
        }

        if(direccion == null || direccion.isEmpty()){
            throw new Exception("La dirección es obligatoria");
        }

        if(email == null || email.isEmpty()){
            throw new Exception("El email es obligatorio");
        }

        if(password == null || password.isEmpty()){
            throw new Exception("La contraseña es obligatoria");
        }

        if(buscarUsuario(id) != null){
            throw new Exception("El usuario ya existe");
        }

        Usuario usuario = new Usuario(id, nombre, direccion, email, password);
        // Se agrega el usuario a la lista de usuarios
        usuarios.add(usuario);
        // Se registra la billetera del usuario
        registrarBilletera(usuario);
    }

    /**
     * Permite registrar una billetera para un usuario
     * @param usuario usuario al que se le va a registrar la billetera
     */
    public void registrarBilletera(Usuario usuario){
        String numero = crearNumeroBilletera();
        Billetera billetera = new Billetera(numero, 0, usuario);
        billeteras.add(billetera);
    }

    /**
     * Crea un número de billetera único
     * @return número de billetera
     */
    private String crearNumeroBilletera(){
        String numero = generarNumeroAleatorio();
        while(buscarBilletera(numero) != null){
            numero = generarNumeroAleatorio();
        }
        return numero;
    }

    /**
     * Permite generar un número aleatorio de 10 dígitos para la billetera
     * @return número aleatorio
     */
    private String generarNumeroAleatorio(){
        String numero = "";
        for(int i = 0; i < 10; i++){
            numero += ""+((int) (Math.random() * 10));
        }
        return numero;
    }

    /**
     * Permite buscar una billetera por su número
     * @param numero número de la billetera
     * @return billetera encontrada o null si no existe
     */
    public Billetera buscarBilletera(String numero){
        return billeteras.stream()
                .filter(billetera -> billetera.getNumero().equals(numero))
                .findFirst()
                .orElse(null);
    }

    /**
     * Permite consultar el saldo y las transacciones de una billetera de un usuario
     * @param numeroIdentificacion identificación del usuario
     * @param password contraseña del usuario
     * @return Saldo y transacciones de la billetera
     * @throws Exception si el usuario no existe o la contraseña es incorrecta
     */
    public SaldoTransaccionesBilletera consultarSaldoYTransacciones(String numeroIdentificacion, String password)throws Exception{
        Usuario usuario = buscarUsuario(numeroIdentificacion);

        if(usuario == null){
            throw new Exception("El usuario no existe");
        }

        if(!usuario.getPassword().equals(password)){
            throw new Exception("Contraseña incorrecta");
        }

        Billetera billetera = buscarBilleteraUsuario(usuario.getId());

        return new SaldoTransaccionesBilletera(
                billetera.consultarSaldo(),
                billetera.obtenerTransacciones()
        );
    }

    /**
     * Permite buscar la billetera de un usuario
     * @param idUsuario identificación del usuario
     * @return billetera del usuario o null si no existe
     */
    public Billetera buscarBilleteraUsuario(String idUsuario){
        return billeteras.stream()
                .filter(billetera -> billetera.getUsuario().getId().equals(idUsuario))
                .findFirst()
                .orElse(null);
    }

    /**
     * Permite buscar un usuario por su id
     * @param id id del usuario
     * @return usuario encontrado o null si no existe
     */
    public Usuario buscarUsuario(String id){
        return usuarios.stream()
                .filter(usuario -> usuario.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    /**
     * Permite realizar una recarga a una billetera
     * @param numeroBiletera número de la billetera
     * @param monto monto a recargar
     * @throws Exception si la billetera no existe
     */
    public void recargarBilletera(String numeroBiletera, float monto) throws Exception{
        Billetera billetera = buscarBilletera(numeroBiletera);
        if(billetera == null){
            throw new Exception("La billetera no existe");
        }

        Transaccion transaccion = new Transaccion(
                UUID.randomUUID().toString(),
                monto,
                LocalDateTime.now(),
                Categoria.RECARGA,
                billetera,
                billetera,
                0
        );

        billetera.depositar(monto, transaccion);
    }

    public void realizarTransferencia(String numeroBilleteraOrigen, String numeroBilleteraDestino, float monto, Categoria categoria) throws Exception{
        Billetera billeteraOrigen = buscarBilletera(numeroBilleteraOrigen);
        Billetera billeteraDestino = buscarBilletera(numeroBilleteraDestino);

        if(billeteraOrigen == null || billeteraDestino == null){
            throw new Exception("La billetera no existe");
        }

        if(!billeteraOrigen.tieneSaldo(monto)){
            throw new Exception("Saldo insuficiente");
        }

        Transaccion transaccion = new Transaccion(
                UUID.randomUUID().toString(),
                monto,
                LocalDateTime.now(),
                categoria,
                billeteraOrigen,
                billeteraDestino,
                Constantes.COMISION
        );

        billeteraOrigen.retirar(monto, transaccion);
        billeteraDestino.depositar(monto, transaccion);

    }

    public List<Transaccion> obtenerTransacciones(String numeroBilletera){
        Billetera billetera = buscarBilletera(numeroBilletera);
        if(billetera != null){
            return billetera.obtenerTransacciones();
        }
        return new ArrayList<>();
    }

    public List<Transaccion> obtenerTransaccionesPeriodo(String numeroBilletera, LocalDateTime inicio, LocalDateTime fin){
        Billetera billetera = buscarBilletera(numeroBilletera);
        if(billetera != null){
            return billetera.obtenerTransaccionesPeriodo(inicio, fin);
        }
        return new ArrayList<>();
    }

    public PorcentajeGastosIngresos obtenerPorcentajeGastosIngresos(String numeroBilletera, int mes, int anio) throws Exception{
        Billetera billetera = buscarBilletera(numeroBilletera);
        if(billetera == null){
            throw new Exception("La billetera no existe");
        }
        return billetera.obtenerPorcentajeGastosIngresos(mes, anio);
    }

    public void setUsuarios(List<Usuario> usuarios) {
        this.usuarios = usuarios;
    }

    public void setBilleteras(List<Billetera> billeteras) {
        this.billeteras = billeteras;
    }
}
