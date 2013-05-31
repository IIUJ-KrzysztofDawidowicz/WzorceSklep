package WzorceSklep.Data;

import WzorceSklep.DataEntity;

/**
 * Created with IntelliJ IDEA.
 * User: KrzysztofD
 * Date: 16.05.13
 * Time: 14:30
 * To change this template use File | Settings | File Templates.
 */
public abstract class Adres implements DataEntity
{
    public int ID;
    public String ulica;
    public int nrDomu;
    public String kodPocztowy;
    public String miejscowosc;
    public String poczta;
    public String kraj;
}
