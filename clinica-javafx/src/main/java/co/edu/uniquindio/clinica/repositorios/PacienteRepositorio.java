package co.edu.uniquindio.clinica.repositorios;

import co.edu.uniquindio.clinica.modelo.Paciente;
import java.util.ArrayList;
import java.util.List;

public class PacienteRepositorio {

    private final List<Paciente> pacientes;

    public PacienteRepositorio() {
        this.pacientes = new ArrayList<>();
    }

    public void agregar(Paciente paciente) {
        pacientes.add(paciente);
    }

    public Paciente obtener(String identificacion) {
        for (Paciente paciente : pacientes) {
            if (paciente.getCedula().equals(identificacion)) {
                return paciente;
            }
        }
        return null;
    }

    public List<Paciente> listar() {
        return pacientes;
    }

}
