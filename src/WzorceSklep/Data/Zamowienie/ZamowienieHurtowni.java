package WzorceSklep.Data.Zamowienie;

import WzorceSklep.Data.Hurtownia.Hurtownia;

/**
 * Created with IntelliJ IDEA.
 * User: KrzysztofD
 * Date: 16.05.13
 * Time: 14:49
 * To change this template use File | Settings | File Templates.
 */
public class ZamowienieHurtowni extends Zamowienie
{
    private Hurtownia zamawiajacy;

    public ZamowienieHurtowni()
    {
        setZamawiajacy(new Hurtownia());
    }

    public Hurtownia getZamawiajacy() {
        return zamawiajacy;
    }

    public void setZamawiajacy(Hurtownia zamawiajacy) {
        this.zamawiajacy = zamawiajacy;
    }
}
