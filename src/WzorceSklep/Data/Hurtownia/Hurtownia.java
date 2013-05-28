package WzorceSklep.Data.Hurtownia;

import WzorceSklep.DataEntity;

import java.math.BigDecimal;

/**
 * Created with IntelliJ IDEA.
 * User: KrzysztofD
 * Date: 16.05.13
 * Time: 14:43
 * To change this template use File | Settings | File Templates.
 */
public class Hurtownia implements DataEntity
{
    public int ID;
    public String nazwa;
    public String osobaKontaktowa;
    public BigDecimal telefon;
    public String mail;
    public AdresHurtowni adres;
}
