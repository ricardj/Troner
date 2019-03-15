package Model.Joc;

import Controlador.IdentificadorJugador;
import Controlador.InformationClasses.EstatJoc.EstatJugadorsPartida;
import Controlador.InformationClasses.EstatJoc.EstatPartida;
import Controlador.InformationClasses.EstatJoc.InfoJugadorPartida;
import Controlador.InformationClasses.EstatPantallaPrincipal.InfoJugador;
import Controlador.InformationClasses.EstatResultats.EstatResultats;
import Model.DBHelper;

import java.awt.*;
import java.sql.SQLException;
import java.util.LinkedList;


/**
 * Esta clase gestiona una partida y sus elementos.
 * Contiene un tablero y las configuraciones por defecto de hasta cuatro jugadores que se pueden añadir.
 * Gestiona el estado de los jugadores durante la partida (puntos, vidas, rondas ganadas), los incrementos
 * y decrementos de puntuación así como la cuenta atrás.
 * @author grupoC6
 */
public class Partida {

    //Configuraciones por defecto de hasta cuatro jugadores
    private static final int DEFAULT_INIT_LIVES = 3;

    private static final int DEFAULT_INIT_X_POSITION_PLAYER1 = 5;
    private static final int DEFAULT_INIT_Y_POSITION_PLAYER1 = 50;
    private static final Color DEFAULT_COLOR_PLAYER1 = Color.BLUE;
    private static final int DEFAULT_INIT_DIRECTION_PLAYER1 = Moto.EST;

    private static final int DEFAULT_INIT_X_POSITION_PLAYER2 = 95;
    private static final int DEFAULT_INIT_Y_POSITION_PLAYER2 = 50;
    private static final Color DEFAULT_COLOR_PLAYER2 = Color.RED;
    private static final int DEFAULT_INIT_DIRECTION_PLAYER2 = Moto.OEST;

    private static final int DEFAULT_INIT_X_POSITION_PLAYER3 = 50;
    private static final int DEFAULT_INIT_Y_POSITION_PLAYER3 = 5;
    private static final Color DEFAULT_COLOR_PLAYER3 = Color.BLACK;
    private static final int DEFAULT_INIT_DIRECTION_PLAYER3 = Moto.SUD;

    private static final int DEFAULT_INIT_X_POSITION_PLAYER4 = 50;
    private static final int DEFAULT_INIT_Y_POSITION_PLAYER4 = 95;
    private static final Color DEFAULT_COLOR_PLAYER4 = new Color(255, 0, 237, 255);
    private static final int DEFAULT_INIT_DIRECTION_PLAYER4 = Moto.NORD;


    //Medidas del tablero
    private static final int MIDA_X_TAULELL = 100;
    private static final int MIDA_Y_TAULELL = 100;

    //Puntos base sobre los que trabajamos
    private static final int BASE_POINTS = 10;


    private LinkedList<Moto> jugadors;                          //Lista de motos que representan a los jugadores
    private LinkedList<IdentificadorJugador> identificadors;    //Identificadores que usan los jugadores para
                                                                //comunicarse con la partida.
    private LinkedList<EstatResultats> resultats;               //Resultados de cada jugador
    private Taulell taulell;                                    //Tablero sobre el que ocurre la partida
    private EstatJugadorsPartida infoJugadors;                  //Información de los jugadores durante la partida

    private int crashedMotos;                                   //Ens informa de cuantes motos han chocat
    private LinkedList<Integer> crashingOrder;                  //Ens informa de en quin ordre han chocat les motos

    /*0-No ha chocat
      1-Primer en chocar
      2-Segon en chocar
      3-Tercer en chocar
    * */

    private int jugadorsActius;     //Numero de jugadores activos
    private boolean rondaAcabada;   //Ens informa de si la ronda ja s'ha acabat

    private ThreadCompteEnrere threadCompteEnrere;      //Thread usado para la cuenta atrás.

    private boolean partidaEnCurso; //Para saber si hay una partida en curso
    private int mode_joc;           //Para saber que modo de juego estamos realizando


