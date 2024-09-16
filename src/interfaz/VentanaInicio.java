package interfaz;

import java.awt.Font;
import java.awt.Image;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import logica.ControlAudio;

public class VentanaInicio {
    public static JLabel crearTituloJuego(JFrame frame) {
    	ControlAudio.getInstancia().reproducirMusica("musicaMenu.wav");
        JLabel labelTitulo = new JLabel("Rompecabezas Deslizante");
        labelTitulo.setFont(new Font("Tahoma", Font.PLAIN, 42));
        labelTitulo.setHorizontalAlignment(JLabel.CENTER);
        labelTitulo.setOpaque(false);
        labelTitulo.setBounds(70, 50, 550, 100);
        frame.getContentPane().add(labelTitulo);
        return labelTitulo;       
        
    }

    public static JComboBox<String> crearOpciones(JFrame frame) {
        JComboBox<String> comboBox = new JComboBox<>();
        comboBox.setBounds(385, 220, 289, 22);
        comboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[]{"Seleccione una dificultad", "Fácil (3x3)", "Normal (4x4)", "Difícil (5x5)"}));
        frame.getContentPane().add(comboBox);
        return comboBox;
    }

    public static JLabel crearMensajeOpciones(JFrame frame) {
        JLabel labelMensajeOpciones = new JLabel("Seleccione una dificultad:");
        labelMensajeOpciones.setFont(new Font("Tahoma", Font.PLAIN, 20));
        labelMensajeOpciones.setHorizontalAlignment(JLabel.CENTER);
        labelMensajeOpciones.setOpaque(false);
        labelMensajeOpciones.setBounds(17, 220, 250, 22);
        frame.getContentPane().add(labelMensajeOpciones);
        return labelMensajeOpciones;
    }

    public static void crearSeleccionModoDeJuego(JFrame frame, ButtonGroup grupoBotones) {
        JRadioButton option1 = new JRadioButton("Con números");
        JRadioButton option2 = new JRadioButton("Con imágenes");
        option1.setBounds(395, 285, 102, 22);
        option2.setBounds(535, 285, 108, 22);
        option1.setOpaque(false);
        option2.setOpaque(false);
        grupoBotones.add(option1);
        grupoBotones.add(option2);
        frame.add(option1);
        frame.add(option2);
    }

    public static JLabel crearMensajeModoJuego(JFrame frame) {
        JLabel labelMensajeModoJuego = new JLabel("Seleccione un modo de juego:");
        labelMensajeModoJuego.setFont(new Font("Tahoma", Font.PLAIN, 20));
        labelMensajeModoJuego.setHorizontalAlignment(JLabel.CENTER);
        labelMensajeModoJuego.setOpaque(false);
        labelMensajeModoJuego.setBounds(29, 285, 280, 26);
        frame.getContentPane().add(labelMensajeModoJuego);
        return labelMensajeModoJuego;
    }

    public static void crearImagenesParaMostrar(JFrame frame) {
        JLabel imagenEjem1 = new JLabel();
        JLabel imagenEjem2 = new JLabel();
        JLabel imagenEjem3 = new JLabel();
        imagenEjem1.setBounds(45, 386, 150, 112);
        imagenEjem2.setBounds(270, 386, 150, 112);
        imagenEjem3.setBounds(490, 386, 150, 112);
        ImageIcon icon1 = new ImageIcon("src//imagenes//imagen1.png");
        Image img1 = icon1.getImage().getScaledInstance(imagenEjem1.getWidth(), imagenEjem1.getHeight(), Image.SCALE_SMOOTH);
        imagenEjem1.setIcon(new ImageIcon(img1));
        ImageIcon icon2 = new ImageIcon("src//imagenes//imagen2.png");
        Image img2 = icon2.getImage().getScaledInstance(imagenEjem2.getWidth(), imagenEjem2.getHeight(), Image.SCALE_SMOOTH);
        imagenEjem2.setIcon(new ImageIcon(img2));
        ImageIcon icon3 = new ImageIcon("src//imagenes//imagen3.png");
        Image img3 = icon3.getImage().getScaledInstance(imagenEjem3.getWidth(), imagenEjem3.getHeight(), Image.SCALE_SMOOTH);
        imagenEjem3.setIcon(new ImageIcon(img3));
        frame.add(imagenEjem1);
        frame.add(imagenEjem2);
        frame.add(imagenEjem3);
    }

    public static void crearImagenFondoFrame(JFrame frame) {
        ImageIcon imagen = new ImageIcon("src/imagenes/fondo.png");
        Image imagenEscalada = imagen.getImage().getScaledInstance(frame.getWidth(), frame.getHeight(), Image.SCALE_SMOOTH);
        ImageIcon imagenRedimensionada = new ImageIcon(imagenEscalada);
        JLabel etiquetaImagen = new JLabel(imagenRedimensionada);
        etiquetaImagen.setBounds(0, 0, frame.getWidth(), frame.getHeight());
        frame.getContentPane().add(etiquetaImagen);
        frame.getContentPane().setComponentZOrder(etiquetaImagen, frame.getContentPane().getComponentCount() - 1);
    }
}