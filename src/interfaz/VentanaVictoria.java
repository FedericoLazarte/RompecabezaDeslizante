package interfaz;

import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.Color;
import javax.swing.border.LineBorder;

import logica.ControlAudio;

public class VentanaVictoria extends JPanel {

    private static final long serialVersionUID = 1L;
    private JTextField textoVictoria;

    // Variables globales para estilos
    private Color colorFondo = new Color(173, 159, 148);
    private Color colorBoton = new Color(255, 255, 255);
    private Color colorBorde = new Color(189, 182, 94);
    private Font fuenteTexto = new Font("Arial Black", Font.PLAIN, 22);

    public VentanaVictoria(ActionListener reiniciarListener, ActionListener salirListener) {
    	ControlAudio.getInstancia().detenerMusica();
    	ControlAudio.getInstancia().reproducirSonido("victoria.wav");
        setBackground(colorFondo);
        setLayout(null);

        // Crear y configurar el campo de texto
        textoVictoria = new JTextField();
        textoVictoria.setBounds(1, 60, 606, 35);
        textoVictoria.setBorder(null);
        textoVictoria.setFont(fuenteTexto);
        textoVictoria.setHorizontalAlignment(SwingConstants.CENTER);
        textoVictoria.setText("¡Ganaste el juego!");
        textoVictoria.setBackground(colorFondo);
        add(textoVictoria);
        textoVictoria.setColumns(10);

        // Crear y configurar el botón de reinicio
        JButton botonReinicio = new JButton("Reiniciar");
        botonReinicio.setBounds(214, 214, 202, 31);
        botonReinicio.setFont(fuenteTexto);
        botonReinicio.setBackground(colorBoton);
        botonReinicio.setBorder(new LineBorder(colorBorde, 4));
        botonReinicio.addActionListener(reiniciarListener);
        add(botonReinicio);

        // Crear y configurar el botón de salir
        JButton botonSalir = new JButton("Salir");
        botonSalir.setBounds(214, 356, 202, 31);
        botonSalir.setFont(fuenteTexto);
        botonSalir.setBackground(colorBoton);
        botonSalir.setBorder(new LineBorder(colorBorde, 4));
        botonSalir.addActionListener(salirListener);
        add(botonSalir);
    }
}
