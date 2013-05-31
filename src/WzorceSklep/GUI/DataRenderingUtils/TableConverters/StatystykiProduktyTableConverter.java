package WzorceSklep.GUI.DataRenderingUtils.TableConverters;

import WzorceSklep.Data.Statystyki.StatystykiProdukty;
import WzorceSklep.GUI.DataRenderingUtils.AbstractTableConverter;

import java.util.HashMap;

/**
 * Created with IntelliJ IDEA.
 * User: KrzysztofD
 * Date: 30.05.13
 * Time: 16:09
 * To change this template use File | Settings | File Templates.
 */
public class StatystykiProduktyTableConverter extends AbstractTableConverter<StatystykiProdukty>{
    private static final String[] colmunNames = new String[]{"ID", "Nazwa", "Zamowienia", "Ilość", "Kwota"};


    @Override
    protected Object[] mapDataToColumns(StatystykiProdukty object) {
        Object[] wynik = new Object[colmunNames.length];

        wynik[index.get("ID")] = object.getId();
        wynik[index.get("Nazwa")] = object.getNazwa();
        wynik[index.get("Zamowienia")] = object.getZamowienia();
        wynik[index.get("Ilość")] = object.getIlosc();
        wynik[index.get("Kwota")] = object.getKwota();

        return wynik;
    }

    @Override
    protected String[] getColumnNames() {
        return colmunNames;
    }
}
