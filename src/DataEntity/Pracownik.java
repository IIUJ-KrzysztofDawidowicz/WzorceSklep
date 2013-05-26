package DataEntity;

public class Pracownik extends Osoba
{

    public Pracownik() {
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

}
