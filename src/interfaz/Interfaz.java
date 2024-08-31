package interfaz;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import logica.RompeCabeza;

public class Interfaz {
	private RompeCabeza tableroJuego;
	private JPanel panelContenedorDeLasFichas;
	JLabel labelMovimientosTexto;
	JButton boton;
	
	private JFrame frameJuego; //el anterior y se llamaba frame
	private JFrame frameInicio;
	boolean pantallaInicio = true; //está para poder hacer el cambio entre pantalla 
	JLabel labelTitulo;
	JLabel labelMensajeOpciones;
	JButton Iniciar;
	private JComboBox<String> comboBox; // declaro el box acá para poder usarlo luego 
	private int numeroDificultad;



	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Interfaz window = new Interfaz();
					window.mostrarPantallaInicio(); // se cambió para poder presentar y llamar este método para jugar con cual pantalla es visible y cual no 
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	
	//primero creamos las dos pantallas 
	public Interfaz() {
		crearFrameInicio();
		crearFrameJuego();
	}

	
	
	//este método pertenece al juego 
	private void initialize() {
        this.tableroJuego = new RompeCabeza(numeroDificultad);
		crearContenedorPiezas();
		actualizarTablero();
		visualizacionMovimientos();
	}

//-------------------------------------------YO----------------------------------------------------------
	
	//hace el cambio de una a otra pantalla (hace visible y no visible una u otra) 
	private void cambiarPantalla() {
        if (!pantallaInicio) {
            frameInicio.setVisible(false);
            frameJuego.setVisible(true);            
            initialize();
        }
    }
	
	 private void mostrarPantallaInicio() {
	        frameInicio.setVisible(true);
	        frameJuego.setVisible(false);
	    }
	

//Crea la pantalla de inicio 	
	private void crearFrameInicio() {
	    frameInicio = new JFrame();
	    frameInicio.setBounds(100, 100, 700, 700); // Tamaño y posición inicial
	    frameInicio.getContentPane().setBackground(new Color(251, 230, 198));
	    frameInicio.setLocationRelativeTo(null); // Centra en la pantalla
	    frameInicio.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //Cierra la aplicación al cerrar el frame
	    frameInicio.getContentPane().setLayout(null); // Desactiva el LayoutManager
	    
	    tituloJuego();
        opciones();
        mensajeOpciones();
        botonIniciar();   
	    
	}

	private void tituloJuego() {
		labelTitulo = new JLabel("Rompecabezas Deslizante");
	    labelTitulo.setFont(new Font("Tahoma", Font.PLAIN, 42));
	    labelTitulo.setHorizontalAlignment(JLabel.CENTER);
	    labelTitulo.setBackground(new Color(251, 230, 198));
	    labelTitulo.setOpaque(true); // Esto es necesario para la visualización del color del fondo
	    labelTitulo.setBounds(16, 50, 650, 150); // Ajusta las coordenadas para centrar el JLabel
	    frameInicio.getContentPane().add(labelTitulo);
	}
	
	private void opciones() {
		comboBox = new JComboBox<>();
	    comboBox.setBounds(415, 253, 249, 22);
	    frameInicio.getContentPane().add(comboBox);
	    comboBox.setModel(new DefaultComboBoxModel<>(new String[]{"Seleccione una dificultad", "Fácil", "Normal", "Difícil"}));
		 
	}
	
	private void mensajeOpciones() {
		
		labelMensajeOpciones = new JLabel("Seleccione una dificultad:");
		labelMensajeOpciones.setFont(new Font("Tahoma", Font.PLAIN, 22));
		labelMensajeOpciones.setHorizontalAlignment(JLabel.CENTER);
		labelMensajeOpciones.setBackground(new Color(251, 230, 198));
		labelMensajeOpciones.setOpaque(true); // Esto es necesario para que el color de fondo se vea
		labelMensajeOpciones.setBounds(25, 256, 250, 22); // Ajusta las coordenadas para centrar el JLabel
		frameInicio.getContentPane().add(labelMensajeOpciones);
		
	}
	
