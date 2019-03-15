package Controlador.ThreadsActualitzadors;


import Controlador.Controlador;
import Controlador.InformationClasses.EstatPantallaPrincipal.EstatConfiguracio;
import Controlador.InformationClasses.EstatPantallaPrincipal.EstatRanking;
import Network.Network;
import Network.ResourceNotAvalaibleException;
import Vista.PantallaPrincipal.PantallaPrincipal;

public class ThreadActualitzaPantallaPrincipal extends Thread{

    private static final int COOLDOWN_TIME = 2000;

    private PantallaPrincipal pantallaPrincipal;

    boolean active = false;

    private Network network;
    private Controlador controlador;

    private EstatConfiguracio estatConfiguracio;
    private EstatRanking estatRanking;


    public ThreadActualitzaPantallaPrincipal(PantallaPrincipal pantallaPrincipal,Network network,Controlador controlador) {
        super();
        this.pantallaPrincipal = pantallaPrincipal;
        this.network = network;
        this.controlador = controlador;
        this.start();
    }

    @Override
    public void run() {
        active = true;
        while (active && pantallaPrincipal.isVisible()){
            try {
                pantallaPrincipal.actualitzaRanking(network.getEstatRanking());
                pantallaPrincipal.actualitzaConfiguracio(network.getEstatConfiguracio());
                System.out.println("Flag: pantalla principal actualitzada");
                sleep(COOLDOWN_TIME);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ResourceNotAvalaibleException e){
                System.out.println(e.getMessage());
            }
        }
        System.out.println("Flag: thread actualitzador de pantalla principal acabat.");
    }

    public void deactivate(){
        active = false;
    }

    public void active(boolean active){
        this.active = active;
    }
}
