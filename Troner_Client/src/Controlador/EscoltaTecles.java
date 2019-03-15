package Controlador;

import Model.Configuracio;
import Network.Network;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


public class EscoltaTecles implements KeyListener {

    private Network network;
    private Configuracio configuracio;

    public EscoltaTecles (Configuracio configuracio, Network network){
        this.network = network;
        this.configuracio = configuracio;
        System.out.println("Flag: key listener creado");
    }

    @Override
    public void keyPressed(KeyEvent e) {
        char tecla = e.getKeyChar();
        //System.out.println("Flag: Han pulsado la tecla: "+ Character.toString(tecla));
        if(tecla == configuracio.getNord()){
            network.enviarComandaNord();
        }
        if(tecla == configuracio.getEst()){
            network.enviarComandaEst();
        }
        if(tecla == configuracio.getSud()){
            network.enviarComandaSud();
        }
        if(tecla == configuracio.getOest()){
            network.enviarComandaOest();
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
        //System.out.println("Flag: han typeado una tecla");
    }

    @Override
    public void keyReleased(KeyEvent e) {
        //System.out.println("Flag: han liberado una tecla");
    }

}
