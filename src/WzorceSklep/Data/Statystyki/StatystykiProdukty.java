package WzorceSklep.Data.Statystyki;

import WzorceSklep.DataEntity;

import java.math.BigDecimal;

/**
 * Created with IntelliJ IDEA.
 * User: KrzysztofD
 * Date: 30.05.13
 * Time: 16:08
 * To change this template use File | Settings | File Templates.
 */
public class StatystykiProdukty implements Statystyki
{

    private int id;
    private String nazwa;
    private int zamowienia;
    private int ilosc;
    private BigDecimal kwota;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNazwa() {
        return nazwa;
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }

    public int getZamowienia() {
        return zamowienia;
    }

    public void setZamowienia(int zamowienia) {
        this.zamowienia = zamowienia;
    }

    public int getIlosc() {
        return ilosc;
    }

    public void setIlosc(int ilosc) {
        this.ilosc = ilosc;
    }

    public BigDecimal getKwota() {
        return kwota;
    }

    public void setKwota(BigDecimal kwota) {
        this.kwota = kwota;
    }
}
