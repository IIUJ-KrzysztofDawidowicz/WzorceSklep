package WzorceSklep.GUI.DataRenderingUtils.TableConverters;

import WzorceSklep.Data.Hurtownia.Hurtownia;
import WzorceSklep.GUI.DataRenderingUtils.AbstractTableConverter;

/**
 * Created with IntelliJ IDEA.
 * User: KrzysztofD
 * Date: 27.05.13
 * Time: 23:33
 * To change this template use File | Settings | File Templates.
 */
public class HurtowniaTableConverter extends AbstractTableConverter<Hurtownia> {

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

    @Override
    protected String[] getColumnNames() {
        return columnNames;
    }
}
