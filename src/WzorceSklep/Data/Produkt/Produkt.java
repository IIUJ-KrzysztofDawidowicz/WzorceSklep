package WzorceSklep.Data.Produkt;

import WzorceSklep.DataEntity;

import java.math.BigDecimal;

/**
 * Created with IntelliJ IDEA.
 * User: KrzysztofD
 * Date: 16.05.13
 * Time: 14:51
 * To change this template use File | Settings | File Templates.
 */
public class Produkt implements DataEntity
{
    public int ID;
    public String nazwa;
    public String typ;
    public BigDecimal cena;
    public int ilosc;
    public String specyfikacja;

}
