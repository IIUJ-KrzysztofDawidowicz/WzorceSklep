package WzorceSklep.Data.Hurtownia;

import WzorceSklep.Data.DataAdapter.DatabaseAdapter;
import WzorceSklep.Data.DataAdapter.TableInfo;
import WzorceSklep.Data.DataAdapter.TableRow;
import WzorceSklep.Data.DataAdapter.TableRowFactory;
import WzorceSklep.Data.TableDataGetter;
import WzorceSklep.DataAccessObject;
import com.sun.media.sound.InvalidDataException;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: KrzysztofD
 * Date: 28.05.13
 * Time: 08:32
 * To change this template use File | Settings | File Templates.
 */
public class HurtowniaDAO implements DataAccessObject<Hurtownia>, TableDataGetter<Hurtownia> {

    private static final String tableNameAdres = "ADRESHURTOWNI";
    private static final String tableNameHurtownia = "HURTOWNIA";
    private final DatabaseAdapter adapter;

    public HurtowniaDAO(DatabaseAdapter adapter) {
        this.adapter = adapter;
    }

    @Override
    public List<Hurtownia> select(String orderBy) throws SQLException {
        orderBy = TableInfo.getColumnNameWithCheckedCase(tableNameHurtownia, orderBy);
        return convertToHurtownia(adapter.select(tableNameHurtownia, orderBy));
    }

    @Override
    public List<Hurtownia> select(String lookFor, String orderBy) throws SQLException {
        throw new UnsupportedOperationException("Not implemented.");  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public List<Hurtownia> select() throws SQLException {
        return convertToHurtownia(adapter.selectAll(tableNameHurtownia));
    }

    @Override
    public void insert(Hurtownia nowy) throws SQLException {
        nowy.setID(adapter.getMaxValueForColumn(tableNameHurtownia, "ID")+1);
        TableRow row = convertToTableRow(nowy);
        adapter.insert(row);
        row = convertAdresToTableRow(nowy.getAdres(), nowy.getID());
        adapter.insert(row);
    }

    @Override
    public void update(Hurtownia nowy) throws SQLException {
        throw new UnsupportedOperationException("Not implemented.");  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void delete(int id) throws SQLException {
        throw new UnsupportedOperationException("Not implemented.");  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public Hurtownia getById(int id) throws SQLException {
        TableRow row = TableRowFactory.createTableRow(tableNameHurtownia);
        row.setValue("ID", id);
        List<Hurtownia> list = convertToHurtownia(adapter.selectExactMatch(row));
        if(list.size()>0)
            return list.get(0);
        return null;
    }

    private List<Hurtownia> convertToHurtownia(ResultSet set) throws SQLException
    {
        List<Hurtownia> wynik = new LinkedList<Hurtownia>();

        while (set.next())
        {
            Hurtownia hurtownia = new Hurtownia();

            hurtownia.setID(set.getInt("ID"));
            hurtownia.setNazwa(set.getString("Nazwa"));
            hurtownia.setOsobaKontaktowa(set.getString("OsobaKontaktowa"));
            hurtownia.setTelefon(set.getBigDecimal("Telefon"));
            hurtownia.setMail(set.getString("Mail"));

            wynik.add(hurtownia);
        }

        return wynik;
    }

    private TableRow convertToTableRow(Hurtownia hurtownia) throws SQLException {
        TableRow wynik = TableRowFactory.createTableRow(tableNameHurtownia);

        wynik.setValue("ID", hurtownia.getID());
        wynik.setValue("Nazwa", hurtownia.getNazwa());
        wynik.setValue("OsobaKontaktowa", hurtownia.getOsobaKontaktowa());
        wynik.setValue("Telefon", hurtownia.getTelefon());
        wynik.setValue("Mail", hurtownia.getMail());

        return wynik;
    }

    private TableRow convertAdresToTableRow(AdresHurtowni adres, int idPracownika) throws SQLException {
        TableRow wynik = TableRowFactory.createTableRow(tableNameAdres);
        wynik.setValue("ID",idPracownika);
        wynik.setValue("Ulica", adres.ulica);
        wynik.setValue("NrDomu", adres.nrDomu);
        wynik.setValue("Kod", adres.kodPocztowy);
        wynik.setValue("Miejscowosc", adres.miejscowosc);
        wynik.setValue("Poczta", adres.poczta);
        wynik.setValue("Kraj", adres.kraj);
        return wynik;
    }
}
