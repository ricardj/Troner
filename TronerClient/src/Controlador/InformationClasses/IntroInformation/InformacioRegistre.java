package Controlador.InformationClasses.IntroInformation;

import java.io.Serializable;

public class InformacioRegistre implements Serializable {

    private String nom;
    private String correu;
    private String password;

    public InformacioRegistre(String nom, String correu, String password){
        this.correu = correu;
        this.nom = nom;
        this.password = password;
    }

    public String getNom() {
        return nom;
    }

    public String getCorreu() {
        return correu;
    }

    public String getPassword() {
        return password;
    }
}
