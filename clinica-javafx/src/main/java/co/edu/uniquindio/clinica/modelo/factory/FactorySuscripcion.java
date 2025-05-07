package co.edu.uniquindio.clinica.modelo.factory;

import co.edu.uniquindio.clinica.modelo.enums.TipoSuscripcion;

public class FactorySuscripcion {

    public static Suscripcion crearSuscripcion(TipoSuscripcion tipoSuscripcion) {

        return switch (tipoSuscripcion){
            case BASICA -> new SuscripcionBasica();
            case PREMIUM -> new SuscripcionPremium();
        };

    }

}
