package WzorceSklep.GUI.DataRenderingUtils.TableConverters;

import WzorceSklep.Data.Pracownik.Pracownik;
import WzorceSklep.GUI.DataRenderingUtils.AbstractTableConverter;

import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.util.HashMap;
import java.util.Map;

/**
 * Konwertuje listę obiektów Pracownicy na TableModel wyświetlany przez panel Pracownicy.
 */
public class PracownicyTableConverter extends AbstractTableConverter<Pracownik> {

    @Override
    protected TableModel getEmptyTableModel(final int length)
    {
        return new DefaultTableModel(columnNames, length)
        {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex)
            {
                return colIndex >= 4;
            }
        };
    }

    @Override
    protected Object[] mapDataToColumns(Pracownik object) {
        Object[] wynik = new Object[columnNames.length];

        wynik[index.get("ID")] = object.getID();
        wynik[index.get("Nazwisko")] = String.format("%s %s", object.getImie(), object.getNazwisko());
        wynik[index.get("login")] = object.getLogin();
        wynik[index.get("Telefon")] = object.getTelefon();
        wynik[index.get("Szczegóły")] = "Szczegóły";
        wynik[index.get("Usuń")] = "Usuń";
        wynik[index.get("Edytuj")] = "Edytuj";

        return wynik;
    }

    @Override
    protected String[] getColumnNames() {
        return columnNames;
    }

    private static final String[] columnNames = new String[]{"ID","Nazwisko","login","Telefon", "Szczegóły", "Edytuj", "Usuń"};
}
