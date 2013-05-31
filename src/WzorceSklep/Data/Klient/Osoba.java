package WzorceSklep.Data.Klient;

import WzorceSklep.Data.AdresOsoby;
import WzorceSklep.Data.ZAdresem;
import WzorceSklep.DataEntity;

import java.math.BigDecimal;

/**
 * Created with IntelliJ IDEA.
 * User: KrzysztofD
 * Date: 16.05.13
 * Time: 14:27
 * To change this template use File | Settings | File Templates.
 */
public abstract class Osoba implements DataEntity, ZAdresem<AdresOsoby>
{
    private int ID;
    private String imie;
    private String nazwisko;
    private BigDecimal telefon;
    private String mail;
    private AdresOsoby adres;

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getImie() {
        return imie;
    }

    public void setImie(String imie) {
        this.imie = imie;
    }

    public String getNazwisko() {
        return nazwisko;
    }

    public void setNazwisko(String nazwisko) {
        this.nazwisko = nazwisko;
    }

    public BigDecimal getTelefon() {
        return telefon;
    }

    public void setTelefon(BigDecimal telefon) {
        this.telefon = telefon;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public AdresOsoby getAdres() {
        return adres;
    }

    public void setAdres(AdresOsoby adres) {
        this.adres = adres;
    }

    @Override
    public String getName() {
        return imie + ' ' + nazwisko;
    }
}
