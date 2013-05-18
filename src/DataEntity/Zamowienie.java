package DataEntity;

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
    BigDecimal kwota;
    Date dataZamowienia;
    Date dataOdebrania;
    public Dictionary<Produkt,Integer> produktyZamowione;
}
