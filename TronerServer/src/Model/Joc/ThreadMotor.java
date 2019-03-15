package Model;

import Model.Joc.Moto;

/**
 * Esta clase permite a la clase moto avanzar continuamente en un hilo de ejecución paralelo.
 * @author grupoC6
 */
public class ThreadMotor extends Thread{

    private final static int VELOCITAT_MOTOR = 50;
    private Moto moto;
    private boolean ences;

    /**
     * Constructor del ThreadMotor. Recibe como parámetro la moto a la que hará avanzar.
     * El estado inicial es siempre apagado: no avanza.
     * @param moto Moto a la que el ThreadMotor hará avanzar.
     */
    public ThreadMotor(Moto moto){
        this.moto = moto;
        ences = false;
    }

    /**
     * Método que hace avanzar la moto continuemente.
     */
    @Override
    public void run(){
        while (true){
            try {
                sleep(VELOCITAT_MOTOR);
            }catch (InterruptedException e){
                System.out.println(e.getMessage());
            }

            if (ences){
                moto.avansaMoto();
                //System.out.println("Model.Moto avansant: "+ moto.getPosicioX() + " " + moto.getPosicioY() + moto.getDireccio());
            }
        }
    }

    /**
     * Método que apaga el Thread motor.
     * La moto deja de avanzar continuamente.
     */
    public synchronized void apagaMotor(){
        ences = false;
    }

    /**
     * Método que enciende el Thread motor.
     * La moto empieza a avanzar continuamente.
     */
    public synchronized void encenMotor(){
        ences = true;
    }
}
