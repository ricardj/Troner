package Vista.PantallaPrincipal;

import Controlador.InformationClasses.EstatPantallaPrincipal.InfoJugador;

import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;

public class PanellArrayElementRanking extends JPanel {


    public static final int MAX_NUM_ELEMENTS = 10;
    public static final int AMPLADA_PANELL = 200;
    public static final int ALTURA_PANELL = 300;

    private LinkedList<PanellElementRanking> llistaElements;

    public PanellArrayElementRanking(LinkedList<InfoJugador> ranking){
        super();
        Dimension mida = new Dimension(AMPLADA_PANELL,ALTURA_PANELL);

        setPreferredSize(mida);
        setMaximumSize(mida);

        //setBorder(BorderFactory.createLineBorder(Color.ORANGE,2,true));
        setLayout(new GridBagLayout());
        llistaElements = new LinkedList<PanellElementRanking>();
        //setOpaque(false);
        setBackground(new Color(0,200,255,200));
        actualitza(ranking);
    }

    public void actualitza(LinkedList<InfoJugador> ranking){
        llistaElements.clear();


        //Treiem tots els elements i afegim els nous

        this.removeAll();


        int mida_llista = ranking.size();

        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;

        for(int i = 0;i < mida_llista; i++){
            InfoJugador jugadorAux = ranking.get(i);

            PanellElementRanking aux = new PanellElementRanking(jugadorAux.getName(),jugadorAux.getPunts(),i+1);
            aux.repaint();
            llistaElements.add(aux);

            gridBagConstraints.gridy = i;
            this.add(llistaElements.get(i),gridBagConstraints);
        }
        if(mida_llista < 10){
            for (int i = mida_llista; i < 10;i++){
                gridBagConstraints.gridy = i;
                this.add(PanellElementRanking.espaiBuit(),gridBagConstraints);
            }
        }

        this.repaint();
        this.revalidate();
    }

}
