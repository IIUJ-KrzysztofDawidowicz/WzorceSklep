package DataAdapter;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: KrzysztofD
 * Date: 20.05.13
 * Time: 21:15
 * To change this template use File | Settings | File Templates.
 */
public class UniversalDataEntityFactory
{
    public static List<UniversalDataEntity> convertToUniversal(ResultSet resultSet) throws SQLException, ClassNotFoundException {

        TableInfo info;
        info = TableInfo.getTableInfo(resultSet.getMetaData());
        List<UniversalDataEntity> wynik = new LinkedList<UniversalDataEntity>();
        UniversalDataEntity entity;
        while (resultSet.next())
        {
            entity = createUniversalDataEntity(info.tableName);
            for (int i = 0; i < entity.getColumnCount(); i++) {
                entity.setValue(i, resultSet.getObject(i));
            }
        }

        return wynik;
    }

    public static UniversalDataEntity createUniversalDataEntity(String tableName) throws SQLException {
        return new UniversalDataEntityImpl(tableName);
    }
}
