package Controlador.ThreadsActualitzadors;

import Controlador.Controlador;
import Controlador.InformationClasses.EstatSalaEspera.EstatSalaEspera;
import Network.ResourceNotAvalaibleException;
import Network.Network;
import Vista.PantallaSalaEspera.PantallaSalaEspera;

public class ThreadActualitzaPantallaSalaEspera extends Thread {


    private static final int COOLDOWN_TIME = 50;
    private static final int NUM_PUNTS_MAX = 10;

    private PantallaSalaEspera pantallaSalaEspera;

    boolean active = false;

    private Network network;
    private Controlador controlador;

    public ThreadActualitzaPantallaSalaEspera(PantallaSalaEspera pantallaSalaEspera,Network network,Controlador controlador) {
        super();
        this.pantallaSalaEspera = pantallaSalaEspera;
        this.network = network;
        this.controlador = controlador;
        this.start();
    }

    @Override
    public void run() {
        active = true;
        int numPunts = 1;
        while (active && pantallaSalaEspera.isVisible()) {
            try {
                sleep(COOLDOWN_TIME);
                EstatSalaEspera estatSalaEspera= network.getEstatSalaEspera();

                if(estatSalaEspera.isPartidaOK()) {
                    controlador.executarPartida();
                    active = false;
                }else {
                    if(numPunts >= NUM_PUNTS_MAX) numPunts = 1;
                    pantallaSalaEspera.actualitzar(estatSalaEspera,numPunts);
                    numPunts++;
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ResourceNotAvalaibleException e) {
                System.out.println(e.getMessage());
            }
        }
        System.out.println("Flag: Thread actualitzador de pantalla espera acabat.");
    }

    public void deactivate(){
        active = false;
    }

    public void active(boolean active){
        this.active = active;
    }

}

