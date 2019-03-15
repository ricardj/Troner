package Vista.PantallaConfiguracio;

import Vista.AccesibleContextSetter;

import Vista.BotoTroner1;
import Controlador.EscoltaAccionsVista;
import Vista.PantallaPare;
import Vista.Vista;

import javax.swing.*;
import java.awt.*;

public class PantallaConfiguracio extends PantallaPare implements AccesibleContextSetter {

    private static final float MIDA_TIPOGRAFIA = 20.0f;
    private static final String DIRECCIO_CONFIRM_BUTTON = "images/BotoConfirmar.png";

    public static final String BOTO_CONFIRMAR = "BotoConfirmar";

    private JTextField ip;
    private JTextField port;
    private BotoTroner1 confirmar;

    public PantallaConfiguracio(EscoltaAccionsVista escoltaAccionsVista){
        super(Vista.MIDA_X,Vista.MIDA_Y);

        setFons(FONDO2);

        getPanellIntermig().setLayout(new GridBagLayout());

        afegirJlabels();
        afegirJTextFields();
        afegirBotoConfirmacio();
        afegirEspaisBuits();

        setContextAccesible();

        registrarListenerBotons(escoltaAccionsVista);
    }

    private void registrarListenerBotons(EscoltaAccionsVista escoltaAccionsVista){
        confirmar.addActionListener(escoltaAccionsVista);
    }

    private void afegirJlabels(){

        JLabel ipLabel = new JLabel("Ip");
        JLabel portLabel = new JLabel("Port");

        settejarJLabels(ipLabel,portLabel);

        GridBagConstraints constraints = new GridBagConstraints();

        constraints.gridy = 0;
        constraints.gridx = 1;
        constraints.weightx = 0.5;
        constraints.weighty = 0.5;
        constraints.anchor = GridBagConstraints.LINE_START;
        getPanellIntermig().add(ipLabel,constraints);

        constraints.gridy = 3;
        constraints.gridx = 1;
        constraints.weightx = 0.5;
        constraints.weighty = 0.5;
        constraints.anchor = GridBagConstraints.LINE_START;
        getPanellIntermig().add(portLabel,constraints);

    }

    private void settejarJLabels(JLabel ipLabel,JLabel portLabel){

        Font font = Vista.getFontTitols().deriveFont(MIDA_TIPOGRAFIA);
        ipLabel.setFont(font);
        ipLabel.setForeground(Color.WHITE);
        portLabel.setFont(font);
        portLabel.setForeground(Color.WHITE);
    }

    private void afegirJTextFields(){
        ip = new JTextField("192.168.43.184");

        port = new JTextField("55555");

        GridBagConstraints constraints = new GridBagConstraints();

        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.gridy = 1;
        constraints.gridx = 1;
        constraints.weightx = 0.5;
        constraints.weighty = 0.5;
        getPanellIntermig().add(ip,constraints);


        constraints.gridy = 4;
        constraints.gridx = 1;
        constraints.weightx = 0.5;
        constraints.weighty = 0.5;
        getPanellIntermig().add(port,constraints);
    }
    private void afegirBotoConfirmacio(){

        confirmar = new BotoTroner1("Confirmar");

        //ImageIcon aux_image = new ImageIcon(DIRECCIO_CONFIRM_BUTTON);

        GridBagConstraints constraints = new GridBagConstraints();

        constraints.gridy = 5;
        constraints.gridx = 1;
        constraints.weightx = 0.5;
        constraints.weighty = 0.5;
        getPanellIntermig().add(confirmar,constraints);

    }

    private void afegirEspaisBuits(){
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridy = 5;
        constraints.gridx = 0;
        constraints.weightx = 1;
        constraints.weighty = 1;
        getPanellIntermig().add(Box.createRigidArea(new Dimension(5,5)),constraints);

        constraints.gridy = 5;
        constraints.gridx = 2;
        constraints.weightx = 1;
        constraints.weighty = 1;
        getPanellIntermig().add(Box.createRigidArea(new Dimension(5,5)),constraints);
    }

    @Override
    public void setContextAccesible(){
        confirmar.getAccessibleContext().setAccessibleName(Integer.toString(Vista.PANTALLA_CONFIGURACIO));
        confirmar.getAccessibleContext().setAccessibleDescription(BOTO_CONFIRMAR);
    }


    public String getPort() {
        return port.getText();
    }

    public String getIP() {
        return ip.getText();
    }

}
