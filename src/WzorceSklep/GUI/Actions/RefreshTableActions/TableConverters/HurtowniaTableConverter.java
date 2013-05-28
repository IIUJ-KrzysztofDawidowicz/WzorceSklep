package WzorceSklep.GUI.Actions.RefreshTableActions.TableConverters;

import WzorceSklep.Data.Hurtownia.Hurtownia;
import WzorceSklep.GUI.Actions.RefreshTableActions.AbstractTableConverter;

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

    @Override
    protected TableModel getEmptyTableModel(int length) {

        return new DefaultTableModel(columnNames, length)
        {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;   //Disallow the editing of any cell
            }
        };
    }
    private final static String[] columnNames = new String[]{"ID", "Nazwa", "Osoba kontaktowa", "Telefon", "Mail"};


    @Override
    protected Object[] mapDataToColumns(Hurtownia hurtownia) {
        Object[] wynik = new Object[columnNames.length];

        wynik[0] = hurtownia.ID;
        wynik[1] = hurtownia.nazwa;
        wynik[2] = hurtownia.osobaKontaktowa;
        wynik[3] = hurtownia.telefon;
        wynik[4] = hurtownia.mail;

        return wynik;
    }
}
