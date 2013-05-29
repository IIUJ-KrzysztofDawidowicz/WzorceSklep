package WzorceSklep.Data.Klient;

import WzorceSklep.Data.AdresOsoby;
import WzorceSklep.DataAccessObject;
import WzorceSklep.Data.DataAdapter.DatabaseAdapter;
import WzorceSklep.Data.DataAdapter.*;

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
    private final DatabaseAdapter adapter;
    private final static String tableNameAdres = "ADRESKLIENTA";

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
            tableRow = TableRowFactory.createTableRow(tableName);
        } catch (SQLException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        if (tableRow!=null) {
            tableRow.setValue("ID", klient.ID);
            tableRow.setValue("Imie", klient.imie);
            tableRow.setValue("Nazwisko", klient.nazwisko);
            tableRow.setValue("Mail", klient.mail);
            tableRow.setValue("Telefon", klient.telefon);
        }
        return tableRow;
    }

    @Override
    public List<Klient> select(String lookFor, String orderBy) throws SQLException {
        orderBy = checkColumnUpperCase(orderBy);
        try {
            return convertToKlient(adapter.select(tableName, lookFor, orderBy));
        } catch (ClassNotFoundException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            return new LinkedList<Klient>();
        }
    }

    private String checkColumnUpperCase(String columnName) throws SQLException {
        if(!TableInfo.getTableInfo(tableName).hasColumn(columnName))
        {
            if(TableInfo.getTableInfo(tableName).hasColumn(columnName.toUpperCase()))
                columnName=columnName.toUpperCase();
            else
                throw new IllegalArgumentException(String.format(
                        "Próba sortowania po kolumnie %s która nie istinieje w tabeli %s",
                        columnName, tableName));
        }
        return columnName;
    }

    @Override
    public void insert(Klient nowy) throws SQLException {
        nowy.ID = adapter.getMaxValueForColumn(tableName, "ID")+1;
        TableRow row = convertToTableRow(nowy);
        adapter.insert(row);
        row = convertAdresToTableRow(nowy.adres, nowy.ID);
        adapter.insert(row);
    }

    private TableRow convertAdresToTableRow(AdresOsoby adres, int id) throws SQLException {
        TableRow wynik = TableRowFactory.createTableRow(tableNameAdres);
        wynik.setValue("ID",id);
        wynik.setValue("Ulica", adres.ulica);
        wynik.setValue("NrDomu", adres.nrDomu);
        wynik.setValue("NrLokalu", adres.nrLokalu);
        wynik.setValue("Kod", adres.kodPocztowy);
        wynik.setValue("Miejscowosc", adres.miejscowosc);
        wynik.setValue("Poczta", adres.poczta);
        wynik.setValue("Kraj", adres.kraj);
        return wynik;
    }

    @Override
    public void update(Klient nowy) throws SQLException {
        adapter.update(convertToTableRow(nowy));
    }

    @Override
    public List<Klient> select(String orderBy) throws SQLException {
        orderBy=checkColumnUpperCase(orderBy);
        return convertToKlient(adapter.select(tableName, orderBy));
    }

    @Override
    public void delete(int id) throws SQLException {
        adapter.delete(tableName,id);
    }

    @Override
    public Klient getById(int id) {
        throw new UnsupportedOperationException("Not implemented.");  //To change body of implemented methods use File | Settings | File Templates.
    }
}
