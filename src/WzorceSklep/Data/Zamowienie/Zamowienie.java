package WzorceSklep.Data.Zamowienie;

import WzorceSklep.DataEntity;
import WzorceSklep.Data.Produkt.Produkt;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.Dictionary;

/**
 * Created with IntelliJ IDEA.
 * User: KrzysztofD
 * Date: 16.05.13
 * Time: 14:45
 * To change this template use File | Settings | File Templates.
 */
public abstract class Zamowienie implements DataEntity
{
    public BigDecimal kwota;
    public Date dataZamowienia;
    public Date dataOdebrania;
    public Produkt produktZamowiony;
    public int ilosc;
    public int ID;
}
