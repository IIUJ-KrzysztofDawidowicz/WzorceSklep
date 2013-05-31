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
 * Time: 09:58
 * To change this template use File | Settings | File Templates.
 */
public class StatystykiHurtowniaDataConverter implements TableDataConverter<StatystykiHurtowni> {

    private final String tableName;

    public StatystykiHurtowniaDataConverter(String tableName) {
        this.tableName = tableName;
    }

    @Override
    public List<TableRow> convertToTableRows(StatystykiHurtowni dataEntity) throws SQLException {
        List<TableRow> wynik = new ArrayList<TableRow>(1);
        wynik.add(convertToTableRow(dataEntity));
        return wynik;
    }

    @Override
    public TableRow convertToTableRow(StatystykiHurtowni dataEntity) throws SQLException {
        TableRow row = TableRowFactory.createTableRow(tableName);

        row.setValue("ID", dataEntity.getID());
        row.setValue("Nazwa", dataEntity.getNazwa());
        row.setValue("Kwota", dataEntity.getKwota());
        row.setValue("Zamowienia", dataEntity.getZamowienia());

        return row;
    }

    @Override
    public List<StatystykiHurtowni> convertResultSet(ResultSet set) throws SQLException {
        List<StatystykiHurtowni> wynik = new ArrayList<StatystykiHurtowni>();

        while (set.next())
        {
            StatystykiHurtowni statystykiHurtowni = new StatystykiHurtowni();

            statystykiHurtowni.setID(set.getInt("ID"));
            statystykiHurtowni.setNazwa(set.getString("Nazwa"));
            statystykiHurtowni.setKwota(set.getBigDecimal("Kwota"));
            statystykiHurtowni.setZamowienia(set.getInt("Zamowienia"));

            wynik.add(statystykiHurtowni);
        }

        return wynik;
    }
}
