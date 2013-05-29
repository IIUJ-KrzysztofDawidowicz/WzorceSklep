package WzorceSklep.GUI.DataRenderingUtils.TableConverters;

import WzorceSklep.Data.Klient.Klient;
import WzorceSklep.GUI.DataRenderingUtils.AbstractTableConverter;

import java.util.HashMap;
import java.util.Map;

/**
 * Konwertuje listę obiektów Klienci na TableModel wyświetlany przez panel Klienci.
 */
public class KlienciTableConverter extends AbstractTableConverter<Klient>
{

    private final static String[] columnNames;
    private final static Map<String,Integer> index;

    static {
        columnNames = new String[]{"ID", "Nazwisko", "Mail", "Telefon"};
        index = new HashMap<String, Integer>();
        for (int i = 0; i < columnNames.length; i++) {
            index.put(columnNames[i], i);
        }
    }

    protected Object[] mapDataToColumns(Klient klient) {
        Object[] wynik = new Object[columnNames.length];
        wynik[index.get("ID")] = klient.ID;
        wynik[index.get("Nazwisko")] = String.format("%s %s", klient.imie, klient.nazwisko);
        wynik[index.get("Mail")] = klient.mail;
        wynik[index.get("Telefon")] = klient.telefon;
        return wynik;
    }

    @Override
    protected String[] getColumnNames() {
        return columnNames;
    }
}
