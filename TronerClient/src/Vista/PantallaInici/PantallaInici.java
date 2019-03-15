package Vista.PantallaInici;

import Vista.AccesibleContextSetter;
import Controlador.EscoltaAccionsVista;
import Vista.PantallaPare;
import Vista.Vista;

import javax.swing.*;
import java.awt.*;

public class PantallaInici extends PantallaPare implements AccesibleContextSetter{

    private static final float MIDA_TIPOGRAFIA = 20.0f;
    private static final String DIRECCIO_REGISTER_BUTTON = "imatges/";
    private static final String DIRECCIO_LOGIN_BUTTON = "imatges/";

    private static final int SEPARACIO_ENTRE_BOTONS = 20;

    public static final String BOTO_REGISTRARSE = "botoRegistrarse";
    public static final String BOTO_INICIAR_SESSIO = "botoIniciarSessio";


    private JButton botoRegistrarse;
    private JButton botoIniciarSessio;

    public PantallaInici(EscoltaAccionsVista escoltaAccionsVista){
        super(Vista.MIDA_X,Vista.MIDA_Y);

        getPanellIntermig().setLayout(new BoxLayout(getPanellIntermig(),BoxLayout.Y_AXIS));

        afegirBotons();

        setContextAccesible();
        registrarListenerBotons(escoltaAccionsVista);
    }

    private void registrarListenerBotons(EscoltaAccionsVista escoltaAccionsVista){
        botoIniciarSessio.addActionListener(escoltaAccionsVista);
        botoRegistrarse.addActionListener(escoltaAccionsVista);
    }

    private void afegirBotons(){

        Font fuente = Vista.getFontTitols().deriveFont(15.0f);

        botoIniciarSessio = new JButton("Iniciar Sessio");
        botoIniciarSessio.setFont(fuente);

        botoRegistrarse = new JButton("Registrarse");
        botoRegistrarse.setFont(fuente);

        botoIniciarSessio.setAlignmentX(Component.CENTER_ALIGNMENT);
        botoRegistrarse.setAlignmentX(Component.CENTER_ALIGNMENT);

        getPanellIntermig().add(Box.createRigidArea(new Dimension(1,SEPARACIO_ENTRE_BOTONS+40)));
        getPanellIntermig().add(botoIniciarSessio);
        getPanellIntermig().add(Box.createRigidArea(new Dimension(1,SEPARACIO_ENTRE_BOTONS)));
        getPanellIntermig().add(botoRegistrarse);
        getPanellIntermig().add(Box.createRigidArea(new Dimension(1,SEPARACIO_ENTRE_BOTONS)));

    }

    private void afegirEspaisBuits(){
        GridBagConstraints constraints = new GridBagConstraints();
    }


    @Override
    public void setContextAccesible(){
        botoIniciarSessio.getAccessibleContext().setAccessibleName(Integer.toString(Vista.PANTALLA_INICI));
        botoIniciarSessio.getAccessibleContext().setAccessibleDescription(BOTO_INICIAR_SESSIO);

        botoRegistrarse.getAccessibleContext().setAccessibleName(Integer.toString(Vista.PANTALLA_INICI));
        botoRegistrarse.getAccessibleContext().setAccessibleDescription(BOTO_REGISTRARSE);
    }


}