    /**
     * Constructor por defecto de la partida.
     * Crea una partida sin ningún jugador. Antes de empezar, deben estar todos los jugadores insertados.
     */
    public Partida(){
        jugadors = new LinkedList<Moto>();
        identificadors = new LinkedList<IdentificadorJugador>();
        taulell = new Taulell(MIDA_X_TAULELL,MIDA_Y_TAULELL);
        infoJugadors = new EstatJugadorsPartida();
        crashingOrder = new LinkedList<Integer>();
        crashedMotos = 0;
        partidaEnCurso = true;
        threadCompteEnrere = new ThreadCompteEnrere();
        resultats = new LinkedList<EstatResultats>();
    }

    /**
     * Setter que configura el modo de juego de la instancia de la partida.
     * @param mode_joc
     */
    public void setMode_joc(int mode_joc) {
        this.mode_joc = mode_joc;
    }

    /**
     * Método que activa la cuenta atrás.
     */
    public void activaCompteEnrere(){
        this.threadCompteEnrere = new ThreadCompteEnrere();
        this.threadCompteEnrere.start();
    }

    /**
     * Método para añadir jugadores a la partida.
     * Según el número de jugadores que ya tenga añadidos a la partida asigna una configuración o u otra a cada
     * jugador.
     * @param nomJugador Nombre del jugador que se añade a la partida.
     * @return Identificador que ha de usar el jugador para comunicarse con la partida.
     */
    public IdentificadorJugador afegirJugador(String nomJugador){

        IdentificadorJugador id = new IdentificadorJugador();
        switch (jugadors.size()){
            case 0:
                jugadors.add(new Moto(DEFAULT_INIT_X_POSITION_PLAYER1,
                        DEFAULT_INIT_Y_POSITION_PLAYER1,
                        DEFAULT_COLOR_PLAYER1,
                        DEFAULT_INIT_DIRECTION_PLAYER1,
                        taulell,this));
                infoJugadors.addInfoJugadorPartida(nomJugador,0,DEFAULT_INIT_LIVES,0,DEFAULT_COLOR_PLAYER1);
                break;
            case 1:
                jugadors.add(new Moto(DEFAULT_INIT_X_POSITION_PLAYER2,
                        DEFAULT_INIT_Y_POSITION_PLAYER2,
                        DEFAULT_COLOR_PLAYER2,
                        DEFAULT_INIT_DIRECTION_PLAYER2,
                        taulell,this));
                infoJugadors.addInfoJugadorPartida(nomJugador,0,DEFAULT_INIT_LIVES,0,DEFAULT_COLOR_PLAYER2);
                break;
            case 2:
                jugadors.add(new Moto(DEFAULT_INIT_X_POSITION_PLAYER3,
                        DEFAULT_INIT_Y_POSITION_PLAYER3,
                        DEFAULT_COLOR_PLAYER3,
                        DEFAULT_INIT_DIRECTION_PLAYER3,
                        taulell,this));
                infoJugadors.addInfoJugadorPartida(nomJugador,0,DEFAULT_INIT_LIVES,0,DEFAULT_COLOR_PLAYER3);
                break;
            case 3:
                jugadors.add(new Moto(DEFAULT_INIT_X_POSITION_PLAYER4,
                        DEFAULT_INIT_Y_POSITION_PLAYER4,
                        DEFAULT_COLOR_PLAYER4,
                        DEFAULT_INIT_DIRECTION_PLAYER4,
                        taulell,this));
                infoJugadors.addInfoJugadorPartida(nomJugador,0,DEFAULT_INIT_LIVES,0,DEFAULT_COLOR_PLAYER4);
                break;
            default:
                System.out.println("Error: no s'admeten mes jugadors.");
                break;
        }
        identificadors.add(id);
        crashingOrder.add(0);
        resultats.add(new EstatResultats(0,0,0));
        jugadorsActius++;
        return id;
    }

    /**
     * Metodo que activa las motos de todos los jugadores.
     * Al activar las motos, estas avanzarán continuamente por el tablero.
     */
    public void activarMotos(){
        int num = jugadors.size();
        for(int i = 0; i < num;i++){
            if(!jugadors.get(i).isEliminated()) {
                jugadors.get(i).encenMoto();
            }
        }
    }

