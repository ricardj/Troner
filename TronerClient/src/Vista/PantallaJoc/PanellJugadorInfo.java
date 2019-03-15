package Vista.PantallaJoc;

import Controlador.InformationClasses.EstatJoc.InfoJugadorPartida;
import Controlador.InformationClasses.EstatPantallaPrincipal.InfoJugador;
import Vista.Vista;

import javax.swing.*;
import java.awt.*;

public class PanellJugadorInfo extends JPanel {

    //Nombre del usuario
    //Color de la moto
    //Wins: se van reiniciando en el campiona
    //Vidas
    //Puntos en el caso de torneo

    public static int MIDA_PANELL_X = Vista.MIDA_Y/4;
    public static int MIDA_PANELL_Y= Vista.MIDA_Y/5;

    private static Color COLOR_PANELL_NORMAL = Color.LIGHT_GRAY;
    private static Color COLOR_PANELL_RESALTAT = Color.ORANGE;


    //Modos del panel
    public static final int PANELL_MODE_X2 = 1;
    public static final int PANELL_MODE_X4 = 2;
    public static final int PANELL_MODE_CAMPIONAT = 3;

    private JLabel nomUsuari;
    private Color colorJugador;


    private JLabel wins;
    private JLabel vides;
    private JLabel punts;

    private int panellMode;
    boolean resaltarPanell = false;

    public PanellJugadorInfo(String nomUsuari, Color colorJugador, int wins, int vides, long punts, int panellMode){
        super();
        this.nomUsuari = new JLabel(" " + nomUsuari);         //Retallem un parell de caracters?
        this.nomUsuari.setFont(Vista.getFontContingut().deriveFont(20.0f));


        this.colorJugador = colorJugador;

        this.wins = new JLabel("Wins: " + Integer.toString(wins));
        this.wins.setFont(Vista.getFontTitols().deriveFont(12.0f));

        this.vides =new JLabel(" Vides:" + Integer.toString(vides));
        this.vides.setFont(Vista.getFontTitols().deriveFont(12.0f));

        this.punts = new JLabel("Punts: " + Long.toString(punts));
        this.punts.setFont(Vista.getFontTitols().deriveFont(12.0f));

        this.panellMode = panellMode;

        setPreferredSize(new Dimension(MIDA_PANELL_X,MIDA_PANELL_Y));
        setLayout(new BorderLayout());

        afegirJLabels();
        afegirEspaiBuit();
    }

    private void afegirJLabels(){
        JPanel winsPunts = new JPanel();
        winsPunts.setOpaque(false);
        winsPunts.setLayout(new BoxLayout(winsPunts,BoxLayout.Y_AXIS));


        add(nomUsuari,BorderLayout.NORTH);

        winsPunts.add(wins);
        winsPunts.add(punts);

        add(winsPunts,BorderLayout.CENTER);

        add(vides,BorderLayout.SOUTH);
    }
    private void afegirEspaiBuit(){

        add(Box.createRigidArea(new Dimension(MIDA_PANELL_X/4,MIDA_PANELL_Y)),BorderLayout.WEST);
    }

    public void setResaltarPanell(boolean resaltarPanell){
        this.resaltarPanell = resaltarPanell;
    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if(resaltarPanell){
            g.setColor(COLOR_PANELL_RESALTAT);
        }else {
            g.setColor(COLOR_PANELL_NORMAL);
        }

        g.fillRect(0,0,MIDA_PANELL_X,MIDA_PANELL_Y);

        g.setColor(colorJugador);
        g.fillPolygon(generarPoligon());
    }

    private Polygon generarPoligon(){
        Polygon poligon= new Polygon();

        poligon.addPoint(MIDA_PANELL_X/8,MIDA_PANELL_Y/4);
        poligon.addPoint(MIDA_PANELL_X/4-MIDA_PANELL_X/16,MIDA_PANELL_Y/2);
        poligon.addPoint(MIDA_PANELL_X/8,MIDA_PANELL_Y-MIDA_PANELL_Y/4);
        poligon.addPoint(MIDA_PANELL_X/16,MIDA_PANELL_Y/2);

        return poligon;
    }

    protected void actualitza(InfoJugadorPartida info){

        this.wins.setText("Wins: " + Integer.toString(info.getWins()));

        this.vides.setText("Vides:" + Integer.toString(info.getVides()));

        this.punts.setText("Punts: " + Long.toString(info.getPunts()));

        this.repaint();
    }
}
