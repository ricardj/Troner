package View.PantallaRanquing;

import Controlador.InformationClasses.EstatPantallaPrincipal.InfoJugador;
import View.Vista;

import javax.swing.*;
import java.awt.*;
import java.util.Date;
import java.util.LinkedList;

public class PanellTaula extends JPanel{
    private JScrollPane jspRanquing;
    private JLabel jlMode;
    private Object[][] data;

    public PanellTaula(String mode, LinkedList<InfoJugador> info, Date[] dates){
        String[] columnNames = {"Nickname",
                "Last connection",
                "Punts"
        };

        data = new Object[info.size()][3];

        for(int i = 0; i < info.size(); i++){
            data[i][0] = info.get(i).getName();
            data[i][1] = dates[i];
            data[i][2] = info.get(i).getPunts();
        }

        JTable jtRanquing = new JTable(data, columnNames);
        jtRanquing.setDefaultEditor(Object.class, null);

        jspRanquing = new JScrollPane(jtRanquing);

        jlMode = new JLabel(mode);
        jlMode.setFont(Vista.TITLES);
        jlMode.setHorizontalAlignment(SwingConstants.CENTER);

        setLayout(new BorderLayout());

        add(jlMode, BorderLayout.NORTH);
        add(jspRanquing, BorderLayout.CENTER);
    }

}