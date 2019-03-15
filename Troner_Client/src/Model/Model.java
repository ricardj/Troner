package Model;

import Controlador.InformationClasses.EstatPantallaPrincipal.EstatConfiguracio;

public class Model {

    //nombre de usuario
    //correo
    //Configuracion

    private Configuracio configuracio;
    private String login;

    public Model(){
        login = "No login";
    }

    public Model(Configuracio configuracio){
        this.configuracio = configuracio;
    }

    public void setConfiguracio(EstatConfiguracio estatConfiguracio) {
        this.configuracio = new Configuracio(estatConfiguracio);
        this.configuracio.setCopiaSeguretat();
    }

    public Configuracio getConfiguracio() {
        return configuracio;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }
}
