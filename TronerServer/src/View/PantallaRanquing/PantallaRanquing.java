package View.PantallaRanquing;

import Controlador.InformationClasses.EstatPantallaPrincipal.EstatRanking;
import View.Vista;
import javax.swing.*;
import java.awt.*;
import java.util.Date;

public class PantallaRanquing extends JPanel {
    private JPanel jpRanquings;

    public PantallaRanquing(){
        setLayout(new BorderLayout());
    }

    public void ompleRanquings(EstatRanking dades, Date[][] dates){
        if(jpRanquings == null) {
            jpRanquings = new JPanel();
            jpRanquings.setLayout(new BoxLayout(jpRanquings, BoxLayout.Y_AXIS));
            jpRanquings.add(new PanellTaula("2 PLAYERS", dades.getRankingX2(), dates[0]));
            jpRanquings.add(new PanellTaula("4 PLAYERS", dades.getRankingX4(), dates[1]));
            jpRanquings.add(new PanellTaula("CHAMPION", dades.getRankingTorneig(), dates[2]));
            jpRanquings.add(Box.createRigidArea(new Dimension(Vista.AMPLADA_PANTALLA /10,Vista.ALTURA_PANTALLA /10)));
            add(jpRanquings, BorderLayout.CENTER);
        }
    }


}
