package WzorceSklep.Data.Pracownik;

import WzorceSklep.Data.AdresOsoby;
import WzorceSklep.Data.Klient.Osoba;

public class Pracownik extends Osoba
{
    public Pracownik()
    {
        setAdres(new AdresOsoby());
    }

    public void setStatusWithString(String status) {
        if(status.equals("Admin"))
            this.setStatus(Statusy.Admin);
        else if(status.equals("Hurtownik"))
            this.setStatus(Statusy.Hurtownik);
        else if (status.equals("Sprzedawca"))
            this.setStatus(Statusy.Sprzedawca);
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUmowa() {
        return umowa;
    }

    public void setUmowa(String umowa) {
        this.umowa = umowa;
    }

    public Statusy getStatus() {
        return status;
    }

    public void setStatus(Statusy status) {
        this.status = status;
    }

    public enum Statusy
    {
        Admin,
        Hurtownik,
        Sprzedawca
    }

    private String login;
    private String password;
    private String umowa;
    private Statusy status;
    public String getStatusString()
    {
        String status="";
        if(this.getStatus() == Pracownik.Statusy.Admin)
            status="Admin";
        else if(this.getStatus() == Pracownik.Statusy.Hurtownik)
            status="Hurtownik";
        else if (this.getStatus() == Pracownik.Statusy.Sprzedawca)
            status="Sprzedawca";
        return status;
    }

}
