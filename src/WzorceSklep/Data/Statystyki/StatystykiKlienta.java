package WzorceSklep.Data.Statystyki;

import WzorceSklep.DataEntity;

import java.math.BigDecimal;

/**
 * Created with IntelliJ IDEA.
 * User: KrzysztofD
 * Date: 30.05.13
 * Time: 22:29
 * To change this template use File | Settings | File Templates.
 */
public class StatystykiKlienta implements DataEntity,Statystyki
{
    private String imie;

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getNazwisko() {
        return Nazwisko;
    }

    public void setNazwisko(String nazwisko) {
        Nazwisko = nazwisko;
    }

    public int getZamowienia() {
        return Zamowienia;
    }

    public void setZamowienia(int zamowienia) {
        Zamowienia = zamowienia;
    }

    public BigDecimal getKwota() {
        return kwota;
    }

    public void setKwota(BigDecimal kwota) {
        this.kwota = kwota;
    }

    private int ID;
    private String Nazwisko;
    private int Zamowienia;
    private BigDecimal kwota;

    public String getImie() {
        return imie;
    }

    public void setImie(String imie) {
        this.imie = imie;
    }
}
