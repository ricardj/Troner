package Network;

import Controlador.Controlador;
import Controlador.InformationClasses.EstatJoc.EstatPartida;
import Controlador.InformationClasses.EstatPantallaPrincipal.EstatConfiguracio;
import Controlador.InformationClasses.EstatPantallaPrincipal.EstatRanking;
import Controlador.InformationClasses.EstatResultats.EstatResultats;
import Controlador.InformationClasses.IntroInformation.InformacioLogin;
import Controlador.InformationClasses.IntroInformation.InformacioRegistre;
import Model.DBHelper;
import Controlador.IdentificadorJugador;


import java.io.*;
import java.net.Socket;
import java.net.SocketException;
import java.sql.SQLException;
import Controlador.ThreadPartida;

/**
 * Esta clase gestiona la connexión con los
 * distintos clientes por medio de threads
 *
 * @author grupoC6
 * @version 1.0
 */
public class ThreadClientAtender extends Thread {

    //Esta clase nos la registraran cuando empieze una partida
    private InputStream iStream;
    private OutputStream oStream;
    private ObjectInputStream oiStream;
    private ObjectOutputStream ooStream;
    private Controlador controlador;
    private boolean flag;
    private EstatConfiguracio config;
    private EstatRanking ranking;
    private String login;

    //Thread amb el que parlarem i que tambe ens parlara amb Identificador. Una clase que ens serveix de comunicacio.
    private ThreadPartida partida;
    private IdentificadorJugador identificadorJugador;
    private SalaEspera salaEspera;
    private int modeJoc;
    private boolean partidaActivada;

