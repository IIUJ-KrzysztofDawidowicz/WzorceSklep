package WzorceSklep.Data.Zamowienie;

import WzorceSklep.DAOFactory;
import WzorceSklep.Data.DataAdapter.DatabaseAdapter;
import WzorceSklep.Data.DataAdapter.TableInfo;
import WzorceSklep.Data.DataAdapter.TableRow;
import WzorceSklep.Data.DataAdapter.TableRowFactory;
import WzorceSklep.Data.Klient.Klient;
import WzorceSklep.Data.Pracownik.Pracownik;
import WzorceSklep.Data.Produkt.Produkt;
import WzorceSklep.Data.TableDataGetter;
import WzorceSklep.DataAccessObject;
import WzorceSklep.TableNames;

import java.math.BigDecimal;
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
public class ZamowienieKlientaDAO implements DataAccessObject<ZamowienieKlienta>, TableDataGetter<ZamowienieKlienta> {
    private final DatabaseAdapter adapter;
    private DAOFactory daoFactory;

    public ZamowienieKlientaDAO(DatabaseAdapter databaseAdapter, DAOFactory daoFactory) {
        adapter = databaseAdapter;
        this.daoFactory = daoFactory;
    }

    private List<ZamowienieKlienta> convertToZamowienieKlienta(ResultSet rs) throws SQLException
    {
        List<ZamowienieKlienta> wynik = new ArrayList<ZamowienieKlienta>();

        while (rs.next())
        {
            ZamowienieKlienta zamowienie = new ZamowienieKlienta();
            zamowienie.tworzacy = new Pracownik();
            zamowienie.zamawiajacy = new Klient();
            zamowienie.setProduktZamowiony(new Produkt());

            zamowienie.setID(rs.getInt("ID"));
            zamowienie.setKwota(rs.getBigDecimal("Kwota"));
            zamowienie.setDataZamowienia(rs.getDate("DataZamowienie"));
            zamowienie.setDataOdebrania(rs.getDate("DataOdebrania"));
            zamowienie.setIlosc(rs.getInt("Ilosc"));
            zamowienie.zamawiajacy.setID(rs.getInt("KlientID"));
            zamowienie.tworzacy.setID(rs.getInt("PracownikID"));
            zamowienie.getProduktZamowiony().ID = rs.getInt("ProduktID");

            wynik.add(zamowienie);
        }

        return wynik;
    }

    @Override
    public List<ZamowienieKlienta> select(String orderBy) throws SQLException {
        List<ZamowienieKlienta>     wynik = convertToZamowienieKlienta(
                adapter.select(TableNames.ZAMOWIENIE_KLIENTA, TableInfo.getColumnNameWithCheckedCase(TableNames.ZAMOWIENIE_KLIENTA, orderBy)));

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
        zamowienie.setProduktZamowiony(produktDataAccessObject.getById(zamowienie.getProduktZamowiony().ID));
        zamowienie.tworzacy = pracownikDataAccessObject.getById(zamowienie.tworzacy.getID());
        zamowienie.zamawiajacy = klientDataAccessObject.getById(zamowienie.zamawiajacy.getID());
    }

    @Override
    public List<ZamowienieKlienta> select(String lookFor, String orderBy) throws SQLException {
        if(lookFor.equals(""))
            return convertToZamowienieKlienta(adapter.select(TableNames.ZAMOWIENIE_KLIENTA, orderBy));
        return convertToZamowienieKlienta(adapter.select(TableNames.ZAMOWIENIE_KLIENTA, lookFor,orderBy));
    }

    @Override
    public List<ZamowienieKlienta> select() throws SQLException {
        return convertToZamowienieKlienta(adapter.selectAll(TableNames.ZAMOWIENIE_KLIENTA));
    }

    @Override
    public void insert(ZamowienieKlienta nowy) throws SQLException {
        nowy.setID(adapter.getMaxValueForColumn(TableNames.ZAMOWIENIE_KLIENTA, "ID")+1);
        adapter.insert(convertToTableRow(nowy));
    }

    private TableRow convertToTableRow(ZamowienieKlienta nowy) throws SQLException {
        TableRow row = TableRowFactory.createTableRow(TableNames.ZAMOWIENIE_KLIENTA);

        row.setValue("ID", nowy.getID());
        row.setValue("Kwota", nowy.getProduktZamowiony().cena.multiply(new BigDecimal(nowy.getIlosc())));
        row.setValue("DataZamowienie", nowy.getDataZamowienia());
        row.setValue("DataOdebrania", nowy.getDataOdebrania());
        row.setValue("PracownikID", nowy.tworzacy.getID());
        row.setValue("KlientID", nowy.zamawiajacy.getID());
        row.setValue("ProduktID", nowy.getProduktZamowiony().ID);
        row.setValue("Ilosc", nowy.getIlosc());

        return row;
    }

    @Override
    public void update(ZamowienieKlienta nowy) throws SQLException {
        adapter.update(convertToTableRow(nowy));
    }

    @Override
    public void delete(int id) throws SQLException {
        adapter.delete(TableNames.ZAMOWIENIE_KLIENTA,id);
    }

    @Override
    public ZamowienieKlienta getById(int id) throws SQLException {
        TableRow row = TableRowFactory.createTableRow(TableNames.ZAMOWIENIE_KLIENTA);
        row.setValue("ID", id);
        List<ZamowienieKlienta> list = convertToZamowienieKlienta(adapter.selectExactMatch(row));
        if (list.size() == 0) {
            return null;
        }
        ZamowienieKlienta zamowienieKlienta = list.get(0);
        zamowienieKlienta.tworzacy = daoFactory.getPracownikDAO().getById(zamowienieKlienta.tworzacy.getID());
        zamowienieKlienta.zamawiajacy = daoFactory.getKlientDAO().getById(zamowienieKlienta.zamawiajacy.getID());
        zamowienieKlienta.setProduktZamowiony(daoFactory.getProduktDAO().getById(zamowienieKlienta.getProduktZamowiony().ID));
        return zamowienieKlienta;
    }
}
