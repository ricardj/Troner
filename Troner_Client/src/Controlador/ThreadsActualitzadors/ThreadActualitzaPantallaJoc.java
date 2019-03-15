package Controlador.ThreadsActualitzadors;

import Controlador.Controlador;
import Controlador.InformationClasses.EstatJoc.EstatJugadorsPartida;
import Controlador.InformationClasses.EstatJoc.EstatPartida;
import Controlador.InformationClasses.EstatJoc.EstatTaulell;
import Vista.PantallaJoc.PantallaJoc;
import Network.Network;
import Network.ResourceNotAvalaibleException;

import java.awt.*;

public class ThreadActualitzaPantallaJoc extends Thread {
    private static final int COOLDOWN_TIME = 50;

    private PantallaJoc pantallaJoc;

    boolean active = false;

    private Network network;
    private Controlador controlador;

    private EstatTaulell estatTaulell;
    private EstatJugadorsPartida estatJugadorsPartida;

    public ThreadActualitzaPantallaJoc(PantallaJoc pantallaJoc, Network network, Controlador controlador) {
        super();
        this.pantallaJoc = pantallaJoc;
        this.network = network;
        this.controlador = controlador;
        this.start();
    }

    @Override
    public void run() {
        active = true;
        while (active && pantallaJoc.isVisible()){
            try {
                sleep(COOLDOWN_TIME);
                EstatPartida estatPartida = network.getEstatPartida();
                pantallaJoc.actualitza(estatPartida.getEstatTaulell(),estatPartida.getEstatJugadorsPartida(),estatPartida.getNumero());
                if(estatPartida.isPartidaAcabada()) {
                    controlador.partidaAcabada();
                    active = false;
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ResourceNotAvalaibleException e){
                System.out.println(e.getMessage());
            }
        }
        System.out.println("Flag: thread actualitzador de joc acabat");
    }

    public void deactivate(){
        active = false;
    }

    public void active(boolean active){
        this.active = active;
    }
}
