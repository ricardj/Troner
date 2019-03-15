package Controlador;

import Controlador.ControllerHelpers.LoginException;
import Controlador.ControllerHelpers.RegisterInformationChecker;
import Controlador.InformationClasses.EstatJoc.EstatPartida;
import Controlador.InformationClasses.EstatPantallaPrincipal.EstatConfiguracio;
import Controlador.InformationClasses.EstatPantallaPrincipal.EstatRanking;
import Controlador.InformationClasses.IntroInformation.InformacioLogin;
import Controlador.InformationClasses.IntroInformation.InformacioRegistre;
import Controlador.ThreadsActualitzadors.ThreadActualitzaPantallaJoc;
import Controlador.ThreadsActualitzadors.ThreadActualitzaPantallaPrincipal;
import Controlador.ThreadsActualitzadors.ThreadActualitzaPantallaSalaEspera;
import Network.ResourceNotAvalaibleException;
import Model.Model;
import Network.Network;
import Vista.PantallaConfiguracio.PantallaConfiguracio;
import Vista.PantallaJoc.PantallaJoc;
import Vista.PantallaLoginRegistre.PantallaLogin;
import Vista.PantallaLoginRegistre.PantallaRegistre;
import Vista.PantallaPrincipal.PantallaPrincipal;
import Vista.PantallaSalaEspera.PantallaSalaEspera;
import Vista.Vista;


public class Controlador {

    //Blocs a controlar
    private Vista vista;
    private Model model;
    private Network network;


    //Vista activada
    private boolean vistaActivada;
    private int modalitatJoc;

    private final static int PARTIDA_X2 = 0;
    private final static int PARTIDA_X4 = 1;
    private final static int PARTIDA_TORNEIG = 2;

    private final static int TIEMPO_PARTIDA_ACABADA = 5000;


    //Listeners y demes coses
    private EscoltaAccionsVista escoltaAccionsVista;
    private EscoltaTecles escoltaTecles;

    //Threads d'actualitzacio
    private ThreadActualitzaPantallaPrincipal actualitzadorPantallaPrincipal;
    private ThreadActualitzaPantallaJoc actualitzadorPantallaJoc;
    private ThreadActualitzaPantallaSalaEspera actualitzadorPantallaSalaEspera;


    public Controlador(Vista vista, Model model,Network network){
        this.model = model;
        this.vista = vista;
        this.network = network;

        escoltaAccionsVista = new EscoltaAccionsVista(this,model);

        vistaActivada = true;
        vista.actualitza();
    }

    public EscoltaAccionsVista getEscoltaAccionsVista() {
        return escoltaAccionsVista;
    }

    public void recullEvaluaConfiguracio(){

        String ip = ((PantallaConfiguracio)vista.getPantalla(Vista.PANTALLA_CONFIGURACIO)).getIP();
        String port = ((PantallaConfiguracio)vista.getPantalla(Vista.PANTALLA_CONFIGURACIO)).getPort();

        if(network.probarConnexio(ip,port)){
            vista.setPantallaInici();
        }
    }

    public void pasarAPantallaLogin(){
        vista.setPantallaLogin();
    }
    public void pasarAPantallaRegistre(){
        vista.setPantallaRegistre();
    }

    //Metode actualitzat
    public void enviarLogin(){
        RegisterInformationChecker comprova= null;

        String usuari = ((PantallaLogin)vista.getPantalla(Vista.PANTALLA_LOGIN)).getNom();
        String contrasenya = ((PantallaLogin)vista.getPantalla(Vista.PANTALLA_LOGIN)).getContrasenya();

        //Comprovem nom i usuari
        try {
            //TODO: comprovar params
            RegisterInformationChecker.contrasenyaOk(contrasenya);
            //RegisterInformationChecker.nomOk(usuari);
            InformacioLogin  informacioLogin = new InformacioLogin(usuari,contrasenya);
            if(network.enviarLogin(informacioLogin)){
                model.setLogin(informacioLogin.getUsuari());
                pasarAPaginaPrincipal();
            }
        } catch (LoginException e) {
            avisarUsuari(e.getMessage());
        }
    }

