package DataEntity;

import sun.security.util.Password;

public class Pracownik extends Osoba
{
    public Pracownik(String login, Password password, String umowa, Statusy status) {
        this.login = login;
        this.password = password;
        this.umowa = umowa;
        this.status = status;
    }

    public Pracownik() {
    }

    public enum Statusy
    {
        Admin,
        Hurtownik,
        Sprzedawca
    }

    public String login;
    public Password password;
    public String umowa;
    public Statusy status;

}
