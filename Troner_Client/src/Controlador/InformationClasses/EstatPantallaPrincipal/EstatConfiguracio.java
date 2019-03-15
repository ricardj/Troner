package Controlador.InformationClasses.EstatPantallaPrincipal;

import java.io.Serializable;

//Relacionarlo amb la configuracio
public class EstatConfiguracio implements Serializable{

    private char comandaNord;
    private char comandaSud;
    private char comandaEst;
    private char comandaOest;

    public EstatConfiguracio(char comandaNord,char comandaSud,char comandaEst,char comandaOest){
        this.comandaEst = comandaEst;
        this.comandaNord = comandaNord;
        this.comandaSud = comandaSud;
        this.comandaOest = comandaOest;
    }

    public EstatConfiguracio(String comandes){
        comandaNord = comandes.charAt(0);
        comandaSud = comandes.charAt(1);
        comandaEst = comandes.charAt(2);
        comandaOest = comandes.charAt(3);
    }

    public char getComandaNord() {
        return comandaNord;
    }

    public char getComandaSud() {
        return comandaSud;
    }

    public char getComandaEst() {
        return comandaEst;
    }

    public char getComandaOest() {
        return comandaOest;
    }



    @Override
    public String toString(){
        return Character.toString(comandaNord)
                + Character.toString(comandaSud)
                + Character.toString(comandaEst)
                + Character.toString(comandaOest);
    }

}
