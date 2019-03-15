package Vista.PantallaPrincipal.PanellConfiguracio;

import Vista.Vista;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;

public class PanellCambiaComanda extends JPanel implements KeyListener {

    private static final int AMPLADA_PANELL = 250;
    private static final int ALTURA_PANELL = 130;
    private PanellElementConfiguracio panellElementConfiguracio;
    private ImageIcon fons;

    private JFrame pantalla;

    public PanellCambiaComanda(PanellElementConfiguracio panellElementConfiguracio) {
        super();

        this.panellElementConfiguracio = panellElementConfiguracio;

        setLayout(new BorderLayout());

        setSize(new Dimension(AMPLADA_PANELL,ALTURA_PANELL));
        fons = new ImageIcon("images/panell_pulsa_tecla_v2.png");
        setVisible(true);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(fons.getImage(),0,0,this);
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        //Destruirnos a nosotros mismos y llamamos a tecla pulsada
        panellElementConfiguracio.teclaPulsada(e);

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void setVisible(boolean value){
        super.setVisible(value);
        if(value){
            JFrame.setDefaultLookAndFeelDecorated(false);
            pantalla = new JFrame();
            pantalla.getContentPane().setLayout(new BorderLayout());
            pantalla.setSize(new Dimension(AMPLADA_PANELL,ALTURA_PANELL));
            pantalla.setLocationRelativeTo(panellElementConfiguracio);
            pantalla.add(this,BorderLayout.CENTER);
            pantalla.setUndecorated(true);
            pantalla.setVisible(true);
            JFrame.setDefaultLookAndFeelDecorated(true);
            pantalla.addKeyListener(this);
            pantalla.setAlwaysOnTop(true);
        }else {
            pantalla.dispose();
        }
    }
}
