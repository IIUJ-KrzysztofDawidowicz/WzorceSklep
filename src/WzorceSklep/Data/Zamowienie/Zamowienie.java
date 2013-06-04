package WzorceSklep.Data.Zamowienie;

import WzorceSklep.Data.Produkt.Produkt;
import WzorceSklep.DataEntity;

import java.math.BigDecimal;
import java.sql.Date;

/**
 * Created with IntelliJ IDEA.
 * User: KrzysztofD
 * Date: 16.05.13
 * Time: 14:45
 * To change this template use File | Settings | File Templates.
 */
public abstract class Zamowienie implements DataEntity
{
    private BigDecimal kwota;
    private Date dataZamowienia;
    private Date dataOdebrania;
    private Produkt produktZamowiony;
    private int ilosc;
    private int ID;

    public Zamowienie()
    {
        setProduktZamowiony(new Produkt());
    }

    public BigDecimal getKwota() {
        return kwota;
    }

    public void setKwota(BigDecimal kwota) {
        this.kwota = kwota;
    }

    public Date getDataZamowienia() {
        return dataZamowienia;
    }

    public void setDataZamowienia(Date dataZamowienia) {
        this.dataZamowienia = dataZamowienia;
    }

    public Date getDataOdebrania() {
        return dataOdebrania;
    }

    public void setDataOdebrania(Date dataOdebrania) {
        this.dataOdebrania = dataOdebrania;
    }

    public Produkt getProduktZamowiony() {
        return produktZamowiony;
    }

    public void setProduktZamowiony(Produkt produktZamowiony) {
        this.produktZamowiony = produktZamowiony;
    }

    public int getIlosc() {
        return ilosc;
    }

    public void setIlosc(int ilosc) {
        this.ilosc = ilosc;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }
}
