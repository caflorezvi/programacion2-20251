package co.edu.uniquindio.billetera.test;

import co.edu.uniquindio.billetera.servicios.BancoServicio;
import co.edu.uniquindio.billetera.modelo.entidades.Usuario;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BancoTest {

    @Test
    public void registrarUsuarioTest() {

        BancoServicio banco = new BancoServicio();

        assertDoesNotThrow(() ->
            banco.registrarUsuario("789", "Carlos", "Calle 816", "carlos@email.com", "1212")
        );
    }


}
