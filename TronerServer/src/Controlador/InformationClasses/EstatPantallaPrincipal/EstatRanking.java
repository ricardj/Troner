package Controlador.InformationClasses.EstatPantallaPrincipal;

import java.io.Serializable;
import java.util.LinkedList;

public class EstatRanking implements Serializable {

    public static final int MAX_JUGADORS_RANKING = 10;

    //Conte la informacio dels 3 rankings
    //Arrays de fins a 10 jugadors ben ordenats
    private LinkedList<InfoJugador> rankingX2;
    private LinkedList<InfoJugador> rankingX4;
    private LinkedList<InfoJugador> rankingTorneig;

    public EstatRanking(){
        rankingX2 = new LinkedList<InfoJugador>();
        rankingX4 = new LinkedList<InfoJugador>();
        rankingTorneig = new LinkedList<InfoJugador>();
    }

    public void afegirJugadorRankingX2(InfoJugador infoJugador){
        rankingX2.add(infoJugador);
    }

    public void afegirJugadorRankingX4(InfoJugador infoJugador){
        rankingX4.add(infoJugador);
    }

    public void afegirJugadorRankingTorneig(InfoJugador infoJugador){
        rankingTorneig.add(infoJugador);
    }

    public LinkedList<InfoJugador> getRankingTorneig() {
        return rankingTorneig;
    }

    public LinkedList<InfoJugador> getRankingX4() {
        return rankingX4;
    }

    public LinkedList<InfoJugador> getRankingX2() {
        return rankingX2;
    }
}
