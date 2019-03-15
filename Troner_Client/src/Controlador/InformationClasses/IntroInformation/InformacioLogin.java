package Controlador.InformationClasses.IntroInformation;

import java.io.Serializable;

public class InformacioLogin implements Serializable {

    private String usuari;
    private String contrasenya;

    public InformacioLogin(String usuari, String contrasenya){
        this.usuari = usuari;
        this.contrasenya = contrasenya;
    }

    public String getUsuari() {
        return usuari;
    }

    public String getContrasenya() {
        return contrasenya;
    }
}
