package View.PantallaGrafic;

import Controlador.ControladorComboBox;
import Controlador.InformationClasses.EstatPantallaPrincipal.HistoricJugador;

import javax.swing.*;
import java.awt.*;

public class PantallaGrafic extends JPanel {
    private JComboBox<String> jcNoms;
    private JPanel grafiques;
    private CardLayout cardLayout;
    private String mode_joc;

    public PantallaGrafic(){
        jcNoms = new JComboBox<>();
        grafiques = new JPanel(new CardLayout());
        setLayout(new BorderLayout());
    }

    public void mostraGrafic(String nom){
        cardLayout.show(grafiques, nom);
        grafiques.updateUI();
    }

    public void registraControladorComboBox(ControladorComboBox controladorComboBox, String mode_joc){
        this.mode_joc = mode_joc;
        jcNoms.setActionCommand(mode_joc);
        jcNoms.addActionListener(controladorComboBox);
    }

    public void ompleDades(HistoricJugador[] dades){
        for (int i = 0; i < dades.length; i++) {
            Boolean existeix = false;
            for (int j = 0; j < jcNoms.getItemCount(); j++) {
                if (dades[i].getName().equals(jcNoms.getItemAt(j))) {
                    DrawGraph graph = new DrawGraph(DrawGraph.createAndShowGui(dades[i].getPunts()), mode_joc);
                    grafiques.add(graph, dades[i].getName());
                    existeix = true;
                }
            }
            if (!existeix) {
                jcNoms.addItem(dades[i].getName());
                DrawGraph graph = new DrawGraph(DrawGraph.createAndShowGui(dades[i].getPunts()), mode_joc);
                grafiques.add(graph, dades[i].getName());
            }
        }
        add(jcNoms, BorderLayout.NORTH);
        add(grafiques, BorderLayout.CENTER);
        cardLayout = (CardLayout) grafiques.getLayout();
    }
}
