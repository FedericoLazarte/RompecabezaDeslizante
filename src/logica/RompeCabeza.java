package logica;

import java.util.ArrayList;
import java.util.Random;

public class RompeCabeza {
    private Pieza[][] piezas;
    private ArrayList<Pieza> piezasCorrectasParaGanar;
    private final int filas;
    private final int columnas;
    private final static int VACIO = 0;
    private int movimientos;
    

    public RompeCabeza(int tamanio) {
        this.filas = tamanio;
        this.columnas = tamanio;
        this.piezas = new Pieza[filas][columnas];
        this.piezasCorrectasParaGanar = new ArrayList<Pieza>();
        inicializarFichas();
//        mezclarFichas(200000); //¡NO FUNCIONA DE MOMENTO!
        this.movimientos = 0; //comienza en 0 tras mezclar el tablero
    }
    
    public void reiniciarJuego() {
	    inicializarFichas();
//	    mezclarFichas(200000); //¡NO FUNCIONA DE MOMENTO!
	    this.movimientos = 0; //luego de mezclar el tablero nuevamente, se reinician los movimientos
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
        	
        	//¡¡FALTA REFACTORIZAR!!
        	
        	//Si se está moviendo la pieza equivocada
        	if (piezasCorrectasParaGanar.isEmpty() || !(piezas[fila][col].equals(piezasCorrectasParaGanar.getLast()))) { 
        		piezasCorrectasParaGanar.add(piezas[filaVacio][columnaVacio]);
            }
        	//Si se está moviendo la pieza correcta
            else {
            	piezasCorrectasParaGanar.removeLast();
            }
        	
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

    private void inicializarFichas() {
    	int numPieza = 1;
        for (int fila = 0; fila < filas; fila++) {
            for (int col = 0; col < columnas; col++) {
            	this.piezas[fila][col] = new Pieza(numPieza);
            	numPieza++;
            }
        }
        this.piezas[filas-1][columnas-1] = new Pieza(VACIO); //se crea una pieza vacía en la esquina inferior derecha del tablero
    }

    //¡HAY QUE MEJORAR! MEZCLA MUY POCO Y EL 0 APARECE EN CUALQUIER LADO
    private void mezclarFichas(int nivelDeEntropia) { 
    	Random random = new Random();
    	int filaRandom;
    	int colRandom;
    	
    	//Mientras más grande sea el nivel de entropía, más aleatorio va a ser el tablero
    	for(int i = 0; i < nivelDeEntropia; i++) {
    		filaRandom = random.nextInt(1,this.filas);
    		colRandom = random.nextInt(1,this.columnas);
    		moverCelda(filaRandom, colRandom);
    	}
    	
    	//Posicionamos el vacío en la esquina inferior derecha (SIRVE PARA PONER EL 0 EN LA ESQUINA INFERIOR)
//    	int[] posVacio = encontrarPosicionVacio();
//        int filaVacio = posVacio[0];
//        int columnaVacio = posVacio[1];
//        
//        this.piezas[filas-1][columnas-1].intercambiarValores(piezas[filaVacio][columnaVacio]);
    }
    
    public Pieza sugerirMovimiento() {
    	return piezasCorrectasParaGanar.getLast();
	}
    

}