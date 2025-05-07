package co.edu.uniquindio.clinica.repositorios;

import co.edu.uniquindio.clinica.modelo.Cita;

import java.util.ArrayList;
import java.util.List;

public class CitaRepositorio {

    private List<Cita> citas;

    public CitaRepositorio() {
        this.citas = new ArrayList<>();
    }

    public void agregar(Cita cita) {
        citas.add(cita);
    }

    public void eliminar(Cita cita) {
        citas.remove(cita);
    }

    public Cita obtener(String id) {
        for (Cita cita : citas) {
            if (cita.getId().equals(id)) {
                return cita;
            }
        }
        return null;
    }

    public List<Cita> listar() {
        return new ArrayList<>(citas);
    }
}
