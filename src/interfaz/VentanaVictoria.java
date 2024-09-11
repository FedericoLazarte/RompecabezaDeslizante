package interfaz;

import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Color;
import javax.swing.border.LineBorder;
import java.awt.Component;

public class VentanaVictoria extends JPanel {
    private static final long serialVersionUID = 1L;
    private JTextField textoVictoria;

    public VentanaVictoria(ActionListener reiniciarListener, ActionListener salirListener) {
        setBackground(new Color(173, 159, 148));
        GridBagLayout gridBagLayout = new GridBagLayout();
        gridBagLayout.columnWidths = new int[]{162, 156, 140, 0};
        gridBagLayout.rowHeights = new int[]{160, 145, 138, 0, 0};
        gridBagLayout.columnWeights = new double[]{1.0, 1.0, 1.0, Double.MIN_VALUE};
        gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
        setLayout(gridBagLayout);

        JButton botonReinicio = new JButton("Reiniciar");
        botonReinicio.setFont(new Font("Arial Black", Font.PLAIN, 16));
        botonReinicio.setBackground(new Color(255, 255, 255));
        botonReinicio.setBorder(new LineBorder(new Color(189, 182, 94), 4, true));
        botonReinicio.addActionListener(reiniciarListener);

        textoVictoria = new JTextField();
        textoVictoria.setBorder(null);
        textoVictoria.setFont(new Font("Arial Black", Font.PLAIN, 24));
        textoVictoria.setHorizontalAlignment(SwingConstants.CENTER);
        textoVictoria.setText("¡Ganaste el juego!");
        textoVictoria.setBackground(new Color(173, 159, 148));
        GridBagConstraints gbc_textoVictoria = new GridBagConstraints();
        gbc_textoVictoria.gridwidth = 3;
        gbc_textoVictoria.insets = new Insets(0, 0, 5, 5);
        gbc_textoVictoria.fill = GridBagConstraints.HORIZONTAL;
        gbc_textoVictoria.gridx = 0;
        gbc_textoVictoria.gridy = 0;
        add(textoVictoria, gbc_textoVictoria);
        textoVictoria.setColumns(10);
        GridBagConstraints gbc_botonReinicio = new GridBagConstraints();
        gbc_botonReinicio.fill = GridBagConstraints.HORIZONTAL;
        gbc_botonReinicio.insets = new Insets(0, 0, 5, 5);
        gbc_botonReinicio.gridx = 1;
        gbc_botonReinicio.gridy = 1;
        add(botonReinicio, gbc_botonReinicio);

        JButton botonSalir = new JButton("Salir");
        botonSalir.setFont(new Font("Arial Black", Font.PLAIN, 16));
        botonSalir.setBackground(new Color(255, 255, 255));
        botonSalir.setAlignmentX(Component.CENTER_ALIGNMENT);
        botonSalir.setBorder(new LineBorder(new Color(189, 182, 94), 4));
        botonSalir.addActionListener(salirListener);
        GridBagConstraints gbc_botonSalir = new GridBagConstraints();
        gbc_botonSalir.fill = GridBagConstraints.HORIZONTAL;
        gbc_botonSalir.insets = new Insets(0, 0, 5, 5);
        gbc_botonSalir.gridx = 1;
        gbc_botonSalir.gridy = 2;
        add(botonSalir, gbc_botonSalir);
    }
}