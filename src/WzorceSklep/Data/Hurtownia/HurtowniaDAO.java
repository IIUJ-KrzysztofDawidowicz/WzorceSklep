package WzorceSklep.Data.Hurtownia;

import WzorceSklep.Data.DataAdapter.DatabaseAdapter;
import WzorceSklep.Data.DataAdapter.TableInfo;
import WzorceSklep.Data.DataAdapter.TableRow;
import WzorceSklep.Data.DataAdapter.TableRowFactory;
import WzorceSklep.Data.TableDataGetter;
import WzorceSklep.DataAccessObject;

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
        return convertToHurtownia(adapter.select(tableNameHurtownia,lookFor,orderBy));
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
        TableRow row = convertToTableRow(nowy);
        adapter.update(row);
        row = convertAdresToTableRow(nowy.getAdres(), nowy.getID());
        adapter.update(row);
    }

    @Override
    public void delete(int id) throws SQLException {
        adapter.delete(tableNameAdres, id);
        adapter.delete(tableNameHurtownia,id);
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
            hurtownia.setTelefon(set.getString("Telefon"));
            hurtownia.setMail(set.getString("Mail"));
            hurtownia.setAdres(getAdres(hurtownia.getID()));

            wynik.add(hurtownia);
        }

        return wynik;
    }

    private AdresHurtowni getAdres(int id) throws SQLException {
        AdresHurtowni wynik = new AdresHurtowni();

        TableRow row = TableRowFactory.createTableRow(tableNameAdres);
        row.setValue("ID", id);
        ResultSet rs = adapter.selectExactMatch(row);
        if(rs.next())
        {
            wynik.setID(id);
            wynik.setKodPocztowy(rs.getString("Kod"));
            wynik.setKraj(rs.getString("Kraj"));
            wynik.setMiejscowosc(rs.getString("Miejscowosc"));
            wynik.setNrDomu((Integer)rs.getObject("NrDomu"));
            wynik.setPoczta(rs.getString("Poczta"));
            wynik.setUlica(rs.getString("Ulica"));
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
        wynik.setValue("Ulica", adres.getUlica());
        wynik.setValue("NrDomu", adres.getNrDomu());
        wynik.setValue("Kod", adres.getKodPocztowy());
        wynik.setValue("Miejscowosc", adres.getMiejscowosc());
        wynik.setValue("Poczta", adres.getPoczta());
        wynik.setValue("Kraj", adres.getKraj());
        return wynik;
    }
}
