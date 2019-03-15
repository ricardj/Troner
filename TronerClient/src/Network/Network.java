package Network;

import Controlador.Controlador;
import Controlador.InformationClasses.EstatJoc.EstatPartida;
import Controlador.InformationClasses.EstatPantallaPrincipal.EstatConfiguracio;
import Controlador.InformationClasses.EstatPantallaPrincipal.EstatRanking;
import Controlador.InformationClasses.EstatResultats.EstatResultats;
import Controlador.InformationClasses.EstatSalaEspera.EstatSalaEspera;
import Controlador.InformationClasses.IntroInformation.InformacioLogin;
import Controlador.InformationClasses.IntroInformation.InformacioRegistre;
import Controlador.InformationClasses.EstatJoc.EstatJugadorsPartida;
import Controlador.InformationClasses.EstatJoc.EstatTaulell;

import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;
import java.io.*;
import java.net.Socket;
import java.net.SocketException;

/*
Descripción de la conexion:
    1-Tratamos de establecer conexion. Si es aceptada proseguimos.
    2-Enviamos el pedir registro o inicio de sesion.
    3-Enviamos: datos registro , datos inicio de sesion, o cancelacion.
    4-Recibimos: confirmación de introducción de datos y recibimos configuración.
    5-Pantalla principal simultaneamente:
            5.1-Recibimos: datos de rankings.
            5.2-Enviamos: Salir sesion | nueva configuracion | nuevaPartidaTipo
    6-Pantalla de espera:
            6.1-Recibimos: numero de jugadores conectados
            6.2-Recibimos: confirmacion partida
    7-Pantalla de juego:
            7.1-Recibimos:
 */

public class Network {

    private String ip;
    private int portServidor;

    private transient Socket connexioServidor;

    //TODO: evaluar si necesitem tots els streams com a atributs

    private InputStream inputStream;
    private OutputStream outputStream;
    private DataInputStream diStream;
    private DataOutputStream doStream;
    private ObjectInputStream oiStream = null;
    private ObjectOutputStream ooStream = null;

    private Controlador controlador;


    public Network(){
        //Inicialitzem les dades que necesitem
        //TODO: hacer que los metodos sean synchronised!
    }

    public void enregistraControlador(Controlador controlador){
        this.controlador = controlador;
    }


    //Tractem d'enviar un formulari de registre. Si falla, retornem fals.
    //Si funciona ens omplen la configuracio
    public synchronized boolean enviarRegistre(InformacioRegistre informacioRegistre){
        boolean resultat = false;

        try {
            ooStream.writeInt(Comunicacio.OPERACIO_REGISTRE);
            ooStream.flush();
            ooStream.writeObject(informacioRegistre);
            ooStream.flush();
            System.out.println("Flag: registre enviat");
            int retorn = oiStream.readInt();
            if(retorn == Comunicacio.OPERACIO_CORRECTE) {
                resultat = true;
            }else {
                controlador.avisarUsuari("Error: el servidor no permet registrar.");
            }
        } catch (IOException e) {
            controlador.avisarUsuari("Error: falla la conexio amb servidor.");
        }
        return resultat;
    }

    //Tractem d'enviar el login. Si falla retornem fals
    //Si funciona ens omplen la configuracio
    public synchronized boolean enviarLogin(InformacioLogin informacioLogin){
        boolean resultat = false;
        try {
            ooStream.writeInt(Comunicacio.OPERACIO_LOGIN);
            ooStream.flush();

            ooStream.writeObject(informacioLogin);
            ooStream.flush();

            int retorn = oiStream.readInt();


            switch (retorn){
                case Comunicacio.OPERACIO_CORRECTE:
                    resultat = true;
                    break;
                case Comunicacio.ERROR_LOGIN:
                    controlador.avisarUsuari("Error: no existeix l'usuari.");
                    break;
                case Comunicacio.ERROR_CONTRASENYA:
                    controlador.avisarUsuari("Error: contrasenya incorrecta.");

            }
        } catch (IOException e) {
            controlador.avisarUsuari("Error: falla la conexio amb el servidor.");
            controlador.apagaPrograma();
        }
        return resultat;
    }


    //Demanem cambiar configuracio
    public synchronized boolean cambiarConfiguracio(EstatConfiguracio estatConfiguracio){
        boolean resultat = false;

        try {
            ooStream.writeInt(Comunicacio.OPERACIO_ACTUALITZAR_CONFIGURACIO);
            ooStream.flush();
            ooStream.writeObject(estatConfiguracio);
            ooStream.flush();
            int resposta = oiStream.readInt();
            if(resposta == Comunicacio.OPERACIO_CORRECTE) {
                resultat = true;
            }else {
                if(resposta == Comunicacio.ERROR_CONFIGURACIO) resultat = false;
            }

        }catch (IOException e){
            controlador.avisarUsuari("Error: falla la conexió amb el servidor.");
            controlador.apagaPrograma();
        }
        return resultat;
    }


