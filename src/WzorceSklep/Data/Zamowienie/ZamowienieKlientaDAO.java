package WzorceSklep.Data.Zamowienie;

import WzorceSklep.DAOFactory;
import WzorceSklep.Data.DataAdapter.DatabaseAdapter;
import WzorceSklep.Data.DataAdapter.TableInfo;
import WzorceSklep.Data.Klient.Klient;
import WzorceSklep.Data.Pracownik.Pracownik;
import WzorceSklep.Data.Produkt.Produkt;
import WzorceSklep.DataAccessObject;
import com.sun.media.sound.InvalidDataException;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: KrzysztofD
 * Date: 28.05.13
 * Time: 19:37
 * To change this template use File | Settings | File Templates.
 */
public class ZamowienieKlientaDAO implements DataAccessObject<ZamowienieKlienta> {
    private static final String TABLE_NAME = "ZAMOWIENIEKLIENTA";
    private final DatabaseAdapter adapter;

    public ZamowienieKlientaDAO(DatabaseAdapter databaseAdapter) {
        adapter = databaseAdapter;
    }

    private static List<ZamowienieKlienta> convertToZamowienieKlienta(ResultSet rs) throws SQLException
    {
        List<ZamowienieKlienta> wynik = new ArrayList<ZamowienieKlienta>();

        while (rs.next())
        {
            ZamowienieKlienta zamowienie = new ZamowienieKlienta();
            zamowienie.tworzacy = new Pracownik();
            zamowienie.zamawiajacy = new Klient();
            zamowienie.produktZamowiony = new Produkt();

            zamowienie.ID = rs.getInt("ID");
            zamowienie.kwota = rs.getBigDecimal("Kwota");
            zamowienie.dataZamowienia = rs.getDate("DataZamowienia");
            zamowienie.dataOdebrania = rs.getDate("DataOdebrania");
            zamowienie.ilosc = rs.getInt("Ilosc");
            zamowienie.zamawiajacy.ID = rs.getInt("KlientID");
            zamowienie.tworzacy.ID = rs.getInt("PracownikID");
            zamowienie.produktZamowiony.ID = rs.getInt("ProduktID");

            wynik.add(zamowienie);
        }

        return wynik;
    }

    @Override
    public List<ZamowienieKlienta> select(String orderBy) throws SQLException {
        List<ZamowienieKlienta>     wynik = convertToZamowienieKlienta(
                adapter.select(TABLE_NAME, TableInfo.getColumnNameWithCheckedCase(TABLE_NAME, orderBy)));

        DataAccessObject<Produkt>   produktDataAccessObject     = DAOFactory.getProduktyDAO();
        DataAccessObject<Klient>    klientDataAccessObject      = new DAOFactory().getKlientDAO();
        DataAccessObject<Pracownik> pracownikDataAccessObject   = new DAOFactory().getPracownikDAO();

        for (ZamowienieKlienta zamowienie: wynik) {
            doczepZamawiajacyTworzacyIProdukt(
                    produktDataAccessObject, klientDataAccessObject, pracownikDataAccessObject, zamowienie);
        }

        return wynik;
    }

    private void doczepZamawiajacyTworzacyIProdukt(DataAccessObject<Produkt> produktDataAccessObject, DataAccessObject<Klient> klientDataAccessObject, DataAccessObject<Pracownik> pracownikDataAccessObject, ZamowienieKlienta zamowienie) throws SQLException {
        try {
            zamowienie.produktZamowiony = produktDataAccessObject.getById(zamowienie.produktZamowiony.ID);
        } catch (InvalidDataException e) {
            e.printStackTrace();  //Ignorujemy, bo nie obchodzi nas że nie ma adresu.
        }
        try {
            zamowienie.tworzacy = pracownikDataAccessObject.getById(zamowienie.tworzacy.ID);
        } catch (InvalidDataException e) {
            e.printStackTrace();  //Ignorujemy, bo nie obchodzi nas że nie ma adresu.
        }
        try {
            zamowienie.zamawiajacy = klientDataAccessObject.getById(zamowienie.zamawiajacy.ID);
        } catch (InvalidDataException e) {
            e.printStackTrace();  //Ignorujemy, bo nie obchodzi nas że nie ma adresu.
        }
    }

    @Override
    public List<ZamowienieKlienta> select(String lookFor, String orderBy) throws SQLException, ClassNotFoundException {
        throw new UnsupportedOperationException("Not implemented.");  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void insert(ZamowienieKlienta nowy) throws SQLException {
        throw new UnsupportedOperationException("Not implemented.");  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void update(ZamowienieKlienta nowy) throws SQLException {
        throw new UnsupportedOperationException("Not implemented.");  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void delete(int id) throws SQLException {
        throw new UnsupportedOperationException("Not implemented.");  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public ZamowienieKlienta getById(int id) throws SQLException, InvalidDataException {
        throw new UnsupportedOperationException("Not implemented.");  //To change body of implemented methods use File | Settings | File Templates.
    }
}
