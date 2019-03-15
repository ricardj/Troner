package Vista.PantallaPrincipal.PanellConfiguracio;

import Controlador.EscoltaAccionsVista;
import Vista.Vista;


import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import Vista.EstatVista;

public class PanellElementConfiguracio extends JPanel implements MouseListener{

    public static final int AMPLADA_PANELL = 150;
    public static final int ALTURA_PANELL = 60;

    private boolean waitingUpdate = false;

    private JLabel jlSet;

    private EscoltaAccionsVista escoltador;

    private JFrame jfMensaje;
    private JPanel jpMensaje;

    public PanellElementConfiguracio(String etiqueta, String set, EscoltaAccionsVista escoltador){
        super();
        setPreferredSize(new Dimension(AMPLADA_PANELL,ALTURA_PANELL));
        setLayout(new BoxLayout(this,BoxLayout.X_AXIS));
        setBackground(new Color(225, 255, 0, 255));

        this.escoltador = escoltador;

        jlSet = new JLabel(set);
        jlSet.setForeground(new Color(0, 0, 0, 172));
        jlSet.setAlignmentY(Component.CENTER_ALIGNMENT);

        JLabel jlEtiqueta = new JLabel(etiqueta);

        //jlSet.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        jlEtiqueta.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        float mida_font = 20.0f;
        jlSet.setFont(Vista.getFontContingut().deriveFont(mida_font));
        jlEtiqueta.setFont(Vista.getFontTitols().deriveFont(mida_font));

        setFocusable(true);
        addMouseListener(this);

        add(jlEtiqueta);
        add(Box.createRigidArea(new Dimension(30,1)));
        add(jlSet);
    }

    public void actualitza(String set){
        jlSet.setText(set);
        this.repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
    }


    public void teclaPulsada(KeyEvent e) {
        if(waitingUpdate){
            escoltador.cambiarComanda(jlSet.getText(),Character.toString(e.getKeyChar()));
        }
        jpMensaje.setVisible(false);
        jpMensaje = null;
        EstatVista.getInstanciaPrincipalJFrame().setEnabled(true);
        waitingUpdate = false;
        System.out.println("Flag: Tecla pulsada y evaluada.");
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        //Si ens pitjen a sobre ens posem en modo waiting
        waitingUpdate = true;
        jpMensaje = new PanellCambiaComanda(this);
        //Bloquejem la pantalla sencera

        EstatVista.getInstanciaPrincipalJFrame().setEnabled(false);

        //Com avisem al usuari?
        //Cambiant de color del panell
    }


    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