    public boolean probarConnexio(String ip, String port) {
        boolean resultat = false;

        if(NetworkHelper.checkIp(ip)){
            if (NetworkHelper.checkPort(port)) {
                try {
                    System.out.println(ip + "   " + port);

                    this.portServidor = Integer.parseInt(port);
                    this.ip = ip;

                    connexioServidor = new Socket(this.ip, this.portServidor);
                    System.out.println("Flag: conexio establerta.");


                    outputStream = connexioServidor.getOutputStream();
                    inputStream = connexioServidor.getInputStream();
                    System.out.println("Flag: input output stream activats");

                    ooStream = new ObjectOutputStream(outputStream);
                    oiStream = new ObjectInputStream(inputStream);

                    //diStream = new DataInputStream(inputStream);
                    //doStream = new DataOutputStream(outputStream);

                    System.out.println("Flag: streams principals registrats.");

                    resultat = true;

                } catch (IOException e) {
                    controlador.avisarUsuari("Error: no s'ha pogut establir la connexio.");
                    resultat = false;
                }
            }else {
                controlador.avisarUsuari("Error: port incorrecte.");
            }
        }else {
            controlador.avisarUsuari("Error: IP incorrecte");
        }

        return resultat;
    }

    public synchronized EstatRanking getEstatRanking() throws ResourceNotAvalaibleException {
        EstatRanking estatRanking = null;

        try{
            ooStream.writeInt(Comunicacio.OPERACIO_ACTUALITZAR_RANKINGS);
            ooStream.flush();
            estatRanking = (EstatRanking) oiStream.readObject();
            ooStream.reset();
            ooStream.writeInt(Comunicacio.OPERACIO_CORRECTE);
            ooStream.flush();
        }catch (Exception e){
            controlador.avisarUsuari("Error: no podem acualitzar rankings.");
            controlador.apagaPrograma();
        }

        if(estatRanking == null) {
            throw new  ResourceNotAvalaibleException(
                    "Error: no podem carregar l'estat dels rankings",
                    "EstatRanking");
        }

        return estatRanking;
    }

    public synchronized EstatConfiguracio getEstatConfiguracio() throws ResourceNotAvalaibleException {
        EstatConfiguracio estatConfiguracio = null;
        try {
            ooStream.writeInt(Comunicacio.OPERACIO_DEMANAR_CONFIGURACIO);
            ooStream.flush();

            estatConfiguracio = (EstatConfiguracio) oiStream.readObject();

            ooStream.writeInt(Comunicacio.OPERACIO_CORRECTE);
            ooStream.flush();

        }catch (IOException e){
            controlador.avisarUsuari("Error: falla la conexió amb el servidor.");
            controlador.apagaPrograma();

        }catch (ClassNotFoundException e){
            controlador.avisarUsuari("Error: dades rebudes del servidor desconegudes.");
            controlador.apagaPrograma();
        }

        if(estatConfiguracio == null) {
            throw new  ResourceNotAvalaibleException(
                    "Error: no podem carregar l'estat de la configuracio",
                    "EstatConfiguracio");
        }

        return estatConfiguracio;
    }

    public synchronized EstatPartida getEstatPartida() throws ResourceNotAvalaibleException {
        //TODO: get l'estat dels jugadors de la  partida
        EstatPartida estatPartida = new EstatPartida(new EstatJugadorsPartida(),new EstatTaulell(null),false,-1);

        try {
            ooStream.writeInt(Comunicacio.OPERACIO_DEMANAR_ESTAT_JOC);
            ooStream.flush();
            estatPartida = (EstatPartida)oiStream.readObject();
        } catch (IOException e) {
            controlador.avisarUsuari("Error: falla la conexió amb el servidor.");
            controlador.apagaPrograma();
        } catch (ClassNotFoundException e) {
            controlador.avisarUsuari("Error: dades rebudes del servidor desconegudes.");
            controlador.apagaPrograma();
        }

        return estatPartida;
    }


    public boolean tancarSessio() {
        boolean resultat = true;
        //TODO: tancar sessio
        try {
            ooStream.writeInt(Comunicacio.OPERACIO_TANCAR_SESSIO);
            ooStream.flush();
        }catch (IOException e){
            e.printStackTrace();
            controlador.avisarUsuari("Error: no hem pogut tancar sessio.");
            resultat = false;
        }
        return resultat;
    }

    public synchronized EstatSalaEspera getEstatSalaEspera() throws ResourceNotAvalaibleException {
        EstatSalaEspera estatSalaEspera = new EstatSalaEspera(1,1,false);
        //TODO: get l'estat de la sala d'espera
        try{
            ooStream.writeInt(Comunicacio.OPERACIO_DEMANAR_SALA_ESPERA);
            ooStream.flush();
            estatSalaEspera = (EstatSalaEspera) oiStream.readObject();

        }catch (IOException e){
            //e.printStackTrace();
            controlador.avisarUsuari("Error: falla la conexió amb el servidor.");
            controlador.apagaPrograma();
        }catch (ClassNotFoundException e){
            //e.printStackTrace();
            controlador.avisarUsuari("Error: dades rebudes del servidor desconegudes.");
            controlador.apagaPrograma();
        }

        return estatSalaEspera;
    }

