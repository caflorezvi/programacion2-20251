package co.edu.uniquindio.clinica.servicio;

import co.edu.uniquindio.clinica.modelo.Cita;
import co.edu.uniquindio.clinica.modelo.Paciente;
import co.edu.uniquindio.clinica.modelo.enums.TipoServicio;
import co.edu.uniquindio.clinica.repositorios.CitaRepositorio;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.UUID;

public class CitaServicio {

    private final CitaRepositorio citaRepositorio;

    public CitaServicio() {
        this.citaRepositorio = new CitaRepositorio();
    }

    public Cita agregarCita(Paciente paciente, TipoServicio tipoServicio, LocalDate dia, String hora) throws Exception {

        String mensajesValidacion = "";

        if(paciente == null ){
            mensajesValidacion += "No existe el paciente\n";
        }

        if(tipoServicio == null){
            mensajesValidacion += "Debe seleccionar un servicio\n";
        }

        if(dia == null){
            mensajesValidacion += "Debe seleccionar el día de la cita\n";
        }

        if(hora == null){
            mensajesValidacion += "Debe seleccionar la hora de la cita\n";
        }

        if(!mensajesValidacion.isEmpty()){
            throw new Exception(mensajesValidacion);
        }

        if(dia.isBefore(LocalDate.now())){
            throw new Exception("La fecha de la cita no puede ser anterior a la fecha actual");
        }

        LocalTime horaCita = LocalTime.parse(hora);

        if(!hayDisponibilidad(dia, horaCita, tipoServicio)){
            throw new Exception("Ya existe una cita agendada para el día y hora seleccionados");
        }

        Cita cita = Cita.builder()
                .paciente(paciente)
                .id(String.valueOf(UUID.randomUUID()))
                .dia(dia)
                .hora(horaCita)
                .servicio(tipoServicio)
                .build();

        citaRepositorio.agregar(cita);

        return cita;
    }

    public boolean hayDisponibilidad(LocalDate dia, LocalTime hora, TipoServicio tipoServicio) {
        for (Cita cita: citaRepositorio.listar()) {
            if(cita.getDia().equals(dia) && cita.getHora().equals(hora) && cita.getServicio() == tipoServicio){
                return false;
            }
        }
        return true;
    }

    public void cancelarCita(String citaId) throws Exception{
        Cita cita = obtenerCita(citaId);
        if(cita != null){
            citaRepositorio.eliminar(cita);
        } else {
            throw new Exception("No existe una cita con el ID ingresado");
        }
    }

    public Cita obtenerCita(String id) {
        return  citaRepositorio.obtener(id);
    }

    public List<Cita> obtenerCitas() {
        return citaRepositorio.listar();
    }
}
