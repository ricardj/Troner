package Vista.PantallaSalaEspera;

import Controlador.EscoltaAccionsVista;
import Controlador.InformationClasses.EstatSalaEspera.EstatSalaEspera;
import Vista.AccesibleContextSetter;
import Vista.Vista;

import javax.swing.*;
import java.awt.*;

public class PantallaSalaEspera extends JPanel implements AccesibleContextSetter{

    public static final String BOTO_ABANDONAR = "botoAbandonar";

    public static final int MODE_JOC_X2 = 0;
    public static final int MODE_JOC_X4 = 1;
    public static final int MODE_JOC_TORNEIG = 2;

    private static final String FONS_X2 = "images/fonsPantallaPrincipal.png";
    private static final String FONS_X4 = "images/fonsPantallaPrincipal1.png";
    private static final String FONS_TORNEIG = "images/tron_fondo1.jpg";

    private ImageIcon fonsX2;
    private ImageIcon fonsX4;
    private ImageIcon fonsTorneig;

    private int modeJoc;
    private JLabel numJugadors;
    private JLabel numMaxJugadors;
    private JLabel waiting;

    private JButton abandonar;

    public PantallaSalaEspera(EscoltaAccionsVista escoltaAccionsVista, EstatSalaEspera estatSalaEspera, int modeJoc){
        super();
        fonsTorneig = new ImageIcon(FONS_TORNEIG);
        fonsX2 = new ImageIcon(FONS_X2);
        fonsX4 = new ImageIcon(FONS_X4);

        this.modeJoc = modeJoc;

        this.numJugadors = new JLabel(Integer.toString(estatSalaEspera.getJugadorsActuals()));
        this.numMaxJugadors = new JLabel(Integer.toString(estatSalaEspera.getJugadorsEsperats()));

        Font fuente = Vista.getFontTitols().deriveFont(50.0f);
        numJugadors.setFont(fuente);
        numMaxJugadors.setFont(fuente);

        waiting = new JLabel(" Waiting...");
        waiting.setFont(Vista.getFontContingut().deriveFont(20.0f));

        setLayout(new BorderLayout());

        afegirBotoAbandonar();
        afegirPanellCentral();

        registrarListenerBotons(escoltaAccionsVista);
        setContextAccesible();
    }

    private void afegirBotoAbandonar(){
        abandonar = new JButton("Abandonar");
        abandonar.setFont(Vista.getFontTitols().deriveFont(20.0f));

        JPanel auxiliar = new JPanel();
        auxiliar.setOpaque(false);

        auxiliar.setLayout(new BorderLayout());
        auxiliar.add(abandonar,BorderLayout.EAST);

        add(auxiliar,BorderLayout.NORTH);
    }

    private void afegirPanellCentral(){

        JPanel auxiliar = new JPanel();
        auxiliar.setLayout(new BorderLayout());

        JLabel barra = new JLabel("/");
        barra.setFont(Vista.getFontTitols().deriveFont(70.0f));

        JPanel nums = new JPanel();
        nums.setOpaque(false);
        //nums.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        nums.add(numJugadors);
        nums.add(barra);
        nums.add(numMaxJugadors);

        auxiliar.add(nums,BorderLayout.CENTER);
        auxiliar.add(waiting,BorderLayout.SOUTH);
        auxiliar.setBackground(new Color(225, 255, 0, 206));

        JPanel panellReductor = new JPanel(new BorderLayout());
        panellReductor.setOpaque(false);
        panellReductor.add(auxiliar,BorderLayout.CENTER);
        posarEspaisBuits(panellReductor);

        add(panellReductor,BorderLayout.CENTER);

    }

    private void posarEspaisBuits(JPanel panell){
        panell.add(Box.createRigidArea(new Dimension(Vista.MIDA_X/3,Vista.MIDA_Y/3)),BorderLayout.NORTH);
        panell.add(Box.createRigidArea(new Dimension(Vista.MIDA_X/3,Vista.MIDA_X/3)),BorderLayout.SOUTH);
        panell.add(Box.createRigidArea(new Dimension(Vista.MIDA_X/3,Vista.MIDA_X/3)),BorderLayout.EAST);
        panell.add(Box.createRigidArea(new Dimension(Vista.MIDA_X/3,Vista.MIDA_X/3)),BorderLayout.WEST);
    }

    public void actualitzar(EstatSalaEspera estatSalaEspera,int n){
        //TODO:aprofitar la informacio que ens dona el estat sala espera
        this.numJugadors.setText(Integer.toString(estatSalaEspera.getJugadorsActuals()));
        StringBuilder aux = new StringBuilder(" Waiting");
        for(int i = 0;i< n;i++) aux.append(".");
        this.waiting.setText(aux.toString());
        repaint();
    }

    private void registrarListenerBotons(EscoltaAccionsVista escoltaAccionsVista){
        abandonar.addActionListener(escoltaAccionsVista);
    }

    @Override
    public void setContextAccesible() {
        abandonar.getAccessibleContext().setAccessibleName(Integer.toString(Vista.PANTALLA_ESPERA));
        abandonar.getAccessibleContext().setAccessibleDescription(BOTO_ABANDONAR);
    }

    @Override
    public void paintComponent(Graphics g){
        //System.out.println("Flag: pintem sala de espera");
        super.paintComponent(g);
        ImageIcon pintar = new ImageIcon();
        switch (modeJoc){
            case MODE_JOC_X2:
                pintar = fonsX2;
                break;
            case MODE_JOC_X4:
                pintar = fonsX4;
                break;
            case MODE_JOC_TORNEIG:
                pintar = fonsTorneig;
                break;
        }
        g.drawImage(pintar.getImage(),0,0,Vista.MIDA_X,Vista.MIDA_Y,this);
    }
}
