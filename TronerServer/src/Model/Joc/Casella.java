package Model.Joc;

import java.awt.*;

/**
 * La clase Casella sirve como unidad de tablero.
 * Por una Casella circularán las motos del juego.
 * La clase puede estar ocupada o no y tener un color concreto.
 * La clase implementa un color de fondo por defecto que se pone
 * siempre que no se haya especificado otro color.
 * @author grupoC6
 */
public class Casella {

    public static Color COLOR_FONS = new Color(203, 203, 203, 255);

    private boolean ocupada;
    private Color color;


    /**
     * Constructor de la clase casilla.
     * Inicia por su estado al color por defecto y en estado desocupado.
     */
    public Casella(){
        ocupada = false;
        color = COLOR_FONS;
    }


    /**
     * Este método, ocupa la casilla.
     * Siempre que se ocupa una casilla se debe especificar de que color se quiere rellenar
     * para forzar que al estar ocupada se distinga de las demás.
     * @param ocupada
     * @param color
     */
    public void setOcupada(boolean ocupada,Color color){
        this.ocupada = ocupada;
        this.color = color;
    }

    /**
     * Este método sirve para averiguar si la casilla ha sido ocupada o no.
     * @return un boleano según si está ocupada o no.
     */
    public boolean isOcupada(){
        return ocupada;
    }


    /**
     * Este método retorna el color con el que se está pintando la casilla.
     * Sirve a las clases de la Vista para saber de que color deben pintar la casilla.
     * @return
     */
    public Color getColor() {
        return color;
    }
}
