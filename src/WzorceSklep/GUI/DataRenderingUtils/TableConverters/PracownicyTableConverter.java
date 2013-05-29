package WzorceSklep.GUI.DataRenderingUtils.TableConverters;

import WzorceSklep.Data.Pracownik.Pracownik;
import WzorceSklep.GUI.DataRenderingUtils.AbstractTableConverter;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.util.HashMap;
import java.util.Map;

/**
 * Konwertuje listę obiektów Pracownicy na TableModel wyświetlany przez panel Pracownicy.
 */
public class PracownicyTableConverter extends AbstractTableConverter<Pracownik> {

    private final Action akcjaPracownikSzczegoly;
    private final Action akcjaUsunPracownika;
    private final JTable pracownicy_tabela;

    public PracownicyTableConverter(Action akcjaPracownikSzczegoly, Action akcjaUsunPracownika, JTable pracownicy_tabela) {
        this.akcjaPracownikSzczegoly = akcjaPracownikSzczegoly;
        this.akcjaUsunPracownika = akcjaUsunPracownika;
        this.pracownicy_tabela = pracownicy_tabela;
    }

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

        wynik[index.get("ID")] = object.ID;
        wynik[index.get("Nazwisko")] = String.format("%s %s", object.imie, object.nazwisko);
        wynik[index.get("login")] = object.login;
        wynik[index.get("Telefon")] = object.telefon;
        wynik[index.get("Szczegóły")] = "Szczegóły";
        wynik[index.get("Usuń")] = "Usuń";

        return wynik;
    }

    @Override
    protected String[] getColumnNames() {
        return columnNames;
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
