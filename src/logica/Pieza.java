package logica;

public class Pieza {
	private int valor;
	
	public Pieza(int valor) {
		this.valor = valor;
	}
	
	public int obtenerValorPieza() {
		return this.valor;
	}
	
	public void intercambiarValores(Pieza otraPieza) {
		int valorPiezaAux = this.valor;
		this.valor = otraPieza.valor;
		otraPieza.valor = valorPiezaAux;
	}
}