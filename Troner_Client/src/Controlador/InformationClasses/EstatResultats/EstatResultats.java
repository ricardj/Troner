package Controlador.InformationClasses.EstatResultats;

import java.io.Serializable;

public class EstatResultats implements Serializable {

    private int posicio;
    private long punts;
    private int total;

    public EstatResultats(int posicio, long punts, int total) {
        this.posicio = posicio;
        this.punts = punts;
        this.total = total;
    }

    public int getPosicio() {
        return posicio;
    }

    public long getPunts() { return punts; }

    public int getTotal() {
        return total;
    }

    public void setPosicio(int posicio) {
        this.posicio = posicio;
    }

    public void setPunts(long punts) {
        this.punts = punts;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}