    /**
     * Apaga todas las motos que tiene registradas.
     * Estas dejarán de desplazarse continuamente por el tablero.
     */
    public void apagarMotos(){
        int num = jugadors.size();
        for(int i = 0; i < num;i++){
            Moto moto_aux = jugadors.get(i);
            moto_aux.apagaMoto();
        }
    }


    /**
     * Metodo que cambia la dirección de una moto para configurarla en NORD.
     * Recibe como parámetro el IdentificadorJugador del jugador que quiere realizar el cambio de dirección.
     * @param id IdentificadorJugador del jugador que quiere realizar el cambio de dirección.
     */
    public void cambiarDireccioNord(IdentificadorJugador id){
        int moto = identificadors.indexOf(id);
        jugadors.get(moto).canviaDireccio(Moto.NORD);
    }

    /**
     * Metodo que cambia la dirección de una moto para configurarla en SUD.
     * Recibe como parámetro el IdentificadorJugador del jugador que quiere realizar el cambio de dirección.
     * @param id IdentificadorJugador del jugador que quiere realizar el cambio de dirección.
     */
    public void cambiarDireccioSud(IdentificadorJugador id){
        int moto = identificadors.indexOf(id);
        jugadors.get(moto).canviaDireccio(Moto.SUD);
    }

    /**
     * Metodo que cambia la dirección de una moto para configurarla en EST.
     * Recibe como parámetro el IdentificadorJugador del jugador que quiere realizar el cambio de dirección.
     * @param id IdentificadorJugador del jugador que quiere realizar el cambio de dirección.
     */
    public void cambiarDireccioEst(IdentificadorJugador id){
        int moto = identificadors.indexOf(id);
        jugadors.get(moto).canviaDireccio(Moto.EST);
    }

    /**
     * Metodo que cambia la dirección de una moto para configurarla en OEST.
     * Recibe como parámetro el IdentificadorJugador del jugador que quiere realizar el cambio de dirección.
     * @param id IdentificadorJugador del jugador que quiere realizar el cambio de dirección.
     */
    public void cambiarDireccioOest(IdentificadorJugador id){
        int moto = identificadors.indexOf(id);
        jugadors.get(moto).canviaDireccio(Moto.OEST);
    }


    /**
     * Getter que devuelve encapsulada en la clase EstatPartida el estado actual de la partida.
     * @param id IdentificadorJugador del jugador que pide el estado de la partida.
     * @return Estado de la partida encapsulado en la clase EstatPartida
     */
    public EstatPartida getEstatPartida(IdentificadorJugador id){
        int moto = identificadors.indexOf(id);
        return new EstatPartida(infoJugadors,
                taulell.getEstatTaulell(),
                !partidaEnCurso,
                threadCompteEnrere.getNumero());
    }

    /**
     * Getter que sirve para saber si la cuenta atrás ha acabado.
     * @return True si la cuenta atrás ya ha acabado.
     */
    public boolean isCompteEnrereAcabat() {
        boolean value = threadCompteEnrere.isCompteEnrereAcabat();
        if(value) System.out.println(value);
        return value;
    }

    /**
     * Metodo que reinicia el estado del tablero pero no reinicia la información de la motos.
     */
    public void resetRonda(){
        taulell.resetTaulell();
        for(int i = 0; i< jugadors.size();i++){
            jugadors.get(i).resetMoto();
        }
        crashedMotos = 0;
        rondaAcabada = false;
    }

    /**
     * Getter que devuelve si la ronda ha acabado.
     * Una ronda acaba en caso de que todas las motos activas hayan chocado.
     * @return True si la partida ha acabado.
     */
    public boolean isRondaAcabada() {
        return rondaAcabada;
    }


    /**
     * Configura una moto para estar en estado de accidente a la vez que actualiza la información de partida
     * relacionada con el jugador.
     * @param crashedMoto Moto que del jugador que se quiere configurar en estado de accidente.
     */
    public synchronized void setCrashedMoto(Moto crashedMoto) {
        int index = jugadors.indexOf(crashedMoto);
        infoJugadors.getJugadorPartida(index).incrementaVides(-1);

        crashedMotos++;
        crashingOrder.set(index,crashedMotos); //Aixi sabrem en quin moment ha chocat

        if(crashedMotos == (jugadorsActius - 1)){
            rondaAcabada = true;
        }

    }

