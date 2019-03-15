package Controlador.InformationClasses.EstatJoc;

import java.io.Serializable;

public class EstatPartida implements Serializable{

    private EstatJugadorsPartida jugadorsPartida;
    private EstatTaulell estatTaulell;

    private boolean partidaAcabada = false;

    private int numero;

    public EstatPartida(EstatJugadorsPartida jugadorsPartida, EstatTaulell estatTaulell,boolean partidaAcabada,int numero) {
        this.jugadorsPartida = jugadorsPartida;
        this.estatTaulell = estatTaulell;
        this.partidaAcabada = partidaAcabada;
        this.numero = numero;
    }

    public int getNumero() {
        return numero;
    }

    public EstatJugadorsPartida getEstatJugadorsPartida() {
        return jugadorsPartida;
    }

    public EstatTaulell getEstatTaulell() {
        return estatTaulell;
    }

    public boolean isPartidaAcabada() {
        return partidaAcabada;
    }
}
