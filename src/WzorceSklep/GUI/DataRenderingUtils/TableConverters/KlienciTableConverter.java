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

    static {
        columnNames = new String[]{"ID", "Nazwisko", "Mail", "Telefon"};
    }

    protected Object[] mapDataToColumns(Klient klient) {
        Object[] wynik = new Object[columnNames.length];
        wynik[index.get("ID")] = klient.getID();
        wynik[index.get("Nazwisko")] = String.format("%s %s", klient.getImie(), klient.getNazwisko());
        wynik[index.get("Mail")] = klient.getMail();
        wynik[index.get("Telefon")] = klient.getTelefon();
        return wynik;
    }

    @Override
    protected String[] getColumnNames() {
        return columnNames;
    }
}
