package co.edu.uniquindio.clinica.servicio;

import co.edu.uniquindio.clinica.modelo.Paciente;
import co.edu.uniquindio.clinica.modelo.factory.Suscripcion;
import co.edu.uniquindio.clinica.repositorios.PacienteRepositorio;
import java.util.List;
import java.util.regex.Pattern;

public class PacienteServicio {

    private final PacienteRepositorio pacienteRepositorio;

    public PacienteServicio() {
        this.pacienteRepositorio = new PacienteRepositorio();
    }

    public void agregarPaciente(String identificacion, String nombre, String telefono, String correo, Suscripcion suscripcion) throws Exception{

        String mensajesValidacion = "";

        if(identificacion == null || identificacion.isEmpty()){
            mensajesValidacion += "Debe ingresar la identificación del paciente\n";
        }

        if(!esSoloNumeros(identificacion)){
            mensajesValidacion += "La identificación solo debe contener números\n";
        }

        if(nombre == null || nombre.isEmpty()){
            mensajesValidacion += "Debe ingresar el nombre del paciente\n";
        }

        if(telefono == null || telefono.isEmpty()){
            mensajesValidacion += "Debe ingresar el teléfono del paciente\n";
        }

        if(correo == null || correo.isEmpty()){
            mensajesValidacion += "Debe ingresar el correo del paciente\n";
        }

        if(!esEmailValido(correo)){
            mensajesValidacion += "El correo ingresado no es válido\n";
        }

        if(suscripcion == null){
            mensajesValidacion += "Debe seleccionar una suscripción\n";
        }

        if(!mensajesValidacion.isEmpty()){
            throw new Exception(mensajesValidacion);
        }

        if(obtenerPaciente(identificacion)!=null){
            throw new Exception("Ya existe un paciente con la identificación ingresada");
        }

        Paciente paciente = Paciente.builder()
                .suscripcion(suscripcion)
                .cedula(identificacion)
                .nombre(nombre)
                .telefono(telefono)
                .email(correo)
                .build();

        pacienteRepositorio.agregar(paciente);

    }

    public Paciente obtenerPaciente(String identificacion) {
        return pacienteRepositorio.obtener(identificacion);
    }

    public List<Paciente> obtenerPacientes() {
        return pacienteRepositorio.listar();
    }

    public boolean esEmailValido(String email) {
        Pattern pattern =  Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,}$", Pattern.CASE_INSENSITIVE);
        return pattern.matcher(email).matches();
    }

    public boolean esSoloNumeros(String input) {
        return Pattern.compile("^[0-9]+$").matcher(input).matches();
    }

}
