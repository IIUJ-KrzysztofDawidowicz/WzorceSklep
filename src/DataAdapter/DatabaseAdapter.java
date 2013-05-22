package DataAdapter;

import java.sql.SQLException;
import java.util.List;

/**
 * Udostępnia metody służące do komunikacji z bazą danych na najniższym poziomie w projekcie.
 * Zna dialekt SQL i używany driver.
 */
public interface DatabaseAdapter
{
    public List<UniversalDataEntity> select(String tableName, String lookFor, String orderBy) throws SQLException, ClassNotFoundException;
    public void insert(UniversalDataEntity nowy) throws SQLException;
    public void update(UniversalDataEntity nowy) throws SQLException;
    public void delete(String tableName, int id) throws SQLException;
    public void createTableInfo(String tableName) throws SQLException;

    void executeArbitraryStatement(String statement) throws SQLException;

    void insert(List<UniversalDataEntity> entityList) throws SQLException;

    List<UniversalDataEntity> selectAll(String tableName) throws SQLException;
}
