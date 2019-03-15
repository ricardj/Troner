package Vista.PantallaPrincipal;

import Controlador.EscoltaAccionsVista;
import Controlador.InformationClasses.EstatPantallaPrincipal.EstatConfiguracio;
import Controlador.InformationClasses.EstatPantallaPrincipal.EstatRanking;
import Vista.PantallaPrincipal.PanellConfiguracio.PanellConfiguracio;
import Vista.Vista;

import Vista.AccesibleContextSetter;
import javax.swing.*;
import java.awt.*;

public class PantallaPrincipal extends JPanel implements AccesibleContextSetter{

    private static final int ESPAI_ENTRE_PANELLS = 50;
    private static final float MIDA_TITOLS = 30.0f;

    private static final String IMATGE_DE_FONS = "images/fonsPantallaPrincipal.png";

    private ImageIcon fons;

    private Color colorTitols;

    private JLabel titol;

    private PanellConfiguracio panellConfiguracio;
    private PanellArrayElementRanking panellRankingX2;
    private PanellArrayElementRanking panellRankingX4;
    private PanellArrayElementRanking panellRankingTorneig;

    private JButton jugarX2;
    private JButton jugarX4;
    private JButton jugarTorneig;
    private JButton tancarSessio;

    public static final String BOTO_JUGARX2 = "botoJugarX2";
    public static final String BOTO_JUGARX4 = "botoJugarX4";
    public static final String BOTO_JUGARTORNEIG = "botoJugarTorneig";
    public static final String BOTO_TANCAR_SESSIO = "tancarSessio";

    private JLabel rankingX2;
    private JLabel rankingX4;
    private JLabel rankingTorneig;

    public PantallaPrincipal(EscoltaAccionsVista escoltaAccionsVista,
                             String login,
                             EstatRanking estatRanking,
                             EstatConfiguracio estatConfiguracio){

        carregarImatgeFons();

        colorTitols = new Color(225, 255, 0, 255);

        //Inicialitzar els panells que usarem
        panellConfiguracio = new PanellConfiguracio(estatConfiguracio,escoltaAccionsVista);
        panellRankingX2 = new PanellArrayElementRanking(estatRanking.getRankingX2());
        panellRankingX4 = new PanellArrayElementRanking(estatRanking.getRankingX4());
        panellRankingTorneig = new PanellArrayElementRanking(estatRanking.getRankingTorneig());

        setLayout(new BorderLayout());

        add(generarPanellSuperior(login), BorderLayout.NORTH);
        add(generarPanellCentral(), BorderLayout.CENTER);
        add(panellConfiguracio, BorderLayout.SOUTH);

        //Hem d'afegir el action Listener
        afegirEscoltaBotons(escoltaAccionsVista);

        setContextAccesible();
        repaint();
    }

    private void carregarImatgeFons() {
        fons = new ImageIcon(IMATGE_DE_FONS);
    }

    @Override
    public void setContextAccesible(){

        jugarX2.getAccessibleContext().setAccessibleName(Integer.toString(Vista.PANTALLA_PRINCIPAL));
        jugarX2.getAccessibleContext().setAccessibleDescription(BOTO_JUGARX2);

        jugarX4.getAccessibleContext().setAccessibleName(Integer.toString(Vista.PANTALLA_PRINCIPAL));
        jugarX4.getAccessibleContext().setAccessibleDescription(BOTO_JUGARX4);

        jugarTorneig.getAccessibleContext().setAccessibleName(Integer.toString(Vista.PANTALLA_PRINCIPAL));
        jugarTorneig.getAccessibleContext().setAccessibleDescription(BOTO_JUGARTORNEIG);

        tancarSessio.getAccessibleContext().setAccessibleName(Integer.toString(Vista.PANTALLA_PRINCIPAL));
        tancarSessio.getAccessibleContext().setAccessibleDescription(BOTO_TANCAR_SESSIO);

    }

    private void afegirEscoltaBotons(EscoltaAccionsVista escoltaAccionsVista){
        jugarX2.addActionListener(escoltaAccionsVista);
        jugarX4.addActionListener(escoltaAccionsVista);
        jugarTorneig.addActionListener(escoltaAccionsVista);
        tancarSessio.addActionListener(escoltaAccionsVista);
    }

    private JPanel generarPanellSuperior(String nom){
        JPanel panellSuperior = new JPanel();
        panellSuperior.setOpaque(false);
        panellSuperior.setLayout(new BorderLayout());

        Font fuente = Vista.getFontTitols().deriveFont(15.0f);
        tancarSessio = new JButton("Tancar Sessio");
        tancarSessio.setFont(fuente);
        tancarSessio.setHorizontalAlignment(JButton.NORTH_EAST);

        JLabel jlLoguin = new JLabel("Logued as: " + nom);
        jlLoguin.setForeground(Color.WHITE);
        jlLoguin.setFont(Vista.getFontContingut().deriveFont(20.0f));

        panellSuperior.add(jlLoguin, BorderLayout.WEST);
        panellSuperior.add(Box.createRigidArea(new Dimension(700,Vista.MIDA_Y/9)),BorderLayout.SOUTH);
        panellSuperior.add(tancarSessio,BorderLayout.EAST);

        return panellSuperior;
    }

