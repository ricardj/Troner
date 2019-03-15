package Model;

import Controlador.InformationClasses.EstatPantallaPrincipal.EstatConfiguracio;

/**
 * Aquesta classe enmagatzema la configuracio del jugador.
 */

public class Configuracio {
    private char nord;
    private char sud;
    private char est;
    private char oest;
    private Configuracio copiaSeguretat;

    public Configuracio(char nord, char sud, char est, char oest){
        this.est = est;
        this.nord = nord;
        this.oest = oest;
        this.sud = sud;
    }

    public Configuracio(EstatConfiguracio estatConfiguracio){
        nord = estatConfiguracio.getComandaNord();
        sud = estatConfiguracio.getComandaSud();
        est = estatConfiguracio.getComandaEst();
        oest = estatConfiguracio.getComandaOest();
    }

    public EstatConfiguracio getEstatConfiguracio(){
        return new EstatConfiguracio(nord,sud,est,oest);
    }



    @Override
    public String toString(){
        return Character.toString(nord) + Character.toString(sud) + Character.toString(est) + Character.toString(oest);
    }

    public char getNord() {
        return nord;
    }

    public char getSud() {
        return sud;
    }

    public char getEst() {
        return est;
    }

    public char getOest() {
        return oest;
    }

    public String getStringNord() {
        return String.valueOf(nord);
    }

    public String getStringSud() {
        return String.valueOf(sud);
    }

    public String getStringEst() {
        return String.valueOf(est);
    }

    public String getStringOest() {
        return String.valueOf(oest);
    }

    public boolean cambiarConfiguracio(String comandaOriginal, String comandaFinal) {
        boolean resultat = false;

        if (this.toString().contains(comandaOriginal)){
            if(!this.toString().contains(comandaFinal)){
                if(respectaFormat(comandaFinal)){
                    substituirComanda(comandaOriginal.charAt(0),comandaFinal.charAt(0));
                    resultat = true;
                }
            }
        }

        return resultat;
    }

    private void substituirComanda(char comandaOriginal, char comandaFinal) {
        if(comandaOriginal == nord){
            nord = comandaFinal;
        }
        if(comandaOriginal == sud){
            sud = comandaFinal;
        }
        if(comandaOriginal == est){
            est = comandaFinal;
        }
        if(comandaOriginal == oest){
            oest = comandaFinal;
        }
    }

    private boolean respectaFormat(String comandaFinal) {
        boolean resultat = true;

        //TODO: mirar quines son les tecles que acceptem y quines no acceptem

        return resultat=true;
    }

    public void setCopiaSeguretat(){
        copiaSeguretat = this;
    }

    public void initialState() {
        nord = copiaSeguretat.nord;
        sud = copiaSeguretat.sud;
        est = copiaSeguretat.est;
        oest = copiaSeguretat.oest;
    }
}
