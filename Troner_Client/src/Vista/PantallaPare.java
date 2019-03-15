package Vista;

import javax.imageio.plugins.jpeg.JPEGHuffmanTable;
import javax.swing.*;
import java.awt.*;

/**
 * La classe PantallaPare estableix el model a seguir per les finestres inicials del joc.
 * Aquestes son:
 *  `   -Pantalla Configuracio
 *      -Pantalla Login
 *      -Pantalla Registre
 */
public class PantallaPare extends JPanel {

    protected int ALTURA_PANTALLA;
    protected int AMPLITUT_PANTALLA;

    protected final static String DIRECCIO_FONDO2 = "images/TronerFondo2.jpg";
    protected final static String DIRECCIO_FONDO1 = "images/TronerFondo1.jpg";


    private static ImageIcon fotoFons1;
    private static ImageIcon fotoFons2;
    private static int fotoActual;

    public static final int FONDO1 = 1;
    public static final int FONDO2 = 2;

    private PanellTroner panellIntermig;
    private JPanel panellSuperiorLateralEsquerre;

    public PantallaPare(int amplitutPantalla,int alturaPantalla){
        ALTURA_PANTALLA = alturaPantalla;
        AMPLITUT_PANTALLA = amplitutPantalla;

        settingsInicials();

        BorderLayout layout = new BorderLayout();
        setLayout(layout);

        panellIntermig = inicialitzarPanellIntermig();

        panellSuperiorLateralEsquerre = new JPanel();
        panellSuperiorLateralEsquerre.setOpaque(false);
        panellSuperiorLateralEsquerre.setLayout(new BorderLayout());


        add(panellIntermig,BorderLayout.CENTER);

        JPanel aux = new JPanel();
        aux.setLayout(new BorderLayout());
        aux.setOpaque(false);
        aux.add(panellSuperiorLateralEsquerre,BorderLayout.EAST);
        aux.add(Box.createRigidArea(new Dimension(AMPLITUT_PANTALLA/3,ALTURA_PANTALLA/3)),BorderLayout.SOUTH);

        add(aux,BorderLayout.NORTH);

        ponerEspaciosVacios();
    }

    private void settingsInicials(){
        setSize(new Dimension(AMPLITUT_PANTALLA,ALTURA_PANTALLA));
        setPreferredSize(new Dimension(AMPLITUT_PANTALLA,ALTURA_PANTALLA));
        fotoFons1 = new ImageIcon(DIRECCIO_FONDO1);
        fotoFons2 = new ImageIcon(DIRECCIO_FONDO2);
        fotoActual = 1;
        setOpaque(true);
    }

    public void ponerEspaciosVacios(){
        add(Box.createRigidArea(new Dimension(AMPLITUT_PANTALLA/3,ALTURA_PANTALLA/3)),BorderLayout.SOUTH);
        add(Box.createRigidArea(new Dimension(AMPLITUT_PANTALLA/3,ALTURA_PANTALLA/3)),BorderLayout.WEST);
        add(Box.createRigidArea(new Dimension(AMPLITUT_PANTALLA/3,ALTURA_PANTALLA/3)),BorderLayout.EAST);
    }

    private PanellTroner inicialitzarPanellIntermig(){
        PanellTroner panell = new PanellTroner();
        return panell;
    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        ImageIcon pintar = new ImageIcon();
        switch (fotoActual){
            case FONDO1:
                pintar = fotoFons1;
                break;
            case FONDO2:
                pintar = fotoFons2;
                break;
        }
        g.drawImage(pintar.getImage(),0,0,AMPLITUT_PANTALLA,ALTURA_PANTALLA,this);
        panellIntermig.actualitza();
    }

    protected void actualitza(){
        panellIntermig.actualitza();
        this.repaint();
    }

    public PanellTroner getPanellIntermig() {
        return panellIntermig;
    }
    public JPanel getPanellSuperiorLateralEsquerre(){
        return panellSuperiorLateralEsquerre;
    }

    public void setFons(int foto){
        fotoActual = foto;
    }
}
