package logica;

public class TableroFichas {
	private Ficha[][] fichas;
	private final static int TABLERO_FILAS = 4;
	private final static int TABLERO_COLUMNAS = 4;
	
	public TableroFichas( ) {
		this.fichas = new Ficha[TABLERO_FILAS][TABLERO_COLUMNAS];
	}
}