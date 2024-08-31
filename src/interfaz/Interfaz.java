package interfaz;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
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
    private JLabel labelMovimientosTexto;
    private JButton boton;
    
    private JFrame frameJuego;
    private JFrame frameInicio;
    private boolean pantallaInicio = true;
    private JLabel labelTitulo;
    private JLabel labelMensajeOpciones;
    private JButton iniciar;
    private JComboBox<String> comboBox;
    private int numeroDificultad = 4; // Valor predeterminado para el estado base

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                new Interfaz();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public Interfaz() {
        initialize();
    }

    private void initialize() {
        crearPantallas();
        mostrarPantallaInicio();
    }

    private void crearPantallas() {
        crearFrameInicio();
        crearFrameJuego();
    }

    private void crearFrameInicio() {
        frameInicio = new JFrame();
        frameInicio.setBounds(100, 100, 700, 700);
        frameInicio.getContentPane().setBackground(new Color(251, 230, 198));
        frameInicio.setLocationRelativeTo(null);
        frameInicio.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frameInicio.getContentPane().setLayout(null);

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
        labelTitulo.setOpaque(true);
        labelTitulo.setBounds(16, 50, 650, 150);
        frameInicio.getContentPane().add(labelTitulo);
    }

    private void opciones() {
        comboBox = new JComboBox<>();
        comboBox.setBounds(415, 253, 249, 22);
        frameInicio.getContentPane().add(comboBox);
        comboBox.setModel(new DefaultComboBoxModel<>(new String[]{"Seleccione una dificultad", "Fácil (3x3)", "Normal (4x4)", "Difícil (5x5)"}));
    }

    private void mensajeOpciones() {
        labelMensajeOpciones = new JLabel("Seleccione una dificultad:");
        labelMensajeOpciones.setFont(new Font("Tahoma", Font.PLAIN, 22));
        labelMensajeOpciones.setHorizontalAlignment(JLabel.CENTER);
        labelMensajeOpciones.setBackground(new Color(251, 230, 198));
        labelMensajeOpciones.setOpaque(true);
        labelMensajeOpciones.setBounds(25, 256, 250, 22);
        frameInicio.getContentPane().add(labelMensajeOpciones);
    }

    private void botonIniciar() {
        iniciar = new JButton("Iniciar Juego");
        iniciar.setFont(new Font("Tahoma", Font.PLAIN, 22));
        iniciar.setBounds(240, 550, 200, 50);
        frameInicio.getContentPane().add(iniciar);

        iniciar.addActionListener(e -> {
            int indice = comboBox.getSelectedIndex();
            switch (indice) {
                case 1: numeroDificultad = 3; break; 
                case 2: numeroDificultad = 4; break; 
                case 3: numeroDificultad = 5; break; 
                default: numeroDificultad = 4; 
            }
            pantallaInicio = false;
            cambiarPantalla();
        });
    }

    private void crearFrameJuego() {
        frameJuego = new JFrame();
        frameJuego.setBounds(100, 100, 500, 650);
        frameJuego.getContentPane().setBackground(new Color(251, 230, 198));
        frameJuego.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frameJuego.setLocationRelativeTo(null);
    }

    private void cambiarPantalla() {
        if (!pantallaInicio) {
            frameInicio.setVisible(false);
            frameJuego.setVisible(true);
            iniciarJuego();
        }
    }

    private void iniciarJuego() {
        tableroJuego = new RompeCabeza(numeroDificultad);
        crearContenedorPiezas();
        actualizarTablero();
        visualizacionMovimientos();
    }

    private void crearContenedorPiezas() {
        if (panelContenedorDeLasFichas != null) {
            frameJuego.remove(panelContenedorDeLasFichas);
        }
        panelContenedorDeLasFichas = new JPanel();
        int tamañoTablero = tableroJuego.totalFilas(); 
        int tamañoPanel = 400; 
       // int tamañoBotón = (tamañoPanel - 4 * (tamañoTablero - 1)) / tamañoTablero;

        panelContenedorDeLasFichas.setBounds(39, 10, tamañoPanel, tamañoPanel);
        panelContenedorDeLasFichas.setBackground(new Color(173, 159, 148));
        panelContenedorDeLasFichas.setBorder(new LineBorder(new Color(173, 159, 148), 4));
        panelContenedorDeLasFichas.setLayout(new GridLayout(tamañoTablero, tamañoTablero, 4, 4));
        frameJuego.getContentPane().add(panelContenedorDeLasFichas);
    }

    private void actualizarTablero() {
        panelContenedorDeLasFichas.removeAll();
        int tamañoTablero = tableroJuego.totalFilas();
        for (int fila = 0; fila < tamañoTablero; fila++) {
            for (int col = 0; col < tamañoTablero; col++) {
                int valor = tableroJuego.damePieza(fila, col).obtenerValorPieza();
                boton = valor == 0 ? new JButton("") : new JButton(String.valueOf(valor));
                
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
        boton.addActionListener(e -> {
            if (tableroJuego.moverCelda(fila, col)) {
                actualizarContadorMovimientos();
                actualizarTablero();
                if (tableroJuego.estaGanado()) {
                    JOptionPane.showMessageDialog(frameJuego, "¡Felicidades! Has ganado el juego.");
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

    private void mostrarPantallaInicio() {
        frameInicio.setVisible(true);
        frameJuego.setVisible(false);
    }
}
