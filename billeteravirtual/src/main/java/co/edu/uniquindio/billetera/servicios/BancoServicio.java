package co.edu.uniquindio.billetera.servicios;

import co.edu.uniquindio.billetera.modelo.entidades.Billetera;
import co.edu.uniquindio.billetera.modelo.entidades.Transaccion;
import co.edu.uniquindio.billetera.modelo.entidades.Usuario;
import co.edu.uniquindio.billetera.modelo.enums.Categoria;
import co.edu.uniquindio.billetera.modelo.vo.PorcentajeGastosIngresos;
import co.edu.uniquindio.billetera.modelo.vo.SaldoTransaccionesBilletera;
import co.edu.uniquindio.billetera.servicios.interfaces.IBancoServicio;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Clase que representa un banco con usuarios y billeteras
 * @version 1.0
 * @author caflorezvi
 */
public class BancoServicio implements IBancoServicio {

    private final UsuarioServicio usuarioServicio;
    private final BilleteraServicio billeteraServicio;
    private final TransaccionServicio transaccionServicio;

    public BancoServicio(){
        this.usuarioServicio = new UsuarioServicio();
        this.billeteraServicio = new BilleteraServicio();
        this.transaccionServicio = new TransaccionServicio();
    }

    @Override
    public void registrarUsuario(String id, String nombre, String direccion, String email, String password) throws Exception {
        Usuario usuario = usuarioServicio.registrarUsuario(id, nombre, direccion, email, password);
        billeteraServicio.registrarBilletera(usuario);
    }

    @Override
    public void actualizarUsuario(String id, String nombre, String direccion, String email, String password) throws Exception {
        usuarioServicio.actualizarUsuario(id, nombre, direccion, email, password);
    }

    @Override
    public void eliminarUsuario(String id) throws Exception {
        usuarioServicio.eliminarUsuario(id);
    }

    @Override
    public void realizarTransferencia(String numeroBilleteraOrigen, String numeroBilleteraDestino, float monto, Categoria categoria) throws Exception {
        Billetera origen = billeteraServicio.buscarBilletera(numeroBilleteraOrigen);
        Billetera destino = billeteraServicio.buscarBilletera(numeroBilleteraDestino);
        transaccionServicio.realizarTransferencia(origen, destino, monto, categoria);
    }

    @Override
    public List<Transaccion> obtenerTransacciones(String numeroBilletera) throws Exception{
        billeteraServicio.buscarBilletera(numeroBilletera);
        return transaccionServicio.obtenerTransacciones(numeroBilletera);
    }

    @Override
    public List<Transaccion> obtenerTransaccionesPeriodo(String numeroBilletera, LocalDateTime inicio, LocalDateTime fin) throws Exception{
        billeteraServicio.buscarBilletera(numeroBilletera);
        return transaccionServicio.obtenerTransaccionesPeriodo(numeroBilletera, inicio, fin);
    }

    @Override
    public PorcentajeGastosIngresos obtenerPorcentajeGastosIngresos(String numeroBilletera, int mes, int anio) throws Exception{
        billeteraServicio.buscarBilletera(numeroBilletera);
        return transaccionServicio.obtenerPorcentajeGastosIngresos(numeroBilletera, mes, anio);
    }

    @Override
    public SaldoTransaccionesBilletera consultarSaldoYTransacciones(String numeroIdentificacion, String password) throws Exception{
        Usuario usuario = usuarioServicio.buscarUsuario(numeroIdentificacion, password);
        Billetera billetera = billeteraServicio.buscarBilleteraPorUsuario(usuario.getId());


        List<Transaccion> transacciones = transaccionServicio.obtenerTransacciones(billetera.getNumero());

        return new SaldoTransaccionesBilletera(
                billetera.getSaldo(),
                transacciones
        );
    }

}
