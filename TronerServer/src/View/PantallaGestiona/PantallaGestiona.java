package View.PantallaGestiona;

import Controlador.Controlador;
import Model.DBHelper;

import javax.swing.*;
import javax.swing.event.AncestorListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class PantallaGestiona extends JPanel {
    private JScrollPane jspUsuaris;
    private String[] columnNames;
    private JButton jbEliminar;
    private JTable jtUsuaris;
    private Object[][] usuaris;
    private JPanel subPanel;
    private GridBagConstraints constraints;
    private DefaultTableModel model;
    private int numUsuaris;


    public PantallaGestiona(){
        columnNames = new String[]{"Nickname",
                "Punts",
                "First connection",
                "Last connection"
        };
        jbEliminar = new JButton("ELIMINAR USUARI SELECCIONAT");
        subPanel = new JPanel(new GridBagLayout());

        setLayout(new BorderLayout());

        constraints = new GridBagConstraints();
        constraints.gridx = 3;
        constraints.weightx = 0.5;
        constraints.weighty = 0.5;
    }

    public void registraControlador(Controlador controlador){
        jbEliminar.setActionCommand("Eliminar");
        jbEliminar.addActionListener(controlador);
    }

    public void setUsuaris(Object[][] usuarisBBDD){

        if (jtUsuaris == null) {
            usuaris = usuarisBBDD;
            numUsuaris = usuaris.length;
            model = new DefaultTableModel(usuaris, columnNames);
            jtUsuaris = new JTable(model);
            jtUsuaris.setDefaultEditor(Object.class, null);
            jspUsuaris = new JScrollPane(jtUsuaris);
            jspUsuaris.setPreferredSize(new Dimension(400, 200));
            constraints.gridy = 5;
            subPanel.add(jbEliminar, constraints);
            constraints.gridy = 4;
            subPanel.add(jspUsuaris, constraints);
            add(subPanel, BorderLayout.CENTER);
        } else {
           if(numUsuaris != usuarisBBDD.length) {
               jtUsuaris.removeAll();
               usuaris = usuarisBBDD;
               model = new DefaultTableModel(usuaris, columnNames);
               jtUsuaris.setModel(model);
           }

        }

    }

    public void removeRow(){
        DefaultTableModel model = (DefaultTableModel)jtUsuaris.getModel();
        model.removeRow(jtUsuaris.getSelectedRow());
        jtUsuaris.repaint();
    }

    public void avisaUsuari(){
        JOptionPane.showMessageDialog(this, "Selecciona algun usuari.");
    }

    public int selectedRow(){
        return jtUsuaris.getSelectedRow();
    }
}
