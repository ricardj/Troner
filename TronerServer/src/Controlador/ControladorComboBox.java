package Controlador;

import Controlador.InformationClasses.EstatPantallaPrincipal.HistoricJugador;
import Model.Model;
import View.Vista;


import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import Model.DBHelper;


public class ControladorComboBox implements ActionListener {
    private Vista vista;
    private Model model;

    public ControladorComboBox(Vista vista) {
        this.vista = vista;
        model = new Model();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JComboBox combo = (JComboBox) e.getSource();
        HistoricJugador[] dades1 = model.agafaDadesGrafic(1);
        vista.getGrafic2x().ompleDades(dades1);
        HistoricJugador[] dades2 = model.agafaDadesGrafic(2);
        vista.getGrafic4x().ompleDades(dades2);
        HistoricJugador[] dades3 = model.agafaDadesGrafic(3);
        vista.getGraficTorneig().ompleDades(dades3);
        switch(combo.getActionCommand()) {
            case "1":
                for (int i = 0; i < dades1.length; i++) {
                    if (combo.getSelectedItem().equals(dades1[i].getName())) {
                        vista.getGrafic2x().mostraGrafic(dades1[i].getName());
                    }
                }
                break;
            case "2":
                for (int i = 0; i < dades2.length; i++) {
                    if (combo.getSelectedItem().equals(dades2[i].getName())) {
                        vista.getGrafic4x().mostraGrafic(dades2[i].getName());
                    }
                }
                break;
            case "3":
                for (int i = 0; i < dades3.length; i++) {
                    if (combo.getSelectedItem().equals(dades3[i].getName())) {
                        vista.getGraficTorneig().mostraGrafic(dades3[i].getName());
                    }
                }
                break;
        }
    }
}
