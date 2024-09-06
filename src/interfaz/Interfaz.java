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
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
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
    private String imagenAjugar ="src/imagenes/imagenFondo.png"; // Valor predeterminado para el estado base
	private final ButtonGroup botonImagen = new ButtonGroup();
	private final ButtonGroup botonModoJuego = new ButtonGroup();
	private int modoJuego =1; //si no se toca se inicia con números solo 
    private JLabel labelMensajeModoJuego;



    
    
    
    
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
        
        // metodos se llaman antes para que esten sobre el fondo 
        
        
        tituloJuego();
        opciones();
        mensajeOpciones();
        
        mensajeModoJuego();
        seleccionModoDeJuego();
        
        botonIniciar();
        //-------------------------nuevos llamados-----------------------------
        seleccionImagen();
        imagenesDeEjemplo();
        
        
        //----------------------Cargar la imagen---------------------------------//
        ImageIcon imagen = new ImageIcon("src/imagenes/fondo.png");
        Image imagenEscalada = imagen.getImage().getScaledInstance(frameInicio.getWidth(), frameInicio.getHeight(), Image.SCALE_SMOOTH);
        ImageIcon imagenRedimensionada = new ImageIcon(imagenEscalada);

        // Crear un JLabel con la imagen redimensionada
        JLabel etiquetaImagen = new JLabel(imagenRedimensionada);

        // Establecer el tamaño y la posición de la etiqueta para que ocupe todo el frame
        etiquetaImagen.setBounds(0, 0, frameInicio.getWidth(), frameInicio.getHeight());
        frameInicio.getContentPane().add(etiquetaImagen);

        // Asegurarse de que el JLabel esté al fondo
        frameInicio.getContentPane().setComponentZOrder(etiquetaImagen, frameInicio.getContentPane().getComponentCount() - 1);
        
        //------------------------------------------------------------------//
        
    }

    private void tituloJuego() {
        labelTitulo = new JLabel("Rompecabezas Deslizante");
        labelTitulo.setFont(new Font("Tahoma", Font.PLAIN, 42));
        labelTitulo.setHorizontalAlignment(JLabel.CENTER);
        labelTitulo.setOpaque(false); //hace transparente el texto 
        labelTitulo.setBounds(70, 50, 550, 100);
        frameInicio.getContentPane().add(labelTitulo);
    }

    private void opciones() {
        comboBox = new JComboBox<>();
        comboBox.setBounds(385, 220, 289, 22);
        frameInicio.getContentPane().add(comboBox);
        comboBox.setModel(new DefaultComboBoxModel<>(new String[]{"Seleccione una dificultad", "Fácil (3x3)", "Normal (4x4)", "Difícil (5x5)"}));
    }

    private void mensajeOpciones() {
        labelMensajeOpciones = new JLabel("Seleccione una dificultad:");
        labelMensajeOpciones.setFont(new Font("Tahoma", Font.PLAIN, 20));
        labelMensajeOpciones.setHorizontalAlignment(JLabel.CENTER);
        labelMensajeOpciones.setOpaque(false);
        labelMensajeOpciones.setBounds(17, 220, 250, 22);
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

//-------------------------------------------------------------------------------------------    
    private void seleccionImagen() {
    	JRadioButton option1 = new JRadioButton("Imagen 1");
        JRadioButton option2 = new JRadioButton("Imagen 2");
        JRadioButton option3 = new JRadioButton("Imagen 3");
        
        
        // Establecer las posiciones y tamaños
        option1.setBounds(80, 356, 80, 22); // (x, y, ancho, alto)
        option2.setBounds(300, 356, 80, 22);
        option3.setBounds(520, 356, 80, 22);

        
        //hace transparente el texto 
        option1.setOpaque(false); 
        option2.setOpaque(false); 
        option3.setOpaque(false); 

        
        // Agrupar los JRadioButton
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
        
        // Asignar el ActionListener a los JRadioButton
        option1.addActionListener(radioButtonListener);
        option2.addActionListener(radioButtonListener);
        option3.addActionListener(radioButtonListener);
        

        // Agregar los JRadioButton al frame
        frameInicio.add(option1);
        frameInicio.add(option2);
        frameInicio.add(option3);   
    }
    
  
    private void imagenesDeEjemplo() {
        JLabel imagenEjem1 = new JLabel();
        JLabel imagenEjem2 = new JLabel();
        JLabel imagenEjem3 = new JLabel();
        
        imagenEjem1.setBounds(45, 386, 150, 112); // Posición y tamaño del JLabel
        imagenEjem2.setBounds(270, 386, 150, 112); // Ajusté la posición para evitar solapamientos
        imagenEjem3.setBounds(490, 386, 150, 112); // Ajusté la posición para evitar solapamientos
        
        // Imágen 1 de muestra
        ImageIcon icon1 = new ImageIcon("src//imagenes//imagen1.png"); 
        Image img1 = icon1.getImage().getScaledInstance(imagenEjem1.getWidth(), imagenEjem1.getHeight(), Image.SCALE_SMOOTH);
        imagenEjem1.setIcon(new ImageIcon(img1));
         
        // Imágen 2 de muestra 
        ImageIcon icon2 = new ImageIcon("src//imagenes//imagen2.png"); 
        Image img2 = icon2.getImage().getScaledInstance(imagenEjem2.getWidth(), imagenEjem2.getHeight(), Image.SCALE_SMOOTH);
        imagenEjem2.setIcon(new ImageIcon(img2));
        
        // Imágen 3 de muestra 
        ImageIcon icon3 = new ImageIcon("src//imagenes//imagen3.png"); 
        Image img3 = icon3.getImage().getScaledInstance(imagenEjem3.getWidth(), imagenEjem3.getHeight(), Image.SCALE_SMOOTH);
        imagenEjem3.setIcon(new ImageIcon(img3));
        
        // Agregar las imágenes al frame de Inicio
        frameInicio.add(imagenEjem1);
        frameInicio.add(imagenEjem2);
        frameInicio.add(imagenEjem3);
    }
    
    
//----------------------Modo de juego-------------------------------
    private void seleccionModoDeJuego() {
    	JRadioButton option1 = new JRadioButton("Con números");
        JRadioButton option2 = new JRadioButton("Con imágenes");
        
        
        // Establecer las posiciones y tamaños
        option1.setBounds(395, 285, 102, 22); // (x, y, ancho, alto)
        option2.setBounds(535, 285, 108, 22);

        
        //hace transparente el texto 
        option1.setOpaque(false); 
        option2.setOpaque(false); 

        
        // Agrupar los JRadioButton
        botonModoJuego.add(option1);
        botonModoJuego.add(option2);
        
        ActionListener radioButtonListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            
                
                if (option1.isSelected()) {
                	modoJuego = 1;     //números
                } else if (option2.isSelected()) {
                	modoJuego = 2; 		//imágenes

                }
               
               
            }
        };
        
        // Asignar el ActionListener a los JRadioButton
        option1.addActionListener(radioButtonListener);
        option2.addActionListener(radioButtonListener);
        

        // Agregar los JRadioButton al frame
        frameInicio.add(option1);
        frameInicio.add(option2);
    }
    
    
    private void mensajeModoJuego() {
    	labelMensajeModoJuego = new JLabel("Seleccione un modo de juego:");
    	labelMensajeModoJuego.setFont(new Font("Tahoma", Font.PLAIN, 20));
    	labelMensajeModoJuego.setHorizontalAlignment(JLabel.CENTER);
    	labelMensajeModoJuego.setOpaque(false);
    	labelMensajeModoJuego.setBounds(29, 285, 280, 26);
        frameInicio.getContentPane().add(labelMensajeModoJuego);
    }
    
    
    
    
    
    
    
    
