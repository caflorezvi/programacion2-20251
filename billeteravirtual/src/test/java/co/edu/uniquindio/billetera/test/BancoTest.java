package co.edu.uniquindio.billetera.test;

import co.edu.uniquindio.billetera.modelo.entidades.Banco;
import co.edu.uniquindio.billetera.modelo.entidades.Billetera;
import co.edu.uniquindio.billetera.modelo.entidades.Transaccion;
import co.edu.uniquindio.billetera.modelo.entidades.Usuario;
import co.edu.uniquindio.billetera.modelo.enums.Categoria;
import co.edu.uniquindio.billetera.modelo.vo.SaldoTransaccionesBilletera;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class BancoTest {

    private Banco banco;

    @BeforeEach
    public void crearDatosPrueba(){

        banco = new Banco();

        Usuario usuario1 = new Usuario("123", "Lucas", "Calle 123", "lucas@email.com", "1234");
        Usuario usuario2 = new Usuario("234", "Pedro", "Calle 234", "pedro@email.com", "2234");
        Usuario usuario3 = new Usuario("345", "Maria", "Calle 345", "maria@email.com", "3234");

        banco.setUsuarios( new ArrayList<>(
            List.of(
                usuario1, usuario2, usuario3
            )
        ));

        Billetera billetera1 = new Billetera("1528017256", 10000, usuario1);
        Billetera billetera2 = new Billetera("1826401633", 25000, usuario2);

        banco.setBilleteras( new ArrayList<>(
            List.of(
                billetera1, billetera2
            )
        ));

        billetera1.obtenerTransacciones().add(
                new Transaccion(
                        "dsf78ds78fdsgf",
                        5000,
                        LocalDateTime.of(2025, 1, 10, 20, 5),
                        Categoria.OTROS,
                        billetera1,
                        billetera2,
                        0
                )
        );

    }

    @Test
    public void registrarUsuarioTest() {
        assertDoesNotThrow(() ->
            banco.registrarUsuario("789", "Carlos", "Calle 816", "carlos@email.com", "1212")
        );
    }

    @Test
    public void buscarUsuarioTest() {
        assertDoesNotThrow(() -> {
            Usuario usuario = banco.buscarUsuario("345");
            assertNotNull(usuario);
        });
    }

    @Test
    public void crearBilleteraTest() {
        assertDoesNotThrow(() -> {
            Usuario usuario = banco.buscarUsuario("345");
            banco.registrarBilletera(usuario);

            Billetera billetera = banco.buscarBilleteraUsuario("345");
            assertNotNull(billetera);
        });
    }

    @Test
    public void realizarTransaccionTest() {
        assertDoesNotThrow(() ->
            banco.realizarTransferencia("1528017256", "1826401633", 2000, Categoria.TRANSPORTE)
        );
    }

    @Test
    public void consultarSaldoTest() {

        assertDoesNotThrow(() -> {

            banco.realizarTransferencia("1528017256", "1826401633", 2000, Categoria.TRANSPORTE);

            SaldoTransaccionesBilletera consulta1 = banco.consultarSaldoYTransacciones("123", "1234");

            assertEquals(10000-2000-200, consulta1.getSaldo());

        });

    }

    @Test
    public void consultarTransaccionesTest() {

        assertDoesNotThrow(() -> {

            banco.realizarTransferencia("1528017256", "1826401633", 2000, Categoria.TRANSPORTE);

            List<Transaccion> transacciones = banco.obtenerTransacciones("1826401633");
            assertEquals(1, transacciones.size());

        });

    }

    @Test
    public void consultarTransaccionesPeriodoTest() {
        List<Transaccion> lista = banco.obtenerTransaccionesPeriodo("1528017256", LocalDateTime.of(2025, 1, 1, 0, 0), LocalDateTime.of(2025, 1, 15, 0, 0));
        assertEquals(1, lista.size());
    }

}
