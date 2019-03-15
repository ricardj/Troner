package Network;


import Controlador.InformationClasses.EstatSalaEspera.EstatSalaEspera;

import java.util.LinkedList;

public class SalaEspera {

    public static final int JUGADORS_PARTIDA_X2 = 2;
    public static final int JUGADORS_PARTIDA_X4 = 4;
    public static final int JUGADORS_PARTIDA_TORNEIG = 4;

    private LinkedList<ThreadClientAtender> llistaJocX2;
    private LinkedList<ThreadClientAtender> llistaJocX4;
    private LinkedList<ThreadClientAtender> llistaJocTorneig;

    private ThreadIniciadorPartida threadIniciadorPartida;

    public SalaEspera(){
        llistaJocTorneig = new LinkedList<ThreadClientAtender>();
        llistaJocX2 = new LinkedList<ThreadClientAtender>();
        llistaJocX4 = new LinkedList<ThreadClientAtender>();

        threadIniciadorPartida = new ThreadIniciadorPartida(this);
        threadIniciadorPartida.start();
    }

    public void afegirUsuariJocX2(ThreadClientAtender client){
        if(!llistaJocX2.contains(client)){
            llistaJocX2.add(client);
        }
    }
    public void afegirUsuariJocX4(ThreadClientAtender client){
        if(!llistaJocX4.contains(client)){
            llistaJocX4.add(client);
        }
    }
    public void afegirUsuariJocTorneig(ThreadClientAtender client){
        if(!llistaJocTorneig.contains(client)){
            llistaJocTorneig.add(client);
        }
    }
    public void treureJugador(ThreadClientAtender client){
        if(llistaJocX2.contains(client)){
            llistaJocX2.remove(client);
        }else {
            if(llistaJocX4.contains(client)){
                llistaJocX4.remove(client);
            }else {
                if(llistaJocTorneig.contains(client)){
                    llistaJocTorneig.remove(client);
                }
            }
        }
    }

    public int getNumJugadorsSalaEsperaX2(){
        return llistaJocX2.size();
    }
    public int getNumJugadorsSalaEsperaX4(){
        return llistaJocX4.size();
    }
    public int getNumJugadorsSalaEsperaTorneig(){
        return llistaJocTorneig.size();
    }

    public ThreadClientAtender[] getJugadorsSalaEsperaX2(int numJugadors){
        ThreadClientAtender[] jugadors = new ThreadClientAtender[numJugadors];
        for (int i = 0; i<numJugadors;i++){
            jugadors[i]=llistaJocX2.removeFirst();
        }
        return jugadors;
    }

    public ThreadClientAtender[] getJugadorsSalaEsperaX4(int numJugadors){
        ThreadClientAtender[] jugadors = new ThreadClientAtender[numJugadors];
        for (int i = 0; i<numJugadors;i++){
            jugadors[i]=llistaJocX4.removeFirst();
        }
        return jugadors;
    }

    public ThreadClientAtender[] getJugadorsSalaEsperaTorneig(int numJugadors){
        ThreadClientAtender[] jugadors = new ThreadClientAtender[numJugadors];
        for (int i = 0; i<numJugadors;i++){
            jugadors[i]=llistaJocTorneig.removeFirst();
        }
        return jugadors;
    }

    public EstatSalaEspera getEstatSalaEsperaX2(boolean partidaActivada){
        boolean partidaOk = false;
        if(llistaJocX2.size() == JUGADORS_PARTIDA_X2) partidaOk = true;
        return new EstatSalaEspera(llistaJocX2.size(),JUGADORS_PARTIDA_X2,partidaActivada);
    }

    public EstatSalaEspera getEstatSalaEsperaX4(boolean partidaActivada){
        boolean partidaOk = false;
        if(llistaJocX4.size() == JUGADORS_PARTIDA_X4) partidaOk = true;
        return new EstatSalaEspera(llistaJocX4.size(),JUGADORS_PARTIDA_X4,partidaActivada);
    }

    public EstatSalaEspera getEstatSalaEsperaTorneig(boolean partidaActivada){
        boolean partidaOk = false;
        if(llistaJocTorneig.size() == JUGADORS_PARTIDA_TORNEIG) partidaOk = true;
        return new EstatSalaEspera(llistaJocTorneig.size(),JUGADORS_PARTIDA_TORNEIG,partidaActivada);

    }


}
