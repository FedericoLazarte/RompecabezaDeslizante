package test;

import static org.junit.Assert.*;

import org.junit.Ignore;
import org.junit.Test;
import logica.Pieza;
import logica.RompeCabeza;

public class RompeCabezaTest {

    @Test
    public void noSeRealizoNingunMovimientoTest() {
        RompeCabeza rc = new RompeCabeza(4);
        assertEquals(0, rc.verCantidadMovimientosRealizados());
    }

    @Test
    public void realizarMovimientoDePiezaNoContiguaACeroTest() {
        RompeCabeza rc = new RompeCabeza(4);
        rc.moverCelda(0, 0);
        assertEquals(0, rc.verCantidadMovimientosRealizados());
    }

    @Test
    public void seRealizoUnMovimientoPiezaContiguaACeroTest() {
        RompeCabeza rc = new RompeCabeza(4);
        rc.moverCelda(3, 2);
        assertEquals(1, rc.verCantidadMovimientosRealizados());
    }

    @Test
    public void seRealizoMasDeUnMovimientoDePiezaContiguaACeroTest() {
        RompeCabeza rc = new RompeCabeza(4);
        rc.moverCelda(3, 2);
        rc.moverCelda(3, 1);
        assertEquals(2, rc.verCantidadMovimientosRealizados());
    }

    @Ignore
    @Test
    public void estaGanadoElJuegoTest() {
        RompeCabeza puzzle = new RompeCabeza(4);
        @SuppressWarnings("unused")
        Pieza[][] piezas = {
                {new Pieza(1), new Pieza(2), new Pieza(3), new Pieza(4)},
                {new Pieza(5), new Pieza(6), new Pieza(7), new Pieza(8)},
                {new Pieza(9), new Pieza(10), new Pieza(11), new Pieza(12)},
                {new Pieza(13), new Pieza(14), new Pieza(15), new Pieza(0)}
        };
        //  puzzle.setRompeCabeza(piezas); Para probarTest se crea un set en RompeCabeza
        assertTrue(puzzle.estaGanado());
    }

    @Ignore
    @Test //SE IGNORA PORQUE EL TABLERO TODAVÍA NO SE MEZCLA
    public void noEstaGanadoElJuegoTest() {
        RompeCabeza puzzle = new RompeCabeza(4);
        assertFalse(puzzle.estaGanado());
    }
}
