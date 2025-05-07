package co.edu.uniquindio.clinica.servicio;

import co.edu.uniquindio.clinica.modelo.Cita;
import co.edu.uniquindio.clinica.modelo.Factura;
import co.edu.uniquindio.clinica.modelo.Paciente;
import co.edu.uniquindio.clinica.modelo.enums.TipoServicio;
import co.edu.uniquindio.clinica.modelo.enums.TipoSuscripcion;
import co.edu.uniquindio.clinica.modelo.factory.FactorySuscripcion;
import co.edu.uniquindio.clinica.modelo.factory.Suscripcion;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ClinicaServicio implements IClinicaServicio{

    private final PacienteServicio pacienteServicio;
    private final CitaServicio citaServicio;

    public ClinicaServicio() {
        this.pacienteServicio = new PacienteServicio();
        this.citaServicio = new CitaServicio();
    }

    @Override
    public void registrarPaciente(String identificacion, String nombre, String telefono, String correo, TipoSuscripcion suscripcion) throws Exception {
        Suscripcion suscripcionPaciente = FactorySuscripcion.crearSuscripcion(suscripcion);
        pacienteServicio.agregarPaciente(identificacion, nombre, telefono, correo, suscripcionPaciente);
    }

    @Override
    public Factura agendarCita(String idPaciente, TipoServicio tipoServicio, LocalDate dia, String hora) throws Exception {
        Paciente paciente = pacienteServicio.obtenerPaciente(idPaciente);
        Cita cita = citaServicio.agregarCita(paciente, tipoServicio, dia, hora);
        Factura factura = paciente.getSuscripcion().generarFacturaCobro(tipoServicio);
        cita.setFactura(factura);

        String mensaje = paciente
                + "\nServicio: " + tipoServicio.getNombre()
                + "\nDía: " + cita.getDia()
                + "\nHora: " + cita.getHora()
                + "\nSubtotal: $" + factura.getSubtotal()
                + "\nTotal: $" + factura.getTotal();

        //EmailServicio.enviarNotificacion(paciente.getEmail(), "Se ha agendado una consulta médica", mensaje);

        return factura;

    }

    @Override
    public void cancelarCita(String citaId) throws Exception{
        citaServicio.cancelarCita(citaId);
    }

    @Override
    public List<Paciente> listarPacientes() {
        return pacienteServicio.obtenerPacientes();
    }

    @Override
    public List<Cita> listarCitas() {
        return citaServicio.obtenerCitas();
    }

    @Override
    public List<String> generarHorarios() {
        List<String> horarios = new ArrayList<>();
        for (int i = 8; i < 18; i++) {
            if(i < 10){
                horarios.add("0" + i + ":00");
                horarios.add("0" + i + ":30");
            }else{
                horarios.add(i + ":00");
                horarios.add(i + ":30");
            }
        }
        return horarios;
    }
}
