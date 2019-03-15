package Vista;


import javax.swing.*;

public class EstatVista {
    private static JFrame instanciaPrincipalJFrame = null;

    private EstatVista(){

    }

    public static JFrame getInstanciaPrincipalJFrame(){
        return instanciaPrincipalJFrame;
    }

    public static void setInstanciaPrincipalJFrame(JFrame instancia){
        instanciaPrincipalJFrame = instancia;
    }

}
