package WzorceSklep.Data.Zamowienie;

import WzorceSklep.Data.DataAdapter.DatabaseAdapter;
import WzorceSklep.Data.DataAdapter.TableInfo;
import WzorceSklep.Data.DataAdapter.TableRow;
import WzorceSklep.Data.DataAdapter.TableRowFactory;
import WzorceSklep.Data.Hurtownia.Hurtownia;
import WzorceSklep.Data.Produkt.Produkt;
import WzorceSklep.Data.TableDataGetter;
import WzorceSklep.DataAccessObject;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: KrzysztofD
 * Date: 29.05.13
 * Time: 13:18
 * To change this template use File | Settings | File Templates.
 */
public class ZamownieniaHurtowniDAO implements DataAccessObject<ZamowienieHurtowni>, TableDataGetter<ZamowienieHurtowni> {

    private static final String TABLE_NAME = "ZAMOWIENIEHURTOWNI";
    private final DatabaseAdapter adapter;
    private final DataAccessObject<Hurtownia> hurtowniaDataAccessObject;
    private final DataAccessObject<Produkt> produktDataAccessObject;

    public ZamownieniaHurtowniDAO(DatabaseAdapter adapter,
                                  DataAccessObject<Hurtownia> hurtowniaDataAccessObject,
                                  DataAccessObject<Produkt> produktDataAccessObject)
    {
        this.adapter = adapter;
        this.hurtowniaDataAccessObject = hurtowniaDataAccessObject;
        this.produktDataAccessObject = produktDataAccessObject;
    }

    private List<ZamowienieHurtowni> convertToZamowienie(ResultSet resultSet) throws SQLException {
        List<ZamowienieHurtowni> wynik = new ArrayList<ZamowienieHurtowni>();

        while (resultSet.next())
        {
            ZamowienieHurtowni zamowienieHurtowni = new ZamowienieHurtowni();

            zamowienieHurtowni.setID(resultSet.getInt("ID"));
            zamowienieHurtowni.setKwota(resultSet.getBigDecimal("Kwota"));
            zamowienieHurtowni.setDataOdebrania(resultSet.getDate("DataOdebrania"));
            zamowienieHurtowni.setDataZamowienia(resultSet.getDate("DataZamowienie"));
            zamowienieHurtowni.setIlosc(resultSet.getInt("Ilosc"));

            zamowienieHurtowni.setZamawiajacy(hurtowniaDataAccessObject.getById(resultSet.getInt("HurtowniaID")));
            zamowienieHurtowni.setProduktZamowiony(produktDataAccessObject.getById(resultSet.getInt("ProduktID")));

            wynik.add(zamowienieHurtowni);
        }

        return wynik;
    }

    @Override
    public List<ZamowienieHurtowni> select(String orderBy) throws SQLException {
        orderBy = TableInfo.getColumnNameWithCheckedCase(TABLE_NAME, orderBy);
        return convertToZamowienie(adapter.select(TABLE_NAME, orderBy));
    }

    @Override
    public List<ZamowienieHurtowni> select(String lookFor, String orderBy) throws SQLException {
        orderBy = TableInfo.getColumnNameWithCheckedCase(TABLE_NAME, orderBy);
        return convertToZamowienie(adapter.select(TABLE_NAME, lookFor, orderBy));
    }

    @Override
    public List<ZamowienieHurtowni> select() throws SQLException {
        return convertToZamowienie(adapter.selectAll(TABLE_NAME));
    }

    @Override
    public void insert(ZamowienieHurtowni nowy) throws SQLException {
        nowy.setID(adapter.getMaxValueForColumn(TABLE_NAME, "ID") +1);
        adapter.insert(convertToTableRow(nowy));
    }

    private TableRow convertToTableRow(ZamowienieHurtowni nowy) throws SQLException {
        TableRow wynik = TableRowFactory.createTableRow(TABLE_NAME);

        wynik.setValue("ID", nowy.getID());
        wynik.setValue("Ilosc", nowy.getIlosc());
        wynik.setValue("Kwota", nowy.getKwota());
        wynik.setValue("DataZamowienie", nowy.getDataZamowienia());
        wynik.setValue("DataOdebrania", nowy.getDataOdebrania());
        wynik.setValue("HurtowniaID", nowy.getZamawiajacy().getID());
        wynik.setValue("ProduktID", nowy.getProduktZamowiony().ID);

        return wynik;
    }

    @Override
    public void update(ZamowienieHurtowni nowy) throws SQLException {
        adapter.update(convertToTableRow(nowy));
    }

    @Override
    public void delete(int id) throws SQLException {
        adapter.delete(TABLE_NAME,id);
    }

    @Override
    public ZamowienieHurtowni getById(int id) throws SQLException {
        TableRow row = TableRowFactory.createTableRow(TABLE_NAME);
        row.setValue("ID", id);
        List<ZamowienieHurtowni> list = convertToZamowienie(adapter.selectExactMatch(row));
        if(list.size()>0)
            return list.get(0);
        return null;
    }
}