	 private void botonIniciar() {
	        Iniciar = new JButton("Iniciar Juego");
	        Iniciar.setFont(new Font("Tahoma", Font.PLAIN, 22));
	        Iniciar.setBounds(240, 550, 200, 50);
	        frameInicio.getContentPane().add(Iniciar);

	        Iniciar.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	                int indice = comboBox.getSelectedIndex();
	                if (indice > 0) {
	                    pantallaInicio = false;
	                    numeroDificultad =indice;
	                    cambiarPantalla(); // Cambia la pantalla según el estado de la variable
	                } else {
	                    JOptionPane.showMessageDialog(frameInicio, "Por favor, seleccione una dificultad.");
	                }
	            }
	        });
	    }
	
	 
	 
	
//-----------------------------------------------------------------------------------------------------	
	
	 
	 
//-------------------------------------JUEGO------------------------------------------------------------	
	
	private void crearFrameJuego() {
		frameJuego = new JFrame();
		frameJuego.setBounds(100, 100, 450, 300);
		frameJuego = new JFrame();
		frameJuego.getContentPane().setBackground(new Color(251, 230, 198));
		int anchoFrame = 500;
		int altoFrame = 650;
		frameJuego.setBounds(
				(PantallaUtils.anchoPantalla - anchoFrame) / 2,
				(PantallaUtils.altoPantalla - altoFrame) / 2,
				anchoFrame,
				altoFrame);

		frameJuego.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	private void crearContenedorPiezas() {
		panelContenedorDeLasFichas = new JPanel();
		panelContenedorDeLasFichas.setBounds(39, 10, 400, 400);
		panelContenedorDeLasFichas.setBackground(new Color(173, 159, 148));
		panelContenedorDeLasFichas.setBorder(new LineBorder(new Color(173, 159, 148), 4));
		int alturaTablero = tableroJuego.totalFilas();
		int anchuraTablero = tableroJuego.totalColumnas();
		panelContenedorDeLasFichas.setLayout(new GridLayout(alturaTablero, anchuraTablero, 4, 4));
		frameJuego.getContentPane().add(panelContenedorDeLasFichas);
	}
	

	private void actualizarTablero() {
        panelContenedorDeLasFichas.removeAll();
        for (int fila = 0; fila < tableroJuego.totalFilas(); fila++) {
            for (int col = 0; col < tableroJuego.totalColumnas(); col++) {
                int valor = tableroJuego.damePieza(fila, col).obtenerValorPieza();
                if(valor == 0) {
                	boton = new JButton("");
                	boton.setBackground(Color.LIGHT_GRAY);
                } else {
                	boton = new JButton(String.valueOf(valor));
                }
                
                boton.setFont(new Font("Arial", Font.PLAIN, 18));
                boton.setFocusPainted(false);
                boton.setBorderPainted(true);
                
                panelContenedorDeLasFichas.add(boton);
                agregarAccionBoton(boton, fila, col);
            }
        }
        panelContenedorDeLasFichas.revalidate();
        panelContenedorDeLasFichas.repaint();
    }
	
	private void agregarAccionBoton(JButton boton, int fila, int col) {
        boton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (tableroJuego.moverCelda(fila, col)) {
                	actualizarContadorMovimientos();
                    actualizarTablero();
                    if (tableroJuego.estaGanado()) {
                        JOptionPane.showMessageDialog(frameJuego, "¡Felicidades! Has ganado el juego.");
                    }
                }
            }
        });
    }
	
	private void visualizacionMovimientos() {
		labelMovimientosTexto = new JLabel("Movimientos: 0");
		labelMovimientosTexto.setFont(new Font("Tahoma", Font.PLAIN, 18));
		labelMovimientosTexto.setHorizontalAlignment(JLabel.CENTER);
		labelMovimientosTexto.setBackground(new Color(199, 199, 199));
		labelMovimientosTexto.setBounds(39, 700, 200, 30);
		frameJuego.getContentPane().add(labelMovimientosTexto, BorderLayout.NORTH);
	}
	
	private void actualizarContadorMovimientos() {
		labelMovimientosTexto.setText("Movimientos: " + tableroJuego.verCantidadMovimientosRealizados());
    }
}
