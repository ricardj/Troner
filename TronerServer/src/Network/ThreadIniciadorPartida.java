package Network;


import Controlador.ThreadPartida;
import Controlador.ThreadPartidaTorneig;

/**
 * Este Thread gestiona la sala de espera, cuando esta se llena,
 * inicia un Thread de partida con los correspondientes jugadores
 *
 * @author grupoC6
 * @version 1.0
 */
public class ThreadIniciadorPartida extends Thread{

    private static final int TEMPS_ESPERA = 1000; //Milis

    SalaEspera salaEspera;
    boolean ences;

    /**
     * Método constructor de la clase
     * @param salaEspera sala de espera la cual gestiona, contiene los 3 modos de juego
     */
    public ThreadIniciadorPartida(SalaEspera salaEspera){
        this.salaEspera = salaEspera;
    }

    /**
     * Método que analiza permanentemente la sala de espera hasta que se llena,
     * inicia la partida con el modo de juego correspondiente
     */
    @Override
    public void run(){
        ences=true;
        while (ences){
            try {
                sleep(TEMPS_ESPERA);
                if(salaEspera.getNumJugadorsSalaEsperaX2() == SalaEspera.JUGADORS_PARTIDA_X2){
                    iniciarPartidaX2();
                }
                if(salaEspera.getNumJugadorsSalaEsperaX4() == SalaEspera.JUGADORS_PARTIDA_X4){
                    iniciarPartidaX4();
                }
                if(salaEspera.getNumJugadorsSalaEsperaTorneig() == SalaEspera.JUGADORS_PARTIDA_TORNEIG){
                    iniciarPartidaTorneig();
                }

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Inicia un thread partida del modo de 2 jugadores
     */
    private void iniciarPartidaX2() {
        //Al hacer el get de los jugadores de la sala de espera tambien los estas borrando

        ThreadClientAtender[] jugadors = salaEspera.getJugadorsSalaEsperaX2(SalaEspera.JUGADORS_PARTIDA_X2);
        ThreadPartida threadPartida = new ThreadPartida(jugadors);
    }

    /**
     * Inicia un thread partida del modo de 4 jugadores
     */
    private void iniciarPartidaX4() {
        //TODO: completar estos metodos
        ThreadClientAtender[] jugadors = salaEspera.getJugadorsSalaEsperaX4(SalaEspera.JUGADORS_PARTIDA_X4);
        ThreadPartida threadPartida = new ThreadPartida(jugadors);
    }

    /**
     * Inicia un thread partida del modo torneo
     */
    private void iniciarPartidaTorneig() {
        //TODO: completar estos metodos
        ThreadClientAtender[] jugadors = salaEspera.getJugadorsSalaEsperaTorneig(SalaEspera.JUGADORS_PARTIDA_TORNEIG);
        ThreadPartidaTorneig threadPartida = new ThreadPartidaTorneig(jugadors);
    }

    /**
     * Libera el thread (puerto)
     */
    public void apagaThread(){
        ences=false;
    }

}
