package WzorceSklep.GUI.DataRenderingUtils.TableConverters;

import WzorceSklep.Data.Hurtownia.Hurtownia;
import WzorceSklep.GUI.DataRenderingUtils.AbstractTableConverter;

import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

/**
 * Created with IntelliJ IDEA.
 * User: KrzysztofD
 * Date: 27.05.13
 * Time: 23:33
 * To change this template use File | Settings | File Templates.
 */
public class HurtowniaTableConverter extends AbstractTableConverter<Hurtownia> {

    private final static String[] columnNames = new String[]{"ID", "Nazwa", "Osoba kontaktowa", "Telefon", "Mail", "Edytuj"};

    @Override
    protected TableModel getEmptyTableModel(int length) {
        return new DefaultTableModel(columnNames, length)
        {
            @Override
            public boolean isCellEditable(int row, int column) {
                return column>=5;
            }
        };
    }

    @Override
    protected Object[] mapDataToColumns(Hurtownia hurtownia) {
        Object[] wynik = new Object[columnNames.length];

        wynik[0] = hurtownia.getID();
        wynik[1] = hurtownia.getNazwa();
        wynik[2] = hurtownia.getOsobaKontaktowa();
        wynik[3] = hurtownia.getTelefon();
        wynik[4] = hurtownia.getMail();
        wynik[5] = "Edytuj";

        return wynik;
    }

    @Override
    protected String[] getColumnNames() {
        return columnNames;
    }
}