    //Metide actuakurzar
    public void enviarRegistre(){
        String nom = ((PantallaRegistre)vista.getPantalla(Vista.PANTALLA_REGISTRE)).getNom();
        String correu = ((PantallaRegistre)vista.getPantalla(Vista.PANTALLA_REGISTRE)).getCorreu();
        String contrasenya = ((PantallaRegistre)vista.getPantalla(Vista.PANTALLA_REGISTRE)).getContrasenya();
        String confirmacioContrasenya = ((PantallaRegistre)vista.getPantalla(Vista.PANTALLA_REGISTRE)).getConfirmacioContrasenya();

        RegisterInformationChecker checker = null;
        try {
            checker = new RegisterInformationChecker(nom,correu,contrasenya,confirmacioContrasenya);
        } catch (LoginException e) {
            avisarUsuari( e.getMessage() );
        }
        if (checker.thereIsProblem()){
            switch (checker.getProblem()){
                case RegisterInformationChecker.NAME_FORMAT_PROBLEM:
                    avisarUsuari("Error: El nom no pot contenir caràcters estranys, només '-' i '_' (no poden estar seguits ni repetits més de 2 vegades)");
                    break;
                case RegisterInformationChecker.MAIL_FORMAT_PROBLEM:
                    avisarUsuari("Error: El correu no compleix un format correcte");
                    break;
                case RegisterInformationChecker.PASSWORD_FORMAT_PROBLEM:
                    avisarUsuari("Error: La contrasenya ha de tenir entre 4 i 6 caràcters");
                    break;
                case RegisterInformationChecker.PASSWORD_MATCHING_PROBLEM:
                    avisarUsuari("Error: Les contrasenyes proporcionades no coincideixen");
                    break;
            }

        }else {
            InformacioRegistre informacioRegistre = new InformacioRegistre(nom,correu,contrasenya);
            if(network.enviarRegistre(informacioRegistre)){
                System.out.println("Flag: registre exitos.");
                model.setLogin(nom);
                pasarAPaginaPrincipal();
            }
        }
    }

    private void pasarAPaginaPrincipal(){
        try {
            EstatRanking estatRanking = network.getEstatRanking();
            EstatConfiguracio estatConfiguracio = network.getEstatConfiguracio();

            model.setConfiguracio(estatConfiguracio);

            vista.setPantallaPrincipal(model.getLogin(),estatRanking,estatConfiguracio);

            actualitzadorPantallaPrincipal = new ThreadActualitzaPantallaPrincipal(
                    (PantallaPrincipal) vista.getPantalla(Vista.PANTALLA_PRINCIPAL),
                    network,this);


        }catch (ResourceNotAvalaibleException exception){
            vista.avisarUsuario(exception.getMessage());
        }
    }

    public void peticioPartidaX2(){
        //Exigimos al servidor que nos ponga en la cola y nos vaya diciendo cuanta peña hay conectada
        if(network.peticioJugarX2()){
            try {
                actualitzadorPantallaPrincipal.deactivate();
                vista.setPantallaSalaEspera(network.getEstatSalaEspera(), PantallaSalaEspera.MODE_JOC_X2);

                actualitzadorPantallaSalaEspera = new ThreadActualitzaPantallaSalaEspera(
                        (PantallaSalaEspera)vista.getPantalla(Vista.PANTALLA_ESPERA),
                        network,this);

                modalitatJoc = PARTIDA_X2;
            }catch (ResourceNotAvalaibleException e){
                System.out.println(e.getMessage());
            }
        }
        System.out.println("Flag: peticio de partida acabada. ");

    }

    public void peticioPartidaX4(){
        if(network.peticioJugarX4()){
            try {
                actualitzadorPantallaPrincipal.deactivate();
                vista.setPantallaSalaEspera(network.getEstatSalaEspera(),PantallaSalaEspera.MODE_JOC_X4);

                actualitzadorPantallaSalaEspera = new ThreadActualitzaPantallaSalaEspera(
                     (PantallaSalaEspera)vista.getPantalla(Vista.PANTALLA_ESPERA),
                     network,this);
                modalitatJoc = PARTIDA_X4;

            } catch (ResourceNotAvalaibleException e) {
                e.printStackTrace();
            }

        }
    }

    public void peticioPartidaTorneig(){
        if(network.peticioJugarTorneig()){
            try {
                actualitzadorPantallaPrincipal.deactivate();
                vista.setPantallaSalaEspera(network.getEstatSalaEspera(),PantallaSalaEspera.MODE_JOC_TORNEIG);

                actualitzadorPantallaSalaEspera = new ThreadActualitzaPantallaSalaEspera(
                        (PantallaSalaEspera)vista.getPantalla(Vista.PANTALLA_ESPERA),
                        network,this);
                modalitatJoc = PARTIDA_TORNEIG;

            } catch (ResourceNotAvalaibleException e) {
                e.printStackTrace();
            }
        }
    }

    public void abandonarPantallaEspera(){
       //TODO: decirle al servidor que queremos que nos quite de la cola en la que nos ha metido
        network.abandonarSalaEspera();
        pasarAPaginaPrincipal();

    }

    public void abandonarJoc() {
        network.abandonarJoc();
        try {
            vista.setPantallaResultats(network.getEstatResultats());
            actualitzadorPantallaJoc.deactivate();
        } catch (ResourceNotAvalaibleException e) {
            e.printStackTrace();
        }
    }

