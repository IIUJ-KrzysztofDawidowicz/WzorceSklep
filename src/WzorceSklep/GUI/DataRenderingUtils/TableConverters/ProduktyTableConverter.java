package WzorceSklep.GUI.DataRenderingUtils.TableConverters;

import WzorceSklep.Data.Produkt.Produkt;
import WzorceSklep.GUI.DataRenderingUtils.AbstractTableConverter;

/**
 * Created with IntelliJ IDEA.
 * User: KrzysztofD
 * Date: 28.05.13
 * Time: 17:58
 * To change this template use File | Settings | File Templates.
 */
public class ProduktyTableConverter extends AbstractTableConverter<Produkt> {


    private final static String[] columnNames = new String[]{"ID", "Typ", "Cena", "Nazwa", "Ilosc", "Specyfikacja"};


    @Override
    protected Object[] mapDataToColumns(Produkt produkt) {
        Object[] wynik = new Object[columnNames.length];

        wynik[0] = produkt.ID;
        wynik[1] = produkt.typ;
        wynik[2] = produkt.cena;
        wynik[3] = produkt.nazwa;
        wynik[4] = produkt.ilosc;
        wynik[5] = produkt.specyfikacja;

        return wynik;
    }

    @Override
    protected String[] getColumnNames() {
        return columnNames;
    }
}
