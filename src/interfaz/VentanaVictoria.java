package interfaz;

import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionListener;
import java.awt.Color;
import logica.ControlAudio;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

public class VentanaVictoria extends JPanel {

    private static final long serialVersionUID = 1L;
    private Font fuenteTexto = new Font("Arial Black", Font.PLAIN, 22);
    private Image imagenFondo;
    private JTextField txtganasteElJuego;

    public VentanaVictoria(ActionListener reiniciarListener, ActionListener salirListener) {
        ControlAudio.getInstancia().detenerMusica();
        ControlAudio.getInstancia().reproducirSonido("victoria.wav");
        
        ImageIcon icon = new ImageIcon(getClass().getResource("/imagenes/imagenVictoria.png"));
        imagenFondo = icon.getImage();
        
        setBackground(new Color(0, 0, 0));
        GridBagLayout gridBagLayout = new GridBagLayout();
        gridBagLayout.columnWidths = new int[]{110, 370, 100, 0};
        gridBagLayout.rowHeights = new int[]{130, 35, 175, 30, 60, 30, 0};
        gridBagLayout.columnWeights = new double[]{0.0, 1.0, 0.0, Double.MIN_VALUE};
        gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
        setLayout(gridBagLayout);
                        
        txtganasteElJuego = new JTextField();
        txtganasteElJuego.setFont(new Font("Arial Black", Font.PLAIN, 28));
        txtganasteElJuego.setHorizontalAlignment(SwingConstants.CENTER);
        txtganasteElJuego.setText("¡Ganaste el juego!");
        txtganasteElJuego.setBackground(Color.BLACK);
        txtganasteElJuego.setBorder(null);
        txtganasteElJuego.setOpaque(false);
        txtganasteElJuego.setForeground(Color.white);
        
        GridBagConstraints gbc_txtganasteElJuego = new GridBagConstraints();
        gbc_txtganasteElJuego.insets = new Insets(0, 0, 5, 5);
        gbc_txtganasteElJuego.fill = GridBagConstraints.HORIZONTAL;
        gbc_txtganasteElJuego.gridx = 1;
        gbc_txtganasteElJuego.gridy = 0;
        add(txtganasteElJuego, gbc_txtganasteElJuego);
                
        JButton botonReinicio = new JButton("Reiniciar");
        botonReinicio.setFont(fuenteTexto);
        botonReinicio.setBackground(Color.BLACK);
        botonReinicio.setBorderPainted(false);
        botonReinicio.setOpaque(false);
        botonReinicio.addActionListener(reiniciarListener);
        botonReinicio.setPreferredSize(new java.awt.Dimension(150, 40));
        botonReinicio.setForeground(Color.white);
        
        GridBagConstraints gbc_botonReinicio = new GridBagConstraints();
        gbc_botonReinicio.insets = new Insets(0, 0, 5, 5);
        gbc_botonReinicio.fill = GridBagConstraints.NONE;
        gbc_botonReinicio.gridx = 1;
        gbc_botonReinicio.gridy = 3;
        add(botonReinicio, gbc_botonReinicio);
                
        JButton botonSalir = new JButton("Salir");
        botonSalir.setFont(fuenteTexto);
        botonSalir.setBackground(Color.BLACK);
        botonSalir.setOpaque(false);
        botonSalir.setBorderPainted(false);
        botonSalir.addActionListener(salirListener);
        botonSalir.setPreferredSize(new java.awt.Dimension(150, 40));
        botonSalir.setForeground(Color.white);
        
        GridBagConstraints gbc_botonSalir = new GridBagConstraints();
        gbc_botonSalir.insets = new Insets(0, 0, 5, 5);
        gbc_botonSalir.fill = GridBagConstraints.NONE;
        gbc_botonSalir.gridx = 1;
        gbc_botonSalir.gridy = 5;
        add(botonSalir, gbc_botonSalir);
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (imagenFondo != null) {
            g.drawImage(imagenFondo, 0, 0, getWidth(), getHeight(), this);
        }
    }
}
