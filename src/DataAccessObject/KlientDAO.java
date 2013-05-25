package DataAccessObject;

import DataAdapter.DatabaseAdapter;
import DataAdapter.TableRow;
import DataAdapter.TableRowFactory;
import DataEntity.Klient;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: KrzysztofD
 * Date: 24.05.13
 * Time: 22:58
 * To change this template use File | Settings | File Templates.
 */
public class KlientDAO {
    private DatabaseAdapter adapter;

    public KlientDAO(DatabaseAdapter adapter) {
        this.adapter = adapter;
    }

    public List<Klient> getAll() throws SQLException {
        return convertToKlient(adapter.selectAll("KLIENT"));
    }

    private List<Klient> convertToKlient(List<TableRow> klienci) {
        List<Klient> wynik = new LinkedList<Klient>();
        for (TableRow tableRow: klienci)
        {
            Klient klient = new Klient();
            klient.ID = (Integer) tableRow.getValue("ID");
            klient.imie = String.valueOf(tableRow.getValue("Imie"));
            klient.nazwisko = String.valueOf(tableRow.getValue("Nazwisko"));
            klient.telefon = (BigDecimal) tableRow.getValue("Telefon");
            klient.mail = String.valueOf(tableRow.getValue("Mail"));
            wynik.add(klient);
        }
        return wynik;
    }

    public static TableRow convertToTableRow(Klient klient) {
        TableRow tableRow = null;
        try {
            tableRow = TableRowFactory.createUniversalDataEntity("KLIENT");
        } catch (SQLException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        tableRow.setValue("ID", klient.ID);
        tableRow.setValue("Imie", klient.imie);
        tableRow.setValue("Nazwisko", klient.nazwisko);
        tableRow.setValue("Mail", klient.mail);
        return tableRow;
    }
}