    /**
     * Método constructor de la clase ThreadClientAtender
     * @param sClient Socket en el que se redirecciona el cliente
     * @param controlador Controlador
     * @param salaEspera SalaEspera
     */
    public ThreadClientAtender(Socket sClient, Controlador controlador,SalaEspera salaEspera){
        flag = true;
        config = new EstatConfiguracio('w', 's', 'd', 'a');


        //Nos guardamos la sala de espera.
        this.salaEspera = salaEspera;



        this.controlador = controlador;
        try {
            iStream = sClient.getInputStream();
            oStream = sClient.getOutputStream();
            oiStream = new ObjectInputStream(iStream);
            ooStream = new ObjectOutputStream(oStream);

            start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Método que gestiona y coordina la comunicación con los distintos
     * clientes por tal de realizar una partida correctamente
     */
    @Override
    public void run() {

        //TODO: quan s'inicia una partida s'ha d'enregistrar en aquest fil la clase en questio per anar avisant de quines tecles pitja l'usuari

        while(flag) {
            try {
                int mensaje = oiStream.readInt();

                switch (mensaje) {
                    case Comunicacio.OPERACIO_REGISTRE:
                        registreUsuari();
                        break;

                    case Comunicacio.OPERACIO_LOGIN:
                        loginUsuari();

                        break;

                    case Comunicacio.OPERACIO_ACTUALITZAR_RANKINGS:
                        actualitzaRankings();
                        break;

                    case Comunicacio.OPERACIO_DEMANAR_CONFIGURACIO:
                        demanaConfiguracio();
                        break;

                    case Comunicacio.OPERACIO_TANCAR_SESSIO:
                        break;

                    case Comunicacio.OPERACIO_DEMANAR_JOC:
                        posarUsuariSalaEspera();
                        break;

                    case Comunicacio.OPERACIO_ACTUALITZAR_CONFIGURACIO:
                        actualitzaConfiguracio();
                        break;

                    case Comunicacio.OPERACIO_DEMANAR_SALA_ESPERA:
                        demanarEstatSalaEspera();
                        break;

                    case Comunicacio.OPERACIO_ABANDONAR_SALA_ESPERA:
                        abandonarSalaEspera();
                        break;

                    case Comunicacio.OPERACIO_DEMANAR_ESTAT_JOC:
                        demanarEstatJoc();
                        break;

                    case Comunicacio.OPERACIO_ABANDONAR_JOC:
                        abandonarJoc();
                        break;

                    case Comunicacio.COMANDA_NORD:
                        partida.comandaNord(identificadorJugador);
                        break;

                    case Comunicacio.COMANDA_EST:
                        partida.comandaEst(identificadorJugador);
                        break;

                    case Comunicacio.COMANDA_OEST:
                        partida.comandaOest(identificadorJugador);
                        break;

                    case Comunicacio.COMANDA_SUD:
                        partida.comandaSud(identificadorJugador);
                        break;

                    case Comunicacio.OPERACIO_DEMANAR_RESULTATS:
                        demanarResultats();
                        break;
                }
            } catch (SocketException e) {
                System.out.println("ERROR: El client s'ha pirat\n");
                flag = false;
            } catch (EOFException e){
                System.out.println("ERROR: El client s'ha pirat\n");
                flag = false;
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (SQLException e){
                e.printStackTrace();
            }
        }
    }

    /**
     * Gestiona lo que debe suceder si alguno de los clientes abandona a medio juego:
     *  En pRTIDA 2X, interrumpimos partida y damos resultados
     *  En partida x4 no interrumpimos partida y dejamos que acabe la ronda.
     *  En torneo eliminamos jugador y saltamos a siguiente ronda.
     */
    private void abandonarJoc() {
        //En pRTIDA 2X, interrumpimos partida y damos resultados
        //En partida x4 no interrumpimos partida y dejamos que acabe la ronda.
        //En torneo eliminamos jugador y saltamos a siguiente ronda.

        partida.abandonarJoc(identificadorJugador);
        partidaActivada = false;
    }

    /**
     * Método que devuelve los resultados de la partida
     * @throws IOException
     */
    private void demanarResultats() throws IOException {
        EstatResultats estatResultats = partida.demanarResultats(identificadorJugador);
        ooStream.writeObject(estatResultats);
        ooStream.flush();
        partidaActivada = false;
    }

    /**
     * Método que devuelve el estado del juego
     * @throws IOException
     */
    private void demanarEstatJoc() throws IOException{
        //System.out.println("Flag: ens demana l'estat del joc " + identificadorJugador.getIdJugador());
        EstatPartida estatPartida = partida.getEstatPartida(identificadorJugador);
        ooStream.writeObject(estatPartida);
        ooStream.reset();
        ooStream.flush();
    }

    /**
     * Devuelve el estado del estado de espera y se lo envia al usuario
     * @throws IOException
     */
    private void demanarEstatSalaEspera() throws IOException{
        //TODO: demanem a la sala d'espera quin es l'estat i l'enviem a l'usuari

        switch (modeJoc){
            case Comunicacio.JOC_X2:
                ooStream.writeObject(salaEspera.getEstatSalaEsperaX2(partidaActivada));
                ooStream.flush();
                break;
            case Comunicacio.JOC_X4:
                ooStream.writeObject(salaEspera.getEstatSalaEsperaX4(partidaActivada));
                ooStream.flush();
                break;
            case Comunicacio.JOC_TORNEIG:
                ooStream.writeObject(salaEspera.getEstatSalaEsperaTorneig(partidaActivada));
                ooStream.flush();
                break;

        }
    }

    /**
     * Método que gestiona la solicitud del cliente por tal de abandonar la sala de espera
     */
    private void abandonarSalaEspera() {
        //TODO: demanar a la sala de espera que ens tregui d'alla on ens hem posat.
         salaEspera.treureJugador(this);
    }

    /**
     * Método que gestiona la solicitud del cliente por tal de entrar en la sala de espera
     */
    private void posarUsuariSalaEspera() throws IOException {
        modeJoc = oiStream.readInt();

        //TODO: como la clase SalaEspera esta incializada deberia haber una orden asi:
        switch (modeJoc) {
            case Comunicacio.JOC_X2:
                salaEspera.afegirUsuariJocX2(this);
                break;
            case Comunicacio.JOC_X4:
                salaEspera.afegirUsuariJocX4(this);
                break;
            case Comunicacio.JOC_TORNEIG:
                salaEspera.afegirUsuariJocTorneig(this);
                break;
        }
        ooStream.writeInt(Comunicacio.OPERACIO_CORRECTE);
        ooStream.flush();
    }

    /**
     * Método que gestiona la solicitud del cliente para actualizar su ranking
     * @throws IOException
     * @throws SQLException
     */
    private void actualitzaRankings() throws IOException, SQLException {
        ranking = DBHelper.getInstance().obtenirRanking();
        ooStream.writeObject(ranking);
        ooStream.flush();
    }

    /**
     * Método que gestiona la solicitud del cliente para actualizar su configuración de controles
     * @throws IOException
     * @throws ClassNotFoundException
     */
    private void actualitzaConfiguracio()throws IOException, ClassNotFoundException {
        EstatConfiguracio configuracio = (EstatConfiguracio) oiStream.readObject();
        if (controlador.esConfiguracioValida(configuracio)){
            config = configuracio;
            DBHelper.getInstance().canviaControls(login, config.getComandaNord(), config.getComandaSud(), config.getComandaOest(), config.getComandaEst(), ' ');
            ooStream.writeInt(Comunicacio.OPERACIO_CORRECTE);
            ooStream.flush();
        } else {
            ooStream.writeInt(Comunicacio.ERROR_CONFIGURACIO);
            ooStream.flush();
        }
    }

    /**
     * Método que lee la configuración de controles del jugador
     * @throws IOException
     */
    private void demanaConfiguracio() throws IOException {
        ooStream.writeObject(DBHelper.getInstance().obtenirConfiguracio(login));
        ooStream.flush();
    }

    /**
     * Gestiona la solicitud de "Login" del cliente
     * @throws IOException
     * @throws ClassNotFoundException
     */
    private void loginUsuari() throws IOException, ClassNotFoundException {
        InformacioLogin infoLogin = (InformacioLogin) oiStream.readObject();

        int error = controlador.loginUsuari(infoLogin);
        if (error == 0) {
            login = DBHelper.getInstance().getLoginBBDD(infoLogin.getUsuari());
            DBHelper.getInstance().actualitzaData(login);
            ooStream.writeInt(Comunicacio.OPERACIO_CORRECTE);
            ooStream.flush();
        } else if (error == 1) {
            ooStream.writeInt(Comunicacio.ERROR_CONTRASENYA);
            ooStream.flush();
        } else if (error == 2) {
            ooStream.writeInt(Comunicacio.ERROR_LOGIN);
            ooStream.flush();
        }
    }

    /**
     * Gestiona la solicitud de registro del cliente
     * @throws IOException
     * @throws ClassNotFoundException
     */
    private void registreUsuari() throws IOException, ClassNotFoundException {
        InformacioRegistre info_registre = (InformacioRegistre) oiStream.readObject();
        if (controlador.registraUsuari(info_registre) == 0) {
            login = info_registre.getNom();
            DBHelper.getInstance().registraUsuari(info_registre.getNom(), info_registre.getPassword(), info_registre.getCorreu());
            ooStream.writeInt(Comunicacio.OPERACIO_CORRECTE);
            ooStream.flush();
        } else {
            ooStream.writeInt(Comunicacio.ERROR_REGISTRE);
            ooStream.flush();
        }
    }

    /**
     * Métode que registra el thread que gestiona la partida, se le envian
     * las interecciones del cliente y se le pide el estado de la partida
     * @param partida
     * @param identificadorJugador
     */
    public void enregistraThreadPartida(ThreadPartida partida, IdentificadorJugador identificadorJugador ){
        this.partida = partida;
        this.identificadorJugador = identificadorJugador;
        partidaActivada = true;
    }

    /**
     * Libera el thread iniciado por el método enregistraThreadPartida
     */
    public void desenregistraThreadPartida(){
        partidaActivada = false;
    }

    /**
     * Devuelve el nombre del usuario
     * @return String nombre
     */
    public String getLogin(){ return login; }

    /**
     * Acaba la connexión con el cliente
     */
    public void expulsaClient(){
        try {
            ooStream.close();
            oiStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        flag = false;
    }
}
