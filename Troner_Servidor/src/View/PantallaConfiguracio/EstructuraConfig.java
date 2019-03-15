package View.PantallaConfiguracio;

public class EstructuraConfig {
    private int PortBBDD;
    private String DireccioIPBBDD;
    private String NomBBDD;
    private String UsuariBBDD;
    private String ContrasenyaBBDD;
    private int PortClients;

    public int getPortBBDD() {
        return PortBBDD;
    }

    public String getDireccioIPBBDD() {
        return DireccioIPBBDD;
    }

    public String getNomBBDD() {
        return NomBBDD;
    }

    public String getUsuariBBDD() {
        return UsuariBBDD;
    }

    public String getContrasenyaBBDD() {
        return ContrasenyaBBDD;
    }

    public int getPortClients() {
        return PortClients;
    }

    public void setPortClients(int portClients) {
        this.PortClients = portClients;
    }
}