    public synchronized boolean peticioJugarX2() {
        boolean resultat = false;
        try {
            ooStream.writeInt(Comunicacio.OPERACIO_DEMANAR_JOC);
            ooStream.flush();
            ooStream.writeInt(Comunicacio.JOC_X2);
            ooStream.flush();
            int retorn = oiStream.readInt();
            if(retorn==Comunicacio.OPERACIO_CORRECTE) resultat = true;
        } catch (IOException e) {
            controlador.avisarUsuari("Error: falla la conexió amb el servidor.");
            //e.printStackTrace();
        }
        return resultat;
    }

    public synchronized boolean peticioJugarX4() {
        boolean resultat = false;
        try {
            ooStream.writeInt(Comunicacio.OPERACIO_DEMANAR_JOC);
            ooStream.flush();
            ooStream.writeInt(Comunicacio.JOC_X4);
            ooStream.flush();
            int retorn = oiStream.readInt();
            if(retorn==Comunicacio.OPERACIO_CORRECTE) resultat = true;
        } catch (IOException e) {
            //e.printStackTrace();
            controlador.avisarUsuari("Error: falla la conexió amb el servidor.");
        }
        return resultat;
    }

    public synchronized boolean peticioJugarTorneig() {
        boolean resultat = false;
        try {
            ooStream.writeInt(Comunicacio.OPERACIO_DEMANAR_JOC);
            ooStream.flush();
            ooStream.writeInt(Comunicacio.JOC_TORNEIG);
            ooStream.flush();
            int retorn = oiStream.readInt();
            if(retorn==Comunicacio.OPERACIO_CORRECTE){
                resultat = true;
            }
        } catch (IOException e) {
            //e.printStackTrace();
            controlador.avisarUsuari("Error: falla la conexió amb el servidor.");
        }
        return resultat;
    }


    public synchronized void enviarComandaNord() {
        //TODO: enviar comanda nord
        try {
            ooStream.writeInt(Comunicacio.COMANDA_NORD);
            ooStream.flush();
        } catch (IOException e) {
            //e.printStackTrace();
            controlador.avisarUsuari("Error: falla la conexió amb el servidor.");
        }
    }

    public synchronized void enviarComandaEst() {
        //TODO: enviar comanda Est
        try {
            ooStream.writeInt(Comunicacio.COMANDA_EST);
            ooStream.flush();
        } catch (IOException e) {
            //e.printStackTrace();
            controlador.avisarUsuari("Error: falla la conexió amb el servidor.");
        }
    }

    public synchronized void enviarComandaSud() {
        //TODO: enviar comanda Sud
        try {
            ooStream.writeInt(Comunicacio.COMANDA_SUD);
            ooStream.flush();
        } catch (IOException e) {
            //e.printStackTrace();
            controlador.avisarUsuari("Error: falla la conexió amb el servidor.");
        }
    }

    public synchronized void enviarComandaOest() {
        //TODO: enviar comanda Oest
        try {
            ooStream.writeInt(Comunicacio.COMANDA_OEST);
            ooStream.flush();
        } catch (IOException e) {
            //e.printStackTrace();
            controlador.avisarUsuari("Error: falla la conexió amb el servidor.");
        }
    }

    public synchronized void abandonarJoc() {
        //TODO: abandonar juego a mitad. Soluciona tus diferencias con el servidor
        try {
            ooStream.writeInt(Comunicacio.OPERACIO_ABANDONAR_JOC);
            ooStream.flush();
        } catch (IOException e) {
            //e.printStackTrace();
            controlador.avisarUsuari("Error: falla la conexió amb el servidor.");
        }
    }

    public synchronized EstatResultats getEstatResultats() throws ResourceNotAvalaibleException {
        EstatResultats estatResultats = new EstatResultats(1,2,3);
        //TODO: solicitar l'estat dels resultats
        try {
            ooStream.writeInt(Comunicacio.OPERACIO_DEMANAR_RESULTATS);
            ooStream.flush();
            estatResultats = (EstatResultats)oiStream.readObject();
        } catch (IOException e) {
            //e.printStackTrace();
            controlador.avisarUsuari("Error: falla la conexió amb el servidor.");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return estatResultats;
    }

    public synchronized void abandonarSalaEspera() {
        try {
            ooStream.writeInt(Comunicacio.OPERACIO_ABANDONAR_SALA_ESPERA);
            ooStream.flush();
        } catch (IOException e) {
            //e.printStackTrace();
            controlador.avisarUsuari("Error: falla la conexió amb el servidor.");
        }
    }

    public void abandonarTroner() {
        try {
            ooStream.writeInt(Comunicacio.OPERACIO_ABANDONAR_TRONER);
            ooStream.flush();
        } catch (IOException e){
            //e.printStackTrace();
            controlador.avisarUsuari("Error: falla la conexió amb el servidor.");
        } catch (NullPointerException e){

        }

    }
}