    /**
     * Método que incrementa los Resultados de todos los jugadores de la partida según las normas
     * de las partidas de X2 y de X4.
     * Antes de pedir los resultados finales de una partida, debe ejecutarse este método.
     */
    public void incrementaStats() {
        //TODO: aixó de moment nomes incrementa els punts en rondes de 2X y 4X
        int numJugadors = jugadors.size();

        for(int i = 0; i< numJugadors;i++){
            if(crashingOrder.get(i) == 0){
                infoJugadors.getJugadorPartida(i).incrementaWins(1);
            }
            if(numJugadors == 2){
                if(crashingOrder.get(i)==1){
                    infoJugadors.getJugadorPartida(i).incrementaPunts(-BASE_POINTS);
                }else {
                    infoJugadors.getJugadorPartida(i).incrementaPunts(BASE_POINTS);
                }
            }
            if (numJugadors == 4){
                switch (crashingOrder.get(i)){
                    case 0:
                        infoJugadors.getJugadorPartida(i).incrementaPunts(2*BASE_POINTS);
                        break;
                    case 1:
                        infoJugadors.getJugadorPartida(i).incrementaPunts(-2*BASE_POINTS);
                        break;
                    case 2:
                        infoJugadors.getJugadorPartida(i).incrementaPunts(-BASE_POINTS);
                        break;
                    case 3:
                        infoJugadors.getJugadorPartida(i).incrementaPunts(BASE_POINTS);
                        break;
                }
            }
        }
        for(int i = 0; i< crashingOrder.size(); i++) crashingOrder.set(i,0);
    }

    /**
     * Método que incrementa los Resultados de todos los jugadores de la partida según las normas
     * de las partidas de Torneo.
     * Antes de pedir los resultados finales de una partida, debe ejecutarse este método.
     */
    public void incrementaStatsTorneig(){

        //Primer mirem el numero de jugadors que queden
        int currentPlayers = 0;
        for(int i =0; i<jugadors.size();i++){
            if(!jugadors.get(i).isEliminated()){
                currentPlayers++;
            }
        }

        switch (currentPlayers){
            case 2:
                for(int i = 0; i<jugadors.size();i++){
                    if(!jugadors.get(i).isEliminated()){
                        switch (crashingOrder.get(i)){
                            case 0:
                                infoJugadors.getJugadorPartida(i).incrementaPunts(BASE_POINTS);
                                infoJugadors.getJugadorPartida(i).incrementaWins(1);
                                break;
                            case 1:
                                infoJugadors.getJugadorPartida(i).incrementaPunts(-BASE_POINTS);
                                break;
                        }
                    }
                }
                break;
            case 3:
                for(int i = 0; i<jugadors.size();i++){
                    if(!jugadors.get(i).isEliminated()){
                        switch (crashingOrder.get(i)){
                            case 0:
                                infoJugadors.getJugadorPartida(i).incrementaPunts(BASE_POINTS);
                                infoJugadors.getJugadorPartida(i).incrementaWins(1);
                                break;
                            case 1:
                                infoJugadors.getJugadorPartida(i).incrementaPunts(-BASE_POINTS);
                                break;
                            case 2:
                                infoJugadors.getJugadorPartida(i).incrementaPunts(0);
                                break;
                        }
                    }
                }
                break;
            case 4:
                for(int i = 0; i<jugadors.size();i++){
                    switch (crashingOrder.get(i)){
                        case 0:
                            infoJugadors.getJugadorPartida(i).incrementaPunts(2*BASE_POINTS);
                            infoJugadors.getJugadorPartida(i).incrementaWins(1);
                            break;
                        case 1:
                            infoJugadors.getJugadorPartida(i).incrementaPunts(-2*BASE_POINTS);
                            break;
                        case 2:
                            infoJugadors.getJugadorPartida(i).incrementaPunts(-BASE_POINTS);
                            break;
                        case 3:
                            infoJugadors.getJugadorPartida(i).incrementaPunts(BASE_POINTS);
                            break;
                    }
                }
                break;
        }

        //Segons aixo aigmentem uns punts o altres

    }


