package interfaz;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import logica.RompeCabeza;

public class Interfaz {
    private RompeCabeza tableroJuego;
    private JPanel panelContenedorDeLasFichas;
    private JFrame frmRompecabezas;
    JLabel labelMovimientosTexto;
    JButton boton;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Interfaz window = new Interfaz();
                    window.frmRompecabezas.setVisible(true);
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
        this.tableroJuego = new RompeCabeza();
        initialize();
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        crearFrame();
        crearContenedorPiezas();
        actualizarTablero();
        visualizacionMovimientos();
    }

    private void crearFrame() {
        frmRompecabezas = new JFrame();
        frmRompecabezas.setTitle("Rompecabezas");
        frmRompecabezas.setBounds(100, 100, 500, 650);
        frmRompecabezas.getContentPane().setBackground(new Color(251, 230, 198));
        int anchoFrame = 500;
        int altoFrame = 650;
        frmRompecabezas.setBounds(
                (PantallaUtils.anchoPantalla - anchoFrame) / 2,
                (PantallaUtils.altoPantalla - altoFrame) / 2,
                anchoFrame,
                altoFrame);

        frmRompecabezas.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void crearContenedorPiezas() {
        panelContenedorDeLasFichas = new JPanel();
        panelContenedorDeLasFichas.setBounds(39, 10, 400, 400);
        panelContenedorDeLasFichas.setBackground(new Color(173, 159, 148));
        panelContenedorDeLasFichas.setBorder(new LineBorder(new Color(173, 159, 148), 4));
        int alturaTablero = tableroJuego.totalFilas();
        int anchuraTablero = tableroJuego.totalColumnas();
        panelContenedorDeLasFichas.setLayout(new GridLayout(alturaTablero, anchuraTablero, 4, 4));
        frmRompecabezas.getContentPane().add(panelContenedorDeLasFichas);
    }

    private void actualizarTablero() {
        panelContenedorDeLasFichas.removeAll();
        for (int fila = 0; fila < tableroJuego.totalFilas(); fila++) {
            for (int col = 0; col < tableroJuego.totalColumnas(); col++) {
                int valor = tableroJuego.damePieza(fila, col).obtenerValorPieza();
                if (valor == 0) {
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
                        mostrarPantallaVictoria();
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
        frmRompecabezas.getContentPane().add(labelMovimientosTexto, BorderLayout.NORTH);
    }

    private void actualizarContadorMovimientos() {
        labelMovimientosTexto.setText("Movimientos: " + tableroJuego.verCantidadMovimientosRealizados());
    }
    
    private void mostrarPantallaVictoria() {
        frmRompecabezas.getContentPane().removeAll();

        VentanaVictoria panelVictoria = new VentanaVictoria(
            new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    reiniciarInterfaz();
                }
            },
            new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    System.exit(0);
                }
            }
        );

        frmRompecabezas.getContentPane().add(panelVictoria);
        frmRompecabezas.repaint();
    }


    private void reiniciarInterfaz() {
        tableroJuego.reiniciarJuego();

        frmRompecabezas.getContentPane().removeAll();
        crearContenedorPiezas();
        actualizarTablero();
        labelMovimientosTexto.setText("Movimientos: 0");
        frmRompecabezas.getContentPane().add(labelMovimientosTexto, BorderLayout.NORTH);
        frmRompecabezas.repaint();
    }
}
