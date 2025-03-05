package co.edu.uniquindio.billetera.servicios.interfaces;

import co.edu.uniquindio.billetera.modelo.entidades.Transaccion;
import co.edu.uniquindio.billetera.modelo.enums.Categoria;
import co.edu.uniquindio.billetera.modelo.vo.PorcentajeGastosIngresos;
import co.edu.uniquindio.billetera.modelo.vo.SaldoTransaccionesBilletera;

import java.time.LocalDateTime;
import java.util.List;

public interface IBancoServicio {

    void registrarUsuario(String id, String nombre, String direccion, String email, String password) throws Exception;

    void actualizarUsuario(String id, String nombre, String direccion, String email, String password) throws Exception;

    void eliminarUsuario(String id) throws Exception;

    void realizarTransferencia(String numeroBilleteraOrigen, String numeroBilleteraDestino, float monto, Categoria categoria) throws Exception;

    List<Transaccion> obtenerTransacciones(String numeroBilletera) throws Exception;

    List<Transaccion> obtenerTransaccionesPeriodo(String numeroBilletera, LocalDateTime inicio, LocalDateTime fin) throws Exception;

    PorcentajeGastosIngresos obtenerPorcentajeGastosIngresos(String numeroBilletera, int mes, int anio) throws Exception;

    SaldoTransaccionesBilletera consultarSaldoYTransacciones(String numeroIdentificacion, String password) throws Exception;

}
