package logica;

public class Pieza {
	private int valor;

	public Pieza(int valor) {
		valorInvalido(valor);
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

	private void valorInvalido(int valor) {
		if(valor < 0)
			throw new IllegalArgumentException("El valor de la pieza no puede ser menor a 0");
	}

	@Override
	public String toString() {
		return ("Ficha: "+this.valor);
	}
}