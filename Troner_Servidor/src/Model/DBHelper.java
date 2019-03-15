package Model;


import Controlador.InformationClasses.EstatPantallaPrincipal.EstatConfiguracio;
import Controlador.InformationClasses.EstatPantallaPrincipal.EstatRanking;
import Controlador.InformationClasses.EstatPantallaPrincipal.HistoricJugador;
import Controlador.InformationClasses.EstatPantallaPrincipal.InfoJugador;
import Controlador.InformationClasses.IntroInformation.InformacioLogin;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.LinkedList;

/**
 * Permite la gestion de información a partir de la base de datos.
 * @author grupoC6
 */
public class DBHelper {
    private static DBHelper instance;
    private ResultSet consulta;
    private ConectorDB conn;

    /**
     * Constructor sin parametros de la clase, realiza la conexión con la base de datos.
     */
    private DBHelper() {
        //Enviem a la nova instància ConectorDB usuari, password, BBDD i port per iniciar els paràmetres de connexió
        conn = new ConectorDB( "root", "root", "dpo_troner", 3307);
        //Ens connectem a la BBDD
        conn.connect();
    }

    /**
     * Realiza la desconexión de la base de datos
     */
    public void desconnectaDB(){
        conn.disconnect();
    }

    /**
     * Permite instanciar la clase sin necesidad de crear una variable.
     * @return una instancia de la clase.
     */
    public static DBHelper getInstance() {
        if (instance == null) {
            instance = new DBHelper();
        }
        return instance;
    }

    /**
     * Registra a un usuario en la base de datos.
     * @param nom nombre del usuario a registrar.
     * @param contrasenya contrseña del usuario a registrar
     * @param correu correo del usuario a registrar
     * @return retorna un entero que informa sobre un posible error siguiendo el código:
     *  0 = tod0 correcto
     *  -1 = error en el mail
     *  1 = error en el login
     *  2 = error en los campos
     *  3 = error en la contraseña
     */
    public int registraUsuari(String nom, String contrasenya, String correu) {
        String t_turbo = "";
        if(contrasenya != null) {
            if (!contrasenya.equals("") & !correu.equals("") & !nom.equals("")) {
                if (!existeixLogin(nom)) {
                    if(correu.indexOf('@') > 0) {
                        conn.insertQuery("INSERT INTO Usuari (nom, contrasenya, data_inici, data_ultim_acces, correu, t_turbo) VALUES ('" + nom + "', '" + contrasenya + "', CURDATE(), CURDATE(), '" + correu + "', '" + t_turbo + "')");
                        return 0; //Tod0 correcto
                    } else{
                        return -1;//Error en el mail
                    }
                } else {
                    return 1;//Error en el login
                }
            } else {
                return 2;//Error campos
            }
        } else{
            return 3;//Error contraseña
        }
    }

    /**
     * Comprueba si un login existe o no en la base de datos
     * @param login login del usuario a comprobar.
     * @return Booleano que informa true si existe y false si no existe.
     */
    public boolean existeixLogin (String login){
        String login_obtingut;
        try {
            consulta = conn.selectQuery( "SELECT nom FROM usuari WHERE nom LIKE '" + login + "'" );
            //Recorrem el ResultSet que ens retorna el selectQuery i agafem els paràmetres desitjats
            while (consulta.next()) {
                //Passem a String la consulta
                login_obtingut = (String) consulta.getObject( "nom" );
                if (login_obtingut.equals(login)) {
                    return true;
                }
            }

            consulta = conn.selectQuery( "SELECT correu FROM usuari WHERE correu LIKE '" + login + "'" );
            //Recorrem el ResultSet que ens retorna el selectQuery i agafem els paràmetres desitjats
            while (consulta.next()) {
                //Passem a String la consulta
                login_obtingut = (String) consulta.getObject( "correu" );
                if (login_obtingut.equals(login)) {
                    return true;
                }
            }

            return false;

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            System.out.println( "Problema al recuperar les dades..." );
            return false;
        } catch (NullPointerException e) {
            System.out.println( "Problema al recuperar les dades..." );
            return false;
        }
    }

