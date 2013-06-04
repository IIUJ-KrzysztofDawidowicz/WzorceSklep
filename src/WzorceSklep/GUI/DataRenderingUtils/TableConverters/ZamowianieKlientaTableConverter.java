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
    private final static String[] columnNames = new String[]{"Klient","Produkt","Typ","Ilość","Data","Kwota","Pracownik"};

    @Override
    protected String[] getColumnNames() {
        return columnNames;
    }

    @Override
    protected Object[] mapDataToColumns(ZamowienieKlienta zamowienieKlienta) {
        Object[] wynik = new Object[getColumnNames().length];

        wynik[0] = zamowienieKlienta.zamawiajacy.getName();
        wynik[1] = zamowienieKlienta.getProduktZamowiony().nazwa;
        wynik[2] = zamowienieKlienta.getProduktZamowiony().typ;
        wynik[3] = zamowienieKlienta.getIlosc();
        wynik[4] = zamowienieKlienta.getDataZamowienia();
        wynik[5] = zamowienieKlienta.getKwota();
        wynik[6] = String.format("%s %s", zamowienieKlienta.tworzacy.getImie(), zamowienieKlienta.tworzacy.getNazwisko());

        return wynik;
    }
}
