package logica;

import java.util.ArrayList;
import java.util.Random;

public class RompeCabeza {
    private Pieza[][] piezas;
    private ArrayList<Pieza> piezasCorrectasParaGanar;
    private final int filas;
    private final int columnas;
    private final static int VACIO = 0;
    private int[] posicionActualVacio;
    private int movimientos;


    public RompeCabeza(int tamanio) {
        this.filas = tamanio;
        this.columnas = tamanio;
        this.piezas = new Pieza[filas][columnas];
        this.piezasCorrectasParaGanar = new ArrayList<>();
        this.posicionActualVacio = new int[2];
        inicializarTablero();
    }

    private void inicializarTablero() {
        inicializarPiezas();
        mezclarPiezas();
        this.movimientos = 0;
    }

    public void reiniciarJuego() {
        inicializarTablero();
    }

    //Crea las piezas y las coloca en orden (partimos de un tablero ordenado)
    private void inicializarPiezas() {
        int numPieza = 1;
        for (int fila = 0; fila < filas; fila++) {
            for (int col = 0; col < columnas; col++) {
                this.piezas[fila][col] = new Pieza(numPieza);
                numPieza++;
            }
        }
        inicializarPiezaVacia();

        //PRUEBA
        System.out.println("Posicion del vacio: "+ posicionActualVacio[0]+","+posicionActualVacio[1]);
    }

    //Crea la pieza vacía la esquina inferior derecha del tablero e inicializa su posición
    private void inicializarPiezaVacia() {
        this.piezas[filas-1][columnas-1] = new Pieza(VACIO);
        actualizarPosicionVacio(filas-1, columnas-1);

    }

    private void actualizarPosicionVacio(int fila,int columna) {
        posicionActualVacio[0] = fila;
        posicionActualVacio[1] = columna;
    }

    //Desde un tablero ordenado, se mezclan las piezas moviendolas un número determinado de veces
    //Las piezas no se mueven a la última celda donde estuvieron
    private void mezclarPiezas() {
        Random random = new Random();
        int filaRandom;
        int columnaRandom;

        moverCelda(filas-1, columnas-2);

        //Se mezcla hasta que la celda inferior derecha sea vacía otra vez
        while(piezas[filas-1][columnas-1].obtenerValorPieza() != 0) {
            filaRandom = random.nextInt(0,this.filas);
            columnaRandom = random.nextInt(0,this.columnas);

            int filaVacio = posicionActualVacio[0];
            int columnaVacio = posicionActualVacio[1];

            //Al momento de mezclar, solo se mueven las piezas adyacentes e incorrectas para ganar
            if (esAdyacente(filaRandom, columnaRandom, filaVacio, columnaVacio)) {
                if (esPiezaIncorrecta(filaRandom, columnaRandom)) {
                    moverCelda(filaRandom, columnaRandom);
                }
            }
        }
    }

    public boolean moverCelda(int fila, int col) {
        int filaVacio = posicionActualVacio[0];
        int columnaVacio = posicionActualVacio[1];

        if (esAdyacente(fila, col, filaVacio, columnaVacio)) {

            //Si se está moviendo la pieza equivocada
            if (esPiezaIncorrecta(fila, col)) {
                piezasCorrectasParaGanar.add(piezas[filaVacio][columnaVacio]);
            }
            //Si se está moviendo la pieza correcta
            else {
                piezasCorrectasParaGanar.removeLast();
            }

            piezas[filaVacio][columnaVacio].intercambiarValores(piezas[fila][col]);
            //Cada vez que se mueve una pieza se actualiza la nueva posición de la pieza vacía
            actualizarPosicionVacio(fila,col);
            this.movimientos++;

            //PRUEBA
            System.out.println("Se movió una ficha");
            System.out.println("Posicion del vacio: "+ posicionActualVacio[0]+","+posicionActualVacio[1]);
            System.out.println(sugerirMovimiento());

            return true;
        }
        return false;
    }

    //Devuelve si una pieza es incorrecta para ganar, es decir, que si la pieza se mueve desordena el tablero
    private boolean esPiezaIncorrecta(int fila, int columna) {
        if (piezasCorrectasParaGanar.isEmpty())
            return true;
        if (!piezas[fila][columna].equals(piezasCorrectasParaGanar.getLast()))
            return true;
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

    private boolean esAdyacente(int fila1, int col1, int fila2, int col2) {
        return (Math.abs(fila1 - fila2) == 1 && col1 == col2) ||
                (Math.abs(col1 - col2) == 1 && fila1 == fila2);
    }

    public Pieza sugerirMovimiento() {
        if (piezasCorrectasParaGanar.isEmpty()) {
            return null;
        }
        return piezasCorrectasParaGanar.getLast();
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
}