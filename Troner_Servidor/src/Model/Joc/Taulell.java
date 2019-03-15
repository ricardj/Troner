package Model.Joc;

import Controlador.InformationClasses.EstatJoc.EstatTaulell;
import Model.Joc.Casella;
import Model.Joc.*;

import java.awt.*;
import java.util.LinkedList;

/**
 * Esta clase sirve para almacenar todas la casillas de un tablero a la vez que sirve a las motos
 * para desplazarse por ellas.
 * @author grupoC6
 */
public class Taulell {
    public final int NUM_CELES_HORITZONTALS;
    public final int NUM_CELES_VERTICALS;

    private Casella[][] taulell;

    /**
     * Crea un tablero con tantas casillas en vertical y horizontal como indiquen los parámetros.
     * @param celesHoritzontals Número de casillas en horizontal
     * @param celesVerticals Número de casillas en vertical
     */
    public Taulell(int celesHoritzontals, int celesVerticals){
        NUM_CELES_HORITZONTALS = celesHoritzontals;
        NUM_CELES_VERTICALS  = celesVerticals;

        taulell = new Casella[NUM_CELES_HORITZONTALS][NUM_CELES_VERTICALS];
        for(int i = 0; i < NUM_CELES_HORITZONTALS; i++){
            for (int j = 0; j < NUM_CELES_VERTICALS; j++){
                taulell[i][j] = new Casella();
            }
        }
    }


    /**
     * Método que sirve a las motos para avanzar a través del tablero.
     * Este método comprueba si la nueva posición de la moto está ocupada o queda fuera de los límites.
     * En caso de que la nueva posición quede fuera de los límites o ya esté ocupada entonces ponemos el estado
     * de la moto en estado accidente.
     * @param moto Moto de la cual se quiere actualizar la posición.
     */
    public void actualitzaPosicio(Moto moto){
        //TODO: comprovamos que no se sale de los limites i que no hay otra moto
        int novaPosicioX = moto.getPosicioX();
        int novaPosicioY = moto.getPosicioY();

        if(novaPosicioX>=NUM_CELES_HORITZONTALS || novaPosicioX < 0){
            moto.setCrashed(true);
        }else {
            if(novaPosicioY>=NUM_CELES_VERTICALS || novaPosicioY<0){
                moto.setCrashed(true);
            }else {
                if (taulell[novaPosicioX][novaPosicioY].isOcupada()){
                    moto.setCrashed(true);
                }else {
                    taulell[novaPosicioX][novaPosicioY].setOcupada(true,moto.getColorMoto());
                }
            }
        }
    }

    /**
     * Getter que sirve para saber el color de una casilla en concreto del tablero.
     * @param x Posición en el eje de las X de la casilla en tablero.
     * @param y Posición en el eje de las X de la casilla en tablero.
     * @return Color actual de la casilla.
     */
    public Color getColorCela(int x, int y){
        return taulell[x][y].getColor();
    }

    /**
     * Getter que retorna encapsulada en una clase EstatTaulell el estado actual del tablero.
     * @return Estado acytual del tablero encapsulado en una clase EstatTaulell.
     */
    public EstatTaulell getEstatTaulell(){
        Color[][] matriu = new Color[NUM_CELES_HORITZONTALS][NUM_CELES_VERTICALS];
        for(int i =0;i<NUM_CELES_HORITZONTALS;i++){
            for(int j = 0;j<NUM_CELES_VERTICALS;j++){
                matriu[i][j] = taulell[i][j].getColor();
            }
        }
        return new EstatTaulell(matriu);
    }

    /**
     * Reinicia el estado del tablero sin tener que crear una nueva instancia de este.
     */
    public void resetTaulell() {
        for(int i = 0; i < NUM_CELES_HORITZONTALS; i++){
            for (int j = 0; j < NUM_CELES_VERTICALS; j++){
                taulell[i][j] = new Casella();
            }
        }
    }
}
