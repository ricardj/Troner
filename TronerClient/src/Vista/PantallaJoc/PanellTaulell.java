package Vista.PantallaJoc;

import Controlador.InformationClasses.EstatJoc.EstatTaulell;
import Vista.Vista;

import javax.swing.*;
import java.awt.*;

public class PanellTaulell extends JPanel {

    private static int AMPLITUD_TAULELL = Vista.MIDA_X*3/4;
    private static int ALTURA_TAULELL= Vista.MIDA_Y;
    private final static int NUM_CELES_VERTICALS = 100;
    private final static int NUM_CELES_HORITZONTALS = 100;
    private static int MIDA_CELES_VERTICALS = AMPLITUD_TAULELL/100;
    private static int MIDA_CELES_HORITZONTALS = ALTURA_TAULELL/100;
    private final static int GRUIX_LINIA_ENREIXAT = 2;
    private final static Color COLOR_REIXETA = Color.WHITE;

    private EstatTaulell taulell;

    public PanellTaulell(EstatTaulell taulell){
        super();
        this.taulell = taulell;
    }

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);

        //Caselles
        for(int i =0; i < NUM_CELES_HORITZONTALS;i++){
            for(int j = 0; j < NUM_CELES_VERTICALS;j++){
                g.setColor(taulell.getColorCela(i,j));
                g.fillRect(i*MIDA_CELES_HORITZONTALS,j*MIDA_CELES_VERTICALS,MIDA_CELES_HORITZONTALS,MIDA_CELES_VERTICALS);
            }
        }

        g.setColor(COLOR_REIXETA);
        //Rectes VERTICALS
        int offset = 3;
        for(int i = 0,r = 0; i <= NUM_CELES_HORITZONTALS; i++){
            r++;
            if(r==offset){
                g.drawLine(MIDA_CELES_HORITZONTALS*i,0,MIDA_CELES_HORITZONTALS*i,ALTURA_TAULELL);
                r = 0;
            }
        }

        //RECTES VERTICALS
        for(int i = 0,r = 0; i <= NUM_CELES_VERTICALS; i++){
            r++;
            if(r == offset){
                g.drawLine(0,MIDA_CELES_VERTICALS*i,AMPLITUD_TAULELL,MIDA_CELES_VERTICALS*i);
                r=0;
            }
        }

    }

    public void  actualitza(EstatTaulell taulell){
        this.taulell = taulell;
        this.repaint();
    }

}
