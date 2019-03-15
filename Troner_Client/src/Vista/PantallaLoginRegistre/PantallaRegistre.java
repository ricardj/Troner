package Vista.PantallaLoginRegistre;

import Vista.AccesibleContextSetter;
import Controlador.EscoltaAccionsVista;
import Vista.PantallaPare;
import Vista.Vista;

import javax.swing.*;
import java.awt.*;
import Vista.BotoTroner1;

public class PantallaRegistre extends PantallaPare implements AccesibleContextSetter{

    private static final float MIDA_TIPOGRAFIA = 10.0f;

    public static final String BOTO_CONFIRMAR = "botoConfirmar";
    public static final String BOTO_ENRERE = "botoEnrere";

    private JTextField usuari;
    private JTextField correu;
    private JPasswordField contrasenya;
    private JPasswordField confirmacioContrasenya;
    private BotoTroner1 botoConfirmar;
    private JButton botoEnrere;


    public PantallaRegistre(EscoltaAccionsVista escoltaAccionsVista) {
        super(Vista.MIDA_X, Vista.MIDA_Y);

        getPanellIntermig().setLayout(new GridBagLayout());

        //Afegir labels
        afegirLabels();
        afegirTextFields();
        afegirBotoConfirmar();
        afegirBotoEnrere();
        afegirEspaisBuits();

        registrarListenerBotons(escoltaAccionsVista);
        setContextAccesible();
    }

    @Override
    public void setContextAccesible(){
        botoConfirmar.getAccessibleContext().setAccessibleName(Integer.toString(Vista.PANTALLA_REGISTRE));
        botoConfirmar.getAccessibleContext().setAccessibleDescription(BOTO_CONFIRMAR);

        botoEnrere.getAccessibleContext().setAccessibleName(Integer.toString(Vista.PANTALLA_REGISTRE));
        botoEnrere.getAccessibleContext().setAccessibleDescription(BOTO_ENRERE);
    }

    private void registrarListenerBotons(EscoltaAccionsVista escoltaAccionsVista){
        botoConfirmar.addActionListener(escoltaAccionsVista);
        botoEnrere.addActionListener(escoltaAccionsVista);
    }

    private void afegirBotoEnrere(){
        Font fuente = Vista.getFontTitols().deriveFont(15.0f);

        botoEnrere = new JButton("Enrere");
        botoEnrere.setFont(fuente);

        getPanellSuperiorLateralEsquerre().add(botoEnrere,BorderLayout.CENTER);
    }

    private void afegirLabels(){
        JLabel usuari = new JLabel("Usuari");
        JLabel correu = new JLabel("correu");
        JLabel contrasenya = new JLabel("Contrasenya");
        JLabel confirmarContrasenya = new JLabel("Confirmar contrasenya");

        Color colorForeground = Color.WHITE;

        usuari.setForeground(colorForeground);
        correu.setForeground(colorForeground);
        contrasenya.setForeground(colorForeground);
        confirmarContrasenya.setForeground(colorForeground);


        Font fuente = Vista.getFontTitols().deriveFont(MIDA_TIPOGRAFIA);

        usuari.setFont(fuente);
        correu.setFont(fuente);
        contrasenya.setFont(fuente);
        confirmarContrasenya.setFont(fuente);


        GridBagConstraints constraints = new GridBagConstraints();

        constraints.gridy = 0;
        constraints.gridx = 0;
        constraints.anchor = GridBagConstraints.LINE_START;
        getPanellIntermig().add(usuari,constraints);

        constraints.gridy = 2;
        constraints.gridx = 0;
        constraints.anchor = GridBagConstraints.LINE_START;
        getPanellIntermig().add(correu,constraints);

        constraints.gridy = 4;
        constraints.gridx = 0;
        constraints.anchor = GridBagConstraints.LINE_START;
        getPanellIntermig().add(contrasenya,constraints);

        constraints.gridy = 6;
        constraints.gridx = 0;
        constraints.anchor = GridBagConstraints.LINE_START;
        getPanellIntermig().add(confirmarContrasenya,constraints);
    }

    private void afegirTextFields(){
        usuari = new JTextField();
        correu = new JTextField();
        contrasenya = new JPasswordField();
        confirmacioContrasenya = new JPasswordField();

        GridBagConstraints constraints = new GridBagConstraints();

        constraints.gridy = 1;
        constraints.gridx = 0;
        constraints.anchor = GridBagConstraints.LINE_START;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        getPanellIntermig().add(usuari,constraints);

        constraints.gridy = 3;
        constraints.gridx = 0;
        constraints.anchor = GridBagConstraints.LINE_START;
        getPanellIntermig().add(correu,constraints);

        constraints.gridy = 5;
        constraints.gridx = 0;
        constraints.anchor = GridBagConstraints.LINE_START;
        getPanellIntermig().add(contrasenya,constraints);

        constraints.gridy = 7;
        constraints.gridx = 0;
        constraints.anchor = GridBagConstraints.LINE_START;
        getPanellIntermig().add(confirmacioContrasenya,constraints);
    }

    private void afegirBotoConfirmar(){
        botoConfirmar = new BotoTroner1("Confirmar");

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridy = 8;
        constraints.gridx = 0;
        getPanellIntermig().add(botoConfirmar,constraints);

    }

    private void afegirEspaisBuits(){

    }


    public String getNom() {
        return usuari.getText();
    }

    public String getCorreu() {
        return correu.getText();
    }

    public String getContrasenya() {
        return String.valueOf(contrasenya.getPassword());
    }

    public String getConfirmacioContrasenya() {
        return String.valueOf(confirmacioContrasenya.getPassword());
    }
}
