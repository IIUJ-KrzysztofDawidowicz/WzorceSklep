package DataAccessObject;

import DataAdapter.DatabaseAdapter;
import DataAdapter.TableRow;
import DataAdapter.TableRowFactory;
import DataEntity.Klient;

import java.math.BigDecimal;
import java.sql.ResultSet;
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
public class KlientDAO implements DataAccessObject<Klient> {
    private final static String tableName = "KLIENT";
    private DatabaseAdapter adapter;

    public KlientDAO(DatabaseAdapter adapter) {
        this.adapter = adapter;
    }

    public List<Klient> getAll() throws SQLException {
        return convertToKlient(adapter.selectAll(tableName));
    }

    private static Klient convertToKlient(TableRow tableRow) {
        Klient klient = new Klient();
        klient.ID = (Integer) tableRow.getValue("ID");
        klient.imie = String.valueOf(tableRow.getValue("Imie"));
        klient.nazwisko = String.valueOf(tableRow.getValue("Nazwisko"));
        klient.telefon = (BigDecimal) tableRow.getValue("Telefon");
        klient.mail = String.valueOf(tableRow.getValue("Mail"));
        return klient;
    }

    private List<Klient> convertToKlient(ResultSet set) throws SQLException {
        List<Klient> wynik = new LinkedList<Klient>();

        while (set.next())
        {
            Klient klient = new Klient();
            klient.ID = set.getInt("ID");
            klient.imie = set.getString("Imie");
            klient.nazwisko = set.getString("Nazwisko");
            klient.telefon = set.getBigDecimal("Telefon");
            klient.mail = set.getString("Mail");
            wynik.add(klient);
        }

        return wynik;
    }

    public static TableRow convertToTableRow(Klient klient) {
        TableRow tableRow = null;
        try {
            tableRow = TableRowFactory.createUniversalDataEntity(tableName);
        } catch (SQLException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        if (tableRow!=null) {
            tableRow.setValue("ID", klient.ID);
            tableRow.setValue("Imie", klient.imie);
            tableRow.setValue("Nazwisko", klient.nazwisko);
            tableRow.setValue("Mail", klient.mail);
        }
        return tableRow;
    }

    @Override
    public List<Klient> select(String lookFor, String orderBy) {
        try {
            return convertToKlient(adapter.select(tableName, lookFor, orderBy));
        } catch (SQLException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (ClassNotFoundException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        return new LinkedList<Klient>();
    }

    @Override
    public Klient selectOne(int id) throws SQLException {
        return convertToKlient(adapter.selectOne(tableName, id));
    }

    @Override
    public void insert(Klient nowy) throws SQLException {
        adapter.insert(convertToTableRow(nowy));
    }

    @Override
    public void update(Klient nowy) throws SQLException {
        adapter.update(convertToTableRow(nowy));
    }

    @Override
    public List<Klient> select(String orderBy) throws SQLException {
        return convertToKlient(adapter.select(tableName, orderBy));
    }

    @Override
    public void delete(int id) throws SQLException {
        adapter.delete(tableName,id);
    }
}
