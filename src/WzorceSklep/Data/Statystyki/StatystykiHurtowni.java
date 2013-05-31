package WzorceSklep.Data.Statystyki;

import java.math.BigDecimal;

/**
 * Created with IntelliJ IDEA.
 * User: KrzysztofD
 * Date: 30.05.13
 * Time: 22:37
 * To change this template use File | Settings | File Templates.
 */
public class StatystykiHurtowni implements Statystyki{
    private int ID;
    private String nazwa;
    private int zamowienia;
    private BigDecimal kwota;

    public int getID() {
        return ID;
    }

    public String getNazwa() {
        return nazwa;
    }

    public int getZamowienia() {
        return zamowienia;
    }

    public BigDecimal getKwota() {

        return kwota;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }

    public void setZamowienia(int zamowienia) {
        this.zamowienia = zamowienia;
    }

    public void setKwota(BigDecimal kwota) {
        this.kwota = kwota;
    }
}
