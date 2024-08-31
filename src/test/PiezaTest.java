package test;

import static org.junit.Assert.*;

import org.junit.Test;

import logica.Pieza;

public class PiezaTest {

	@Test(expected = IllegalArgumentException.class)
	public void piezaValorNoValidoTest() {
		Pieza pieza = new Pieza(-1);
	}
	
	@Test
	public void intercarcambiarValoresCorrectamenteTest() {
		Pieza pieza = new Pieza(1);
		Pieza pieza2 = new Pieza(2);
		
		int valorEsperadoDePieza = pieza2.obtenerValorPieza();
		
		pieza.intercambiarValores(pieza2);
		
		assertEquals(valorEsperadoDePieza, pieza.obtenerValorPieza());
	}
	
	@Test
	public void intercambiarValoresIgualesTest() {
		Pieza pieza = new Pieza(1);
		Pieza pieza2 = new Pieza(1);
		int valorEsperadoDePieza = pieza2.obtenerValorPieza();
		assertEquals(valorEsperadoDePieza, pieza.obtenerValorPieza());
	}

}
