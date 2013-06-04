package WzorceSklep.Data.Hurtownia;

import WzorceSklep.Data.ZAdresem;
import WzorceSklep.DataEntity;

/**
 * Created with IntelliJ IDEA.
 * User: KrzysztofD
 * Date: 16.05.13
 * Time: 14:43
 * To change this template use File | Settings | File Templates.
 */
public class Hurtownia implements DataEntity, ZAdresem<AdresHurtowni> {
    private int ID;
    private String nazwa;
    private String osobaKontaktowa;
    private String telefon;
    private String mail;
    private AdresHurtowni adres;

    public Hurtownia()
    {
        adres = new AdresHurtowni();
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
        adres.setID(ID);
    }

    public String getNazwa() {
        return nazwa;
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }

    public String getOsobaKontaktowa() {
        return osobaKontaktowa;
    }

    public void setOsobaKontaktowa(String osobaKontaktowa) {
        this.osobaKontaktowa = osobaKontaktowa;
    }

    public String getTelefon() {
        return telefon;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    @Override
    public AdresHurtowni getAdres() {
        return adres;
    }

    @Override
    public void setAdres(AdresHurtowni adres) {
        this.adres = adres;
    }

    @Override
    public String getName() {
        return getNazwa();
    }
}
