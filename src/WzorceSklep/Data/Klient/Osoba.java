package WzorceSklep.Data.Klient;

import WzorceSklep.Data.AdresOsoby;
import WzorceSklep.DataEntity;

import java.math.BigDecimal;

/**
 * Created with IntelliJ IDEA.
 * User: KrzysztofD
 * Date: 16.05.13
 * Time: 14:27
 * To change this template use File | Settings | File Templates.
 */
public abstract class Osoba implements DataEntity
{
    public int ID;
    public String imie;
    public String nazwisko;
    public BigDecimal telefon;
    public String mail;
    public AdresOsoby adres;
}
