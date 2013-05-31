package WzorceSklep.Data.Hurtownia;

import WzorceSklep.Data.ZAdresem;
import WzorceSklep.DataEntity;

import java.math.BigDecimal;

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
    private BigDecimal telefon;
    private String mail;
    private AdresHurtowni adres;

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
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
