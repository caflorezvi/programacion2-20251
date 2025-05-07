package co.edu.uniquindio.clinica.modelo.factory;

import co.edu.uniquindio.clinica.modelo.Factura;
import co.edu.uniquindio.clinica.modelo.Servicio;
import co.edu.uniquindio.clinica.modelo.enums.TipoServicio;
import co.edu.uniquindio.clinica.modelo.enums.TipoSuscripcion;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public class SuscripcionBasica implements Suscripcion{

    @Override
    public TipoSuscripcion getNombreSuscripcion() {
        return TipoSuscripcion.BASICA;
    }

    @Override
    public List<Servicio> getServiciosDisponibles() {
        return List.of(
                Servicio.builder()
                        .precio(20000)
                        .nombre(TipoServicio.OFTALMOLOGIA)
                        .build(),
                Servicio.builder()
                        .precio(0)
                        .nombre(TipoServicio.CONSULTA_GENERAL)
                        .build()
        );
    }

    @Override
    public Factura generarFacturaCobro(TipoServicio tipoServicio) {

        double subtotal = tipoServicio.getPrecio();
        double total;

        Servicio servicio = getServiciosDisponibles().stream()
                .filter(s -> s.getNombre().equals(tipoServicio))
                .findFirst().orElse(null);

        if(servicio == null){
            total = subtotal;
        }else{
            total = servicio.getPrecio();
        }

        return Factura.builder()
                .id(String.valueOf(UUID.randomUUID()))
                .fecha(LocalDateTime.now())
                .subtotal(subtotal)
                .total(total)
                .build();
    }

}
