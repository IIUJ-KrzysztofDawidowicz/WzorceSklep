package WzorceSklep.Data.Klient;

import WzorceSklep.DataAccessObject;
import WzorceSklep.Data.DataAdapter.DatabaseAdapter;
import WzorceSklep.Data.DataAdapter.*;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: KrzysztofD
 * Date: 24.05.13
 * Time: 22:58
 * To change this template use File | Settings | File Templates.
 */
public class KlientDAO implements DataAccessObject<Klient> {
    private final DatabaseAdapter adapter;

    private final static String tableName = "KLIENT";
    private final static String tableNameAdres = "ADRESKLIENTA";

    private final TableDataGetter<Klient> tableDataGetter;
    private final TableDataConverter<Klient> tableDataConverter;

    public KlientDAO(DatabaseAdapter adapter) {
        this.adapter = adapter;
        tableDataConverter = new KlientTableDataConverter(tableName, tableNameAdres);
        tableDataGetter = new SingleTableDataGetter<Klient>(tableDataConverter, this.adapter, tableName);
    }

    @Override
    public List<Klient> select(String orderBy) throws SQLException {
        return tableDataGetter.select(orderBy);
    }

    @Override
    public List<Klient> select(String lookFor, String orderBy) throws SQLException {
        return tableDataGetter.select(lookFor, orderBy);
    }

    @Override
    public void insert(Klient nowy) throws SQLException {
        nowy.ID = nowy.adres.ID = adapter.getMaxValueForColumn(tableName, "ID")+1;
        Map<String, TableRow> rows = tableDataConverter.convertToTableRows(nowy);
        adapter.insert(rows.get(tableNameAdres));
        adapter.insert(rows.get(tableName));
    }

    @Override
    public void update(Klient nowy) throws SQLException {
        adapter.update(tableDataConverter.convertToTableRow(nowy));
    }

    @Override
    public void delete(int id) throws SQLException {
        adapter.delete(tableNameAdres, id);
        adapter.delete(tableName,id);
    }

    @Override
    public Klient getById(int id) throws SQLException {
        TableRow row = TableRowFactory.createTableRow(tableName);
        row.setValue("ID", id);
        List<Klient> list = tableDataConverter.convertResultSet(adapter.selectExactMatch(row));
        if(list.size()==0)
            return null;
        return list.get(0);
    }
}
