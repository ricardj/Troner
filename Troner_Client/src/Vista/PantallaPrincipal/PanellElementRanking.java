package Vista.PantallaPrincipal;

import Vista.Vista;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class PanellElementRanking extends JPanel {

    public static final int ALTURA_PANELL = PanellArrayElementRanking.ALTURA_PANELL/10;
    public static final int AMPLADA_PANELL = PanellArrayElementRanking.AMPLADA_PANELL;

    public PanellElementRanking(String nom, long punts, int posicio){
        super();
        setLayout(new BorderLayout());

        Dimension mida = new Dimension(this.AMPLADA_PANELL, this.ALTURA_PANELL);
        setPreferredSize(mida);
        setMaximumSize(mida);

        setOpaque(false);
        setBorder(BorderFactory.createLineBorder(Color.BLACK,1,true));


        JLabel jlPosicio;
        jlPosicio = new JLabel(Integer.toString(posicio));
        jlPosicio.setHorizontalAlignment(JLabel.CENTER);
        jlPosicio.setPreferredSize(new Dimension(AMPLADA_PANELL/3,ALTURA_PANELL));
        jlPosicio.setFont(Vista.getFontTitols().deriveFont(20.0f));

        JPanel aux= new JPanel();
        aux.setLayout(new BorderLayout());
        aux.add(jlPosicio,BorderLayout.CENTER);
        aux.setPreferredSize(new Dimension(AMPLADA_PANELL/3,ALTURA_PANELL));


        JLabel jlNom;
        jlNom = new JLabel(nom);
        //jlNom.setFont(Vista.getFontContingut().deriveFont(20.0f));

        JLabel jlPunts;
        //jlPunts = new JLabel("Punts: " + Long.toString(punts));
        jlPunts = new JLabel(Long.toString(punts));
        //jlPunts.setFont(Vista.getFontContingut().deriveFont(15.0f));

        add(jlPosicio,BorderLayout.WEST);
        add(generarPanellDret(jlNom,jlPunts),BorderLayout.CENTER);
    }

    private JPanel generarPanellDret(JLabel jlNom,JLabel jlPunts) {
        JPanel panellDret = new JPanel();
        panellDret.setLayout(new BorderLayout());
        panellDret.add(jlNom,BorderLayout.CENTER);
        panellDret.add(jlPunts,BorderLayout.SOUTH);

        panellDret.setOpaque(false);

        return panellDret;
    }

    public static JPanel espaiBuit(){
        JPanel aux = new JPanel();
        aux.setLayout(new BorderLayout());
        aux.setPreferredSize(new Dimension(AMPLADA_PANELL,ALTURA_PANELL));
        aux.add(Box.createRigidArea(new Dimension(AMPLADA_PANELL,ALTURA_PANELL)),BorderLayout.CENTER);
        aux.setPreferredSize(new Dimension(AMPLADA_PANELL/3*2,ALTURA_PANELL));

        aux.setOpaque(false);
        return aux;
    }


}
