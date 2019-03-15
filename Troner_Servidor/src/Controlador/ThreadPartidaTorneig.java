package Controlador;

import Network.ThreadClientAtender;

public class ThreadPartidaTorneig extends ThreadPartida {

    private int ronda;

    public ThreadPartidaTorneig(ThreadClientAtender[] jugadors ){
        super(jugadors);
        ronda = 4;
        this.partida.setMode_joc(3);
    }

    @Override
    public void run(){
        try {
            while (ronda!=1) {
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

                partida.incrementaStatsTorneig();

                sleep(1000); //Deixem un temps per a que ho paeixin

                //Mirem si algú ha mort
                if(partida.isSomeoneDead() || partida.recentAbandonation()){
                    ronda--;
                    if(ronda!=1){
                        partida.eliminateLast();
                    }else {
                        partida.setPartidaAcabada();
                    }

                }
                partida.resetRonda();


            }
        }catch (InterruptedException e){
            e.printStackTrace();
        }

        partida.guardaDades(3);

        System.out.println("Flag: threadPartida acabat i alliberat.");

        for(int i =0; i<jugadors.length;i++){
            jugadors[i].desenregistraThreadPartida();
        }

    }

    @Override
    public void abandonarJoc(IdentificadorJugador id){
        if(partida.isDead(id)){
            partida.setAbandonated(id);
        }else {
            partida.penalitzar(id);
        }
    }
}
