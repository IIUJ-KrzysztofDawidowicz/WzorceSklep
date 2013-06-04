package WzorceSklep.Data.Statystyki;

import WzorceSklep.Data.DataAdapter.TableRow;
import WzorceSklep.Data.DataAdapter.TableRowFactory;
import WzorceSklep.Data.TableDataConverter;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: KrzysztofD
 * Date: 30.05.13
 * Time: 17:02
 * To change this template use File | Settings | File Templates.
 */
public class StatystykiProduktyDataConverter implements TableDataConverter<StatystykiProdukty> {
    private final String tableName;

    public StatystykiProduktyDataConverter(String tableName) {
        this.tableName = tableName;
    }

    @Override
    public List<TableRow> convertToTableRows(StatystykiProdukty dataEntity) throws SQLException {
        List<TableRow> wynik = new ArrayList<TableRow>(1);
        wynik.add(convertToTableRow(dataEntity));
        return wynik;
    }

    public TableRow convertToTableRow(StatystykiProdukty dataEntity) throws SQLException {
        TableRow wynik = TableRowFactory.createTableRow(tableName);

        wynik.setValue("ID", dataEntity.getId());
        wynik.setValue("Nazwa", dataEntity.getNazwa());
        wynik.setValue("Zamowienia", dataEntity.getZamowienia());
        wynik.setValue("Kwota", dataEntity.getKwota());
        wynik.setValue("Ilosc", dataEntity.getIlosc());

        return wynik;
    }

    @Override
    public List<StatystykiProdukty> convertResultSet(ResultSet set) throws SQLException {
        List<StatystykiProdukty> wynik = new ArrayList<StatystykiProdukty>();

        while (set.next())
        {
            StatystykiProdukty statystykiProdukty = new StatystykiProdukty();

            statystykiProdukty.setId(set.getInt("ID"));
            statystykiProdukty.setIlosc(set.getInt("Ilosc"));
            statystykiProdukty.setZamowienia(set.getInt("Zamowienia"));
            statystykiProdukty.setNazwa(set.getString("Nazwa"));
            statystykiProdukty.setKwota(set.getBigDecimal("Kwota"));

            wynik.add(statystykiProdukty);
        }

        return wynik;
    }
}