//----------------------------------------------------------------------------------------------    
    private void crearFrameJuego() {
        frameJuego = new JFrame();
        int anchoFrame = 500;
        int altoFrame = 650;
        frameJuego.setBounds(
                (PantallaUtils.anchoPantalla - anchoFrame) / 2,
                (PantallaUtils.altoPantalla - altoFrame) / 2,
                anchoFrame,
                altoFrame);
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
        
      //------------------------------------  
        if(modoJuego == 1) {
        	actualizarTableroConNúmeros();
        }
        if(modoJuego == 2) {
        	actualizarTableroConImágenes();
        }
       //------------------------------------- 
        
        visualizacionMovimientos();
    }

    private void crearContenedorPiezas() {
        if (panelContenedorDeLasFichas != null) {
            frameJuego.remove(panelContenedorDeLasFichas);
        }
        panelContenedorDeLasFichas = new JPanel();
        int tamañoTablero = tableroJuego.totalFilas(); 
        int tamañoPanel = 400; 
        panelContenedorDeLasFichas.setBounds(39, 10, tamañoPanel, tamañoPanel);
        panelContenedorDeLasFichas.setBackground(new Color(173, 159, 148));
        panelContenedorDeLasFichas.setBorder(new LineBorder(new Color(173, 159, 148), 4));
        panelContenedorDeLasFichas.setLayout(new GridLayout(tamañoTablero, tamañoTablero, 4, 4));
        frameJuego.getContentPane().add(panelContenedorDeLasFichas);
    }
 
    
    private void actualizarTableroConImágenes() {
        panelContenedorDeLasFichas.removeAll();
        
        //revisa si puede leer la imagen de manera correcta
        BufferedImage imagenCompleta = iniciarImagen();
        
        int tamañoTablero = tableroJuego.totalFilas();
        
        BufferedImage imagenEscalada = redimensionarImagen(imagenCompleta);
        
        // Calcular el tamaño de cada pieza
        int partWidth = imagenEscalada.getWidth() / tamañoTablero;
        int partHeight = imagenEscalada.getHeight() / tamañoTablero;
        
        for (int fila = 0; fila < tamañoTablero; fila++) {
            for (int col = 0; col < tamañoTablero; col++) {
                int valor = tableroJuego.damePieza(fila, col).obtenerValorPieza(); // Aquí es importante que el valor esté desordenado como el tablero
                
                if (valor != 0) {
                    BufferedImage parteImagen = calcularParteDeImagen(tamañoTablero, imagenEscalada, partWidth,partHeight, valor);
                    
                    boton = new JButton(new ImageIcon(parteImagen));
                } else {
                    boton = new JButton(""); // Si el valor es 0, creamos un botón vacío
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
    
    
    //
    private void actualizarTableroConNúmeros() {
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
    
    
    
    
    
    

private BufferedImage iniciarImagen() {
	BufferedImage imagenCompleta = null;
	try {
	    imagenCompleta = ImageIO.read(new File(imagenAjugar));
	} catch (IOException e) {
	    e.printStackTrace();
	    
	}
	return imagenCompleta;
}

	private BufferedImage calcularParteDeImagen(int tamañoTablero, BufferedImage imagenEscalada, int partWidth,int partHeight, int valor) {
		// Calcular las coordenadas de la subimagen en base al valor de la pieza
		int filaOriginal = (valor - 1) / tamañoTablero; // La fila original de la imagen
		int colOriginal = (valor - 1) % tamañoTablero; // La columna original de la imagen
		
		BufferedImage parteImagen = imagenEscalada.getSubimage(
		    colOriginal * partWidth,  // Posición horizontal original
		    filaOriginal * partHeight, // Posición vertical original
		    partWidth, partHeight);
		return parteImagen;
	}

	private BufferedImage redimensionarImagen(BufferedImage imagenCompleta) {
		// Calcular el tamaño total necesario basado en el tamaño del tablero
        int anchoNecesario = panelContenedorDeLasFichas.getWidth();
        int altoNecesario = panelContenedorDeLasFichas.getHeight();
        
        // Redimensionar la imagen a las dimensiones necesarias
        Image imagenRedimensionada = imagenCompleta.getScaledInstance(anchoNecesario, altoNecesario, Image.SCALE_SMOOTH);
        
        // Convertir la imagen redimensionada a BufferedImage
        BufferedImage imagenEscalada = new BufferedImage(anchoNecesario, altoNecesario, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = imagenEscalada.createGraphics();
        g2d.drawImage(imagenRedimensionada, 0, 0, null);
        g2d.dispose();
		return imagenEscalada;
	}
    
   
	
//----------------------------------------------------------------------------------------------------	
    private void agregarAccionBoton(JButton boton, int fila, int col) {
        boton.addActionListener(e -> {
            if (tableroJuego.moverCelda(fila, col)) {
                actualizarContadorMovimientos();
                
             //-----------cambios----------------------   
                if(modoJuego == 1) {
                	actualizarTableroConNúmeros();
                }
                else if(modoJuego == 2){
                	 actualizarTableroConImágenes();
                }
             //----------------------------------------   
                
                if (tableroJuego.estaGanado()) {
                	mostrarPantallaVictoria();                }
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
        
      //------------------------------------------  
        if(modoJuego ==1) {
        	actualizarTableroConNúmeros();
        }
        if(modoJuego == 2) {
        	actualizarTableroConImágenes();
        }
        
     //--------------------------------------------   
        labelMovimientosTexto.setText("Movimientos: 0");
        frameJuego.getContentPane().add(labelMovimientosTexto, BorderLayout.NORTH);
        frameJuego.repaint();
    }

    private void mostrarPantallaInicio() {
        frameInicio.setVisible(true);
        frameJuego.setVisible(false);
    }
}
