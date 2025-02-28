package co.edu.uniquindio.billetera.modelo.vo;

import co.edu.uniquindio.billetera.modelo.entidades.Transaccion;

import java.util.ArrayList;

public class SaldoTransaccionesBilletera {

    private float saldo;
    private ArrayList<Transaccion> transacciones;

    public SaldoTransaccionesBilletera(float saldo, ArrayList<Transaccion> transacciones) {
        this.saldo = saldo;
        this.transacciones = transacciones;
    }

    public float getSaldo() {
        return saldo;
    }

    public ArrayList<Transaccion> getTransacciones() {
        return transacciones;
    }
}
