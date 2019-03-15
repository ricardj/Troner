package View.PantallaPrincipal;

import Controlador.Controlador;
import View.Vista;

import javax.swing.*;
import java.awt.*;

public class PantallaPrincipal extends JPanel {
    private JLabel jlTroner;
    private JButton jbOpcio1;
    private JButton jbOpcio2;
    private JButton jbOpcio3;
    private JButton jbOpcio4;
    private JButton jbOpcio5_2x;
    private JButton jbOpcio5_4x;
    private JButton jbOpcio5_Torneig;
    private JPanel jpOpcions;
    private JPanel jpVisualitzarGrafics;

    public PantallaPrincipal(){

        jpOpcions = new JPanel(new GridLayout(5,1));
        jpVisualitzarGrafics = new JPanel(new GridLayout(1,3));
        jlTroner = new JLabel("TRONER");
        jlTroner.setFont(new Font("Berlin Sans FB", Font.BOLD, 50));
        jlTroner.setHorizontalAlignment(SwingConstants.CENTER);

        jbOpcio1 = new JButton("Registrar usuari");
        jbOpcio2 = new JButton("Gestionar usuaris");
        jbOpcio3 = new JButton("Configuracio");
        jbOpcio4 = new JButton("Visualitzar rànquing");
        jbOpcio5_2x = new JButton("Gràfics 2X");
        jbOpcio5_4x = new JButton("Gràfics 4X");
        jbOpcio5_Torneig = new JButton("Gràfics TORNEIG");


        jbOpcio1.setFont(Vista.INFO);
        jbOpcio2.setFont(Vista.INFO);
        jbOpcio3.setFont(Vista.INFO);
        jbOpcio4.setFont(Vista.INFO);
        jbOpcio5_2x.setFont(Vista.INFO);
        jbOpcio5_4x.setFont(Vista.INFO);
        jbOpcio5_Torneig.setFont(Vista.INFO);

        jpVisualitzarGrafics.add(jbOpcio5_2x);
        jpVisualitzarGrafics.add(jbOpcio5_4x);
        jpVisualitzarGrafics.add(jbOpcio5_Torneig);

        jpOpcions.add(jbOpcio1);
        jpOpcions.add(jbOpcio2);
        jpOpcions.add(jbOpcio3);
        jpOpcions.add(jbOpcio4);
        jpOpcions.add(jpVisualitzarGrafics);

        setLayout(new BorderLayout());
        add(jlTroner, BorderLayout.NORTH);
        add(jpOpcions, BorderLayout.CENTER);
    }
    public JButton getJbOpcio1() {
        return jbOpcio1;
    }

    public JButton getJbOpcio2() {
        return jbOpcio2;
    }

    public JButton getJbOpcio3() {
        return jbOpcio3;
    }

    public JButton getJbOpcio4() {
        return jbOpcio4;
    }

    public JButton getJbOpcio5_2x() {
        return jbOpcio5_2x;
    }

    public JButton getJbOpcio5_4x() {
        return jbOpcio5_4x;
    }

    public JButton getJbOpcio5_Torneig() {
        return jbOpcio5_Torneig;
    }

    public void registraControlador(Controlador controlador){
        getJbOpcio1().setActionCommand("Opcio1");
        getJbOpcio2().setActionCommand("Opcio2");
        getJbOpcio3().setActionCommand("Opcio3");
        getJbOpcio4().setActionCommand("Opcio4");
        getJbOpcio5_2x().setActionCommand("Opcio5_2x");
        getJbOpcio5_4x().setActionCommand("Opcio5_4x");
        getJbOpcio5_Torneig().setActionCommand("Opcio5_Torneig");

        getJbOpcio1().addActionListener(controlador);
        getJbOpcio2().addActionListener(controlador);
        getJbOpcio3().addActionListener(controlador);
        getJbOpcio4().addActionListener(controlador);
        getJbOpcio5_2x().addActionListener(controlador);
        getJbOpcio5_4x().addActionListener(controlador);
        getJbOpcio5_Torneig().addActionListener(controlador);
    }

}