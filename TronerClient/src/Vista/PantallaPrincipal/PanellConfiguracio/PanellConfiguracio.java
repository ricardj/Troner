package Vista.PantallaPrincipal.PanellConfiguracio;

import Controlador.EscoltaAccionsVista;
import Controlador.InformationClasses.EstatPantallaPrincipal.EstatConfiguracio;
import Vista.Vista;

import javax.swing.*;
import java.awt.*;
import java.io.File;

public class PanellConfiguracio extends JPanel {


    public static final int AMPLADA_PANELL = Vista.MIDA_X ;
    public static final int ALTURA_PANELL = Vista.MIDA_Y/5;

    private Font fuente1;
    private Font fuente2;

    private PanellElementConfiguracio comandaNord;
    private PanellElementConfiguracio comandaSud;
    private PanellElementConfiguracio comandaEst;
    private PanellElementConfiguracio comandaOest;

    public PanellConfiguracio(EstatConfiguracio configuracio, EscoltaAccionsVista escoltaAccionsVista){
        super();

        setPreferredSize(new Dimension(AMPLADA_PANELL,ALTURA_PANELL));
        setLayout(new BorderLayout());

        try {
            fuente1 = Font.createFont(Font.TRUETYPE_FONT,new File(Vista.DIRECCIO_TIPOGRAFIA_SPY_AGENCY));
            fuente2 = Font.createFont(Font.TRUETYPE_FONT,new File(Vista.DIRECCIO_TIPOGRAFIA_SPY_AGENCY));
        }catch (Exception e){
            e.getMessage();
        }


        add(generarPanellSuperior(), BorderLayout.NORTH);
        add(generarRecuadreConfiguracio(configuracio,escoltaAccionsVista),BorderLayout.CENTER);
    }

    private JPanel generarPanellSuperior() {
        JLabel jlConfiguracio = new JLabel("Configuracio");
        jlConfiguracio.setFont(fuente1.deriveFont(15.0f));

        JPanel panellSuperior = new JPanel();
        panellSuperior.setLayout(new BoxLayout(panellSuperior,BoxLayout.Y_AXIS));

        panellSuperior.add(jlConfiguracio);
        //panellSuperior.add(Box.createRigidArea(new Dimension(1,100)));


        panellSuperior.setBackground(new Color(0,200,255,200).brighter());
        return panellSuperior;
    }

    private JPanel generarRecuadreConfiguracio(EstatConfiguracio configuracio,EscoltaAccionsVista escoltaAccionsVista){
        JPanel recuadreConfiguracio = new JPanel();

        recuadreConfiguracio.setLayout(new BoxLayout(recuadreConfiguracio,BoxLayout.X_AXIS));

        recuadreConfiguracio.setBackground(new Color(0,200,255,200));
        setOpaque(false);

        //Inicialitzem els recuadres
        comandaNord = new PanellElementConfiguracio("Nord",Character.toString(configuracio.getComandaNord()),escoltaAccionsVista);
        comandaSud = new PanellElementConfiguracio("Sud",Character.toString(configuracio.getComandaSud()),escoltaAccionsVista);
        comandaEst = new PanellElementConfiguracio("Est",Character.toString(configuracio.getComandaEst()),escoltaAccionsVista);
        comandaOest = new PanellElementConfiguracio("Oest",Character.toString(configuracio.getComandaOest()),escoltaAccionsVista);

        //Afegim els recuadres
        int espacio_vacio = 50;
        recuadreConfiguracio.add(Box.createRigidArea(new Dimension(espacio_vacio,1)));
        recuadreConfiguracio.add(comandaNord);
        recuadreConfiguracio.add(Box.createRigidArea(new Dimension(espacio_vacio,1)));
        recuadreConfiguracio.add(comandaSud);
        recuadreConfiguracio.add(Box.createRigidArea(new Dimension(espacio_vacio,1)));
        recuadreConfiguracio.add(comandaEst);
        recuadreConfiguracio.add(Box.createRigidArea(new Dimension(espacio_vacio,1)));
        recuadreConfiguracio.add(comandaOest);
        recuadreConfiguracio.add(Box.createRigidArea(new Dimension(espacio_vacio,1)));

        return recuadreConfiguracio;
    }

    private JPanel recuadreComanda(String etiqueta, String caracter){
        JPanel recuadreComanda = new JPanel();
        //recuadreComanda.setLayout();

        recuadreComanda.add(new JLabel(etiqueta));
        recuadreComanda.add(new JLabel(caracter));

        return recuadreComanda;
    }

    public void actualitza(EstatConfiguracio configuracio){
        comandaNord.actualitza(Character.toString(configuracio.getComandaNord()));
        comandaSud.actualitza(Character.toString(configuracio.getComandaSud()));
        comandaEst.actualitza(Character.toString(configuracio.getComandaEst()));
        comandaOest.actualitza(Character.toString(configuracio.getComandaOest()));
    }

}
