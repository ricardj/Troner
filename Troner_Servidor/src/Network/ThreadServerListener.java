package Network;

import Controlador.Controlador;
import Model.ConfiguracioFitxer;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.LinkedList;

/**
 * Esta clase {@code ThreadServerListener} controla el socket del servidor y registra los threads de los diferentes clientes que se conectan.
 *
 * @author grupoC6
 */
public class ThreadServerListener implements Runnable {
    private Controlador controlador;
    private ServerSocket sServer;
    private LinkedList<ThreadClientAtender> clients;

    //TODO: aqui inicialitzem la sala d'espera i la pasem als ThreadClientAtender.
    private SalaEspera salaEspera;
    private boolean active;

    /**
     * Constructor de la clase ThreadServerListener que inicializa variables y registra el controlador.
     *
     * @param controlador Es el controlador que utilizara el socket.
     */
    public ThreadServerListener(Controlador controlador){
        active = true;
        salaEspera = new SalaEspera();
        clients = new LinkedList<>();
        this.controlador = controlador;
    }

    /**
     * Inicializa el socket en el puerto recibido por parámetros.
     *
     * @param port Número de puerto en el que se iniciará el socket.
     */
    public void startServer(int port){
        try {
            sServer = new ServerSocket(port);
            new Thread(this).start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Recibe los diferentes clientes que quieren conectarse y crea un ThreadClientAtender para cada uno.
     *
     */
    @Override
    public void run() {
        while (true) try {
            if (active) {
                System.out.println("WAITING: Client connectant-se\n");
                Socket sClient = sServer.accept();
                System.out.println("ACCEPTED");

                ThreadClientAtender escoltaClient = new ThreadClientAtender(sClient, controlador, salaEspera);
                clients.add(escoltaClient);
            }

            } catch(SocketException e){
                System.out.println("WARNING: Client desconectat\n");
            } catch(IOException e){
                e.printStackTrace();
            }
    }

    /**
     * Reactiva el servicio en caso de que este haya sido suspendido.
     */
    public synchronized void reactivaServei() {
        active = true;
        startServer(ConfiguracioFitxer.getNouPort());
    }

    /**
     * Para el servicio si este se encuentra en ejecución.
     */
    public void aturaServei(){
        active = false;
        for(ThreadClientAtender i: clients){
            i.expulsaClient();
        }
        clients.clear();
    }
}
