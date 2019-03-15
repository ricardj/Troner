package Vista;

import Controlador.EscoltaTecles;

import Controlador.InformationClasses.EstatPantallaPrincipal.EstatConfiguracio;
import Controlador.InformationClasses.EstatPantallaPrincipal.EstatRanking;
import Controlador.InformationClasses.EstatResultats.EstatResultats;
import Controlador.InformationClasses.EstatSalaEspera.EstatSalaEspera;
import Controlador.InformationClasses.EstatJoc.EstatJugadorsPartida;
import Controlador.InformationClasses.EstatJoc.EstatTaulell;
import Vista.PantallaConfiguracio.PantallaConfiguracio;
import Vista.PantallaInici.PantallaInici;
import Vista.PantallaJoc.PantallaJoc;
import Vista.PantallaLoginRegistre.PantallaLogin;
import Vista.PantallaLoginRegistre.PantallaRegistre;
import Vista.PantallaPrincipal.PantallaPrincipal;
import Vista.PantallaResultats.PantallaResultats;
import Vista.PantallaSalaEspera.PantallaSalaEspera;

import Controlador.EscoltaAccionsVista;

import javax.swing.*;
import java.awt.*;
import java.io.File;

/**
 *La classe vista es hereva de JFrame i s'encarrega d'executar el canvi entre pantalles i gestionar les
 * particularitats de cada una en quan a nivell estetic y de threads d'actualitzacio.
 */
public class Vista extends JFrame {
    public final static String DIRECCIO_TIPOGRAFIA_SPY_AGENCY = "typography/spyagencyv3expand.ttf";
    public final static String DIRECCIO_TIPOGRAFIA_AGENCY_FB = "typography/AGENCYB.TTF";

    private static Font FONT_TITOLS;
    private static Font FONT_CONTINGUT;

    public static final int MIDA_X = 800;                                               //Mida de la finestra
    public static final int MIDA_Y = 600;                                               //Mida de la finestra
    public static final String DIRECCIO_ICONA_PROGRAMA = "images/Troner_icono.png";
    private static final String TITOL_FINESTRA = "Troner";                              //Titol de la finestra

    private static ImageIcon iconaFinestra;                                             //Icona de la finestra

    public static final int PANTALLA_CONFIGURACIO = 1;
    public static final int PANTALLA_INICI = 2;
    public static final int PANTALLA_LOGIN = 3;
    public static final int PANTALLA_REGISTRE = 4;
    public static final int PANTALLA_JOC = 5;
    public static final int PANTALLA_ESPERA = 6;
    public static final int PANTALLA_PRINCIPAL = 7;
    public static final int PANTALLA_RESULTATS = 8;

    private int pantallaActual;

    private EscoltaAccionsVista escoltaAccionsVista;

    //Totes les pantalles del joc
    private PantallaConfiguracio pantallaConfiguracio;
    private PantallaInici pantallaInici;
    private PantallaLogin pantallaLogin;
    private PantallaRegistre pantallaRegistre;
    private PantallaPrincipal pantallaPrincipal;
    private PantallaJoc pantallaJoc;
    private PantallaSalaEspera pantallaSalaEspera;
    private PantallaResultats pantallaResultats;


    public Vista(){

        EstatVista.setInstanciaPrincipalJFrame(this);

        settingsInicials();

        carregaFonts();

        getContentPane().setLayout(new BorderLayout());

    }


    @Override
    public void setVisible(boolean valor){
        setPantallaConfiguracio();
        super.setVisible(valor);
    }

    private void settingsInicials(){
        setTitle(TITOL_FINESTRA);
        setSize(new Dimension(MIDA_X,MIDA_Y+40));
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        iconaFinestra = new ImageIcon(DIRECCIO_ICONA_PROGRAMA);
        setIconImage(iconaFinestra.getImage());
        setResizable(false);
        setAlwaysOnTop(true);
    }

    public void actualitza(){
        switch (pantallaActual){
            case PANTALLA_CONFIGURACIO:
                pantallaConfiguracio.actualitza();
                break;

            case PANTALLA_INICI:
                pantallaInici.actualitza();
                break;
            case PANTALLA_LOGIN:
                pantallaLogin.actualitza();
                break;

            case PANTALLA_REGISTRE:
                pantallaRegistre.actualitza();
                break;

            case PANTALLA_JOC:

                break;

            case PANTALLA_ESPERA:

                break;

            case PANTALLA_RESULTATS:
                break;

            default:
                break;

        }
        repaint();
    }

    public void actualitza(EstatRanking ranking, EstatConfiguracio configuracio){
        switch (pantallaActual){
            case PANTALLA_PRINCIPAL:
                pantallaPrincipal.actualitzaConfiguracio(configuracio);
                pantallaPrincipal.actualitzaRanking(ranking);
                break;
            default:
                break;
        }
        repaint();
    }


    public void enregistraControlador(EscoltaAccionsVista escoltaAccionsVista){
        this.escoltaAccionsVista = escoltaAccionsVista;
        this.addWindowListener(escoltaAccionsVista);
    }

