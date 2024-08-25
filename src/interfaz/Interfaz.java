package interfaz;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import logica.TableroFichas;

public class Interfaz {
	private TableroFichas tableroJuego;
	private JPanel panelContenedorDeLasFichas;
	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Interfaz window = new Interfaz();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Interfaz() {
		this.tableroJuego = new TableroFichas();
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(251, 230, 198));
		int anchoFrame = 500;
		int altoFrame = 650;
		frame.setBounds(
				(PantallaUtils.anchoPantalla - anchoFrame) / 2,
				(PantallaUtils.altoPantalla - altoFrame) / 2,
				anchoFrame,
				altoFrame);

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		// ----------------------- Tablero Fichas ---------------------------------
		
		panelContenedorDeLasFichas = new JPanel();
		panelContenedorDeLasFichas.setBounds(39, 10, 400, 400);
		panelContenedorDeLasFichas.setBackground(new Color(173, 159, 148));
		panelContenedorDeLasFichas.setBorder(new LineBorder(new Color(173, 159, 148), 4));
		int alturaTablero = tableroJuego.totalFilas();
		int anchuraTablero = tableroJuego.totalColumnas();
		panelContenedorDeLasFichas.setLayout(new GridLayout(alturaTablero, anchuraTablero, 4, 4));
		frame.getContentPane().add(panelContenedorDeLasFichas);
		
		for(int fila = 0; fila < tableroJuego.totalFilas(); fila++) {
			for(int col = 0; col < tableroJuego.totalColumnas(); col++) {
				int valor = tableroJuego.dameFicha(fila, col).obtenerValorFicha();
				JButton boton = new JButton(String.valueOf(valor));
				
				// Configurar el botón
                boton.setFont(new Font("Arial", Font.PLAIN, 18));
                boton.setFocusPainted(false);
                boton.setBorderPainted(true);
                panelContenedorDeLasFichas.add(boton);
                agregarAccionBoton(boton, fila, col);
    	        	
			}
			
		}
	}
	

	private void actualizarTablero() {
        panelContenedorDeLasFichas.removeAll();
        for (int fila = 0; fila < tableroJuego.totalFilas(); fila++) {
            for (int col = 0; col < tableroJuego.totalColumnas(); col++) {
                int valor = tableroJuego.dameFicha(fila, col).obtenerValorFicha();
                JButton boton = new JButton(String.valueOf(valor));
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
                    actualizarTablero();
                    if (tableroJuego.ganarJuego()) {
                        JOptionPane.showMessageDialog(frame, "¡Felicidades! Has ganado el juego.");
                    }
                }
            }
        });
    }
}