    /**
     * Extrae la estructura de los usuarios desde la base de datos para su gestion desde el servidor
     * @return retorna una LinkedList de usuarios donde cada uno tiene LinkedList con sus datos.
     */
    public LinkedList<LinkedList> getUsuaris(){
        LinkedList<LinkedList> usuarisServidor = new LinkedList<>();
        LinkedList<String> noms = new LinkedList<>();
        LinkedList<Integer> punts = new LinkedList<>();
        LinkedList<Date> inici = new LinkedList<>();
        LinkedList<Date> ultim= new LinkedList<>();
        try {
            consulta = conn.selectQuery("SELECT nom, SUM(puntuacio), data_inici, data_ultim_acces FROM usuari NATURAL JOIN juga GROUP BY nom");
            while (consulta.next()) {
                noms.add((String) consulta.getObject("nom"));
                punts.add((int) Long.parseLong(consulta.getObject("SUM(puntuacio)").toString()));
                inici.add((Date) consulta.getObject("data_inici"));
                ultim.add((Date) consulta.getObject("data_ultim_acces"));
            }

            consulta = conn.selectQuery("SELECT nom, data_inici, data_ultim_acces FROM usuari WHERE nom NOT IN (SELECT nom FROM usuari NATURAL JOIN juga GROUP BY nom) GROUP BY nom");
            while (consulta.next()) {
                noms.add((String) consulta.getObject("nom"));
                punts.add(null);
                inici.add((Date) consulta.getObject("data_inici"));
                ultim.add((Date) consulta.getObject("data_ultim_acces"));
            }

            usuarisServidor.add(noms);
            usuarisServidor.add(punts);
            usuarisServidor.add(inici);
            usuarisServidor.add(ultim);
            return usuarisServidor;
        }catch(SQLException e){
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Elimina un usuario de la base de datos.
     * @param nom login del usuario a eliminar.
     */
    public void eliminaUsuari(String nom){
        conn.deleteQuery("DELETE FROM usuari WHERE nom = \"" + nom + "\"");
    }

    /**
     * Comprueba que el login introducido sea un login y no un correo.
     * @param login login introducido.
     * @return true si es un login y false si es un correo.
     */
    public boolean decideixLogin (String login){
        String login_obtingut;
        try {
            consulta = conn.selectQuery( "SELECT nom FROM usuari WHERE nom LIKE '" + login + "'" );
            consulta.next();

            login_obtingut = (String) consulta.getObject( "nom" );
            if (login_obtingut.equals(login)) {
                return true;
            }

            consulta = conn.selectQuery( "SELECT correu FROM usuari WHERE correu LIKE '" + login + "'" );
            consulta.next();
            login_obtingut = (String) consulta.getObject( "correu" );
            if (login_obtingut.equals(login)) {
                return false;
            }
            //Mai arribarà aquí
            return true;

        } catch (SQLException e) {
            System.out.println( "Problema al recuperar les dades..." );
            return false;
        }
    }

    /**
     * Cnvia los controles asignados a un usuario en la base de datos.
     * @param login login del usuario a modificar.
     * @param t_amunt nueva tecla NORD
     * @param t_avall nueva tecla SUD
     * @param t_esquerra nueva tecla OEST
     * @param t_dreta nueva tecla EST
     * @param t_turbo nueva tecla TURBO
     * @return
     */
    public boolean canviaControls(String login, char t_amunt, char t_avall, char t_esquerra, char t_dreta, char t_turbo) {
        boolean error = true;
        String login_usuari;
        boolean esNom = true;
        boolean existeix;

        existeix = existeixLogin( login );
        System.out.println("Existeix: "+existeix);
        if (existeix) {
            if (esNom) {
                try {
                    consulta = conn.selectQuery( "SELECT nom FROM usuari WHERE nom LIKE '" + login + "';" );
                    //Recorrem el ResultSet que ens retorna el selectQuery i agafem els paràmetres desitjats
                    while (consulta.next()) {
                        //Passem a String la consulta
                        login_usuari = (String) consulta.getObject( "nom" );
                        if (login.equals( login_usuari )) {
                            //Modifiquem controls de l'usuari a la BBDD
                            conn.insertQuery( "UPDATE Usuari SET t_amunt= '" + t_amunt + "' WHERE nom LIKE '" + login + "'" );
                            conn.insertQuery( "UPDATE Usuari SET t_avall= '" + t_avall + "' WHERE nom LIKE '" + login + "'" );
                            conn.insertQuery( "UPDATE Usuari SET t_esquerra= '" + t_esquerra + "' WHERE nom LIKE '" + login + "'" );
                            conn.insertQuery( "UPDATE Usuari SET t_dreta= '" + t_dreta + "' WHERE nom LIKE '" + login + "'" );
                            conn.insertQuery( "UPDATE Usuari SET t_turbo= '" + t_turbo + "' WHERE nom LIKE '" + login + "'" );
                            error = false;
                            return error;
                        }
                    }

                    System.out.println( "Error, l'usuari no existeix" );

                } catch (SQLException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                    System.out.println( "Problema al recuperar les dades..." );
                }
            } else {
                try {
                    consulta = conn.selectQuery( "SELECT correu FROM usuari WHERE correu LIKE '" + login + "'" );
                    //Recorrem el ResultSet que ens retorna el selectQuery i agafem els paràmetres desitjats
                    while (consulta.next()) {
                        //Passem a String la consulta
                        login_usuari = (String) consulta.getObject( "correu" );
                        if (login.equals( login_usuari )) {
                            //Modifiquem controls de l'usuari a la BBDD
                            conn.insertQuery( "UPDATE Usuari SET t_amunt= '" + t_amunt + "' WHERE correu LIKE '" + login_usuari + "'" );
                            conn.insertQuery( "UPDATE Usuari SET t_avall= '" + t_avall + "' WHERE correu LIKE '" + login_usuari + "'" );
                            conn.insertQuery( "UPDATE Usuari SET t_esquerra= '" + t_esquerra + "' WHERE correu LIKE '" + login_usuari + "'" );
                            conn.insertQuery( "UPDATE Usuari SET t_dreta= '" + t_dreta + "' WHERE correu LIKE '" + login_usuari + "'" );
                            conn.insertQuery( "UPDATE Usuari SET t_turbo= '" + t_turbo + "' WHERE correu LIKE '" + login_usuari + "'" );
                            error = false;
                            return error;
                        }
                    }

                    System.out.println( "Error, l'usuari no existeix" );

                } catch (SQLException e) {
                    // TODO Auto-generated catch block
                    System.out.println( "Problema al recuperar les dades..." );
                }


            }
        }
        return error;
    }

    /**
     * Comprueba si la contraseña es correcta.
     * @param usuari usuario al que corresponde la contraseña.
     * @param contrasenya contraseña introducida.
     * @return booleano que informa si es correcta o no la contraseña. True = correcta.
     */
    public boolean contrasenyaCorrecta (String usuari, String contrasenya){
        InformacioLogin dades = new InformacioLogin(usuari, contrasenya);
        String login = getLoginBBDD(usuari);
        consulta = conn.selectQuery("SELECT contrasenya FROM usuari WHERE nom LIKE '" + login + "'");
        try {
            while(consulta.next()) {
                String password = consulta.getObject("contrasenya").toString();
                if (password.equals(contrasenya)) {
                    return true;
                } else {
                    return false;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return false;
    }

    /**
     * Obtiene el ranking de la base de datos mediante querys.
     * @return retorna la estructura EstatRanking con la información requerida por los rankings de los 3 modos de juego.
     * @throws SQLException lanza si hay algun error de SQL.
     */
    public synchronized EstatRanking obtenirRanking() throws SQLException {
        EstatRanking rankings = new EstatRanking();

        consulta = conn.selectQuery("SELECT nom, SUM(puntuacio) AS sum FROM juga NATURAL JOIN partida WHERE mode_joc = 1 GROUP BY nom ORDER BY sum DESC LIMIT 10");
        while(consulta.next()){
            InfoJugador info = new InfoJugador((String) consulta.getObject("nom"), Long.parseLong(consulta.getObject("sum").toString()));
            rankings.afegirJugadorRankingX2(info);
        }

        consulta = conn.selectQuery("SELECT nom, SUM(puntuacio) AS sum FROM juga NATURAL JOIN partida WHERE mode_joc = 2 GROUP BY nom ORDER BY sum DESC LIMIT 10");
        while(consulta.next()){
            InfoJugador info = new InfoJugador((String) consulta.getObject("nom"), Long.parseLong(consulta.getObject("sum").toString()));
            rankings.afegirJugadorRankingX4(info);
        }

        consulta = conn.selectQuery("SELECT nom, SUM(puntuacio) AS sum FROM juga NATURAL JOIN partida WHERE mode_joc = 3 GROUP BY nom ORDER BY sum DESC LIMIT 10");
        while(consulta.next()){
            InfoJugador info = new InfoJugador((String) consulta.getObject("nom"), Long.parseLong(consulta.getObject("sum").toString()));
            rankings.afegirJugadorRankingTorneig(info);
        }

        return rankings;
    }

    /**
     * Extrae las fechas de ultimo acceso de los usuarios de la base de datos.
     * @return retorna la estructura Date[][] con todas las fechas.
     * @throws SQLException lanza si hay algun error de SQL.
     */
    public Date[][] obtenirDatas() throws SQLException {
        Date[][] datas = new Date[3][10];
        int i = 0;
        consulta = conn.selectQuery("SELECT data_ultim_acces, SUM(puntuacio) FROM juga AS j NATURAL JOIN partida NATURAL JOIN usuari WHERE mode_joc = 1 GROUP BY j.nom ORDER BY SUM(puntuacio) DESC LIMIT 10");
        while(consulta.next()){
            datas[0][i] = (Date) consulta.getObject("data_ultim_acces");
            i++;
        }

        i = 0;
        consulta = conn.selectQuery("SELECT data_ultim_acces, SUM(puntuacio) FROM juga AS j NATURAL JOIN partida NATURAL JOIN usuari WHERE mode_joc = 2 GROUP BY j.nom ORDER BY SUM(puntuacio) DESC LIMIT 10");
        while(consulta.next()){
            datas[1][i] = (Date) consulta.getObject("data_ultim_acces");
            i++;
        }

        i = 0;
        consulta = conn.selectQuery("SELECT data_ultim_acces, SUM(puntuacio) FROM usuari NATURAL JOIN juga AS j NATURAL JOIN partida WHERE mode_joc = 3 GROUP BY j.nom ORDER BY SUM(puntuacio) DESC LIMIT 10");
        while(consulta.next()){
            datas[2][i] = (Date) consulta.getObject("data_ultim_acces");
            i++;
        }

        return datas;
    }

    /**
     * Extrae la configuración de teclado guardada en la base de datos para un usuario.
     * @param login login dle usuario del que se quiere la configuración de teclado.
     * @return información de la configuración en la estructura EstatConfiguracio.
     */
    public synchronized EstatConfiguracio obtenirConfiguracio(String login){
        if (existeixLogin(login)){
            consulta = conn.selectQuery("SELECT t_amunt, t_avall, t_esquerra, t_dreta FROM Usuari WHERE nom LIKE '" + login + "'");
            try {
                consulta.next();
                EstatConfiguracio config = new EstatConfiguracio(((String)consulta.getObject("t_amunt")).charAt(0), ((String)consulta.getObject("t_avall")).charAt(0), ((String)consulta.getObject("t_dreta")).charAt(0), ((String)consulta.getObject("t_esquerra")).charAt(0));
                return config;
            } catch (SQLException e) {
                e.printStackTrace();
                return null;
            }
        }
        return null;
    }

    /**
     * Añade una nueva partida en la base de datos en el modo de juego indicado.
     * @param mode_joc modo de juego del que se quiere guardar una nueva partida.
     */
    public void crearPartida(int mode_joc){
        conn.insertQuery( "INSERT INTO Partida (mode_joc) VALUES ("+mode_joc+")");
    }

    /**
     * Añade nueva información sobre un jugador sobre su ultima partida jugada.
     * @param nom nombre del usuario que ha jugado la partida.
     * @param puntuacio puntuación obtenida por el usuario en la partida.
     * @throws SQLException
     */
    public void crearJuga (String nom, long puntuacio ) throws SQLException {
        consulta = conn.selectQuery( "SELECT correu FROM Usuari WHERE nom LIKE '" + nom + "'" );
        consulta.next();
        String correu = (String) consulta.getObject( "correu" );
        consulta = conn.selectQuery( "SELECT id_partida FROM Partida WHERE id_partida >= ALL (SELECT id_partida FROM Partida)" );
        consulta.next();
        long id_actual = (long) consulta.getObject( "id_partida" );
        System.out.println("id més gran: "+id_actual);
        conn.insertQuery( "INSERT INTO Juga (id_partida, nom, correu , puntuacio, data_partida) VALUES ("+id_actual+", '"+nom+"', '"+correu+"', "+puntuacio+", CURDATE())" );
    }

    /**
     * Actualiza la fecha de última conexión del usuario a la base de datos.
     * @param login login del usuario del que queremos actualizar la fecha
     * @return retorna un Boolean que ifnorma sobre si ha ido bien la acutalización de fecha.
     */
    public synchronized void actualitzaData (String login){
        if (existeixLogin(login)) {
            if (decideixLogin(login)) {
                conn.insertQuery( "UPDATE Usuari SET data_ultim_acces= CURDATE() WHERE nom LIKE '" + login + "'" );
            }else {
                conn.insertQuery( "UPDATE Usuari SET data_ultim_acces= CURDATE() WHERE correu LIKE '" + login + "'" );
            }
        }
        System.out.println("Error, l'usuari no es troba registrat");
    }

    /**
     * Retorna el Nickname de un usuario a partir de lo que el haya introducido como login.
     * @param infoLogin información introducida como login por el usuario
     * @return retorna String con el Nickname del usuario
     */
    public String getLoginBBDD(String infoLogin){
        if(infoLogin.indexOf('@') > 0) {
            try {
                consulta = conn.selectQuery("SELECT nom FROM Usuari WHERE correu LIKE '" + infoLogin + "'");
                consulta.next();
                return (String) consulta.getObject("nom");
            } catch (SQLException e) {
                System.out.println("Error a la base de dades.");
                return null;
            }
        } else {
            return infoLogin;
        }
    }

    /**
     * Calcula el total de puntos de un usuario en un modo de juego.
     * @param mode_joc indica el modo de juego del que queremos calcular el total de puntos.
     * @param usuari indica el usuario del que queremos calcular el total de puntos.
     * @return un entero que contiene los puntos totales en el modo de juego.
     * @throws SQLException
     */
    public int puntsTotals(int mode_joc, String usuari) throws SQLException {
        int punts;
        consulta = conn.selectQuery("SELECT SUM(puntuacio) AS sum FROM juga NATURAL JOIN partida WHERE nom LIKE '" + usuari + "' AND mode_joc = " + mode_joc);
        consulta.next();
        Object aux =consulta.getObject("sum");
        if(aux == null){
            punts = 0;
            return punts;
        }else {
            int punts_totals = (int) Long.parseLong(aux.toString());
            if(punts_totals < 0){
                punts = 0;
                return punts;
            } else {
                punts = punts_totals;
                return punts;
            }
        }
    }

    /**
     * Calcula la progresión de puntos que ha seguido un jugador desde su ingreso en la base de datos.
     * @param login login del jugador del que queremos saber la progresión.
     * @param mode_joc modo de juego del que queremos saber la progresión.
     * @return Array de enteros con la progresión de puntos.
     * @throws SQLException lanza si hay algun error SQL.
     */
    public int[] obtenirPuntuacio (String login, int mode_joc) throws SQLException {
        int[] puntuacio;
        int punts_acumulats = 0;
        login = getLoginBBDD(login);
        ResultSet consulta = conn.selectQuery("SELECT COUNT(puntuacio) FROM juga NATURAL JOIN partida WHERE nom LIKE '" + login + "' AND mode_joc = " + mode_joc);
        consulta.next();
        puntuacio = new int[(int) Long.parseLong(consulta.getObject("COUNT(puntuacio)").toString()) + 1];
        consulta = conn.selectQuery("SELECT puntuacio FROM juga NATURAL JOIN partida WHERE nom LIKE '" + login + "' AND mode_joc = " + mode_joc);
        puntuacio[0] = 0;
        int i = 1;
        while(consulta.next()) {
            punts_acumulats += (int) consulta.getObject("puntuacio");
            puntuacio[i] = punts_acumulats;
            i++;
        }
        return puntuacio;
    }

    /**
     * Extrae la inforación necesária para mostrar en las graficas de progresión de cada jugador.
     * @param mode_joc modo de juego del que se quiere extraer la progresión.
     * @return estructura HistoricJugador[] con la información de cada jugador en el modo de juego indicado.
     */
    public HistoricJugador[] infoGrafiques(int mode_joc) {
       HistoricJugador[] dades;
        try {
            consulta = conn.selectQuery("SELECT COUNT(DISTINCT nom) FROM juga NATURAL JOIN partida WHERE mode_joc = " + mode_joc);
            consulta.next();
            dades = new HistoricJugador[(int) (Long.parseLong(consulta.getObject("COUNT(DISTINCT nom)").toString()))];

            consulta = conn.selectQuery("SELECT DISTINCT nom FROM juga NATURAL JOIN partida WHERE mode_joc = " + mode_joc);
            int i = 0;
            while(consulta.next()){
                String nom = (String) consulta.getObject("nom");
                int[] punts = obtenirPuntuacio(nom, mode_joc);
                if(punts[punts.length - 1] != 0){
                    dades[i] = new HistoricJugador(nom, punts);
                    i++;
                }
            }
            return dades;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}

