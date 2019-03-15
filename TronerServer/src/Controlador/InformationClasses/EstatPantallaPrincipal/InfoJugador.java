package Controlador.InformationClasses.EstatPantallaPrincipal;

import java.io.Serializable;

public class InfoJugador implements Serializable{
    protected String name;
    protected long punts;

    public InfoJugador(String name, long punts){
        this.name = name;
        this.punts = punts;
    }

    public String getName() {
        return name;
    }

    public long getPunts() { return punts; }

}

