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
import java.util.stream.Collectors;

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

        List<Transaccion> transacciones = transaccionRepositorio.obtenerPorBilletera(numeroBilletera);

        return transacciones.stream()
                .filter(t -> t.getFecha().isAfter(inicio) && t.getFecha().isBefore(fin))
                .collect(Collectors.toList());
    }

    public PorcentajeGastosIngresos obtenerPorcentajeGastosIngresos(String numeroBilletera, int mes, int anio){

        List<Transaccion> transacciones = transaccionRepositorio.obtenerPorBilletera(numeroBilletera);
        float ingresos = 0;
        float egresos = 0; //gastos

        for (Transaccion transaccion : transacciones){
            if(transaccion.getFecha().getMonthValue() == mes && transaccion.getFecha().getYear() == anio){
                if(transaccion.getBilleteraOrigen().getNumero().equals(numeroBilletera)) {
                    egresos += transaccion.getMonto() + transaccion.getComision();
                }else{
                    ingresos += transaccion.getMonto();
                }
            }
        }

        float total = ingresos + egresos;
        float porcentajeGastos = (egresos / total) * 100;
        float porcentajeIngresos = (ingresos / total) * 100;

        return new PorcentajeGastosIngresos(
                porcentajeGastos,
                porcentajeIngresos
        );

    }

}
