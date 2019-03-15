import Controlador.Controlador;
//import Model.DBHelper;
import Controlador.ControladorComboBox;
import View.Vista;

public class Main {
    public static void main(String[] args) {
        Vista vista = new Vista();
        Controlador controlador = new Controlador(vista);
        ControladorComboBox controladorComboBox = new ControladorComboBox(vista);
        vista.setVisible(true);
        vista.registraControladorComboBox(controladorComboBox);
        vista.registraControlador(controlador);
    }
}
