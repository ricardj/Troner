package Vista.PantallaLoginRegistre;

import Vista.AccesibleContextSetter;
import Controlador.EscoltaAccionsVista;
import Vista.PantallaPare;
import Vista.Vista;
import Vista.BotoTroner1;

import javax.swing.*;
import java.awt.*;

public class PantallaLogin extends PantallaPare implements AccesibleContextSetter{

    private static final float MIDA_TIPOGRAFIA = 15.0f;

    public static final String BOTO_CONFIRMAR = "botoConfirmar";
    public static final String BOTO_ENRERE = "botoEnrere";

    private JTextField usuari;
    private JPasswordField contrasenya;
    private BotoTroner1 botoConfirmar;
    private JButton botoEnrere;

    public PantallaLogin(EscoltaAccionsVista escoltaAccionsVista){
        super(Vista.MIDA_X,Vista.MIDA_Y);

        //Settejar layout del panell intermig

        GridBagLayout layout = new GridBagLayout();
        getPanellIntermig().setLayout(layout);


        //Afegir labels
        afegirLabels();
        //Afegir textfields
        afegirTextFields();
        //Afegir botons
        afegirBotoConfirmar();
        afegirBotoEnrere();
        //Afegir espais en blanc
        afegirEspaisBuits();

        registrarListenerBotons(escoltaAccionsVista);

        setContextAccesible();

    }

    @Override
    public void setContextAccesible(){
        botoConfirmar.getAccessibleContext().setAccessibleName(Integer.toString(Vista.PANTALLA_LOGIN));
        botoConfirmar.getAccessibleContext().setAccessibleDescription(BOTO_CONFIRMAR);

        botoEnrere.getAccessibleContext().setAccessibleName(Integer.toString(Vista.PANTALLA_LOGIN));
        botoEnrere.getAccessibleContext().setAccessibleDescription(BOTO_ENRERE);

    }

    private void afegirBotoEnrere(){
        Font fuente = Vista.getFontTitols().deriveFont(15.0f);

        botoEnrere = new JButton("Enrere");
        botoEnrere.setFont(fuente);

        getPanellSuperiorLateralEsquerre().add(botoEnrere,BorderLayout.CENTER);
    }

    private void registrarListenerBotons(EscoltaAccionsVista escoltaAccionsVista){
        botoConfirmar.addActionListener(escoltaAccionsVista);
        botoEnrere.addActionListener(escoltaAccionsVista);
    }


    private void afegirTextFields(){
        usuari = new JTextField(); //Se puede hacer setFont del texto de los textfields (agencyFB?)
        contrasenya = new JPasswordField();

        GridBagConstraints constraints = new GridBagConstraints();

        constraints.gridy = 1;
        constraints.gridx = 0;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.weightx = 1;
        getPanellIntermig().add(usuari,constraints);

        constraints.gridy = 3;
        constraints.gridx = 0;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        getPanellIntermig().add(contrasenya,constraints);
    }

    private void afegirLabels(){
        JLabel usuari = new JLabel("Usuari");
        JLabel contrasenya = new JLabel("Contrasenya");

        Color colorForeground = Color.WHITE;
        usuari.setForeground(colorForeground);
        contrasenya.setForeground(colorForeground);

        Font font = Vista.getFontTitols().deriveFont(MIDA_TIPOGRAFIA);
        usuari.setFont(font);
        contrasenya.setFont(font);

        GridBagConstraints constraints = new GridBagConstraints();

        constraints.gridy = 0;
        constraints.gridx = 0;
        constraints.anchor = GridBagConstraints.LINE_START;
        getPanellIntermig().add(usuari,constraints);

        constraints.gridy = 2;
        constraints.gridx = 0;
        constraints.anchor = GridBagConstraints.LINE_START;
        getPanellIntermig().add(contrasenya,constraints);
    }

    private void afegirBotoConfirmar(){
        botoConfirmar = new BotoTroner1("Confirmar");

        GridBagConstraints constraints = new GridBagConstraints();

        constraints.gridy = 5;
        constraints.gridx = 0;
        getPanellIntermig().add(botoConfirmar,constraints);
    }

    private void afegirEspaisBuits(){

    }


    public String getNom() {
        return usuari.getText();
    }

    public String getContrasenya() {
        return String.valueOf(contrasenya.getPassword());
    }
}
