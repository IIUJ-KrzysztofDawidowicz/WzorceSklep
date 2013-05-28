package WzorceSklep.GUI.Actions.RefreshTableActions.TableConverters;

import WzorceSklep.Data.Klient.Klient;
import WzorceSklep.GUI.Actions.RefreshTableActions.AbstractTableConverter;

import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.util.HashMap;
import java.util.Map;

/**
 * Konwertuje listę obiektów Klienci na TableModel wyświetlany przez panel Klienci.
 */
public class KlienciTableConverter extends AbstractTableConverter<Klient>
{

    private final static String[] columnNames;
    private final static Map<String,Integer> index;

    @Override
    protected TableModel getEmptyTableModel(final int length) {
        return new DefaultTableModel(columnNames, length)
            {
                @Override
                public boolean isCellEditable(int rowIndex, int colIndex) {
                    return false;   //Disallow the editing of any cell
                }
            };
    }

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
}
