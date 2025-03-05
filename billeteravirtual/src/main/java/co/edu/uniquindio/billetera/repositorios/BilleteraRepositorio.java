package co.edu.uniquindio.billetera.repositorios;

import co.edu.uniquindio.billetera.modelo.entidades.Billetera;
import java.util.ArrayList;
import java.util.List;

public class BilleteraRepositorio {
    private final List<Billetera> billeteras;

    public BilleteraRepositorio() {
        billeteras = new ArrayList<>();
    }

    public void agregar(Billetera billetera) {
        billeteras.add(billetera);
    }

    public Billetera buscarPorNumero(String numero) {
        return billeteras.stream()
                .filter(b -> b.getNumero().equals(numero))
                .findFirst()
                .orElse(null);
    }

    public Billetera buscarPorUsuario(String idUsuario) {
        return billeteras.stream()
                .filter(b -> b.getUsuario().getId().equals(idUsuario))
                .findFirst()
                .orElse(null);
    }

    public List<Billetera> listarTodas() {
        return billeteras;
    }
}