    public void tornarPantallaPrincipal() {
        pasarAPaginaPrincipal();
    }

    public void continuarJoc() {
        //TODO: averiguar para que sirve esto de aka y poner nombre descriptivo
        boolean resultat = false;
        switch (modalitatJoc){
            case Controlador.PARTIDA_X2:
                resultat = network.peticioJugarX2();
                break;

            case Controlador.PARTIDA_X4:
                resultat = network.peticioJugarX4();
                break;

            case Controlador.PARTIDA_TORNEIG:
                resultat = network.peticioJugarTorneig();
                break;
        }
        if (!resultat) {
            avisarUsuari("Error: servidor no permet afegir en cua.");
        }else {
            try {
                vista.setPantallaSalaEspera(network.getEstatSalaEspera(), PantallaSalaEspera.MODE_JOC_X2);
                actualitzadorPantallaSalaEspera = new ThreadActualitzaPantallaSalaEspera(
                        (PantallaSalaEspera)vista.getPantalla(Vista.PANTALLA_ESPERA),
                        network,this);
            }catch (ResourceNotAvalaibleException e){
                System.out.println(e.getMessage());
            }
        }
    }

    public void tancarSessio() {
        network.tancarSessio();
        vista.setPantallaInici();
    }

    public void tornarPantallaInici() {
        vista.setPantallaInici();
    }

    public void avisarUsuari(String missatge){
        vista.avisarUsuario(missatge);
    }

    public void executarPartida() {
        try {
            escoltaTecles = new EscoltaTecles(model.getConfiguracio(),network);
            EstatPartida estatPartida = network.getEstatPartida();

            vista.setPantallaJoc(estatPartida.getEstatTaulell(),estatPartida.getEstatJugadorsPartida(),estatPartida.getNumero(),escoltaTecles);

            switch (modalitatJoc){
                case PARTIDA_X2:
                    actualitzadorPantallaJoc = new ThreadActualitzaPantallaJoc(
                            (PantallaJoc) vista.getPantalla(Vista.PANTALLA_JOC)
                            ,network,this);
                    break;
                case PARTIDA_X4:

                    actualitzadorPantallaJoc = new ThreadActualitzaPantallaJoc(
                            (PantallaJoc) vista.getPantalla(Vista.PANTALLA_JOC)
                            ,network,this);
                    break;
                case PARTIDA_TORNEIG:

                    actualitzadorPantallaJoc = new ThreadActualitzaPantallaJoc(
                            (PantallaJoc) vista.getPantalla(Vista.PANTALLA_JOC)
                            ,network,this);
                    break;
                default:
                    System.out.println("Error: Modalitat de joc no seleccionada.");
                    break;
            }
        }catch (ResourceNotAvalaibleException e){
            System.out.println(e.getMessage());
        }
        System.out.println("Flag: partida acabada d'activar.");
    }

    public void partidaAcabada() {
        //TODO: QUE HACEMOS CUANDO ACABA LA PARTIDA
        try {
            Thread.sleep(TIEMPO_PARTIDA_ACABADA);
            if(vista.getPantallaActual() == Vista.PANTALLA_JOC){
                vista.setPantallaResultats(network.getEstatResultats());
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }catch (ResourceNotAvalaibleException e){
            System.out.println(e.getMessage());
        }
        switch (modalitatJoc){
           /* case PARTIDA_X2:
                //Esperamos un tiempo para que el jugador admire su victoria o derrota
                try {
                    Thread.sleep(TIEMPO_PARTIDA_ACABADA);
                    vista.setPantallaResultats(network.getEstatResultats());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }catch (ResourceNotAvalaibleException e){
                    System.out.println(e.getMessage());
                }
                break;
                */
        }
    }

    public void solicitaCambiarConfiguracio(EstatConfiguracio estatConfiguracio) {
        if(network.cambiarConfiguracio(estatConfiguracio)){
            ((PantallaPrincipal)vista.getPantalla(Vista.PANTALLA_PRINCIPAL)).actualitzaConfiguracio(estatConfiguracio);
            model.setConfiguracio(estatConfiguracio);
        }else {
            avisarUsuari("Error: no s'ha pogut cambiar la configuracio");
            //Reiniciem la info que tenim al model perque estaba cambiada
            //model.getConfiguracio().initialState();
        }
    }

    public void abandonarTroner() {
        network.abandonarTroner();
    }

    public void apagaPrograma(){
        try {
            actualitzadorPantallaPrincipal.deactivate();
            actualitzadorPantallaSalaEspera.deactivate();
            actualitzadorPantallaJoc.deactivate();
        }catch (Exception e){
           // e.printStackTrace();
        }
        vista.setVisible(false);
        System.exit(0);
    }
}
