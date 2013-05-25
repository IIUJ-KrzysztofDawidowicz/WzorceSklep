package DataAdapter;

import java.sql.SQLException;
import java.util.List;

/**
 * Udostępnia metody służące do komunikacji z bazą danych na najniższym poziomie w projekcie.
 * Zna dialekt SQL i używany driver.
 */
public interface DatabaseAdapter
{
    public List<TableRow> select(String tableName, String lookFor, String orderBy) throws SQLException, ClassNotFoundException;
    public void insert(TableRow nowy) throws SQLException;
    public void update(TableRow nowy) throws SQLException;
    public void delete(String tableName, int id) throws SQLException;
    public void createTableInfo(String tableName) throws SQLException;

    void executeArbitraryStatement(String statement) throws SQLException;

    void insert(List<TableRow> entityList) throws SQLException;

    List<TableRow> selectAll(String tableName) throws SQLException;

    List<TableRow> select(String tableName, String orderBy) throws SQLException;

    int getMaxValueForColumn(String tableName, String columnName) throws SQLException;
}
