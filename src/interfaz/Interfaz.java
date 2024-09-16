package interfaz;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;
import logica.ControlAudio;
import logica.RompeCabeza;

public class Interfaz {
    private RompeCabeza tableroJuego;
    private JPanel panelContenedorDeLasFichas;
    private JLabel labelMovimientosTexto;
    private JButton boton;
    private JButton botonesDelTablero[];
    private JButton botonAyuda;
    private JFrame frameJuego;
    private JFrame frameInicio;
    private boolean pantallaInicio = true;
    private JLabel labelTitulo;
    private JLabel labelMensajeOpciones;
    private JButton iniciar;
    private JComboBox<String> comboBox;
    private int numeroDificultad = 4;  
    private String imagenAjugar = "src/imagenes/imagenFondo.png";
    private final ButtonGroup botonImagen = new ButtonGroup();
    private final ButtonGroup botonModoJuego = new ButtonGroup();
    private int modoJuego = 1;
    private JLabel labelMensajeModoJuego;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                new Interfaz();
            } catch (Exception e) {
                System.out.println(e.getMessage());
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
        labelTitulo = VentanaInicio.crearTituloJuego(frameInicio);
        comboBox = VentanaInicio.crearOpciones(frameInicio);
        labelMensajeOpciones = VentanaInicio.crearMensajeOpciones(frameInicio);
        labelMensajeModoJuego = VentanaInicio.crearMensajeModoJuego(frameInicio);
        seleccionModoDeJuego();
        botonIniciar();
        seleccionImagen();
        VentanaInicio.crearImagenesParaMostrar(frameInicio);
        VentanaInicio.crearImagenFondoFrame(frameInicio);
    }

    private void botonIniciar() {
        iniciar = new JButton("Iniciar Juego");
        iniciar.setFont(new Font("Tahoma", Font.PLAIN, 22));
        iniciar.setBounds(240, 550, 200, 50);
        frameInicio.getContentPane().add(iniciar);
        iniciar.addActionListener(e -> {
            int indice = comboBox.getSelectedIndex();
            switch (indice) {
                case 1:
                    numeroDificultad = 3;
                    break;
                case 2:
                    numeroDificultad = 4;
                    break;
                case 3:
                    numeroDificultad = 5;
                    break;
                default:
                    numeroDificultad = 4;
            }
            pantallaInicio = false;
            cambiarPantalla();
        });
    }

    private void seleccionImagen() {
        JRadioButton option1 = new JRadioButton("Imagen 1");
        JRadioButton option2 = new JRadioButton("Imagen 2");
        JRadioButton option3 = new JRadioButton("Imagen 3");
        option1.setBounds(80, 356, 80, 22);
        option2.setBounds(300, 356, 80, 22);
        option3.setBounds(520, 356, 80, 22);
        option1.setOpaque(false);
        option2.setOpaque(false);
        option3.setOpaque(false);
        botonImagen.add(option1);
        botonImagen.add(option2);
        botonImagen.add(option3);
        ActionListener radioButtonListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (option1.isSelected()) {
                    imagenAjugar = "src/imagenes/imagenFondo.png";
                } else if (option2.isSelected()) {
                    imagenAjugar = "src/imagenes/imagenFondo2.png";
                } else if (option3.isSelected()) {
                    imagenAjugar = "src/imagenes/imagenFondo3.png";
                }
            }
        };
        option1.addActionListener(radioButtonListener);
        option2.addActionListener(radioButtonListener);
        option3.addActionListener(radioButtonListener);
        frameInicio.add(option1);
        frameInicio.add(option2);
        frameInicio.add(option3);
    }

    private void seleccionModoDeJuego() {
        JRadioButton option1 = new JRadioButton("Con números");
        JRadioButton option2 = new JRadioButton("Con imágenes");
        option1.setBounds(395, 285, 102, 22);
        option2.setBounds(535, 285, 108, 22);
        option1.setOpaque(false);
        option2.setOpaque(false);
        botonModoJuego.add(option1);
        botonModoJuego.add(option2);
        ActionListener radioButtonListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (option1.isSelected()) {
                    modoJuego = 1;
                } else if (option2.isSelected()) {
                    modoJuego = 2;
                }
            }
        };
        option1.addActionListener(radioButtonListener);
        option2.addActionListener(radioButtonListener);
        frameInicio.add(option1);
        frameInicio.add(option2);
    }

    private void crearFrameJuego() {
        frameJuego = new JFrame();
        int anchoFrame = 500;
        int altoFrame = 650;
        frameJuego.setBounds((PantallaUtils.anchoPantalla - anchoFrame) / 2,
                (PantallaUtils.altoPantalla - altoFrame) / 2, anchoFrame, altoFrame);
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
        ControlAudio.getInstancia().detenerMusica();
        ControlAudio.getInstancia().reproducirMusica("musicaFondo.wav");
        botonesDelTablero = new JButton[tableroJuego.totalFilas()*tableroJuego.totalColumnas()];
        if (modoJuego == 1) {
            actualizarTableroConNumeros();
        }
        if (modoJuego == 2) {
            actualizarTableroConImagenes();
        }
        visualizacionMovimientos();
        crearBotonAyuda();
    }

    private void crearContenedorPiezas() {
        if (panelContenedorDeLasFichas != null) {
            frameJuego.remove(panelContenedorDeLasFichas);
        }
        panelContenedorDeLasFichas = new JPanel();
        int tamanioTablero = tableroJuego.totalFilas();
        int tamanioPanel = 400;
        panelContenedorDeLasFichas.setBounds(39, 10, tamanioPanel, tamanioPanel);
        panelContenedorDeLasFichas.setBackground(new Color(173, 159, 148));
        panelContenedorDeLasFichas.setBorder(new LineBorder(new Color(173, 159, 148), 4));
        panelContenedorDeLasFichas.setLayout(new GridLayout(tamanioTablero, tamanioTablero, 4, 4));
        frameJuego.getContentPane().add(panelContenedorDeLasFichas);
    }

    private void actualizarTableroConNumeros() {
        panelContenedorDeLasFichas.removeAll();
        int tamanioTablero = tableroJuego.totalFilas();
        for (int fila = 0; fila < tamanioTablero; fila++) {
            for (int col = 0; col < tamanioTablero; col++) {
                int valor = tableroJuego.damePieza(fila, col).obtenerValorPieza();
                boton = valor == 0 ? new JButton("") : new JButton(String.valueOf(valor));
                botonesDelTablero[valor] = boton;
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

    private void actualizarTableroConImagenes() {
        panelContenedorDeLasFichas.removeAll();
        BufferedImage imagenCompleta = iniciarImagen();
        int tamanioTablero = tableroJuego.totalFilas();
        BufferedImage imagenEscalada = redimensionarImagen(imagenCompleta);
        int partWidth = imagenEscalada.getWidth() / tamanioTablero;
        int partHeight = imagenEscalada.getHeight() / tamanioTablero;
        for (int fila = 0; fila < tamanioTablero; fila++) {
            for (int col = 0; col < tamanioTablero; col++) {
                int valor = tableroJuego.damePieza(fila, col).obtenerValorPieza();
                if (valor != 0) {
                    BufferedImage parteImagen = calcularParteDeImagen(tamanioTablero, imagenEscalada, partWidth,
                            partHeight, valor);
                    boton = new JButton(new ImageIcon(parteImagen));
                } else {
                    boton = new JButton("");
                }
                botonesDelTablero[valor] = boton;
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

    private BufferedImage iniciarImagen() {
        BufferedImage imagenCompleta = null;
        try {
            imagenCompleta = ImageIO.read(new File(imagenAjugar));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return imagenCompleta;
    }

    private BufferedImage calcularParteDeImagen(int tamanioTablero, BufferedImage imagenEscalada, int partWidth,
                                                int partHeight, int valor) {
        int filaOriginal = (valor - 1) / tamanioTablero;
        int colOriginal = (valor - 1) % tamanioTablero;
        BufferedImage parteImagen = imagenEscalada.getSubimage(colOriginal * partWidth, filaOriginal * partHeight,
                partWidth, partHeight);
        return parteImagen;
    }

    private BufferedImage redimensionarImagen(BufferedImage imagenCompleta) {
        int anchoNecesario = panelContenedorDeLasFichas.getWidth();
        int altoNecesario = panelContenedorDeLasFichas.getHeight();
        Image imagenRedimensionada = imagenCompleta.getScaledInstance(anchoNecesario, altoNecesario,
                Image.SCALE_SMOOTH);
        BufferedImage imagenEscalada = new BufferedImage(anchoNecesario, altoNecesario, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = imagenEscalada.createGraphics();
        g2d.drawImage(imagenRedimensionada, 0, 0, null);
        g2d.dispose();
        return imagenEscalada;
    }

    private void agregarAccionBoton(JButton boton, int fila, int col) {
        boton.addActionListener(e -> {
        	ControlAudio.getInstancia().reproducirSonido("deslizar.wav"); // sonido al mover
            if (tableroJuego.moverCelda(fila, col)) {
                actualizarContadorMovimientos();
                if (modoJuego == 1) {
                    actualizarTableroConNumeros();
                } else if (modoJuego == 2) {
                    actualizarTableroConImagenes();
                }
                if (tableroJuego.estaGanado()) {
                    mostrarPantallaVictoria();
                }
            }
        });
    }

    private void crearBotonAyuda() {
    	botonAyuda = new JButton("ayuda");
    	botonAyuda.setFont(new Font("Tahoma", Font.PLAIN, 18));
    	botonAyuda.setHorizontalAlignment(JLabel.CENTER);
    	botonAyuda.setBounds(39, 700, 200, 30);
    	frameJuego.getContentPane().add(botonAyuda, BorderLayout.SOUTH);
    	botonAyuda.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int piezaCorrecta = tableroJuego.sugerirMovimiento();
				botonesDelTablero[piezaCorrecta].setBorder(new EtchedBorder(EtchedBorder.LOWERED, Color.GREEN, Color.GREEN));;
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

    private void mostrarPantallaVictoria() {
        frameJuego.getContentPane().removeAll();
        VentanaVictoria panelVictoria = new VentanaVictoria(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                reiniciarInterfaz();
            }
        }, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        frameJuego.getContentPane().add(panelVictoria);
        frameJuego.repaint();
    }

    private void reiniciarInterfaz() {
        tableroJuego.reiniciarJuego();
        frameJuego.getContentPane().removeAll();
        crearContenedorPiezas();
        mostrarPantallaInicio();
        ControlAudio.getInstancia().detenerMusica();
        ControlAudio.getInstancia().reproducirMusica("musicaFondo.wav");
        if (modoJuego == 1) {
            actualizarTableroConNumeros();
        }
        if (modoJuego == 2) {
            actualizarTableroConImagenes();
        }
        frameJuego.repaint();
    }

    private void mostrarPantallaInicio() {
        frameInicio.setVisible(true);
        frameJuego.setVisible(false);
    }
}