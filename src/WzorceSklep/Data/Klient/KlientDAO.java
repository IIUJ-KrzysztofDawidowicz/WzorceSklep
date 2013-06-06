package WzorceSklep.Data.Klient;

import WzorceSklep.Data.SingleTableDataGetter;
import WzorceSklep.Data.TableDataConverter;
import WzorceSklep.Data.TableDataGetter;
import WzorceSklep.DataAccessObject;
import WzorceSklep.Data.DataAdapter.DatabaseAdapter;
import WzorceSklep.Data.DataAdapter.*;
import WzorceSklep.TableNames;

import java.sql.SQLException;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: KrzysztofD
 * Date: 24.05.13
 * Time: 22:58
 * To change this template use File | Settings | File Templates.
 */
public class KlientDAO implements DataAccessObject<Klient>, TableDataGetter<Klient> {
    private final DatabaseAdapter adapter;
    private final TableDataGetter<Klient> tableDataGetter;
    private final TableDataConverter<Klient> tableDataConverter;

    public KlientDAO(DatabaseAdapter adapter) {
        this.adapter = adapter;
        tableDataConverter = new KlientTableDataConverter(TableNames.KLIENT, TableNames.ADRES_KLIENTA, adapter);
        tableDataGetter = new SingleTableDataGetter<Klient>(tableDataConverter, this.adapter, TableNames.KLIENT);
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
    public List<Klient> select() throws SQLException {
        return tableDataGetter.select();
    }

    @Override
    public void insert(Klient nowy) throws SQLException {
        int id = adapter.getMaxValueForColumn(TableNames.KLIENT, "ID") + 1;
        nowy.getAdres().setID(id);
        nowy.setID(id);
        for (TableRow row: tableDataConverter.convertToTableRows(nowy))
            adapter.insert(row);
    }

    @Override
    public void update(Klient nowy) throws SQLException {
        for (TableRow row: tableDataConverter.convertToTableRows(nowy))
            adapter.update(row);
    }

    @Override
    public void delete(int id) throws SQLException {
        adapter.delete(TableNames.ADRES_KLIENTA, id);
        adapter.delete(TableNames.KLIENT,id);
    }

    @Override
    public Klient getById(int id) throws SQLException {
        TableRow row = TableRowFactory.createTableRow(TableNames.KLIENT);
        row.setValue("ID", id);
        List<Klient> list = tableDataConverter.convertResultSet(adapter.selectExactMatch(row));
        if(list.size()==0)
            return null;
        return list.get(0);
    }
}
