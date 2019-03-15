package Controlador.InformationClasses.EstatJoc;

import java.awt.*;
import java.io.Serializable;

public class EstatTaulell implements Serializable {

    private Color[][] taulell;

    public EstatTaulell(Color[][] taulell){
        this.taulell = taulell;
    }

    public Color getColorCela (int x, int y) {
        return taulell[x][y];
    }

}
