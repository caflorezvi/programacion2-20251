package co.edu.uniquindio.billetera.modelo.entidades;

import co.edu.uniquindio.billetera.config.Constantes;
import lombok.Getter;

@Getter
public class Billetera {
    private String numero;
    private float saldo;
    private Usuario usuario;

    public Billetera(String numero, float saldo, Usuario usuario) {
        this.numero = numero;
        this.saldo = saldo;
        this.usuario = usuario;
    }

    public boolean tieneSaldo(float monto) {
        float montoConComision = monto + Constantes.COMISION;
        return saldo >= montoConComision;
    }

    public void retirar(float monto) throws Exception{

        float montoConComision = monto + Constantes.COMISION;

        if (montoConComision <= 0) {
            throw new Exception("El monto a retirar debe ser mayor a cero");
        }

        if (!tieneSaldo(monto)) {
            throw new Exception("Saldo insuficiente");
        }

        saldo -= montoConComision;
    }

    public void depositar(float monto) throws Exception {

        if (monto <= 0){
            throw new Exception("El monto a retirar debe ser mayor a cero");
        }

        saldo += monto;
    }

}
