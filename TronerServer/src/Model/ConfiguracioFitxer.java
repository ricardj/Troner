package Model;

import View.PantallaConfiguracio.EstructuraConfig;
import com.google.gson.Gson;

import java.io.*;

/**
 * Gestiona la información del fichero config.json tanto para lectura como para escritura.
 * @grupoC6
 */
public class ConfiguracioFitxer {

    /**
     * Reescribe el puerto de conexión con el servidor registrado en el fichero.
     * @param nou_port recibe el nuevo puerto.
     */
    public static void setNouPort(int nou_port){
        Gson gson = new Gson();
        BufferedWriter bw;
        try {
            EstructuraConfig dades = carregaConfig();
            bw = new BufferedWriter(new FileWriter("config.json"));
            dades.setPortClients(nou_port);
            String noves_dades = gson.toJson(dades);
            bw.write(noves_dades);
            bw.close();
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    /**
     * Carga los datos del fichero en una estructura de datos.
     * @return Estructura de datos del tipo EstructuraConfig con los datos que contiene el fichero.
     */
    public static EstructuraConfig carregaConfig(){
        String json = new String();
        String line;
        BufferedReader br;
        try {
            br = new BufferedReader(new FileReader("config.json"));
            while ((line = br.readLine()) != null) {
                json = json + line + "\n";
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        EstructuraConfig dades = (new Gson()).fromJson(json, EstructuraConfig.class);
        return dades;
    }

    /**
     * Retorna el puerto actual de conexión con el servidor.
     * @return un entero referente al puerto.
     */
    public static int getNouPort(){
        EstructuraConfig dades = carregaConfig();
        return dades.getPortClients();
    }
}
