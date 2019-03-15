package Controlador;


import Controlador.InformationClasses.EstatJoc.EstatPartida;
import Controlador.InformationClasses.EstatPantallaPrincipal.InfoJugador;
import Controlador.InformationClasses.EstatResultats.EstatResultats;
import Model.Joc.Partida;
import Network.ThreadClientAtender;

//TODO: completar aquesta classe
/*Esta clase puede recibir de 1 a 4 jugadores.
Los registra en plan ClientAtender ? Yo voto que si. Pero debe existir una solucion mejor.
Los registra. Inicializa una partida. Se autoregistra en los clientes que han de acceder a el.
Ha de permitir que le pidan el estado de la partida y ha de realizar los cambios que sean en la partida si se lo indican.
Tambien ha de permitir que los jugadores abandonen.
//TODO: establecer qual sera la comunnicacion entre ThreadClientAtender y este Thread. De momento lo establecemos con la
//clase Identificador.
* */
public class ThreadPartida extends Thread{

    //TODO: important que aquí s'han de posar l'array dinfo de jugador.
    protected ThreadClientAtender[] jugadors;
    protected InfoJugador[] infoJugadors;
    protected int numJugadors;
    protected Partida partida;
    protected boolean partidaAcabada;

    public ThreadPartida(ThreadClientAtender[] jugadors ){
        //TODO: crear una partida amb el numero determinat de jugadors.
        numJugadors = jugadors.length;
        this.jugadors = jugadors;
        infoJugadors = new InfoJugador[numJugadors];
        partida = new Partida();

        if(numJugadors == 2){
            partida.setMode_joc(1);
        }else {
            partida.setMode_joc(2);
        }

        System.out.println("Flag: Iniciem partida. NumJugadors = "+ Integer.toString(numJugadors));

        //TODO: hem de guardar els identificadors!!
        //En cada jugador hemos de registrar al propio Thread partida i la clase identificador
        for (int i =0;i<numJugadors;i++){
            jugadors[i].enregistraThreadPartida(this,partida.afegirJugador(jugadors[i].getLogin()));
            System.out.println("Flag: partida registrada en Thread client atender " +Integer.toString(i+1));
        }

        //TODO: compte enrere i començar partida
        this.start();
    }

    public EstatPartida getEstatPartida(IdentificadorJugador identificadorJugador){
        return partida.getEstatPartida(identificadorJugador);
    }

    public void comandaNord(IdentificadorJugador identificadorJugador){
        partida.cambiarDireccioNord(identificadorJugador);
    }

    public void comandaEst(IdentificadorJugador identificadorJugador){
        partida.cambiarDireccioEst(identificadorJugador);
    }

    public void comandaOest(IdentificadorJugador identificadorJugador){
        partida.cambiarDireccioOest(identificadorJugador);
    }
    public void comandaSud(IdentificadorJugador identificadorJugador){
        partida.cambiarDireccioSud(identificadorJugador);
    }

    public void abandonarJoc(IdentificadorJugador id){
        //TODO: els treiem de les llistes que s'hagin de treure i generem els seus resultats
        if(jugadors.length == 2){
            partida.penalitzar(id);
            partida.setPartidaAcabada();
            partidaAcabada = true;
        }

        if(jugadors.length == 4){
            partida.penalitzar(id);
        }
    }


    //TODO: aqui anem comprovant els diferents aspectes de la partida
    /*Gestionem la informacio que s'esta enviant, es a dir, la informacio que te partida a dins y que ens empaqueta per a fer
    l'estat partida.
    Si un dels motoristes choca, llavors, la partida mateixa actualitzara les dades.
    Si en algun moment ens demanen resultats i el motorista encara no ha chocat, li restem punts!
    En mode dos jugadors:
        -Aquest Thread anira fent rondes fins que segons la situacio s'hagi de sortir.
        -En mode dos jugadors:
            -Comença una ronda. En morir un, es paren les dues motos, ens esperem un temps i començem la seguent ronda.
            -Comença una altra ronda.
            -Si un jugador abandona la partida, li ho marquem als punts.
            -No indiquem al jugador que la partida s'ha acabat fins que s'acabin les rondes

    * */
    //continuar amb la partida

    @Override
    public void run(){
        try {
            while (!partidaAcabada) {                   //La partida acaba quan es declara un jugador guanyador
                //Nova ronda. Començem compte enrere
                partida.activaCompteEnrere();

                boolean sortir = false;
                while (!sortir) {
                    if (partida.isCompteEnrereAcabat()) {
                        System.out.println("Flag: motos activades.");
                        partida.activarMotos();
                        sortir = true;
                    }else {
                        sleep(10);
                    }
                }

                //S'acava compte enrere s'engegen les motoos
                while (!partida.isRondaAcabada()){
                    sleep(100); //Petita pausa
                }

                partida.apagarMotos();

                partida.incrementaStats();

                sleep(1000); //Deixem un temps per a que ho paeixin


                //Mirem si algú ha mort
                //TODO: això mira si a algú se li han acabat les vides.
                if(partida.isSomeoneDead() || partida.isSomeoneAbandonated()) {
                    partida.setPartidaAcabada();
                    partidaAcabada = true;
                }
                else {
                    partida.resetRonda();
                }

            }
        }catch (InterruptedException e){
            e.printStackTrace();
        }

        if(jugadors.length == 2){
            partida.guardaDades(1);
        }else{
            partida.guardaDades(2);
        }

        System.out.println("Flag: threadPartida acabat i alliberat.");
        //TODO: avisar a todos los client atender de que no hay más partida
        for(int i =0; i<jugadors.length;i++){
            jugadors[i].desenregistraThreadPartida();
        }

    }

    public EstatResultats demanarResultats(IdentificadorJugador identificadorJugador) {
        return partida.getResultats(identificadorJugador);
    }
}
