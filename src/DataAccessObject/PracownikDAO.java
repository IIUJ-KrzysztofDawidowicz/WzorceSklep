package DataAccessObject;

import DataAdapter.DatabaseAdapter;
import DataAdapter.TableRow;
import DataEntity.Pracownik;

import java.math.BigDecimal;
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
    private final static String tableName = "PRACOWNIK";

    public PracownikDAO(DatabaseAdapter databaseAdapter) {
        adapter = databaseAdapter;
    }

    @Override
    public List<Pracownik> select(String lookFor, String orderBy) throws SQLException, ClassNotFoundException {
        return convertToPracownik(adapter.select(tableName, lookFor, orderBy));
    }

    private List<Pracownik> convertToPracownik(ResultSet set) throws SQLException {
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
            if(status.equals("Admin"))
                pracownik.status= Pracownik.Statusy.Admin;
            else if(status.equals("Hurtownik"))
                pracownik.status = Pracownik.Statusy.Hurtownik;
            else if (status.equals("Sprzedawca"))
                pracownik.status = Pracownik.Statusy.Sprzedawca;
            else
                throw new IllegalArgumentException("Nieprawidłowy status pracownika: " + status);

            wynik.add(pracownik);
        }

        return wynik;
    }

    @Override
    public Pracownik selectOne(int id) throws SQLException {
        throw new UnsupportedOperationException("Not implemented.");  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void insert(Pracownik nowy) throws SQLException {
        throw new UnsupportedOperationException("Not implemented.");  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void update(Pracownik nowy) throws SQLException {
        throw new UnsupportedOperationException("Not implemented.");  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void delete(int id) throws SQLException {
        throw new UnsupportedOperationException("Not implemented.");  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public List<Pracownik> select(String orderBy) throws SQLException {
        return convertToPracownik(adapter.select(tableName, orderBy));
    }
}
