package WzorceSklep.GUI.DataRenderingUtils.TableConverters;

import WzorceSklep.Data.Statystyki.StatystykiHurtowni;
import WzorceSklep.GUI.DataRenderingUtils.AbstractTableConverter;

/**
 * Created with IntelliJ IDEA.
 * User: KrzysztofD
 * Date: 30.05.13
 * Time: 22:36
 * To change this template use File | Settings | File Templates.
 */
public class StatystykiHurtownieTableConverter extends AbstractTableConverter<StatystykiHurtowni> {
    private static final String[] columnNames = new String[]{"ID", "Nazwa", "Zamowienia", "Kwota"};

    @Override
    protected Object[] mapDataToColumns(StatystykiHurtowni object) {
        Object[] wynik = new Object[columnNames.length];

        wynik[index.get("ID")] = object.getID();
        wynik[index.get("Nazwa")] = object.getNazwa();
        wynik[index.get("Zamowienia")] = object.getZamowienia();
        wynik[index.get("Kwota")] = object.getKwota();

        return wynik;
    }

    @Override
    protected String[] getColumnNames() {
        return columnNames;
    }
}
