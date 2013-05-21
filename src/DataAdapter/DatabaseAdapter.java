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
    public int insert(UniversalDataEntity nowy);
    public void update(UniversalDataEntity nowy);
    public void delete(String tableName, int id);
    public void createTableInfo(String tableName) throws SQLException;
}