    /**
     * Métode que elimina al últim jugador de la partida.
     * El últim jugador de la partida es sempre aquell que te menys punts.
     */
    public void eliminateLast(){
        Integer ultimJugador = null;
        for (int i = 0; i<jugadors.size();i++){
            Moto jugador = jugadors.get(i);
            if(ultimJugador==null && !jugador.isEliminated()){
                ultimJugador = i;
            }else {
                if(ultimJugador!=null){
                    if(infoJugadors.getJugadorPartida(ultimJugador).getPunts()>infoJugadors.getJugadorPartida(i).getPunts() && !jugador.isEliminated()){
                        ultimJugador = i;
                    }
                }
            }
        }

        jugadors.get(ultimJugador).setEliminated(true);

        //Setteamos sus resultados
        resultats.get(ultimJugador).setPunts(infoJugadors.getJugadorPartida(ultimJugador).getPunts());

        int punts_totals=0;
        try {
            punts_totals= DBHelper.getInstance().puntsTotals(mode_joc, infoJugadors.getJugadorPartida(ultimJugador).getName());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        resultats.get(ultimJugador).setTotal((int) (punts_totals+resultats.get(ultimJugador).getPunts()));

        resultats.get(ultimJugador).setPosicio(jugadorsActius);


        for(int i=0 ;i<infoJugadors.length() ;i++ ){
            if(!jugadors.get(i).isEliminated() && !identificadors.get(i).isAbandonated()) {
                InfoJugadorPartida aux = infoJugadors.getJugadorPartida(i);
                aux.setVides(DEFAULT_INIT_LIVES);
                aux.setWins(0);
            }
        }

        jugadorsActius--;
    }

    /**
     * Metode que serveix per a comprobar si algun dels jugadors de la partida ha mort
     * @return True si hi ha algun jugador mort.
     */
    public boolean isSomeoneDead() {
        boolean someoneDead = false;
        for(int i = 0; i<jugadors.size(); i++){
            if(infoJugadors.getJugadorPartida(i).getVides() == 0 && !jugadors.get(i).isEliminated()){
                someoneDead = true;
                break;
            }
        }
        return someoneDead;
    }


    /**
     * Configura el estat de la partida per a que acabi.
     */
    public synchronized void setPartidaAcabada(){
        partidaEnCurso = false;

        for(int i = 0; i < infoJugadors.length(); i++){
            int posicio = infoJugadors.length();
            InfoJugadorPartida aux = infoJugadors.getJugadorPartida(i);
            for(int j = 0; j < infoJugadors.length(); j++){
                if( i != j && (infoJugadors.getJugadorPartida(i).getPunts() > infoJugadors.getJugadorPartida(j).getPunts())){
                    posicio--;
                }
            }

            try {
                if(!identificadors.get(i).isAbandonated()) {
                    int punts_totals = DBHelper.getInstance().puntsTotals(mode_joc, infoJugadors.getJugadorPartida(i).getName());
                    resultats.get(i).setPosicio(posicio);
                    resultats.get(i).setPunts(aux.getPunts());
                    resultats.get(i).setTotal(punts_totals + (int) aux.getPunts());
                }
                //resultats.add(new EstatResultats(posicio,aux.getPunts(), punts_totals));
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Retorna el estado de los resultados encapsulados en una clase EstatResultats.
     * @param identificadorJugador IdentificadorJugador del jugador que está pidiendo los resultados.
     * @return resultados del jugador encapsulados en una instancia de Estat Resuktats.
     */
    public synchronized EstatResultats getResultats(IdentificadorJugador identificadorJugador) {

        return resultats.get(identificadors.indexOf(identificadorJugador));
    }

    /**
     * Método que escrive los resultados guardados hasta el momento en la base de datos.
     * @param mode_joc Modo de juego en el que se quieren guardar los datos de la partida.
     */
    public void guardaDades(int mode_joc){
        DBHelper.getInstance().crearPartida(mode_joc);

        for(int i = 0; i < infoJugadors.length(); i++) {
            try {
                DBHelper.getInstance().crearJuga(infoJugadors.getJugadorPartida(i).getName(), resultats.get(i).getPunts());
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Metodo para penalizar en los resultados a un ugador.
     * El decremento de puntos que hace en el jugador penalizado es significativa en comparación con el decremento
     * máximo de puntos que puede llegar a tener sin ser depenalizado.
     * @param id IdentificadorJugador del jugador al que se quiere penalizr.
     */
    public synchronized void penalitzar(IdentificadorJugador id) {
        id.setAbandonated(true);

        EstatResultats aux = resultats.get(identificadors.indexOf(id));

        switch (mode_joc){
            case 1:
                aux.setPunts(-3*BASE_POINTS-5);
                aux.setPosicio(2);
                int punts_totals=0;
                try {
                    punts_totals= DBHelper.getInstance().puntsTotals(mode_joc, infoJugadors.getJugadorPartida(identificadors.indexOf(id)).getName());
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                aux.setTotal((int)(punts_totals+aux.getPunts()));

                break;

            case 2:
                //Restem punts
                aux.setPunts(-4*BASE_POINTS);

                //Posem el total
                punts_totals=0;
                try {
                    punts_totals= DBHelper.getInstance().puntsTotals(mode_joc, infoJugadors.getJugadorPartida(identificadors.indexOf(id)).getName());
                } catch (SQLException e) {
                    e.printStackTrace();
                }

                aux.setTotal((int)(punts_totals+aux.getPunts()));

                //Posem la posicio
                int posicio = infoJugadors.length();
                for(int j = 0; j < infoJugadors.length(); j++){
                    if(identificadors.get(j).isAbandonated() && j!= identificadors.indexOf(id)){
                        posicio--;
                    }
                }

                aux.setPosicio(posicio);
                break;

            case 3:
                aux.setPunts(-6*BASE_POINTS);

                //Posem el total
                punts_totals=0;
                try {
                    punts_totals= DBHelper.getInstance().puntsTotals(mode_joc, infoJugadors.getJugadorPartida(identificadors.indexOf(id)).getName());
                } catch (SQLException e) {
                    e.printStackTrace();
                }

                aux.setTotal((int)(punts_totals+aux.getPunts()));

                aux.setPosicio(jugadorsActius);

                break;

        }
        //Tambien penalizamos sus puntos de infojugador
        infoJugadors.getJugadorPartida(identificadors.indexOf(id)).setPunts((int)aux.getPunts());
    }

    /**
     * Getter que sirve para saber si alguien ha abandonado la partida.
     * @return True en caso de que alguien haya abandonado la partida.
     */
    public boolean isSomeoneAbandonated() {
        boolean resultado = false;
        for (int i =0; i <identificadors.size();i++){
            if(identificadors.get(i).isAbandonated()){
                resultado = true;
                break;
            }
        }

        return resultado;
    }

    /**
     * Getter para saber si alguien en concreto de los jugadores de la partida está muerto (tiene las vidas a 0).
     * @param id IdentificadorJugador del jugador que se quiere comprobar si sigue vivo o no.
     * @return True en caso de estar muerto.
     */
    public boolean isDead(IdentificadorJugador id) {
        boolean resultado = false;

        if(infoJugadors.getJugadorPartida(identificadors.indexOf(id)).getVides() == 0){
            resultado = true;
        }

        return resultado;
    }

    /**
     * Setter que configura a un jugador en estado de abandono.
     * @param id IdentificadorJugador del jugador que se quiere configurar en estado de abandono.
     */
    public void setAbandonated(IdentificadorJugador id) {
        id.setAbandonated(false);
    }

    /**
     * Método para saber si alguien, en la ronda actual, nos ha abandonado.
     * @return True en caso de que algun jugador haya abandonado la partida en la ronda actual.
     */
    public boolean recentAbandonation() {
        boolean resultado = false;

        for (int i = 0; i < jugadors.size();i++){
            if(infoJugadors.getJugadorPartida(i).getVides()>0 && !jugadors.get(i).isEliminated() && identificadors.get(i).isAbandonated()){
                resultado = true;
            }
        }

        return resultado;
    }
}
