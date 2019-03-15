package View;

import Controlador.Controlador;
import Controlador.ControladorComboBox;
import View.PantallaConfiguracio.PantallaConfiguracio;
import View.PantallaGestiona.PantallaGestiona;
import View.PantallaGrafic.PantallaGrafic;
import View.PantallaPrincipal.PantallaPrincipal;
import View.PantallaRanquing.PantallaRanquing;
import View.RegistrarUsuari.PantallaRegistre;

import javax.swing.*;
import java.awt.*;

public class Vista extends JFrame {
    public static final int ALTURA_PANTALLA = 600;
    public static final int AMPLADA_PANTALLA = 860;
    public static Font TITLES = new Font("Berlin Sans FB", Font.BOLD, 20);
    public static Font INFO = new Font("Berlin Sans FB", Font.PLAIN, 20);
    private PantallaPrincipal principal;
    private PantallaRegistre registre;
    private PantallaConfiguracio config;
    private PantallaRanquing ranquing;
    private PantallaGestiona gestiona;
    private PantallaGrafic grafic2x;
    private PantallaGrafic grafic4x;
    private PantallaGrafic graficTorneig;
    private JButton jbAtras;
    private JPanel opcions;
    private CardLayout cardLayout;
    public Vista(){
        setSize(AMPLADA_PANTALLA, ALTURA_PANTALLA);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("Troner");
        setLayout(new BorderLayout());

        jbAtras = new JButton("BACK");
        jbAtras.setHorizontalAlignment(SwingConstants.LEFT);

        opcions = new JPanel(new CardLayout());

        principal = new PantallaPrincipal();
        opcions.add(principal, "Principal");

        registre = new PantallaRegistre();
        añadeMargenes(registre, 10);
        opcions.add(registre, "Registre");

        config = new PantallaConfiguracio();
        añadeMargenes(config, 10);
        opcions.add(config, "Configuracio");

        ranquing = new PantallaRanquing();
        añadeMargenes(ranquing, 10);
        opcions.add(ranquing, "Ranquing");

        gestiona = new PantallaGestiona();
        añadeMargenes(gestiona, 10);
        opcions.add(gestiona, "Gestiona");

        grafic2x = new PantallaGrafic();
       // añadeMargenes(grafic, 10);
        opcions.add(grafic2x, "Grafic2x");

        grafic4x = new PantallaGrafic();
        // añadeMargenes(grafic, 10);
        opcions.add(grafic4x, "Grafic4x");

        graficTorneig = new PantallaGrafic();
        // añadeMargenes(grafic, 10);
        opcions.add(graficTorneig, "GraficTorneig");



        getContentPane().add(opcions, BorderLayout.CENTER);
        cardLayout = (CardLayout) opcions.getLayout();

        setPantallaPrincipal();
    }
    public void setPantallaPrincipal(){
        cardLayout.show(opcions, "Principal");
    }

    public void setPantallaRegistre(){
        registre.add(jbAtras, BorderLayout.SOUTH);
        cardLayout.show(opcions, "Registre");
    }

    public void setPantallaConfig(){
        config.add(jbAtras, BorderLayout.SOUTH);
        cardLayout.show(opcions, "Configuracio");
    }

    public void setPantallaRanquing(){
        ranquing.add(jbAtras, BorderLayout.SOUTH);
        cardLayout.show(opcions, "Ranquing");
    }
    public void setPantallaGestiona(){
        gestiona.add(jbAtras, BorderLayout.SOUTH);
        cardLayout.show(opcions, "Gestiona");
    }
    public void setPantallaGrafic2x(){
        grafic2x.add(jbAtras, BorderLayout.SOUTH);
        cardLayout.show(opcions, "Grafic2x");
    }

    public void setPantallaGrafic4x(){
        grafic4x.add(jbAtras, BorderLayout.SOUTH);
        cardLayout.show(opcions, "Grafic4x");
    }

    public void setPantallaGraficTorneig(){
        graficTorneig.add(jbAtras, BorderLayout.SOUTH);
        cardLayout.show(opcions, "GraficTorneig");
    }

    public int getNouPort(int antic_port){
     try {
         int nou_port = Integer.parseInt(config.getJtfPortClients().getText());
         return nou_port;
     }catch(NumberFormatException e){
         JOptionPane.showMessageDialog(config, "Error! Port incorrecte, torni a introduir.");
         return antic_port;
     }
    }

    public PantallaConfiguracio getConfig() {
        return config;
    }

    public PantallaRegistre getRegistre() { return registre; }

    public PantallaGestiona getGestiona() { return gestiona; }

    public PantallaGrafic getGrafic2x() { return grafic2x; }

    public PantallaGrafic getGrafic4x() { return grafic4x; }

    public PantallaGrafic getGraficTorneig() { return graficTorneig; }

    public PantallaRanquing getRanquing() { return ranquing; }

    public void registraControlador(Controlador controlador){
        this.addWindowListener(controlador);

        principal.registraControlador(controlador);
        registre.registraControlador(controlador);
        gestiona.registraControlador(controlador);
        config.registraControlador(controlador);

        jbAtras.setActionCommand("Atras");
        jbAtras.addActionListener(controlador);

        config.getJtfPortClients().setActionCommand("PortClients");
        config.getJtfPortClients().addKeyListener(controlador);
    }

    public void registraControladorComboBox(ControladorComboBox controladorComboBox){
        grafic2x.registraControladorComboBox(controladorComboBox, "1");
        grafic4x.registraControladorComboBox(controladorComboBox, "2");
        graficTorneig.registraControladorComboBox(controladorComboBox, "3");
    }

    public void añadeMargenes(JPanel panell, int proportion){
        panell.add(Box.createRigidArea(new Dimension(Vista.AMPLADA_PANTALLA /proportion,Vista.ALTURA_PANTALLA /proportion)) ,BorderLayout.NORTH);
        panell.add(Box.createRigidArea(new Dimension(Vista.AMPLADA_PANTALLA /proportion,Vista.ALTURA_PANTALLA /proportion)) ,BorderLayout.WEST);
        panell.add(Box.createRigidArea(new Dimension(Vista.AMPLADA_PANTALLA /proportion,Vista.ALTURA_PANTALLA /proportion)) ,BorderLayout.EAST);
    }
}
