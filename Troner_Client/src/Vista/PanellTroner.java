package Vista;

import javax.swing.*;
import java.awt.*;

public class PanellTroner extends JPanel{

    private Color COLOR_PANELL_TRONER = Color.GRAY;
    private final static String DIRECCIO_ICONA = "images/TronerPanel2.png";

    private ImageIcon imageIcon;

    public PanellTroner(){
        super();
        this.setOpaque(false);
        this.setBackground(new Color(255,0,0,100));
        imageIcon = new ImageIcon(DIRECCIO_ICONA);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

    }


    protected void actualitza(){
        this.repaint();
    }


}
