package WzorceSklep.Data.Pracownik;

import WzorceSklep.Data.Klient.Osoba;

public class Pracownik extends Osoba
{

    public Pracownik() {
    }

    public void setStatusWithString(String status) {
        if(status.equals("Admin"))
            this.status= Statusy.Admin;
        else if(status.equals("Hurtownik"))
            this.status = Statusy.Hurtownik;
        else if (status.equals("Sprzedawca"))
            this.status = Statusy.Sprzedawca;
    }

    public enum Statusy
    {
        Admin,
        Hurtownik,
        Sprzedawca
    }

    public String login;
    public String password;
    public String umowa;
    public Statusy status;
    public String getStatusString()
    {
        String status="";
        if(this.status== Pracownik.Statusy.Admin)
            status="Admin";
        else if(this.status == Pracownik.Statusy.Hurtownik)
            status="Hurtownik";
        else if (this.status == Pracownik.Statusy.Sprzedawca)
            status="Sprzedawca";
        return status;
    }

}
