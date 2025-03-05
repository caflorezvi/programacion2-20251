package co.edu.uniquindio.billetera.modelo.vo;

import co.edu.uniquindio.billetera.modelo.entidades.Transaccion;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Getter
public class SaldoTransaccionesBilletera {

    private float saldo;
    private List<Transaccion> transacciones;

}
