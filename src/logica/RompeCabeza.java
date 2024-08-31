package logica;

import java.util.Collections;
import java.util.List;
import java.util.ArrayList;

public class RompeCabeza {
    private Pieza[][] piezas;
    private final int filas;
    private final int columnas;
    private final static int VACIO = 0;
    private int movimientos;

    public RompeCabeza(int tamanio) {
        this.filas = tamanio;
        this.columnas = tamanio;
        this.piezas = new Pieza[filas][columnas];
        this.movimientos = 0;
        inicializarFichasDesordenadas();
    }

    public Pieza damePieza(int i, int j) {
        return this.piezas[i][j];
    }

    public int totalFilas() {
        return filas;
    }

    public int totalColumnas() {
        return columnas;
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

    public boolean estaGanado() {
        int contador = 1; 
        for (int fila = 0; fila < filas; fila++) {
            for (int col = 0; col < columnas; col++) {
                int valorActual = piezas[fila][col].obtenerValorPieza();
                if (fila == filas - 1 && col == columnas - 1) {
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
        for (int fila = 0; fila < filas; fila++) {
            for (int col = 0; col < columnas; col++) {
                if (this.damePieza(fila, col).obtenerValorPieza() == VACIO) {
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
        List<Pieza> listaPiezas = new ArrayList<>();
        for (int valor = 1; valor <= totalDePiezas() - 1; valor++) {
            listaPiezas.add(new Pieza(valor));
        }
        Collections.shuffle(listaPiezas);
        int index = 0;
        for (int fila = 0; fila < filas; fila++) {
            for (int col = 0; col < columnas; col++) {
                if (index < listaPiezas.size()) {
                    this.piezas[fila][col] = listaPiezas.get(index);
                    index++;
                } else {
                    this.piezas[fila][col] = new Pieza(VACIO);
                }
            }
        }
    }

    private int totalDePiezas() {
        return filas * columnas;
    }
}