    public JPanel getPantalla(int pantalla){

        JPanel resultat = new JPanel();

        switch (pantalla){
            case PANTALLA_CONFIGURACIO:
                resultat = pantallaConfiguracio;
                break;

            case PANTALLA_INICI:
                resultat = pantallaInici;
                break;

            case PANTALLA_LOGIN:
                resultat = pantallaLogin;
                break;

            case PANTALLA_REGISTRE:
                resultat = pantallaRegistre;
                break;

            case PANTALLA_JOC:
                resultat = pantallaJoc;
                break;

            case PANTALLA_ESPERA:
                resultat = pantallaSalaEspera;
                break;

            case PANTALLA_PRINCIPAL:
                resultat = pantallaPrincipal;
                break;

            case PANTALLA_RESULTATS:
                resultat = pantallaResultats;
                break;
        }

        return resultat;
    }

    public void setPantallaConfiguracio() {
        getPantalla(pantallaActual).setVisible(false);
        pantallaActual = PANTALLA_CONFIGURACIO;
        if(pantallaConfiguracio==null) {
            pantallaConfiguracio = new PantallaConfiguracio(escoltaAccionsVista);
        }else{
            pantallaConfiguracio.setVisible(true);
        }
        getContentPane().add(pantallaConfiguracio,BorderLayout.CENTER);
    }

    public void setPantallaInici() {
        getPantalla(pantallaActual).setVisible(false);
        pantallaActual = PANTALLA_INICI;
        if(pantallaInici==null) {
            pantallaInici = new PantallaInici(escoltaAccionsVista);
        }else {
            pantallaInici.setVisible(true);
        }
        getContentPane().add(pantallaInici,BorderLayout.CENTER);
        actualitza();
    }

    public void setPantallaLogin() {
        getPantalla(pantallaActual).setVisible(false);
        pantallaActual  = PANTALLA_LOGIN;

        pantallaLogin = new PantallaLogin(escoltaAccionsVista);

        getContentPane().add(pantallaLogin,BorderLayout.CENTER);
        actualitza();
    }

    public void setPantallaRegistre() {
        getPantalla(pantallaActual).setVisible(false);
        pantallaActual = PANTALLA_REGISTRE;

        pantallaRegistre = new PantallaRegistre(escoltaAccionsVista);

        pantallaRegistre.setVisible(true);

        getContentPane().add(pantallaRegistre,BorderLayout.CENTER);
        actualitza();
    }

    public void setPantallaJoc(EstatTaulell estatTaulell, EstatJugadorsPartida estatJugadorsPartida,int numero, EscoltaTecles escoltaTecles) {

        getPantalla(pantallaActual).setVisible(false);
        pantallaActual = PANTALLA_JOC;

        pantallaJoc = new PantallaJoc(escoltaAccionsVista,estatTaulell,estatJugadorsPartida,numero,escoltaTecles);
        getContentPane().add(pantallaJoc,BorderLayout.CENTER);
        this.revalidate();
        pantallaJoc.setVisible(true);
    }

    public void setPantallaSalaEspera(EstatSalaEspera salaEspera, int modeJoc) {
        getPantalla(pantallaActual).setVisible(false);
        pantallaActual = PANTALLA_ESPERA;

        pantallaSalaEspera = new PantallaSalaEspera(escoltaAccionsVista,
                    salaEspera,
                    modeJoc);

        getContentPane().add(pantallaSalaEspera ,BorderLayout.CENTER);
    }

    public void setPantallaResultats(EstatResultats estatResultats) {
        getPantalla(pantallaActual).setVisible(false);
        pantallaActual = PANTALLA_RESULTATS;

        pantallaResultats = new PantallaResultats(escoltaAccionsVista,estatResultats);
        getContentPane().add(pantallaResultats,BorderLayout.CENTER);
        this.revalidate();
        pantallaResultats.setVisible(true);

    }

    public void setPantallaPrincipal(String login,EstatRanking estatRanking,EstatConfiguracio estatConfiguracio){
        getPantalla(pantallaActual).setVisible(false);
        pantallaActual = PANTALLA_PRINCIPAL;

        pantallaPrincipal = new PantallaPrincipal(escoltaAccionsVista,login,estatRanking,estatConfiguracio);

        getContentPane().add(pantallaPrincipal,BorderLayout.CENTER);
        actualitza();
    }

    private void carregaFonts() {
        if(FONT_CONTINGUT == null){
            try {
                FONT_CONTINGUT = Font.createFont(Font.TRUETYPE_FONT,new File(DIRECCIO_TIPOGRAFIA_AGENCY_FB));
            }catch (Exception e){
                e.getMessage();
            }
        }
        if(FONT_TITOLS== null){
            try {
                FONT_TITOLS = Font.createFont(Font.TRUETYPE_FONT,new File(DIRECCIO_TIPOGRAFIA_SPY_AGENCY));
            }catch (Exception e){
                e.getMessage();
            }
        }
    }

    public static Font getFontTitols(){
        return FONT_TITOLS;
    }

    public static Font getFontContingut(){
        return FONT_CONTINGUT;
    }

    public void avisarUsuario(String message) {
        JOptionPane.showMessageDialog(this,message);
    }

    public int getPantallaActual(){
        return pantallaActual;
    }
}
