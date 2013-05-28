package WzorceSklep.Data.Hurtownia;

import WzorceSklep.Data.DataAdapter.DatabaseAdapter;
import WzorceSklep.Data.DataAdapter.TableInfo;
import WzorceSklep.Data.DataAdapter.TableRow;
import WzorceSklep.Data.DataAdapter.TableRowFactory;
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
public class HurtowniaDAO implements DataAccessObject<Hurtownia> {

    public static final String tableNameAdres = "ADRESHURTOWNI";
    public static final String tableNameHurtownia = "HURTOWNIA";
    private final DatabaseAdapter adapter;

    public HurtowniaDAO(DatabaseAdapter adapter) {
        this.adapter = adapter;
    }

    @Override
    public List<Hurtownia> select(String orderBy) throws SQLException {
        orderBy = TableInfo.getColumnNameWithCheckedCase(orderBy, tableNameHurtownia);
        return convertToHurtownia(adapter.select(tableNameHurtownia, orderBy));
    }

    @Override
    public List<Hurtownia> select(String lookFor, String orderBy) throws SQLException, ClassNotFoundException {
        throw new UnsupportedOperationException("Not implemented.");  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void insert(Hurtownia nowy) throws SQLException {
        nowy.ID = adapter.getMaxValueForColumn(tableNameHurtownia, "ID")+1;
        TableRow row = convertToTableRow(nowy);
        adapter.insert(row);
        row = convertAdresToTableRow(nowy.adres, nowy.ID);
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
    public Hurtownia getDetails(int id) throws SQLException, InvalidDataException {
        throw new UnsupportedOperationException("Not implemented.");  //To change body of implemented methods use File | Settings | File Templates.
    }

    private List<Hurtownia> convertToHurtownia(ResultSet set) throws SQLException
    {
        List<Hurtownia> wynik = new LinkedList<Hurtownia>();

        while (set.next())
        {
            Hurtownia hurtownia = new Hurtownia();

            hurtownia.ID = set.getInt("ID");
            hurtownia.nazwa = set.getString("Nazwa");
            hurtownia.osobaKontaktowa = set.getString("OsobaKontaktowa");
            hurtownia.telefon = set.getBigDecimal("Telefon");
            hurtownia.mail = set.getString("Mail");

            wynik.add(hurtownia);
        }

        return wynik;
    }

    private TableRow convertToTableRow(Hurtownia hurtownia) throws SQLException {
        TableRow wynik = TableRowFactory.createUniversalDataEntity(tableNameHurtownia);

        wynik.setValue("ID", hurtownia.ID);
        wynik.setValue("Nazwa", hurtownia.nazwa);
        wynik.setValue("OsobaKontaktowa", hurtownia.osobaKontaktowa);
        wynik.setValue("Telefon", hurtownia.telefon);
        wynik.setValue("Mail", hurtownia.mail);

        return wynik;
    }

    private List<AdresHurtowni> convertToAdres(ResultSet set) throws SQLException {
        List<AdresHurtowni> wynik = new LinkedList<AdresHurtowni>();
        while (set.next())
        {
            AdresHurtowni adres = new AdresHurtowni();

            adres.ulica = set.getString("Ulica");
            adres.nrDomu = set.getInt("NrDomu");
            adres.kodPocztowy = set.getString("Kod");
            adres.miejscowosc = set.getString("Miejscowosc");
            adres.poczta = set.getString("Poczta");
            adres.kraj = set.getString("Kraj");

            wynik.add(adres);
        }
        return wynik;
    }

    private TableRow convertAdresToTableRow(AdresHurtowni adres, int idPracownika) throws SQLException {
        TableRow wynik = TableRowFactory.createUniversalDataEntity(tableNameAdres);
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
