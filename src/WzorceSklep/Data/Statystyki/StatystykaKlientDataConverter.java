package WzorceSklep.Data.Statystyki;

import WzorceSklep.Data.DataAdapter.TableRow;
import WzorceSklep.Data.DataAdapter.TableRowFactory;
import WzorceSklep.Data.Klient.TableDataConverter;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: KrzysztofD
 * Date: 31.05.13
 * Time: 10:02
 * To change this template use File | Settings | File Templates.
 */
public class StatystykaKlientDataConverter implements TableDataConverter<StatystykiKlienta> {
    private final String tableName;

    public StatystykaKlientDataConverter(String tableName) {
        this.tableName = tableName;
    }

    @Override
    public List<TableRow> convertToTableRows(StatystykiKlienta dataEntity) throws SQLException {
        List<TableRow> wynik = new ArrayList<TableRow>(1);
        wynik.add(convertToTableRow(dataEntity));
        return wynik;
    }

    @Override
    public TableRow convertToTableRow(StatystykiKlienta dataEntity) throws SQLException {
        TableRow wynik = TableRowFactory.createTableRow(tableName);

        wynik.setValue("ID", dataEntity.getID());
        wynik.setValue("Imie", dataEntity.getImie());
        wynik.setValue("Nazwisko", dataEntity.getNazwisko());
        wynik.setValue("Zamowienia", dataEntity.getZamowienia());
        wynik.setValue("Kwota", dataEntity.getKwota());

        return wynik;
    }

    @Override
    public List<StatystykiKlienta> convertResultSet(ResultSet set) throws SQLException {
        List<StatystykiKlienta> wynik = new ArrayList<StatystykiKlienta>();

        while (set.next())
        {
            StatystykiKlienta statystykiKlienta = new StatystykiKlienta();

            statystykiKlienta.setID(set.getInt("ID"));
            statystykiKlienta.setImie(set.getString("Imie"));
            statystykiKlienta.setNazwisko(set.getString("Nazwisko"));
            statystykiKlienta.setKwota(set.getBigDecimal("Kwota"));
            statystykiKlienta.setZamowienia(set.getInt("Zamowienia"));

            wynik.add(statystykiKlienta);
        }

        return wynik;
    }
}
