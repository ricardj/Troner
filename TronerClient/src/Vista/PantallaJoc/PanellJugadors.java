package Vista.PantallaJoc;

import Controlador.InformationClasses.EstatJoc.EstatJugadorsPartida;
import Controlador.InformationClasses.EstatJoc.InfoJugadorPartida;
import Vista.Vista;

import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;

public class PanellJugadors extends JPanel {

    private static final int AMPLADA_PANELL = Vista.MIDA_X/4-17;
    private static final int ALTURA_PANELL = Vista.MIDA_Y;

    private EstatJugadorsPartida estatJugadorsPartida;
    private LinkedList<PanellJugadorInfo> panellsJugadors;

    public PanellJugadors(EstatJugadorsPartida estatJugadorsPartida,JButton botoAbandonar){
        this.estatJugadorsPartida = estatJugadorsPartida;
        panellsJugadors = new LinkedList<PanellJugadorInfo>();
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(AMPLADA_PANELL,ALTURA_PANELL));

        //Ponemos imagen de fondo...

        afegirTitol();
        afegirPanellsJugadors();
        afegirBotoAbandonar(botoAbandonar);
    }

    private void afegirTitol(){
        JLabel titol = new JLabel("Troner");
        titol.setHorizontalAlignment(JLabel.CENTER);
        titol.setFont(Vista.getFontTitols().deriveFont(15.0f));

        add(titol,BorderLayout.NORTH);
    }

    private void afegirPanellsJugadors(){
        JPanel auxiliar = new JPanel();
        for(int i = 0; i < estatJugadorsPartida.length();i++){
            InfoJugadorPartida j = estatJugadorsPartida.getJugadorPartida(i);
            panellsJugadors.add(new PanellJugadorInfo(j.getName(),j.getColorJugador(),j.getWins(),j.getVides(),j.getPunts(),PanellJugadorInfo.PANELL_MODE_X4));
            auxiliar.add(panellsJugadors.get(i));
        }

        add(auxiliar,BorderLayout.CENTER);
    }

    private void afegirBotoAbandonar(JButton botoAbandonar){
        add(botoAbandonar,BorderLayout.SOUTH);
    }

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        for (int i = 0; i< panellsJugadors.size(); i++){
            panellsJugadors.get(i).actualitza(estatJugadorsPartida.getJugadorPartida(i));
        }
        //Ponemos foto de fondo?
    }

    public void actualitza(EstatJugadorsPartida estatJugadorsPartida){
        this.estatJugadorsPartida = estatJugadorsPartida;
        this.repaint();
    }

}
