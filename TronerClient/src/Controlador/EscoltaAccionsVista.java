package Controlador;

import Model.Configuracio;
import Model.Model;
import Vista.PantallaConfiguracio.PantallaConfiguracio;
import Vista.PantallaInici.PantallaInici;
import Vista.PantallaJoc.PantallaJoc;
import Vista.PantallaLoginRegistre.PantallaLogin;
import Vista.PantallaLoginRegistre.PantallaRegistre;
import Vista.PantallaPrincipal.PantallaPrincipal;
import Vista.PantallaResultats.PantallaResultats;
import Vista.PantallaSalaEspera.PantallaSalaEspera;
import Vista.Vista;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import Vista.BotoTroner1;

public class EscoltaAccionsVista implements ActionListener,WindowListener {

    private Controlador controlador;
    private Model model;

    public EscoltaAccionsVista(Controlador controlador,Model model){

        this.controlador = controlador;
        this.model = model;
    }



    @Override
    public void actionPerformed(ActionEvent event) {

        //Botons normals
        if (event.getSource() instanceof JButton) {
            evaluaBotoSwing((JButton) event.getSource());
        }

        //Botons personalitzats
        if (event.getSource() instanceof BotoTroner1) {
            evaluaBotoTroner1((BotoTroner1) event.getSource());
        }
    }

    private void evaluaBotoSwing(JButton boto){

        String description = boto.getAccessibleContext().getAccessibleDescription();
        String name = boto.getAccessibleContext().getAccessibleName();
        switch (Integer.parseInt(name)) {

            case Vista.PANTALLA_CONFIGURACIO:
                if (description.equals(PantallaConfiguracio.BOTO_CONFIRMAR)){
                    controlador.recullEvaluaConfiguracio();
                }
                break;

            case Vista.PANTALLA_INICI:
                if (description.equals(PantallaInici.BOTO_INICIAR_SESSIO)) controlador.pasarAPantallaLogin();
                if (description.equals(PantallaInici.BOTO_REGISTRARSE)) controlador.pasarAPantallaRegistre();
                break;

            case Vista.PANTALLA_LOGIN:
                //if(description.equals(PantallaLogin.BOTO_CONFIRMAR))controlador.enviarLogin();
                if(description.equals(PantallaLogin.BOTO_ENRERE))controlador.tornarPantallaInici();
                break;

            case Vista.PANTALLA_REGISTRE:
                //TODO: analitzar si això ja no serviex
                //if(description.equals(PantallaRegistre.BOTO_CONFIRMAR))controlador.enviarRegistre();
                if(description.equals(PantallaRegistre.BOTO_ENRERE))controlador.tornarPantallaInici();
                break;

            case Vista.PANTALLA_PRINCIPAL:
                //TODO:  que es pugui adonar que s'ha de canviar la configuracio
                if(description.equals(PantallaPrincipal.BOTO_JUGARX2)) controlador.peticioPartidaX2();
                if(description.equals(PantallaPrincipal.BOTO_JUGARX4))controlador.peticioPartidaX4();
                if(description.equals(PantallaPrincipal.BOTO_JUGARTORNEIG))controlador.peticioPartidaTorneig();
                if (description.equals(PantallaPrincipal.BOTO_TANCAR_SESSIO)) controlador.tancarSessio();
                break;

            case Vista.PANTALLA_ESPERA:
                if(description.equals(PantallaSalaEspera.BOTO_ABANDONAR))controlador.abandonarPantallaEspera();
                break;

            case Vista.PANTALLA_JOC:
                if(description.equals(PantallaJoc.BOTO_ABANDONAR)) controlador.abandonarJoc();
                break;


            case Vista.PANTALLA_RESULTATS:
                if(description.equals(PantallaResultats.BOTO_CONTINUAR)) controlador.continuarJoc();
                if(description.equals(PantallaResultats.BOTO_ABANDONAR)) controlador.tornarPantallaPrincipal();
                break;

            default:
                System.out.println("Warning: Accio no registrada.");
                break;

        }
    }

    private void evaluaBotoTroner1(BotoTroner1 botoTroner1){
        String description = botoTroner1.getAccessibleContext().getAccessibleDescription();
        String name = botoTroner1.getAccessibleContext().getAccessibleName();
        switch (Integer.parseInt(botoTroner1.getAccessibleContext().getAccessibleName())) {

            case Vista.PANTALLA_CONFIGURACIO:
                controlador.recullEvaluaConfiguracio();
                break;

            case Vista.PANTALLA_LOGIN:
                if(description.equals(PantallaLogin.BOTO_CONFIRMAR))controlador.enviarLogin();
                break;

            case Vista.PANTALLA_REGISTRE:
                if(description.equals(PantallaRegistre.BOTO_CONFIRMAR))controlador.enviarRegistre();
                break;

            default:
                System.out.println("Warning: Accio no registrada.");
                break;

        }
    }

    public void cambiarComanda(String comandaOriginal, String comandaFinal) {
        Configuracio configuracio = new Configuracio(model.getConfiguracio().getEstatConfiguracio());
        if(configuracio.cambiarConfiguracio(comandaOriginal,comandaFinal)){
            controlador.solicitaCambiarConfiguracio(configuracio.getEstatConfiguracio());
        }else {
            controlador.avisarUsuari("Error: tecla assignada a una altre comanda.");
        }


    }

    @Override
    public void windowOpened(WindowEvent e) {

    }

    @Override
    public void windowClosing(WindowEvent e) {
        //TODO: aqui hacemos el querer abandonar la vida
        controlador.abandonarTroner();
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

