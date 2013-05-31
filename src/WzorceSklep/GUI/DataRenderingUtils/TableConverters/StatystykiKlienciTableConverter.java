package WzorceSklep.GUI.DataRenderingUtils.TableConverters;

import WzorceSklep.Data.Statystyki.StatystykiKlienta;
import WzorceSklep.GUI.DataRenderingUtils.AbstractTableConverter;

/**
 * Created with IntelliJ IDEA.
 * User: KrzysztofD
 * Date: 30.05.13
 * Time: 22:27
 * To change this template use File | Settings | File Templates.
 */
public class StatystykiKlienciTableConverter extends AbstractTableConverter<StatystykiKlienta> {
    private static final String[] columnNames = new String[]{"ID", "Nazwisko", "Zamowienia", "Kwota"};

    @Override
    protected String[] getColumnNames() {
        return columnNames;
    }

    @Override
    protected Object[] mapDataToColumns(StatystykiKlienta object) {
        Object[] wynik = new Object[columnNames.length];

        wynik[index.get("ID")] = object.getID();
        wynik[index.get("Nazwisko")] = object.getNazwisko();
        wynik[index.get("Zamowienia")] = object.getZamowienia();
        wynik[index.get("Kwota")] = object.getKwota();

        return wynik;
    }
}
