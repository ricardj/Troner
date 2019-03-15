package Controlador;

import Controlador.InformationClasses.EstatPantallaPrincipal.EstatConfiguracio;
import Controlador.InformationClasses.IntroInformation.InformacioLogin;
import Controlador.InformationClasses.IntroInformation.InformacioRegistre;
import Model.ConfiguracioFitxer;
import Network.ThreadServerListener;
import View.Vista;
import Model.Model;

import javax.swing.*;
import java.awt.event.*;

public class Controlador implements ActionListener, KeyListener, WindowListener {
    private Vista vista;
    private ThreadServerListener socket;
    private Model model;

    public Controlador(Vista vista){
        model = new Model();
        this.vista = vista;
        this.vista.getGrafic2x().ompleDades(model.agafaDadesGrafic(1));
        this.vista.getGrafic4x().ompleDades(model.agafaDadesGrafic(2));
        this.vista.getGraficTorneig().ompleDades(model.agafaDadesGrafic(3));
        socket = new ThreadServerListener(this);
        socket.startServer(ConfiguracioFitxer.getNouPort());
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton boto = (JButton) e.getSource();

        switch (boto.getActionCommand()){
            case "Atras":
                vista.setPantallaPrincipal();
                break;
            case "Registra":
                int ok = registraUsuari(agafaDadesRegistre());
                if(ok == 1) {
                    vista.getRegistre().avisaUsuariLog();
                } else if (ok == -1){
                    vista.getRegistre().avisaUsuariMail();
                } else if (ok == 2){
                    vista.getRegistre().avisaUsuariCamps();
                } else if (ok == 3){
                    vista.getRegistre().avisaUsuariPass();
                } else if(ok == 4){
                    vista.getRegistre().avisaUsuariError();
                } else {
                    vista.getGestiona().setUsuaris(model.agafaUsuaris());
                }
                break;
            case "Eliminar":
                int row = vista.getGestiona().selectedRow();
                if (row < 0) {
                    vista.getGestiona().avisaUsuari();
                } else {
                    eleminaUsuari(row);
                    vista.getGestiona().removeRow();
                }
                break;
            case "Opcio1":
                vista.setPantallaRegistre();
                break;
            case "Opcio2":
                vista.getGestiona().setUsuaris(model.agafaUsuaris());
                vista.setPantallaGestiona();
                break;
            case "Opcio3":
                vista.getConfig().setDades(ConfiguracioFitxer.carregaConfig());
                vista.getConfig().ompleDades();
                vista.setPantallaConfig();
                break;
            case "Opcio4":
                vista.getRanquing().ompleRanquings(model.agafaRanquings().getRanquings(), model.agafaRanquings().getDatas());
                vista.setPantallaRanquing();
                break;
            case "Opcio5_2x":
                vista.getGrafic2x().ompleDades(model.agafaDadesGrafic(1));
                vista.setPantallaGrafic2x();
                break;
            case "Opcio5_4x":
                vista.getGrafic4x().ompleDades(model.agafaDadesGrafic(2));
                vista.setPantallaGrafic4x();
                break;
            case "Opcio5_Torneig":
                vista.getGraficTorneig().ompleDades(model.agafaDadesGrafic(3));
                vista.setPantallaGraficTorneig();
                break;
            case "Power":
                if (boto.getText().equals("OFF")) {
                    vista.getConfig().onOff("ON");
                    socket.aturaServei();

                } else{
                    vista.getConfig().onOff("OFF");
                    socket.reactivaServei();
                }
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_ENTER){
            ConfiguracioFitxer.setNouPort(vista.getNouPort(vista.getConfig().getPortClients()));
            vista.getConfig().setJlPortClients(String.valueOf(vista.getNouPort(vista.getConfig().getPortClients())));
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    public InformacioRegistre agafaDadesRegistre(){
        InformacioRegistre dades;
        String nom = vista.getRegistre().getNom();
        String mail = vista.getRegistre().getMail();
        String password = vista.getRegistre().getContrasenya();
        String validacio = vista.getRegistre().getValidacio();
        if (nom.equals("") || mail.equals("") || password.equals("") || validacio.equals("")){
            dades = new InformacioRegistre("", "","");
            return dades;
        } else {
            if (!password.equals(validacio) || password.length() < 6) {
                vista.getRegistre().resetCamps(0);
                dades = new InformacioRegistre(nom, mail, null);
                return dades;
            } else {
                dades = new InformacioRegistre(nom, mail, password);
                vista.getRegistre().resetCamps(1);
                return dades;
            }
        }
    }

    public int loginUsuari(InformacioLogin dades) {
        return model.loginUsuari(dades);
    }

    public int registraUsuari(InformacioRegistre dades){
        try {
            int ok = model.registraUsuari(dades);
            if (ok == 0){
                vista.getGestiona().setUsuaris(model.agafaUsuaris());
            }
            return ok;
        } catch (NullPointerException e){
            return 4;
        }
    }

    public void eleminaUsuari(int a_eliminar){
        model.eliminaUsuari(a_eliminar);
    }

    public boolean esConfiguracioValida(EstatConfiguracio configuracio){
        char nord = configuracio.getComandaNord();
        char sud = configuracio.getComandaSud();
        char est = configuracio.getComandaEst();
        char oest = configuracio.getComandaOest();

        if (((nord > 64 && nord < 91) || (nord > 96 && nord < 123) || nord == 8) && ((sud > 64 && sud < 91) || (sud > 96 && sud < 123) || sud == 8) && ((est > 64 && est < 91) || (est > 96 && est < 123) || est == 8) && ((oest > 64 && oest < 91) || (oest > 96 && oest < 123) || oest == 8)) {
            if (nord != est && nord != sud && nord != oest && sud != est && sud != oest && est != oest) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    @Override
    public void windowOpened(WindowEvent e) {

    }

    @Override
    public void windowClosing(WindowEvent e) {
        model.desconnectaBBDD();
    }

    @Override
    public void windowClosed(WindowEvent e) {

    }

    @Override
    public void windowIconified(WindowEvent e) {

    }

    @Override
    public void windowDeiconified(WindowEvent e) {

    }

    @Override
    public void windowActivated(WindowEvent e) {

    }

    @Override
    public void windowDeactivated(WindowEvent e) {

    }
}
