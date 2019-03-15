package Vista;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Vector;

public class BotoTroner1 extends JTabbedPane implements MouseListener,KeyListener{
    private String title;
    private Vector listeners ;
    private boolean hit = false;
    private int AMPLITUT_BOTO = 120;
    private int  ALTURA_BOTO = 50;
    private ImageIcon icon1;
    private ImageIcon icon2;
    private boolean resaltar = false;

    public BotoTroner1 (String title){
        super();
        this.title = title;
        getAccessibleContext().setAccessibleName(title);
        listeners = new Vector();

        addMouseListener(this);
        addKeyListener(this);


        icon1 = new ImageIcon("images/BotoConfirmar.png");
        icon2 = new ImageIcon("images/BotoConfirmar2.png");;
        setOpaque(false);

    }

    public Dimension getPreferredSize(){return new Dimension(AMPLITUT_BOTO,ALTURA_BOTO);}

    @Override
    public void paintComponent(Graphics g){

        Graphics2D g2D = (Graphics2D)g;
        //super.paintComponent(g);

        /*
        g2D.setRenderingHint(
                RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

        g2D.setComposite(AlphaComposite.getInstance(
                AlphaComposite.SRC_OVER, 1.0f));
                */

        setOpaque(false);
        ((Graphics2D) g).setBackground(new Color(219, 80, 124, 178));
        if (resaltar){
            g.drawImage(icon1.getImage(),0,0,AMPLITUT_BOTO,ALTURA_BOTO,this);
        }else{
            g.drawImage(icon2.getImage(),0,0,AMPLITUT_BOTO,ALTURA_BOTO,this);
        }
    }

    @Override
    public void mousePressed(MouseEvent e){
        hit=true;
        this.repaint();
    }

    @Override
    public void mouseReleased(MouseEvent e){
        hit=false;
        this.repaint();
    }

    @Override
    public void mouseClicked(MouseEvent e){
        fireEvent(new ActionEvent(this,0,title));
    }

    @Override
    public void mouseEntered(MouseEvent e){
        resaltar = true;
        this.repaint();
    }

    @Override
    public void mouseExited(MouseEvent e){
        resaltar = false;
        this.repaint();
    }



    public void addActionListener(ActionListener listener){
        listeners.addElement(listener);
    }


    public void removeActionListener(ActionListener listener){
        listeners.removeElement(listener);
    }


    private void fireEvent(ActionEvent event){
        for (int i = 0;i<listeners.size() ;i++ ){
            ActionListener listener = (ActionListener)listeners.elementAt(i);
            listener.actionPerformed(event);
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
        System.out.println("Key pressed");
    }

    @Override
    public void keyPressed(KeyEvent e) {
        System.out.println("Key pasassasa");
        if(e.getKeyChar() == KeyEvent.VK_ENTER){
            resaltar = true;
            this.repaint();
            fireEvent(new ActionEvent(this,0,title));
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        System.out.println("Key pasassasa");
    }
}
