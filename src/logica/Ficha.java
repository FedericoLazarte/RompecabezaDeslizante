package logica;

public class Ficha {
	private int valor;
	
	public Ficha(int valor) {
		this.valor = valor;
	}
	
	public int obtenerValorFicha() {
		return this.valor;
	}
	
	public void intercambiarValores(Ficha otraFicha) {
		int valorFichaAux = this.valor;
		this.valor = otraFicha.valor;
		otraFicha.valor = valorFichaAux;
	}
}