package WzorceSklep.Data.Klient;

import WzorceSklep.Data.AdresOsoby;
import WzorceSklep.Data.DataAdapter.TableRow;
import WzorceSklep.Data.DataAdapter.TableRowFactory;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class KlientTableDataConverter implements TableDataConverter<Klient> {

    private final String klientTableName;
    private final String adresTableName;

    public KlientTableDataConverter(String klientTableName, String adresTableName) {
        this.klientTableName = klientTableName;
        this.adresTableName = adresTableName;
    }

    @Override
    public Map<String, TableRow> convertToTableRows(Klient dataEntity) throws SQLException {
        Map<String, TableRow> wynik = new HashMap<String, TableRow>();

        wynik.put(klientTableName, convertToTableRow(dataEntity));
        wynik.put(adresTableName, convertAdresToTableRow(dataEntity.adres));

        return wynik;
    }

    @Override
    public TableRow convertToTableRow(Klient klient) {
        TableRow tableRow = null;
        try {
            tableRow = TableRowFactory.createTableRow(klientTableName);
        } catch (SQLException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        if (tableRow != null) {
            tableRow.setValue("ID", klient.ID);
            tableRow.setValue("Imie", klient.imie);
            tableRow.setValue("Nazwisko", klient.nazwisko);
            tableRow.setValue("Mail", klient.mail);
            tableRow.setValue("Telefon", klient.telefon);
        }
        return tableRow;
    }

    @Override
    public List<Klient> convertResultSet(ResultSet set) throws SQLException {
        List<Klient> wynik = new LinkedList<Klient>();

        while (set.next()) {
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

    private TableRow convertAdresToTableRow(AdresOsoby adres) throws SQLException {
        TableRow wynik = TableRowFactory.createTableRow(adresTableName);
        wynik.setValue("ID", adres.ID);
        wynik.setValue("Ulica", adres.ulica);
        wynik.setValue("NrDomu", adres.nrDomu);
        wynik.setValue("NrLokalu", adres.nrLokalu);
        wynik.setValue("Kod", adres.kodPocztowy);
        wynik.setValue("Miejscowosc", adres.miejscowosc);
        wynik.setValue("Poczta", adres.poczta);
        wynik.setValue("Kraj", adres.kraj);
        return wynik;
    }
}