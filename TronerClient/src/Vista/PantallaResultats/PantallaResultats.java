package Vista.PantallaResultats;

import Controlador.EscoltaAccionsVista;
import Controlador.InformationClasses.EstatResultats.EstatResultats;
import Vista.AccesibleContextSetter;
import Vista.Vista;

import javax.swing.*;
import java.awt.*;

public class PantallaResultats extends JPanel implements AccesibleContextSetter{

    public static final String BOTO_CONTINUAR = "botoContinuar";
    public static final String BOTO_ABANDONAR = "botoAbandonar";

    private JButton continuar;
    private JButton abandonar;
    private JLabel posicio;
    private JLabel punts;
    private JLabel total;
    private EstatResultats estatResultats;

    public PantallaResultats(EscoltaAccionsVista escoltaAccionsVista, EstatResultats estatResultats){
        super();

        this.estatResultats = estatResultats;

        setLayout(new BorderLayout());

        add(generarPanellCentral(),BorderLayout.CENTER);
        posarEspaisBuits();

        this.estatResultats = estatResultats;

        registrarListenerBotons(escoltaAccionsVista);
        setContextAccesible();
        System.out.println("Flag: pantalla de resultats settejada i posada");
        updateUI();
        repaint();
    }

    private JPanel generarPanellCentral() {
        JPanel panellCentral = new JPanel();
        panellCentral.setBackground(new Color(225, 255,0, 206));
        panellCentral.setLayout(new BorderLayout());

        panellCentral.add(generarPanellSuperior(),BorderLayout.CENTER);
        panellCentral.add(generarPanellInferior(),BorderLayout.SOUTH);

        return panellCentral;
    }

    private JPanel generarPanellSuperior() {
        JPanel panellLabels = new JPanel();
        panellLabels.setOpaque(false);

        posicio = new JLabel(Integer.toString(estatResultats.getPosicio()));
        punts = new JLabel("Punts: "+ Long.toString(estatResultats.getPunts()));
        total = new JLabel("Total: "+ Integer.toString(estatResultats.getTotal()));

        Font fuente = Vista.getFontTitols().deriveFont(20.0f);
        posicio.setFont(Vista.getFontTitols().deriveFont(40.0f));
        punts.setFont(fuente);
        total.setFont(fuente);

        posicio.setHorizontalAlignment(JLabel.CENTER);
        punts.setHorizontalAlignment(JLabel.CENTER);
        total.setHorizontalAlignment(JLabel.CENTER);

        panellLabels.setLayout(new BorderLayout());
        panellLabels.add(posicio,BorderLayout.NORTH);
        panellLabels.add(punts,BorderLayout.CENTER);
        panellLabels.add(total,BorderLayout.SOUTH);

        return panellLabels;
    }

    private JPanel generarPanellInferior() {
        JPanel panellInferior = new JPanel();
        panellInferior.setOpaque(false);
        panellInferior.setLayout(new GridBagLayout());

        GridBagConstraints gridBagConstraints = new GridBagConstraints();

        abandonar = new JButton("Abandonar");
        continuar = new JButton("Continuar");

        Font fuente = Vista.getFontTitols().deriveFont(10.0f);
        abandonar.setFont(fuente);
        continuar.setFont(fuente);

        gridBagConstraints.gridx = 0;
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        panellInferior.add(continuar,gridBagConstraints);
        gridBagConstraints.gridx = 1;
        panellInferior.add(abandonar,gridBagConstraints);

        return panellInferior;
    }

    private void posarEspaisBuits(){
        add(Box.createRigidArea(new Dimension(Vista.MIDA_X/3,Vista.MIDA_Y/3)),BorderLayout.EAST);
        add(Box.createRigidArea(new Dimension(Vista.MIDA_X/3,Vista.MIDA_Y/3)),BorderLayout.NORTH);
        add(Box.createRigidArea(new Dimension(Vista.MIDA_X/3,Vista.MIDA_Y/3)),BorderLayout.SOUTH);
        add(Box.createRigidArea(new Dimension(Vista.MIDA_X/3,Vista.MIDA_Y/3)),BorderLayout.WEST);
    }

    private void registrarListenerBotons(EscoltaAccionsVista escoltaAccionsVista){
        continuar.addActionListener(escoltaAccionsVista);
        abandonar.addActionListener(escoltaAccionsVista);
    }


    @Override
    public void setContextAccesible() {
        continuar.getAccessibleContext().setAccessibleName(Integer.toString(Vista.PANTALLA_RESULTATS));
        continuar.getAccessibleContext().setAccessibleDescription(BOTO_CONTINUAR);

        abandonar.getAccessibleContext().setAccessibleName(Integer.toString(Vista.PANTALLA_RESULTATS));
        abandonar.getAccessibleContext().setAccessibleDescription(BOTO_ABANDONAR);
    }

    @Override
    public void paintComponent(Graphics g){
        //TODO: pintem imatge de fons
        super.paintComponent(g);
        ImageIcon pintar = new ImageIcon("images/fonsPantallaPrincipal1.png");
        g.drawImage(pintar.getImage(),0,0,Vista.MIDA_X,Vista.MIDA_Y,this);
    }

}
