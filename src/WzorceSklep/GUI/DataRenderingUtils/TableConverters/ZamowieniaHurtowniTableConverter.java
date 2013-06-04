package WzorceSklep.GUI.DataRenderingUtils.TableConverters;

import WzorceSklep.Data.Zamowienie.ZamowienieHurtowni;
import WzorceSklep.GUI.DataRenderingUtils.AbstractTableConverter;

/**
 * Created with IntelliJ IDEA.
 * User: KrzysztofD
 * Date: 29.05.13
 * Time: 13:17
 * To change this template use File | Settings | File Templates.
 */
public class ZamowieniaHurtowniTableConverter extends AbstractTableConverter<ZamowienieHurtowni> {

    private final static String[] columnNames = new String[]{
            "Dostawca","Produkt","Typ","Ilość","Kwota","Data zamowienia","Data odebrania"
    };

    @Override
    protected Object[] mapDataToColumns(ZamowienieHurtowni zamowienie) {
        Object[] wynik = new Object[columnNames.length];

        wynik[0] = zamowienie.getZamawiajacy().getNazwa();
        wynik[1] = zamowienie.getProduktZamowiony().nazwa;
        wynik[2] = zamowienie.getProduktZamowiony().typ;
        wynik[3] = zamowienie.getIlosc();
        wynik[4] = zamowienie.getKwota();
        wynik[5] = zamowienie.getDataZamowienia();
        wynik[6] = zamowienie.getDataOdebrania();

        return wynik;
    }

    @Override
    protected String[] getColumnNames() {
        return columnNames;
    }
}
