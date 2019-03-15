package Controlador.InformationClasses.EstatJoc;

import java.awt.*;
import java.io.Serializable;
import java.util.LinkedList;

public class EstatJugadorsPartida implements Serializable{

    private LinkedList<InfoJugadorPartida> jugadors;


    public EstatJugadorsPartida(){
        jugadors = new LinkedList<InfoJugadorPartida>();
    }

    public InfoJugadorPartida getJugadorPartida(int i){
        return jugadors.get(i);
    }

    public void addInfoJugadorPartida(String name, int punts,int vides, int wins, Color colorJugador){
        InfoJugadorPartida info = new InfoJugadorPartida(name, punts,vides, wins,colorJugador);
        jugadors.add(info);
    }

    public int length(){
        return jugadors.size();
    }
}
