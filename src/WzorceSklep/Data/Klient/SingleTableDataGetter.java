package WzorceSklep.Data.Klient;

import WzorceSklep.Data.DataAdapter.DatabaseAdapter;
import WzorceSklep.DataEntity;

import java.sql.SQLException;
import java.util.List;

public class SingleTableDataGetter<T extends DataEntity> implements TableDataGetter<T> {
    private final TableDataConverter<T> tableDataConverter;
    private final DatabaseAdapter adapter;
    private final String tableName;

    public SingleTableDataGetter(TableDataConverter<T> tableDataConverter, DatabaseAdapter adapter, String tableName) {
        this.tableDataConverter = tableDataConverter;
        this.adapter = adapter;
        this.tableName = tableName;
    }

    @Override
    public List<T> getAll() throws SQLException {
        return tableDataConverter.convertResultSet(adapter.selectAll(tableName));
    }

    @Override
    public List<T> select(String orderBy) throws SQLException {
        return tableDataConverter.convertResultSet(adapter.select(tableName, orderBy));
    }

    @Override
    public List<T> select(String lookFor, String orderBy) throws SQLException {
        return tableDataConverter.convertResultSet(adapter.select(tableName, lookFor, orderBy));
    }
}