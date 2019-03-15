package Controlador.InformationClasses.EstatPantallaPrincipal;

import java.util.Date;

public class RanquingDatat {
    private EstatRanking ranquings;
    private Date[][] datas;

    public EstatRanking getRanquings() {
        return ranquings;
    }

    public void setRanquings(EstatRanking ranquings) {
        this.ranquings = ranquings;
    }

    public Date[][] getDatas() {
        return datas;
    }

    public void setDatas(Date[][] datas) {
        this.datas = datas;
    }
}
