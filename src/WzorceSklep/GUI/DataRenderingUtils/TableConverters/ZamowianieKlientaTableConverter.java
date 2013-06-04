package WzorceSklep.GUI.DataRenderingUtils.TableConverters;

import WzorceSklep.Data.Zamowienie.ZamowienieKlienta;
import WzorceSklep.GUI.DataRenderingUtils.AbstractTableConverter;

/**
 * Created with IntelliJ IDEA.
 * User: KrzysztofD
 * Date: 28.05.13
 * Time: 19:38
 * To change this template use File | Settings | File Templates.
 */
public class ZamowianieKlientaTableConverter extends AbstractTableConverter<ZamowienieKlienta> {
    private final static String[] columnNames = new String[]
            {"Klient","Produkt","Typ","Ilość","Data zamówienia","Data realizacji","Kwota","Pracownik"};

    @Override
    protected String[] getColumnNames() {
        return columnNames;
    }

    @Override
    protected Object[] mapDataToColumns(ZamowienieKlienta zamowienieKlienta) {
        Object[] wynik = new Object[getColumnNames().length];

        wynik[index.get("Klient")] = zamowienieKlienta.zamawiajacy.getName();
        wynik[index.get("Produkt")] = zamowienieKlienta.getProduktZamowiony().nazwa;
        wynik[index.get("Typ")] = zamowienieKlienta.getProduktZamowiony().typ;
        wynik[index.get("Ilość")] = zamowienieKlienta.getIlosc();
        wynik[index.get("Data zamówienia")] = zamowienieKlienta.getDataZamowienia();
        wynik[index.get("Data realizacji")] = zamowienieKlienta.getDataOdebrania();
        wynik[index.get("Kwota")] = zamowienieKlienta.getKwota();
        wynik[index.get("Pracownik")] = String.format("%s %s",
                zamowienieKlienta.tworzacy.getImie(), zamowienieKlienta.tworzacy.getNazwisko());

        return wynik;
    }
}
