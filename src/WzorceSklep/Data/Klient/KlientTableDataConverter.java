package WzorceSklep.Data.Klient;

import WzorceSklep.Data.AdresOsoby;
import WzorceSklep.Data.DataAdapter.DatabaseAdapter;
import WzorceSklep.Data.DataAdapter.TableRow;
import WzorceSklep.Data.DataAdapter.TableRowFactory;
import WzorceSklep.Data.TableDataConverter;
import WzorceSklep.Util;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class KlientTableDataConverter implements TableDataConverter<Klient> {

    private final String klientTableName;
    private final String adresTableName;
    private final DatabaseAdapter adapter;

    public KlientTableDataConverter(String klientTableName, String adresTableName, DatabaseAdapter adapter) {
        this.klientTableName = klientTableName;
        this.adresTableName = adresTableName;
        this.adapter = adapter;
    }

    @Override
    public List<TableRow> convertToTableRows(Klient dataEntity) throws SQLException {
        List<TableRow> wynik = new ArrayList<TableRow>(2);

        wynik.add(convertToTableRow(dataEntity));
        wynik.add(convertAdresToTableRow(dataEntity.getAdres()));

        return wynik;
    }

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
            klient.setTelefon(set.getString("Telefon"));
            klient.setMail(set.getString("Mail"));
            klient.setAdres(getAdres(klient.getID()));

            wynik.add(klient);
        }

        return wynik;
    }

    private AdresOsoby getAdres(int id) throws SQLException {
        AdresOsoby wynik = new AdresOsoby();

        TableRow row = TableRowFactory.createTableRow(adresTableName);
        row.setValue("ID", id);
        ResultSet rs = adapter.selectExactMatch(row);
        if(rs.next())
        {
            wynik.setID(id);
            wynik.setNrLokalu(Util.convertZeroToNullElseSameValue(rs.getInt("NrLokalu")));
            wynik.setKodPocztowy(rs.getString("Kod"));
            wynik.setKraj(rs.getString("Kraj"));
            wynik.setMiejscowosc(rs.getString("Miejscowosc"));
            wynik.setNrDomu(Util.convertZeroToNullElseSameValue(rs.getInt("NrDomu")));
            wynik.setPoczta(rs.getString("Poczta"));
            wynik.setUlica(rs.getString("Ulica"));
        }

        return wynik;
    }

    private TableRow convertAdresToTableRow(AdresOsoby adres) throws SQLException {
        TableRow wynik = TableRowFactory.createTableRow(adresTableName);
        wynik.setValue("ID", adres.getID());
        wynik.setValue("Ulica", adres.getUlica());
        wynik.setValue("NrDomu", adres.getNrDomu());
        wynik.setValue("NrLokalu", adres.getNrLokalu());
        wynik.setValue("Kod", adres.getKodPocztowy());
        wynik.setValue("Miejscowosc", adres.getMiejscowosc());
        wynik.setValue("Poczta", adres.getPoczta());
        wynik.setValue("Kraj", adres.getKraj());
        return wynik;
    }
}