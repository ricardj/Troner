package Controlador.InformationClasses.EstatJoc;

import Controlador.InformationClasses.EstatPantallaPrincipal.InfoJugador;

import java.awt.*;
import java.io.Serializable;

public class InfoJugadorPartida extends InfoJugador implements Serializable{


    private int vides;
    private int wins;
    private Color colorJugador;

    public InfoJugadorPartida(String name, int punts,int vides, int wins, Color colorJugador) {
        super(name, punts);
        this.colorJugador = colorJugador;
        this.punts = punts;
        this.vides = vides;
        this.wins = wins;
    }

    public int getVides() {
        return vides;
    }

    public int getWins() {
        return wins;
    }

    public long getPunts(){
        return this.punts;
    }

    public Color getColorJugador() {
        return colorJugador;
    }

    public void setWins(int wins){
        this.wins = wins;
    }

    public void setVides(int vides){
        this.vides = vides;
    }

    public void setPunts(int punts){
        this.punts = punts;
    }

    public void incrementaVides(int increment){
        vides+=increment;
    }

    public void incrementaPunts(int increment){
        punts+=increment;
    }

    public void incrementaWins(int increment){
        wins+= increment;
    }
}
