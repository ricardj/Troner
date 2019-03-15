package Controlador.InformationClasses.EstatPantallaPrincipal;

import java.io.Serializable;

public class HistoricJugador implements Serializable {
    protected String name;

    protected int[] punts;

    public HistoricJugador(String name, int[] punts){
        this.name = name;
        this.punts = punts;
    }

    public String getName() {
        return name;
    }

    public int[] getPunts() {
        return punts;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPunts(int[] punts) {
        this.punts = punts;
    }
}
