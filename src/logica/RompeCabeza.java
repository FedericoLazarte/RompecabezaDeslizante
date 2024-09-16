package logica;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;

public class RompeCabeza {
    private Pieza[][] piezas;
    private LinkedList<Pieza> piezasCorrectasParaGanar;
    private final int filas;
    private final int columnas;
    private final static int VACIO = 0;
    private int[] posicionActualVacio;
    private int movimientos;

    public RompeCabeza(int tamanio) {
        this.filas = tamanio;
        this.columnas = tamanio;
        this.piezas = new Pieza[filas][columnas];
        this.piezasCorrectasParaGanar = new LinkedList<>();
        this.posicionActualVacio = new int[2];
        inicializarTablero();
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

    public void reiniciarJuego() {
        inicializarTablero();
    }

    public boolean moverCelda(int fila, int col) {
        int filaVacio = posicionActualVacio[0];
        int columnaVacio = posicionActualVacio[1];
        if (esAdyacente(fila, col, filaVacio, columnaVacio)) {
            if (esPiezaIncorrecta(fila, col)) {
                piezasCorrectasParaGanar.add(piezas[filaVacio][columnaVacio]);
            }
            else {
                piezasCorrectasParaGanar.removeLast();
            }
            piezas[filaVacio][columnaVacio].intercambiarValores(piezas[fila][col]);
            actualizarPosicionVacio(fila,col);
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

    public int sugerirMovimiento() {
        if (piezasCorrectasParaGanar.isEmpty()) {
            return 0;
        }
        return piezasCorrectasParaGanar.getLast().obtenerValorPieza();
    }

    private void inicializarTablero() {
        inicializarPiezas();
        mezclarPiezas();
        this.movimientos = 0;
    }

    private void inicializarPiezas() {
        int numPieza = 1;
        for (int fila = 0; fila < filas; fila++) {
            for (int col = 0; col < columnas; col++) {
                this.piezas[fila][col] = new Pieza(numPieza);
                numPieza++;
            }
        }
        inicializarPiezaVacia();
    }

    private void inicializarPiezaVacia() {
        this.piezas[filas-1][columnas-1] = new Pieza(VACIO);
        actualizarPosicionVacio(filas-1, columnas-1);

    }

    private void actualizarPosicionVacio(int fila,int columna) {
        posicionActualVacio[0] = fila;
        posicionActualVacio[1] = columna;
    }

    private void mezclarPiezas() {
        Random random = new Random();
        int filaRandom;
        int columnaRandom;
        moverCelda(filas-1, columnas-2);
        while(piezas[filas-1][columnas-1].obtenerValorPieza() != 0) {
            filaRandom = random.nextInt(0,this.filas);
            columnaRandom = random.nextInt(0,this.columnas);
            int filaVacio = posicionActualVacio[0];
            int columnaVacio = posicionActualVacio[1];
            if (esAdyacente(filaRandom, columnaRandom, filaVacio, columnaVacio)) {
                if (esPiezaIncorrecta(filaRandom, columnaRandom)) {
                    moverCelda(filaRandom, columnaRandom);
                }
            }
        }
    }

    private boolean esPiezaIncorrecta(int fila, int columna) {
        if (piezasCorrectasParaGanar.isEmpty())
            return true;
        return !piezas[fila][columna].equals(piezasCorrectasParaGanar.getLast());
    }

    private boolean esAdyacente(int fila1, int col1, int fila2, int col2) {
        return (Math.abs(fila1 - fila2) == 1 && col1 == col2) ||
                (Math.abs(col1 - col2) == 1 && fila1 == fila2);
    }
}