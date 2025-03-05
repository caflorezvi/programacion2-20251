package co.edu.uniquindio.billetera.repositorios;

import co.edu.uniquindio.billetera.modelo.entidades.Transaccion;
import co.edu.uniquindio.billetera.modelo.vo.PorcentajeGastosIngresos;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TransaccionRepositorio {
    private final List<Transaccion> transacciones;

    public TransaccionRepositorio() {
        this.transacciones = new ArrayList<>();
    }

    public void agregar(Transaccion transaccion) {
        transacciones.add(transaccion);
    }

    public List<Transaccion> obtenerPorBilletera(String numeroBilletera) {
        return transacciones.stream()
                .filter(t -> t.getBilleteraOrigen().getNumero().equals(numeroBilletera) ||
                        t.getBilleteraDestino().getNumero().equals(numeroBilletera))
                .collect(Collectors.toList());
    }

    public List<Transaccion> obtenerPorPeriodo(String numeroBilletera, LocalDateTime inicio, LocalDateTime fin) {
        return transacciones.stream()
                .filter(t ->
                        t.getBilleteraOrigen().getNumero().equals(numeroBilletera) ||
                        t.getBilleteraDestino().getNumero().equals(numeroBilletera))
                .filter(t ->
                        t.getFecha().isAfter(inicio) && t.getFecha().isBefore(fin))
                .collect(Collectors.toList());
    }

    public PorcentajeGastosIngresos obtenerPorcentajeGastosIngresos(String numeroBilletera, int mes, int anio) {

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
