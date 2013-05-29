package WzorceSklep.Data.Pracownik;

import WzorceSklep.DataAccessObject;
import WzorceSklep.Data.DataAdapter.DatabaseAdapter;
import WzorceSklep.Data.DataAdapter.TableInfo;
import WzorceSklep.Data.DataAdapter.TableRow;
import WzorceSklep.Data.DataAdapter.TableRowFactory;
import WzorceSklep.Data.AdresOsoby;
import com.sun.media.sound.InvalidDataException;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: KrzysztofD
 * Date: 25.05.13
 * Time: 19:03
 * To change this template use File | Settings | File Templates.
 */
public class PracownikDAO implements DataAccessObject<Pracownik> {
    private final DatabaseAdapter adapter;
    private final static String tableNamePracownik = "PRACOWNIK";
    private final static String tableNameAdres = "ADRESPRACOWNIKA";

    public PracownikDAO(DatabaseAdapter databaseAdapter) {
        adapter = databaseAdapter;
    }

    @Override
    public List<Pracownik> select(String lookFor, String orderBy) throws SQLException, ClassNotFoundException {
        orderBy = TableInfo.getColumnNameWithCheckedCase(tableNamePracownik, orderBy);
        return convertToPracownik(adapter.select(tableNamePracownik, lookFor, orderBy));
    }

    @Override
    public void insert(Pracownik nowy) throws SQLException {
        nowy.ID = adapter.getMaxValueForColumn(tableNamePracownik, "ID")+1;
        TableRow row = convertPracownikToTableRow(nowy);
        adapter.insert(row);
        row = convertAdresToTableRow(nowy.adres, nowy.ID);
        adapter.insert(row);
    }

    @Override
    public void update(Pracownik nowy) throws SQLException {
        throw new UnsupportedOperationException("Not implemented.");  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void delete(int id) throws SQLException {
        adapter.delete(tableNameAdres, id);
        adapter.delete(tableNamePracownik, id);
    }

    @Override
    public List<Pracownik> select(String orderBy) throws SQLException {
        orderBy = TableInfo.getColumnNameWithCheckedCase(tableNamePracownik, orderBy);
        return convertToPracownik(adapter.select(tableNamePracownik, orderBy));
    }

    @Override
    public Pracownik getById(int id) throws SQLException, InvalidDataException {
        Pracownik pracownik;
        {
            TableRow row = TableRowFactory.createTableRow(tableNamePracownik);
            row.setValue("ID", id);
            List<Pracownik> list = convertToPracownik(adapter.selectExactMatch(row));
            if(list.isEmpty())
                return null;
            pracownik = list.get(0);
        }
        {
            getAdresByID(id, pracownik);
        }
        return pracownik;
    }

    private void getAdresByID(int id, Pracownik pracownik) throws SQLException, InvalidDataException {
        TableRow row = TableRowFactory.createTableRow(tableNameAdres);
        row.setValue("ID", id);
        List<AdresOsoby> list = convertToAdres(adapter.selectExactMatch(row));
        if(list.isEmpty())
            throw new InvalidDataException(String.format("Nie znaleziono adresu dla pracownika %s %s (id %s)", pracownik.imie, pracownik.nazwisko, pracownik.ID));
        pracownik.adres = list.get(0);
    }

    public Pracownik getByLogin(String login, String haslo) throws SQLException, InvalidDataException {
        TableRow row = TableRowFactory.createTableRow(tableNamePracownik);
        row.setValue("Login", login);
        row.setValue("Password", haslo);
        List<Pracownik> list = convertToPracownik(adapter.selectExactMatch(row));
        if(list.size()<1)
            return null;

        Pracownik pracownik = list.get(0);
        getAdresByID(pracownik.ID, pracownik);

        return pracownik;
    }

    private static TableRow convertPracownikToTableRow(Pracownik pracownik) throws SQLException {
        TableRow wynik = TableRowFactory.createTableRow(tableNamePracownik);

        wynik.setValue("ID", pracownik.ID);
        wynik.setValue("Imie",pracownik.imie);
        wynik.setValue("Nazwisko", pracownik.nazwisko);
        wynik.setValue("Telefon", pracownik.telefon);
        wynik.setValue("Mail", pracownik.mail);
        wynik.setValue("Login", pracownik.login);
        wynik.setValue("Password", pracownik.password);
        wynik.setValue("Umowa", pracownik.umowa);
        String status=pracownik.getStatusString();
        wynik.setValue("Status", status);

        return wynik;
    }

    private static List<AdresOsoby> convertToAdres(ResultSet set) throws SQLException {
        List<AdresOsoby> wynik = new LinkedList<AdresOsoby>();
        while (set.next())
        {
            AdresOsoby adres = new AdresOsoby();

            adres.ulica = set.getString("Ulica");
            adres.nrDomu = set.getInt("NrDomu");
            adres.nrLokalu = set.getInt("NrLokalu");
            adres.kodPocztowy = set.getString("Kod");
            adres.miejscowosc = set.getString("Miejscowosc");
            adres.poczta = set.getString("Poczta");
            adres.kraj = set.getString("Kraj");

            wynik.add(adres);
        }
        return wynik;
    }

    private static TableRow convertAdresToTableRow(AdresOsoby adres, int idPracownika) throws SQLException {
        TableRow wynik = TableRowFactory.createTableRow(tableNameAdres);
        wynik.setValue("ID",idPracownika);
        wynik.setValue("Ulica", adres.ulica);
        wynik.setValue("NrDomu", adres.nrDomu);
        wynik.setValue("NrLokalu", adres.nrLokalu);
        wynik.setValue("Kod", adres.kodPocztowy);
        wynik.setValue("Miejscowosc", adres.miejscowosc);
        wynik.setValue("Poczta", adres.poczta);
        wynik.setValue("Kraj", adres.kraj);
        return wynik;
    }

    private static List<Pracownik> convertToPracownik(ResultSet set) throws SQLException {
        List<Pracownik> wynik = new LinkedList<Pracownik>();

        while (set.next())
        {
            Pracownik pracownik = new Pracownik();

            pracownik.ID = set.getInt("ID");
            pracownik.imie = set.getString("Imie");
            pracownik.nazwisko = set.getString("Nazwisko");
            pracownik.telefon = set.getBigDecimal("Telefon");
            pracownik.mail = set.getString("Mail");
            pracownik.login = set.getString("Login");
            pracownik.password = set.getString("Password");
            pracownik.umowa = set.getString("Umowa");
            String status = set.getString("Status");
            pracownik.setStatusWithString(status);

            wynik.add(pracownik);
        }

        return wynik;
    }
}
