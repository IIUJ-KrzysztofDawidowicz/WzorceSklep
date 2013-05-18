package DataEntity;

/**
 * Created with IntelliJ IDEA.
 * User: KrzysztofD
 * Date: 16.05.13
 * Time: 14:27
 * To change this template use File | Settings | File Templates.
 */
public abstract class Osoba implements DataEntity
{
    public String imie;
    public String nazwisko;
    public long telefon;
    public String mail;
    public AdresOsoby adres;
}
