package WzorceSklep.Data.DataAdapter;

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
public class TableRowFactory
{
    public static List<TableRow> convertToTableRow(ResultSet resultSet) throws SQLException {

        TableInfo info;
        info = TableInfo.getTableInfo(resultSet.getMetaData());
        List<TableRow> wynik = new LinkedList<TableRow>();
        TableRow entity;
        while (resultSet.next())
        {
            entity = createTableRow(info.tableName);
            for (int i = 0; i < entity.getColumnCount(); i++) {
                entity.setValue(i, resultSet.getObject(i+1));
            }
            wynik.add(entity);
        }

        return wynik;
    }

    public static TableRow createTableRow(String tableName) throws SQLException {
        return new JavaDBTableRow(tableName);
    }
}
