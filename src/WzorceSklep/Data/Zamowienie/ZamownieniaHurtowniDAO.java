package WzorceSklep.Data.Zamowienie;

import WzorceSklep.Data.DataAdapter.DatabaseAdapter;
import WzorceSklep.Data.DataAdapter.TableInfo;
import WzorceSklep.Data.Hurtownia.Hurtownia;
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
 * Date: 29.05.13
 * Time: 13:18
 * To change this template use File | Settings | File Templates.
 */
public class ZamownieniaHurtowniDAO implements DataAccessObject<ZamowienieHurtowni> {

    public static final String TABLE_NAME = "ZAMOWIENIEHURTOWNI";
    private DatabaseAdapter adapter;
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

    private List<ZamowienieHurtowni> convertToZamowienie(ResultSet resultSet) throws SQLException, InvalidDataException {
        List<ZamowienieHurtowni> wynik = new ArrayList<ZamowienieHurtowni>();

        while (resultSet.next())
        {
            ZamowienieHurtowni zamowienieHurtowni = new ZamowienieHurtowni();

            zamowienieHurtowni.ID = resultSet.getInt("ID");
            zamowienieHurtowni.kwota = resultSet.getBigDecimal("Kwota");
            zamowienieHurtowni.dataOdebrania = resultSet.getDate("DataOdebrania");
            zamowienieHurtowni.dataZamowienia = resultSet.getDate("DataZamowienia");
            zamowienieHurtowni.ilosc = resultSet.getInt("Ilosc");

            zamowienieHurtowni.zamawiajacy = hurtowniaDataAccessObject.getById(resultSet.getInt("HurtowniaID"));
            zamowienieHurtowni.produktZamowiony = produktDataAccessObject.getById(resultSet.getInt("ProduktID"));

            wynik.add(zamowienieHurtowni);
        }

        return wynik;
    }

    @Override
    public List<ZamowienieHurtowni> select(String orderBy) throws SQLException, InvalidDataException {
        orderBy = TableInfo.getColumnNameWithCheckedCase(TABLE_NAME, orderBy);
        return convertToZamowienie(adapter.select(TABLE_NAME, orderBy));
    }

    @Override
    public List<ZamowienieHurtowni> select(String lookFor, String orderBy) throws SQLException, ClassNotFoundException, InvalidDataException {
        orderBy = TableInfo.getColumnNameWithCheckedCase(TABLE_NAME, orderBy);
        return convertToZamowienie(adapter.select(TABLE_NAME, lookFor, orderBy));
    }

    @Override
    public void insert(ZamowienieHurtowni nowy) throws SQLException {
        throw new UnsupportedOperationException("Not implemented.");  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void update(ZamowienieHurtowni nowy) throws SQLException {
        throw new UnsupportedOperationException("Not implemented.");  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void delete(int id) throws SQLException {
        throw new UnsupportedOperationException("Not implemented.");  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public ZamowienieHurtowni getById(int id) throws SQLException, InvalidDataException {
        throw new UnsupportedOperationException("Not implemented.");  //To change body of implemented methods use File | Settings | File Templates.
    }
}
