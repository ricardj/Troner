package Model.Joc;

import Model.ThreadMotor;

import java.awt.*;


/**
 * Esta clase sirve como medio para el jugador para desplazarse por un tablero.
 * Guarda la dirección y la posición del puntero que se dibujará en un tablero a la vez
 * que incorpora un Thread que lo hará avanzar siempre y cuando se active.
 * @author grupoC6
 */
public class Moto {

    public static final int NORD = 1;
    public static final int SUD = 2;
    public static final int EST = 3;
    public static final int OEST = 4;


    private int posicioX;           //Posición actual de la moto en el eje X
    private int posicioY;           //Posición actual de la moto en el eje Y
    private int posicioXinicial;    //Primera posición asignada en el eje X
    private int posicioYinical;     //Primera posición asignada en el eje Y
    private int direccioInicial;    //Dirección inicial.Puede tener los valores NORD,SUD,EST,OEST
    private Color color;            //Color que va dejando el rastro de esta moto
    private int direccio;           //Puede tener los valores NORD,SUD,EST,OEST

    private Taulell taulell;    //Tablero sobre el que la moto (o puntero) realiza sus acciones (movimientos)
    private Partida partida;    //Partida a la que pertenece la moto i a quien ha de avisar de accidente.

    private boolean motorEncess;        //Para saber si la moto avanza o no
    private ThreadMotor threadMotor;

    private boolean isCrashed;          //Para saber si el puntero ha chocado
    private boolean isEliminated;       //Para saber si ha sido eliminado

    /**
     * Único constructor de la clase Moto. Coloca la moto sobre el tablero y usa la referencia a
     * partida para informar de sus estadis especiales (como chocar por ejemplo).
     * La posiciónX, posicionY y direccion son los valores iniciales que tomará la moto.
     *
     * @param posicioX Posición inicial de la moto en el eje X
     * @param posicioY Posición inicial de la moto en el eje Y
     * @param color Color de la moto
     * @param direccio Dirección de la moto
     * @param taulell Referencia al tablero sobbre el que se moverá la moto.
     * @param partida Referencia a la partida sobre la que avisará de estados especiales la moto.
     */
    public Moto(int posicioX,int posicioY, Color color, int direccio, Taulell taulell,Partida partida){
        this.posicioX = posicioX;
        this.posicioY = posicioY;
        this.direccio = direccio;

        direccioInicial = direccio;
        posicioXinicial = posicioX;
        posicioYinical = posicioY;

        this.color = color;
        threadMotor = new ThreadMotor(this);
        threadMotor.start();

        this.taulell= taulell;
        this.partida = partida;

        taulell.actualitzaPosicio(this);
        isCrashed = false;
        isEliminated = false;
    }


    /**
     * Este método avanza una posición sobre el tablero en la dirección que tenga configurada.
     */
    public synchronized void avansaMoto(){
        if(direccio == NORD){
            posicioY-=1;
        }
        if(direccio == SUD){
            posicioY += 1;
        }
        if (direccio == EST){
            posicioX += 1;
        }
        if (direccio == OEST){
            posicioX -=1;
        }
        taulell.actualitzaPosicio(this);
    }

    /**
     * Este método sirve para cambiar la dirección de la moto.
     * Solo hace caso a las constantes estáticas NORD,SUD,EST,OEST
     * En caso de poner otra constante no hace nngún cambio.
     * Antes de reasignar una nueva dirección, comprueba que el giro total de la moto
     * no sea de más de 90º grados con respecto a la dirección anterior.
     * Por tanto, no realizará cambios de de NORD a SUD por ejemplo.
     * @param direccioCanvi Acepta los valores estáticos NORD,SUD,EST,OEST
     */
    public void canviaDireccio(int direccioCanvi){
        if((direccio == NORD || direccio == SUD )&& (direccioCanvi == EST || direccioCanvi == OEST)){
            direccio = direccioCanvi;
        }
        if((direccio == OEST || direccio == EST )&& (direccioCanvi == NORD || direccioCanvi == SUD)){
            direccio = direccioCanvi;
        }
    }

    /**
     * Activa el Thread que hará avanzar la posición de la moto constantemente.
     */
    public void encenMoto(){
        motorEncess = true;
        threadMotor.encenMotor();
    }

    /**
     * Apaga el Thread que hace avanzar la moto constantemente.
     */
    public void apagaMoto(){
        threadMotor.apagaMotor();
        motorEncess = false;
    }

    /**
     * Getter que devuelve la posición actual de la moto en el eje de las X
     * @return Posición actual de la moto en el eje de las X.
     */
    public int getPosicioX(){
        return posicioX;
    }

    /**
     * Getter que devuelve la posición actual de la moto en el eje de las Y
     * @return Posición actual de la moto en el eje de las Y.
     */
    public int getPosicioY(){
        return posicioY;
    }

    /**
     * Getter que devuelve el color de la Moto.
     * @return color actual de la Moto.
     */
    public Color getColorMoto() {
        return color;
    }

    /**
     * Getter que devuelve la dirección actual de la Moto.
     * @return Dirección actual de la Moto.
     */
    public int getDireccio(){
        return direccio;
    }

    /**
     * Getter que devuelve si la moto está avanzando continuamente o no.
     * @return True en caso de que la moto esté avanzando constantemente.
     */
    public boolean isMotorEncess(){
        return motorEncess;
    }

    /**
     * Setter que configura la moto a un estado de accidente.
     * Al configurar la moto en un estado de accidente esta se apaga y deja de avanzar.
     * @param isCrashed Estado al que se quiere someter la Moto.
     */
    public void setCrashed(boolean isCrashed){
        this.isCrashed = isCrashed;
        if(isCrashed){
            partida.setCrashedMoto(this); //Avisem a la partida de que hem tingut un accident
            apagaMoto();
        }else {
            encenMoto();
        }
    }

    /**
     * Getter que retorna si la Moto está en estado de accidente o no.
     * @return True en caso de estar en estado de accidente.
     */
    public boolean isCrashed(){
        return isCrashed;
    }

    /**
     * Configura la moto para que esté eliminada.
     * @param isEliminated True si se quiere que la moto esté eliminada.
     */
    public void setEliminated(boolean isEliminated){
        this.isEliminated = isEliminated;
    }

    /**
     * Getter que sirve para saber si la moto ha sido eliminada en algún momento.
     * @return True en caso de estar eliminada.
     */
    public boolean isEliminated(){
        return this.isEliminated;
    }

    /**
     * Reinicia la configuración de la moto sin tener que reinstanciarla de nuevo.
     * Retoma la misma configuración con la que había sido creada.
     * En caso de estar encendida (avanza constantemente), se apaga.
     */
    public void resetMoto(){
        threadMotor.apagaMotor();
        posicioY = posicioYinical;
        posicioX = posicioXinicial;
        direccio = direccioInicial;
    }
}