    //Aqui fem el panel central amb els 3 labels, 3 rankings, 3 botons
    private JPanel generarPanellCentral(){

        //Botones
        Font fuente = Vista.getFontTitols().deriveFont(15.0f);
        jugarX2 = new JButton("Jugar X2");
        jugarX2.setFont(fuente);
        jugarX2.setAlignmentX(Component.CENTER_ALIGNMENT);


        jugarX4 = new JButton("Jugar X4");
        jugarX4.setFont(fuente);
        jugarX4.setAlignmentX(Component.CENTER_ALIGNMENT);


        jugarTorneig = new JButton("Jugar Torneig");
        jugarTorneig.setFont(fuente);
        jugarTorneig.setAlignmentX(Component.CENTER_ALIGNMENT);



        //Panell per a posar un espai entre configuracio i panells de ranking
        JPanel pont = new JPanel();
        pont.setOpaque(false);
        pont.setLayout(new BorderLayout());

        JPanel panellCentral = new JPanel();

        panellCentral.setOpaque(false);

        panellCentral.setLayout(new BoxLayout(panellCentral,BoxLayout.X_AXIS));

        panellCentral.add(Box.createRigidArea(new Dimension(ESPAI_ENTRE_PANELLS,0)));
        panellCentral.add(generarRankingX2());
        panellCentral.add(Box.createRigidArea(new Dimension(ESPAI_ENTRE_PANELLS,0)));
        panellCentral.add(generarRankingX4());
        panellCentral.add(Box.createRigidArea(new Dimension(ESPAI_ENTRE_PANELLS,0)));
        panellCentral.add(generarRankingTorneig());
        panellCentral.add(Box.createRigidArea(new Dimension(ESPAI_ENTRE_PANELLS,0)));

        pont.add(panellCentral,BorderLayout.CENTER);
        pont.add(Box.createRigidArea(new Dimension(Vista.MIDA_X,Vista.MIDA_Y/20)),BorderLayout.SOUTH);
        return pont;
    }


    private JPanel generarRankingX2(){
        JPanel panell = new JPanel();
        panell.setOpaque(false);
        panell.setLayout(new GridBagLayout());

        JLabel jlTitol= new JLabel("X2");
        jlTitol.setFont(Vista.getFontTitols().deriveFont(MIDA_TITOLS));
        jlTitol.setAlignmentX(Component.CENTER_ALIGNMENT);
        jlTitol.setForeground(colorTitols);

        GridBagConstraints gridBagConstraints = new GridBagConstraints();

        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        panell.add(jlTitol,gridBagConstraints);

        gridBagConstraints.gridy = 1;
        panell.add(panellRankingX2,gridBagConstraints);

        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        panell.add(jugarX2, gridBagConstraints);

        return panell;
    }

    private JPanel generarRankingX4(){
        JPanel panell = new JPanel();
        panell.setOpaque(false);
        panell.setLayout(new GridBagLayout());

        JLabel jlTitol= new JLabel("X4");
        jlTitol.setFont(Vista.getFontTitols().deriveFont(MIDA_TITOLS));
        jlTitol.setAlignmentX(Component.CENTER_ALIGNMENT);
        jlTitol.setForeground(colorTitols);

        GridBagConstraints gridBagConstraints = new GridBagConstraints();

        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridx = 0;
        panell.add(jlTitol,gridBagConstraints);

        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        panell.add(panellRankingX4,gridBagConstraints);

        gridBagConstraints.gridy = 2;
        panell.add(jugarX4,gridBagConstraints);

        return panell;
    }

    private JPanel generarRankingTorneig(){
        JPanel panell = new JPanel();
        panell.setOpaque(false);
        panell.setLayout(new GridBagLayout());

        JLabel jlTitol= new JLabel("Torneig");
        jlTitol.setForeground(colorTitols);
        jlTitol.setFont(Vista.getFontTitols().deriveFont(MIDA_TITOLS));
        jlTitol.setAlignmentX(Component.CENTER_ALIGNMENT);


        GridBagConstraints gridBagConstraints = new GridBagConstraints();

        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        panell.add(jlTitol,gridBagConstraints);

        gridBagConstraints.gridy = 1;
        panell.add(panellRankingTorneig,gridBagConstraints);

        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        panell.add(jugarTorneig,gridBagConstraints);

        return panell;
    }


    public void actualitzaRanking(EstatRanking estatRanking){
        //Extraiem la informacio neccesaria i la posem als panells que siguin necesarios
        panellRankingX2.actualitza(estatRanking.getRankingX2());
        panellRankingX4.actualitza(estatRanking.getRankingX4());
        panellRankingTorneig.actualitza(estatRanking.getRankingTorneig());
        repaint();
    }

    public void actualitzaConfiguracio(EstatConfiguracio configuracio){
        //Extraiem la informacio del estat i la posem al panell
        panellConfiguracio.actualitza(configuracio);
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        g.drawImage(fons.getImage(),0,0,Vista.MIDA_X,Vista.MIDA_Y,this);
    }



}
