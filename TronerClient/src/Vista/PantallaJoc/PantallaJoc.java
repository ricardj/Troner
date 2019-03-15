package Vista.PantallaJoc;

import Controlador.EscoltaAccionsVista;
import Controlador.EscoltaTecles;
import Controlador.InformationClasses.EstatJoc.EstatJugadorsPartida;
import Controlador.InformationClasses.EstatJoc.EstatTaulell;
import Vista.AccesibleContextSetter;
import Vista.Vista;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyListener;
import java.awt.font.TextAttribute;
import java.text.AttributedCharacterIterator;

public class PantallaJoc extends JPanel implements AccesibleContextSetter{

    //Grid del taulell
    private PanellTaulell panellTaulell;
    private PanellJugadors panellJugadors;

    public static final String BOTO_ABANDONAR = "botoAbandonar";
    private JButton botoAbandonar;

    private int numero; //Forma part del compte enrere

    public PantallaJoc(EscoltaAccionsVista escoltaAccionsVista,
                       EstatTaulell estatTaulell,
                       EstatJugadorsPartida estatJugadorsPartida,
                       int numero,
                       EscoltaTecles escoltaTecles){

        super();

        setLayout(new BorderLayout());
        setOpaque(false);

        this.numero = numero; //Nos asignamos el numero de la cuenta atras

        botoAbandonar = new JButton("Abandonar");

        panellJugadors = generaPanellJugadors(estatJugadorsPartida);
        panellTaulell = generarPanellTaulell(estatTaulell);

        add(panellTaulell,BorderLayout.CENTER);
        add(panellJugadors,BorderLayout.EAST);

        setContextAccesible();

        this.updateUI();
        this.revalidate();


        this.registrarListenerBotons(escoltaAccionsVista);
        this.addKeyListener(escoltaTecles);
        this.setFocusable(true);
        this.requestFocusInWindow();



        repaint();
    }

    private void registrarListenerBotons(EscoltaAccionsVista escoltaAccionsVista){
        botoAbandonar.addActionListener(escoltaAccionsVista);
    }

    private PanellJugadors generaPanellJugadors(EstatJugadorsPartida estatJugadorsPartida){
        PanellJugadors panellJugadors = new PanellJugadors(estatJugadorsPartida,botoAbandonar);
        return panellJugadors;
    }

    private PanellTaulell generarPanellTaulell(EstatTaulell estatTaulell){
        PanellTaulell panellTaulell = new PanellTaulell(estatTaulell);
        return panellTaulell;
    }

    @Override
    public void paintComponent(Graphics g){

        super.paintComponent(g);
        g.setColor(Color.BLACK);
        if(numero>=0) {
            panellTaulell.setVisible(false);

            String aux = Integer.toString(numero);
            g.setColor(new Color(0, 0, 0, 178));
            g.setFont(Vista.getFontTitols().deriveFont(100.0f));
            g.drawString(aux,Vista.MIDA_X/3,Vista.MIDA_Y/2);
        }else {
            panellTaulell.setVisible(true);
        }
    }

    public void actualitza(EstatTaulell estatTaulell, EstatJugadorsPartida estatRanking, int numero){
        this.numero = numero;
        panellTaulell.actualitza(estatTaulell);
        panellJugadors.actualitza(estatRanking);
        this.repaint();
    }


    @Override
    public void setContextAccesible() {
        botoAbandonar.getAccessibleContext().setAccessibleName(Integer.toString(Vista.PANTALLA_JOC));
        botoAbandonar.getAccessibleContext().setAccessibleDescription(BOTO_ABANDONAR);
    }

    @Override
    public void setVisible(boolean valor){
        super.setVisible(valor);

        if(valor) this.requestFocusInWindow();

        if(!valor){
            System.out.println("Flag: borrem keylisteners de la pantalla de joc");
            KeyListener[] array = getKeyListeners();
            for(KeyListener element: array){
                removeKeyListener(element);
            }
        }

    }
}
