import Controlador.Controlador;
import Model.Model;
import Network.Network;
import Vista.Vista;

public class Main {
    public static void main(String[] args) {
        Model model = new Model();
        Vista vista = new Vista();
        Network network = new Network();

        Controlador controlador = new Controlador(vista,model,network);

        vista.enregistraControlador(controlador.getEscoltaAccionsVista());
        network.enregistraControlador(controlador);

        vista.setVisible(true);
    }
}
