package View.PantallaConfiguracio;

import Controlador.Controlador;
import View.Vista;

import javax.swing.*;
import java.awt.*;

public class PantallaConfiguracio extends JPanel {
    private JButton jbPower;
    private JTextField jtfPortClients;
    private JLabel jlPortBBDD;
    private JLabel jlDireccioIPBBDD;
    private JLabel jlNomBBDD;
    private JLabel jlUsuariBBDD;
    private JLabel jlContrasenyaBBDD;
    private JLabel jlPortClients;
    private String power;
    private EstructuraConfig dades;

    public PantallaConfiguracio() {
        setLayout(new BorderLayout());
        JLabel jlTitol = new JLabel("CONFIGURACIÓ");
        jlTitol.setFont(new Font("Arial", Font.BOLD, 100));

       // dades = carregaConfig();

        power = "OFF";
        JLabel jlCanviaPort = new JLabel("Canvia el port de connexió del servidor:");

        jbPower = new JButton(power);
        jtfPortClients = new JTextField();
        jlPortBBDD = new JLabel();
        jlDireccioIPBBDD = new JLabel();
        jlNomBBDD = new JLabel();
        jlUsuariBBDD = new JLabel();
        jlContrasenyaBBDD = new JLabel();
        jlPortClients = new JLabel();

        JPanel jpControls = new JPanel(new GridLayout(2,2));
        JPanel jpInfo = new JPanel(new GridBagLayout());

        GridBagConstraints constraints = new GridBagConstraints();

        jpControls.add(jlCanviaPort);
        jpControls.add(new JPanel());
        jpControls.add(jtfPortClients);
        jpControls.add(jbPower);

        constraints.gridy = 0;
        constraints.gridx = 0;
        constraints.weightx = 0.5;
        constraints.weighty = 0.5;
        constraints.anchor = GridBagConstraints.LINE_START;

        JLabel jlPortBBDD_ttl = new JLabel("Port BBDD: ");
        jlPortBBDD_ttl.setFont(Vista.TITLES);
        jpInfo.add(jlPortBBDD_ttl, constraints);

        constraints.gridy++;
        JLabel jlDireccioIPBBDD_ttl = new JLabel("IP BBDD: ");
        jlDireccioIPBBDD_ttl.setFont(Vista.TITLES);
        jpInfo.add(jlDireccioIPBBDD_ttl, constraints);

        constraints.gridy++;
        JLabel jlNomBBDD_ttl = new JLabel("Nom BBDD: ");
        jlNomBBDD_ttl.setFont(Vista.TITLES);
        jpInfo.add(jlNomBBDD_ttl, constraints);

        constraints.gridy++;
        JLabel jlUsuariBBDD_ttl = new JLabel("Usuari BBDD: ");
        jlUsuariBBDD_ttl.setFont(Vista.TITLES);
        jpInfo.add(jlUsuariBBDD_ttl, constraints);

        constraints.gridy++;
        JLabel jlContrasenyaBBDD_ttl = new JLabel("Contrasenya BBDD: ");
        jlContrasenyaBBDD_ttl.setFont(Vista.TITLES);
        jpInfo.add(jlContrasenyaBBDD_ttl, constraints);

        constraints.gridy++;
        JLabel jlPortClients_ttl = new JLabel("Port servidor: ");
        jlPortClients_ttl.setFont(Vista.TITLES);
        jpInfo.add(jlPortClients_ttl, constraints);

        constraints.gridx = 2;
        constraints.gridy = 0;
        jlPortBBDD.setFont(Vista.INFO);
        jpInfo.add(jlPortBBDD, constraints);

        constraints.gridy++;
        jlDireccioIPBBDD.setFont(Vista.INFO);
        jpInfo.add(jlDireccioIPBBDD, constraints);

        constraints.gridy++;
        jlNomBBDD.setFont(Vista.INFO);
        jpInfo.add(jlNomBBDD, constraints);

        constraints.gridy++;
        jlUsuariBBDD.setFont(Vista.INFO);
        jpInfo.add(jlUsuariBBDD, constraints);

        constraints.gridy++;
        jlContrasenyaBBDD.setFont(Vista.INFO);
        jpInfo.add(jlContrasenyaBBDD, constraints);

        constraints.gridy++;
        jlPortClients.setFont(Vista.INFO);
        jpInfo.add(jlPortClients, constraints);

        JPanel jpConfig = new JPanel(new BorderLayout());
        jpConfig.add(jpControls, BorderLayout.NORTH);
        jpConfig.add(jpInfo, BorderLayout.CENTER);

        add(jpConfig, BorderLayout.CENTER);
        }


    public JTextField getJtfPortClients() {
        return jtfPortClients;
    }

    public void ompleDades(){
        jlPortBBDD.setText(String.valueOf(dades.getPortBBDD()));
        jlDireccioIPBBDD.setText(dades.getDireccioIPBBDD());
        jlNomBBDD.setText(dades.getNomBBDD());
        jlUsuariBBDD.setText(dades.getUsuariBBDD());
        jlContrasenyaBBDD.setText(dades.getContrasenyaBBDD());
        jlPortClients.setText(String.valueOf(dades.getPortClients()));
    }

    public void setDades(EstructuraConfig dades){
        this.dades = dades;
    }

    public int getPortClients(){
        return Integer.parseInt(jlPortClients.getText());
    }

    public void setJlPortClients(String port){
        jlPortClients.setText(port);
    }

    public void registraControlador(Controlador controlador){
        jbPower.setActionCommand("Power");
        jbPower.addActionListener(controlador);
    }

    public void onOff(String onOff){
        jbPower.setText(onOff);
    }



}
