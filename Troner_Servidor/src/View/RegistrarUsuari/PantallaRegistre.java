package View.RegistrarUsuari;

import Controlador.Controlador;

import javax.swing.*;
import java.awt.*;

public class PantallaRegistre extends JPanel {
    private JTextField jtfNom;
    private JTextField jtfMail;
    private JPasswordField jpfContrasenya;
    private JPasswordField jpfValidacio_contrasenya;
    private JButton jbRegistra;

    public PantallaRegistre(){
        jtfNom = new JTextField();
        jtfMail = new JTextField();
        jpfContrasenya = new JPasswordField();
        jpfValidacio_contrasenya = new JPasswordField();
        jbRegistra = new JButton("REGISTRAR USUARI");

        JPanel jpRegistre = new JPanel(new GridLayout(11, 1));

        JLabel jlNom = new JLabel("Nom");
        JLabel jlMail = new JLabel("Correu electronic");
        JLabel jlContrasenya = new JLabel("Contrasenya");
        JLabel jlValidacio_contrasenya = new JLabel("Confirmació de contrasenya");

        jpRegistre.add(jlNom);
        jpRegistre.add(jtfNom);
        jpRegistre.add(jlMail);
        jpRegistre.add(jtfMail);
        jpRegistre.add(jlContrasenya);
        jpRegistre.add(jpfContrasenya);
        jpRegistre.add(jlValidacio_contrasenya);
        jpRegistre.add(jpfValidacio_contrasenya);
        jpRegistre.add(new JPanel());
        jpRegistre.add(jbRegistra);
        jpRegistre.add(new JPanel());

        setLayout(new BorderLayout());
        add(jpRegistre, BorderLayout.CENTER);
    }

    public JButton getJbRegistra(){return jbRegistra; }

    public String getNom(){ return jtfNom.getText(); }
    public String getMail(){ return jtfMail.getText(); }
    public String getContrasenya(){ return jpfContrasenya.getText(); }
    public String getValidacio(){ return jpfValidacio_contrasenya.getText(); }

    public void avisaUsuariPass(){
        JOptionPane.showMessageDialog(this, "Les contrasenyes han de coincidir i tenir una mida mínima de 6 caràcters,\ntorna a introduir-les.");
    }

    public void avisaUsuariLog(){
        JOptionPane.showMessageDialog(this, "Login existent, provi un de diferent.");
    }

    public void avisaUsuariCamps(){
        JOptionPane.showMessageDialog(this, "És necessàri omplir tots els camps!.");
    }

    public void avisaUsuariMail() {
        JOptionPane.showMessageDialog(this, "El correu ha de seguir el format: exemple@exemple.com");
    }

    public void avisaUsuariError() {
        JOptionPane.showMessageDialog(this, "Error en la connexió amb la base de dades, torni a intentar-ho més tard.");
    }

    public JTextField getJtfNom() { return jtfNom; }

    public JTextField getJtfMail() { return jtfMail; }

    public JTextField getJpfContrasenya() { return jpfContrasenya; }

    public JTextField getJpfValidacio_contrasenya() { return jpfValidacio_contrasenya; }

    public void resetCamps(int cas){
        getJpfContrasenya().setText("");
        getJpfValidacio_contrasenya().setText("");
        if (cas == 1){
            getJtfNom().setText("");
            getJtfMail().setText("");
        }
    }


    public void registraControlador(Controlador controlador){
        getJbRegistra().setActionCommand("Registra");
        getJbRegistra().addActionListener(controlador);
    }


}
