package WzorceSklep.Data.Zamowienie;

import WzorceSklep.Data.Klient.Klient;
import WzorceSklep.Data.Pracownik.Pracownik;

/**
 * Created with IntelliJ IDEA.
 * User: KrzysztofD
 * Date: 16.05.13
 * Time: 14:53
 * To change this template use File | Settings | File Templates.
 */
public class ZamowienieKlienta extends Zamowienie
{
    public Klient zamawiajacy;
    public Pracownik tworzacy;

    public ZamowienieKlienta()
    {
        zamawiajacy=new Klient();
        tworzacy = new Pracownik();
    }
}
