package View.PantallaGrafic;


import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.Stroke;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;

public class DrawGraph extends JPanel {
    private static final int PREF_W = 800;
    private static final int PREF_H = 650;
    private static final int BORDER_GAP = 100;
    private static final Color GRAPH_COLOR = Color.green;
    private static final Color GRAPH_POINT_COLOR = new Color(150, 50, 50, 180);
    private static final Stroke GRAPH_STROKE = new BasicStroke(3f);
    private static final int GRAPH_POINT_WIDTH = 12;
    private List<Integer> scores;
    private static int max_score;
    private final int min_score;
    private String mode;

    public DrawGraph(List<Integer> scores, String mode){
        this.mode = mode;
        this.scores = scores;
        max_score = getMaxScore(scores);
        min_score = getMinScore(scores);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        double xScale = ((double) getWidth() - 2 * BORDER_GAP) / (scores.size() - 1);
        double yScale = ((double) getHeight() - 2 * BORDER_GAP) / ((max_score - min_score) - 1);


        // omple array de punts de la grafica
        List<Point> graphPoints = new ArrayList<>();
        for (int i = 0; i < scores.size(); i++) {
            int x1 = (int) (i * xScale + BORDER_GAP);
            int y1 = (int) ((max_score - scores.get(i)) * yScale + BORDER_GAP);
            graphPoints.add(new Point(x1, y1));
        }

        // create y axe
        g2.drawLine(BORDER_GAP, getHeight() - BORDER_GAP, BORDER_GAP, BORDER_GAP);
        g2.drawString("PUNTUACIONS", BORDER_GAP - 50, BORDER_GAP - 20);

        //create x axe
        g2.drawLine(BORDER_GAP, getHeight() - BORDER_GAP, getWidth() - BORDER_GAP, getHeight() - BORDER_GAP);
        g2.drawString("PARTIDES" , getWidth() - 80, getHeight() - BORDER_GAP);


        // create hatch marks for y axis.
        for (int i = 0; i < scores.size(); i++) {
            int x0 = BORDER_GAP;
            int x1 = BORDER_GAP + 5;
            int y0 = graphPoints.get(i).y;
            int y1 = y0;
            g2.drawLine(x0, y0, x1, y1);
            if(i == 0){
                g2.drawString(String.valueOf(scores.get(i) - 1), graphPoints.get(i).x - 2 * GRAPH_POINT_WIDTH, graphPoints.get(i).y + 5);
            } else {
                g2.drawString(String.valueOf(scores.get(i) - 1), graphPoints.get(i).x - 10, graphPoints.get(i).y - 10);
            }
        }

        // and for x axis
        for (int i = 0; i < scores.size() - 1; i++) {
            int x0 = (i + 1) * (getWidth() - BORDER_GAP * 2) / (scores.size() - 1) + BORDER_GAP;
            int x1 = x0;
            int y0 = getHeight() - BORDER_GAP;
            int y1 = y0 - 5;
            g2.drawLine(x0, y0, x1, y1);
            g2.drawString(String.valueOf(i + 1), x0 - 5, y0 + 15);
        }

        // draw graphic line
        Stroke oldStroke = g2.getStroke();
        g2.setColor(GRAPH_COLOR);
        g2.setStroke(GRAPH_STROKE);
        for (int i = 0; i < graphPoints.size() - 1; i++) {
            int x1 = graphPoints.get(i).x;
            int y1 = graphPoints.get(i).y;
            int x2 = graphPoints.get(i + 1).x;
            int y2 = graphPoints.get(i + 1).y;
            g2.drawLine(x1, y1, x2, y2);
        }

        // draw graphic points
        g2.setStroke(oldStroke);
        g2.setColor(GRAPH_POINT_COLOR);
        for (int i = 0; i < graphPoints.size(); i++) {
            int x = graphPoints.get(i).x - GRAPH_POINT_WIDTH / 2;
            int y = graphPoints.get(i).y - GRAPH_POINT_WIDTH / 2;
            int ovalW = GRAPH_POINT_WIDTH;
            int ovalH = GRAPH_POINT_WIDTH;
            g2.fillOval(x, y, ovalW, ovalH);
        }
    }

    private Integer getMaxScore(List<Integer> scores){
        Integer max = 0;
        for (Integer i: scores){
            if (i > max){
                max = i;
            }
        }
        return max;
    }

    private Integer getMinScore(List<Integer> scores){
        Integer min = max_score;
        for (Integer i: scores){
            if (i < min){
                min = i;
            }
        }
        return min;
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(PREF_W, PREF_H);
    }

    public static List<Integer> createAndShowGui(int[] punts) {
        List<Integer> scores = new ArrayList<>();
        for (int i = 0; i < punts.length; i++) {
            scores.add(punts[i] + 1);
        }
        return scores;
    }
}
