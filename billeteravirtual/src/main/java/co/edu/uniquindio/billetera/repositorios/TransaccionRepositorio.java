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
                .filter(t ->
                        t.getBilleteraOrigen().getNumero().equals(numeroBilletera) ||
                        t.getBilleteraDestino().getNumero().equals(numeroBilletera))
                .collect(Collectors.toList());
    }

}
