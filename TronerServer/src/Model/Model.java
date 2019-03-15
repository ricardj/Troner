package Model;

import Controlador.InformationClasses.EstatPantallaPrincipal.HistoricJugador;
import Controlador.InformationClasses.EstatPantallaPrincipal.RanquingDatat;
import Controlador.InformationClasses.IntroInformation.InformacioLogin;
import Controlador.InformationClasses.IntroInformation.InformacioRegistre;

import java.sql.SQLException;
import java.util.LinkedList;

/**
 * Clase principal del modelo, gestiona la información.
 *
 * @author grupoC6
 */
public class Model {
    private LinkedList<LinkedList> usuaris;

    /**
     * Hace de puente con la base de datos para guardar la información de un nuevo usuario registrado.
     * @param dades recibe los datos del nuevo usuario a registrar.
     * @return retorna un entero con información sobre como ha ido el registro:
     *  0 = tod0 correcto
     *  -1 = error correo
     *  1 = error login
     *  2 = error campos
     *  3 = error contraseña
     */
    public int registraUsuari(InformacioRegistre dades){
        return DBHelper.getInstance().registraUsuari(dades.getNom(), dades.getPassword(), dades.getCorreu());
    }

    /**
     * Hace las comprobaciones necesarias en la base de datos para que un usuario pueda hacer login.
     * @param dades recibe los datos del usuario que intenta iniciar sesión.
     * @return
     */
    // codi_error = 0 -> NO HI HA ERRORS
    // codi_error = 1 -> ERROR CONTRASENYA
    // codi_error = 2 -> ERROR LOGIN
    public int loginUsuari(InformacioLogin dades) {
        int codi_error;
        if(DBHelper.getInstance().existeixLogin(dades.getUsuari())){
            if(DBHelper.getInstance().contrasenyaCorrecta(dades.getUsuari(), dades.getContrasenya())){
                codi_error = 0;
            } else {
                codi_error = 1;
            }
        } else {
            codi_error = 2;
        }
        return codi_error;
    }

    /**
     * Este metodo extrae los usuarios con su nickname, hora de ultima conexion y  puntuacion de la base de datos
     * @return retorna toda la informacion en un Object[][]
     */
    public Object[][] agafaUsuaris(){
        usuaris = DBHelper.getInstance().getUsuaris();
        Object[][] dades = new Object[usuaris.get(0).size()][4];
        for(int i = 0; i < usuaris.get(0).size(); i++){
            dades[i][0] = usuaris.get(0).get(i);
            dades[i][1] = usuaris.get(1).get(i);
            dades[i][2] = usuaris.get(2).get(i);
            dades[i][3] = usuaris.get(3).get(i);
        }
        return dades;
    }

    /**
     * Elimina un usuario de la base de datos a partir de su indice en la estructura de datos.
     * @param a_eliminar indice del usuario a eliminar
     */
    public void eliminaUsuari(int a_eliminar){
        DBHelper.getInstance().eliminaUsuari((String) usuaris.get(0).get(a_eliminar));
    }

    /**
     * Extrae los datos que se muestran en la grafica de cada usuario, estos son, nombre y puntuaciones acumuladas.
     * @param mode_joc recibe el modo de juego
     * @return retorna la estructura de datos en un HsitoricJugador[]
     */
    public HistoricJugador[] agafaDadesGrafic(int mode_joc){
        return DBHelper.getInstance().infoGrafiques(mode_joc);
    }

    /**
     * Extrae los rankings que verá el servidor de los usuarios usando la base de datos.
     * @return retorna la clase RanquingDatat, la cual contiene un ranking con los usuarios y su puntuación
     * para cada modo de juego además de las fechas de ultima conexión.
     */
    public RanquingDatat agafaRanquings(){
        RanquingDatat ranquings = new RanquingDatat();
        try {
            ranquings.setRanquings(DBHelper.getInstance().obtenirRanking());
            ranquings.setDatas(DBHelper.getInstance().obtenirDatas());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ranquings;
    }

    public void desconnectaBBDD() {
        DBHelper.getInstance().desconnectaDB();
    }
}
