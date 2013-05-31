package WzorceSklep.Data.Klient;

import WzorceSklep.Data.AdresOsoby;
import WzorceSklep.Data.DataAdapter.TableRow;
import WzorceSklep.Data.DataAdapter.TableRowFactory;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class KlientTableDataConverter implements TableDataConverter<Klient> {

    private final String klientTableName;
    private final String adresTableName;

    public KlientTableDataConverter(String klientTableName, String adresTableName) {
        this.klientTableName = klientTableName;
        this.adresTableName = adresTableName;
    }

    @Override
    public List<TableRow> convertToTableRows(Klient dataEntity) throws SQLException {
        List<TableRow> wynik = new ArrayList<TableRow>(2);

        wynik.add(convertToTableRow(dataEntity));
        wynik.add(convertAdresToTableRow(dataEntity.getAdres()));

        return wynik;
    }

    @Override
    public TableRow convertToTableRow(Klient klient) throws SQLException {
        TableRow tableRow = null;
        try {
            tableRow = TableRowFactory.createTableRow(klientTableName);
        } catch (SQLException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        if (tableRow != null) {
            tableRow.setValue("ID", klient.getID());
            tableRow.setValue("Imie", klient.getImie());
            tableRow.setValue("Nazwisko", klient.getNazwisko());
            tableRow.setValue("Mail", klient.getMail());
            tableRow.setValue("Telefon", klient.getTelefon());
        }
        return tableRow;
    }

    @Override
    public List<Klient> convertResultSet(ResultSet set) throws SQLException {
        List<Klient> wynik = new LinkedList<Klient>();

        while (set.next()) {
            Klient klient = new Klient();
            klient.setID(set.getInt("ID"));
            klient.setImie(set.getString("Imie"));
            klient.setNazwisko(set.getString("Nazwisko"));
            klient.setTelefon(set.getBigDecimal("Telefon"));
            klient.setMail(set.getString("Mail"));
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