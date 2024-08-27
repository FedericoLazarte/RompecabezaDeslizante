package logica;

public class Pieza {
	private int valor;
	
	public Pieza(int valor) {
		this.valor = valor;
	}
	
	public int obtenerValorFicha() {
		return this.valor;
	}
	
	public void intercambiarValores(Pieza otraFicha) {
		int valorFichaAux = this.valor;
		this.valor = otraFicha.valor;
		otraFicha.valor = valorFichaAux;
	}
}