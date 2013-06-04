package WzorceSklep.GUI.DataRenderingUtils.TableConverters;

import WzorceSklep.Data.Klient.Klient;
import WzorceSklep.GUI.DataRenderingUtils.AbstractTableConverter;

import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

/**
 * Konwertuje listę obiektów Klienci na TableModel wyświetlany przez panel Klienci.
 */
public class KlienciTableConverter extends AbstractTableConverter<Klient>
{

    @Override
    protected TableModel getEmptyTableModel(int length) {
        return new DefaultTableModel(columnNames, length)
        {
            @Override
            public boolean isCellEditable(int row, int column) {
                return column>=4;
            }
        };
    }

    private final static String[] columnNames = new String[]{"ID", "Nazwa klienta", "Mail", "Telefon", "Edytuj"};

    protected Object[] mapDataToColumns(Klient klient) {
        Object[] wynik = new Object[columnNames.length];
        wynik[index.get("ID")] = klient.getID();
        wynik[index.get("Nazwa klienta")] = String.format("%s %s", klient.getNazwisko(), klient.getImie()==null?"":klient.getImie());
        wynik[index.get("Mail")] = klient.getMail();
        wynik[index.get("Telefon")] = klient.getTelefon();
        wynik[index.get("Edytuj")] = "Edytuj";
        return wynik;
    }

    @Override
    protected String[] getColumnNames() {
        return columnNames;
    }
}
