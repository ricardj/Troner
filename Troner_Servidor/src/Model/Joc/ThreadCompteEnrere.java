package Model.Joc;



/**
 * Esta clase gestiona una cuenta atrás que empieza en 3 y acaba en 0.
 * @author grupoC6
 */
public class ThreadCompteEnrere extends Thread {

    public static final int VALOR_INICIAL_PER_DEFECTE = 3;
    public static final int VALOR_FINAL_PER_DEFECTE = -1;

    public static final int TIMER = 1000;

    private int valorInicial = VALOR_INICIAL_PER_DEFECTE;
    private int valorFinal = VALOR_FINAL_PER_DEFECTE;

    private int valorAModificar;

    private boolean compteEnrereAcabat=false;

    /**
     * Constructor de la clase por defecto.
     */
    public ThreadCompteEnrere(){
        valorAModificar = valorInicial;
    }

    /**
     * Metodo que lleba a cabo la cuenta atrás en bucle.
     */
    @Override
    public void run(){
        while (valorAModificar > valorFinal){
            try {
                sleep(TIMER);
                valorAModificar--;
                //System.out.println("Flag: valor compteEnrere "+ valorAModificar);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        this.compteEnrereAcabat = true;
        //System.out.println("Flag: compte enrere acabat.");
    }

    /**
     * Getter que devuelve el número en el que se encuentra la cuenta atrás actualmente.
     * @return
     */
    public int getNumero(){
        return valorAModificar;
    }

    /**
     * Método que devuelve si la cuenta atrás ha terminado o no.
     * @return True en caso de que la cuenta atrás haya terminado.
     */
    public boolean isCompteEnrereAcabat(){
        return this.compteEnrereAcabat;
    }
}
