package WzorceSklep.Data;

import WzorceSklep.Data.DataAdapter.DatabaseAdapter;
import WzorceSklep.DataEntity;

import java.sql.SQLException;
import java.util.List;

public class SingleTableDataGetter<T extends DataEntity> implements TableDataGetter<T> {
    private final ResultSetConverter<T> resultSetConverter;
    private final DatabaseAdapter adapter;
    private final String tableName;

    public SingleTableDataGetter(ResultSetConverter<T> resultSetConverter, DatabaseAdapter adapter, String tableName) {
        this.resultSetConverter = resultSetConverter;
        this.adapter = adapter;
        this.tableName = tableName;
    }

    @Override
    public List<T> select(String orderBy) throws SQLException {
        if(orderBy.equals(""))
            return select();
        return resultSetConverter.convertResultSet(adapter.select(tableName, orderBy));
    }

    @Override
    public List<T> select(String lookFor, String orderBy) throws SQLException {
        if(lookFor.equals(""))
            return select(orderBy);
        return resultSetConverter.convertResultSet(adapter.select(tableName, lookFor, orderBy));
    }

    @Override
    public List<T> select() throws SQLException {
        return resultSetConverter.convertResultSet(adapter.selectAll(tableName));
    }
}