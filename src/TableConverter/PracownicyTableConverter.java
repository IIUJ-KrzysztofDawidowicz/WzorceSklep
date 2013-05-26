package TableConverter;

import DataEntity.Pracownik;

import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.util.HashMap;
import java.util.Map;

/**
 * Konwertuje listę obiektów Pracownicy na TableModel wyświetlany przez panel Pracownicy.
 */
public class PracownicyTableConverter extends TableConverter<Pracownik> {

    @Override
    protected TableModel getEmptyTableModel(final int length) {
        return new DefaultTableModel(columnNames, length)
        {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex)
            {
                return colIndex <= 4;
            }
        };
    }

    @Override
    protected Object[] mapDataToColumns(Pracownik object) {
        Object[] wynik = new Object[columnNames.length];

        wynik[index.get("ID")] = object.ID;
        wynik[index.get("Nazwisko")] = String.format("%s %s", object.imie, object.nazwisko);
        wynik[index.get("login")] = object.login;
        wynik[index.get("Telefon")] = object.telefon;
        wynik[index.get("Szczegóły")] = "Szczegóły";
        wynik[index.get("Usuń")] = "Usuń";

        return wynik;
    }

    private static final String[] columnNames = new String[]{"ID","Nazwisko","login","Telefon", "Szczegóły", "Usuń"};
    private static final Map<String, Integer> index;
    static
    {
        index = new HashMap<String, Integer>();
        for (int i = 0; i < columnNames.length; i++) {
            index.put(columnNames[i], i);
        }
    }
}
