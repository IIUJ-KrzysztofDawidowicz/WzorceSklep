package WzorceSklep.Data.Produkt;

import WzorceSklep.Data.DataAdapter.DatabaseAdapter;
import WzorceSklep.Data.DataAdapter.TableInfo;
import WzorceSklep.Data.DataAdapter.TableRow;
import WzorceSklep.Data.DataAdapter.TableRowFactory;
import WzorceSklep.DataAccessObject;
import com.sun.media.sound.InvalidDataException;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: KrzysztofD
 * Date: 28.05.13
 * Time: 18:06
 * To change this template use File | Settings | File Templates.
 */
public class ProduktyDAO implements DataAccessObject<Produkt> {


    private static final String TABLE_NAME = "PRODUKT";
    private final DatabaseAdapter adapter;

    public ProduktyDAO(DatabaseAdapter databaseAdapter) {
        adapter = databaseAdapter;
    }

    private static TableRow convertToTableRow(Produkt produkt) throws SQLException {
        TableRow wynik = TableRowFactory.createTableRow(TABLE_NAME);

        wynik.setValue("ID", produkt.ID);
        wynik.setValue("Typ", produkt.typ);
        wynik.setValue("Cena", produkt.cena);
        wynik.setValue("Nazwa", produkt.nazwa);
        wynik.setValue("Ilosc", produkt.ilosc);
        wynik.setValue("Specyfikacja", produkt.specyfikacja);

        return wynik;
    }

    private static List<Produkt> convertToProduct(ResultSet rs) throws SQLException {
        List<Produkt> wynik = new ArrayList<Produkt>();

        while (rs.next())
        {
            Produkt produkt = new Produkt();

            produkt.ID = rs.getInt("ID");
            produkt.typ = rs.getString("Typ");
            produkt.cena = rs.getBigDecimal("Cena");
            produkt.nazwa = rs.getString("Nazwa");
            produkt.ilosc = rs.getInt("Ilosc");
            produkt.specyfikacja = rs.getString("Specyfikacja");

            wynik.add(produkt);
        }

        return wynik;
    }

    @Override
    public List<Produkt> select(String orderBy) throws SQLException {
        return convertToProduct(adapter.select(TABLE_NAME, TableInfo.getColumnNameWithCheckedCase(TABLE_NAME, orderBy)));
    }

    @Override
    public List<Produkt> select(String lookFor, String orderBy) throws SQLException, ClassNotFoundException {
        return convertToProduct(adapter.select(TABLE_NAME, lookFor, TableInfo.getColumnNameWithCheckedCase(TABLE_NAME, orderBy)));
    }

    @Override
    public void insert(Produkt nowy) throws SQLException {
        nowy.ID = adapter.getMaxValueForColumn(TABLE_NAME, "ID")+1;
        adapter.insert(convertToTableRow(nowy));
    }

    @Override
    public void update(Produkt nowy) throws SQLException {
        throw new UnsupportedOperationException("Not implemented.");  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void delete(int id) throws SQLException {
        throw new UnsupportedOperationException("Not implemented.");  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public Produkt getById(int id) throws SQLException, InvalidDataException {
        TableRow row = TableRowFactory.createTableRow(TABLE_NAME);
        row.setValue("ID", id);
        List<Produkt> wynik = convertToProduct(adapter.selectExactMatch(row));
        if(wynik.isEmpty())
            return null;
        return wynik.get(0);
    }
}
