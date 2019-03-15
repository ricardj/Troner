package Controlador;

//TODO: identificador de cada jugador. Serveix al ThreadClientAtender per a identificarse amb el ThreadPartida
/*Aquesta clase sera instanciada per el ThreadPartida en els ThreadClientAtender per a que aquests
la usin per a fer les seves demandes. Ja sigui tan com per demanar un cambi de direccio en la moto o abandonar
la partida. ¿Poden requerir els punts?
Tambe serveix al ThreadPartida per a comunicarse amb la partida.

Com a tal la clase ha d'identificar amb:
-Un Id (pot ser un Integer?)
//De moment no necesitem res mes pero he considerat necesari fer una classe.
* */

public class IdentificadorJugador {

    private static int ID_JUGADOR = 1;

    private int identificador;
    private boolean isAbandonated;

    public IdentificadorJugador(){
        this.identificador = ID_JUGADOR;
        ID_JUGADOR++;
        isAbandonated = false;
    }

    public int getIdJugador(){
        return identificador;
    }


    public boolean isAbandonated() {
        return isAbandonated;
    }

    public void setAbandonated(boolean abandonated) {
        isAbandonated = abandonated;
    }
}
