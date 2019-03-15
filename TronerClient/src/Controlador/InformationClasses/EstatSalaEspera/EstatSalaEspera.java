package Controlador.InformationClasses.EstatSalaEspera;

import java.io.Serializable;

public class EstatSalaEspera implements Serializable {

    private int jugadorsActuals;
    private int jugadorsEsperats;
    private boolean partidaOK;

    public EstatSalaEspera(int jugadorsActuals,int jugadorsEsperats,boolean partidaOK){
        this.jugadorsActuals = jugadorsActuals;
        this.jugadorsEsperats = jugadorsEsperats;
        this.partidaOK = partidaOK;
    }

    public boolean isPartidaOK() {
        return partidaOK;
    }

    public int getJugadorsActuals() {
        return jugadorsActuals;
    }

    public int getJugadorsEsperats() {
        return jugadorsEsperats;
    }
}
