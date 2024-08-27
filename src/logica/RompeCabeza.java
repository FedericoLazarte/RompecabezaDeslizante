package logica;

import java.util.Collections;
import java.util.List;
import java.util.ArrayList;


public class RompeCabeza {
	private Pieza[][] piezas;
	private final static int TABLERO_FILAS = 4;
	private final static int TABLERO_COLUMNAS = 4;
	private final static int VACIO = 0;
	private int movimientos;

	
	public RompeCabeza( ) {
		this.piezas = new Pieza[TABLERO_FILAS][TABLERO_COLUMNAS];
		this.movimientos = 0;
		inicializarFichasDesordenadas();
	}
	
	public Pieza dameFicha(int i, int j) {
		return this.piezas[i][j];
	}
	
	public int totalFilas() {
		return TABLERO_FILAS;
	}
	
	public int totalColumnas() {
		return TABLERO_COLUMNAS;
	}
	
	public int verCantidadMovimientosRealizados() {
		return this.movimientos;
	}
	
	public boolean moverCelda(int fila, int col) {
        int[] posVacio = encontrarPosicionVacio();
        int filaVacio = posVacio[0];
        int columnaVacio = posVacio[1];

        if (esAdyacente(fila, col, filaVacio, columnaVacio)) {
            piezas[filaVacio][columnaVacio].intercambiarValores(piezas[fila][col]);
            this.movimientos++;
            return true;
        }
        return false;
    }
	
	public boolean ganarJuego() {
        int contador = 1; 
        for (int fila = 0; fila < TABLERO_FILAS; fila++) {
            for (int col = 0; col < TABLERO_COLUMNAS; col++) {
                int valorActual = piezas[fila][col].obtenerValorFicha();
                if (fila == TABLERO_FILAS - 1 && col == TABLERO_COLUMNAS - 1) {
                    return valorActual == VACIO;
                }
                if (valorActual != contador) {
                    return false;
                }
                contador++;
            }
        }
        return true;
    }
	
	private int[] encontrarPosicionVacio() {
        for (int fila = 0; fila < TABLERO_FILAS; fila++) {
            for (int col = 0; col < TABLERO_COLUMNAS; col++) {
                if (this.dameFicha(fila, col).obtenerValorFicha() == VACIO) {
                    return new int[]{fila, col};
                }
            }
        }
        throw new RuntimeException("No se encontró el 0");
    }

	private boolean esAdyacente(int fila1, int col1, int fila2, int col2) {
        return (Math.abs(fila1 - fila2) == 1 && col1 == col2) ||  
               (Math.abs(col1 - col2) == 1 && fila1 == fila2);
    }
	
	private void inicializarFichasDesordenadas() {
        List<Pieza> listaFichas = new ArrayList<>();
        for (int valor = 1; valor <= 15; valor++) {
            listaFichas.add(new Pieza(valor));
        }
        Collections.shuffle(listaFichas); // con el método suffle desordenados la listaFichas!!!!
        int index = 0;
        for (int fila = 0; fila < TABLERO_FILAS; fila++) {
            for (int col = 0; col < TABLERO_COLUMNAS; col++) {
                if (index < listaFichas.size()) {
                    this.piezas[fila][col] = listaFichas.get(index);
                    index++;
                } else {
                    this.piezas[fila][col] = new Pieza(VACIO);
                }
            }
        }
    }
	
}