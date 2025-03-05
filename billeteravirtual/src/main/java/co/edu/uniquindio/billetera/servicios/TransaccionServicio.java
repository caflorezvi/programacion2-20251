package co.edu.uniquindio.billetera.servicios;

import co.edu.uniquindio.billetera.config.Constantes;
import co.edu.uniquindio.billetera.modelo.entidades.Billetera;
import co.edu.uniquindio.billetera.modelo.entidades.Transaccion;
import co.edu.uniquindio.billetera.modelo.enums.Categoria;
import co.edu.uniquindio.billetera.modelo.vo.PorcentajeGastosIngresos;
import co.edu.uniquindio.billetera.repositorios.TransaccionRepositorio;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public class TransaccionServicio {

    private final TransaccionRepositorio transaccionRepositorio;

    public TransaccionServicio() {
        this.transaccionRepositorio = new TransaccionRepositorio();
    }

    public void realizarTransferencia(Billetera origen, Billetera destino, float monto, Categoria categoria) throws Exception {

        Transaccion transaccion = new Transaccion(
                UUID.randomUUID().toString(),
                monto,
                LocalDateTime.now(),
                categoria,
                origen,
                destino,
                Constantes.COMISION
        );

        origen.retirar(monto);
        destino.depositar(monto);

        transaccionRepositorio.agregar(transaccion);
    }

    public List<Transaccion> obtenerTransacciones(String numeroBilletera) {
        return transaccionRepositorio.obtenerPorBilletera(numeroBilletera);
    }

    public List<Transaccion> obtenerTransaccionesPeriodo(String numeroBilletera, LocalDateTime inicio, LocalDateTime fin){
        return transaccionRepositorio.obtenerPorPeriodo(numeroBilletera, inicio, fin);
    }

    public PorcentajeGastosIngresos obtenerPorcentajeGastosIngresos(String numeroBilletera, int mes, int anio){
        return transaccionRepositorio.obtenerPorcentajeGastosIngresos(numeroBilletera, mes, anio);
    }

}
