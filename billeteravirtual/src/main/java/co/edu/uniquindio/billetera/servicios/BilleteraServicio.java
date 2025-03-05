package co.edu.uniquindio.billetera.servicios;

import co.edu.uniquindio.billetera.modelo.entidades.Billetera;
import co.edu.uniquindio.billetera.modelo.entidades.Usuario;
import co.edu.uniquindio.billetera.repositorios.BilleteraRepositorio;
import java.util.Random;

public class BilleteraServicio {

    private final BilleteraRepositorio billeteraRepositorio;

    public BilleteraServicio() {
        this.billeteraRepositorio = new BilleteraRepositorio();
    }

    public void registrarBilletera(Usuario usuario) {
        String numero = generarNumeroUnico();
        Billetera billetera = new Billetera(numero, 0, usuario);
        billeteraRepositorio.agregar(billetera);
    }

    private String generarNumeroUnico() {
        String numero = generarNumeroAleatorio();
        while (billeteraRepositorio.buscarPorNumero(numero) != null) {
            numero = generarNumeroAleatorio();
        }
        return numero;
    }

    private String generarNumeroAleatorio() {
        Random random = new Random();
        StringBuilder numero = new StringBuilder();
        for (int i = 0; i < 10; i++) {
            numero.append(random.nextInt(10));
        }
        return numero.toString();
    }

    public Billetera buscarBilletera(String numero) throws Exception{
        Billetera billetera = billeteraRepositorio.buscarPorNumero(numero);
        if (billetera == null) throw new Exception("No existe el billetera con el número " + numero);
        return billetera;
    }

    public Billetera buscarBilleteraPorUsuario(String idUsuario) throws Exception{
        Billetera billetera = billeteraRepositorio.buscarPorUsuario(idUsuario);
        if (billetera == null) throw new Exception("No existe la billetera para el usuario con el id: " + idUsuario);
        return billetera;
    }
}
