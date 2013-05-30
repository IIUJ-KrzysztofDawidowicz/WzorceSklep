package WzorceSklep.Data.Klient;

import WzorceSklep.Data.DataAdapter.TableRow;
import WzorceSklep.DataEntity;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: KrzysztofD
 * Date: 30.05.13
 * Time: 10:48
 * To change this template use File | Settings | File Templates.
 */
public interface TableDataConverter<T extends DataEntity> {
    Map<String, TableRow> convertToTableRows(T dataEntity) throws SQLException;

    TableRow convertToTableRow(T dataEntity);

    List<T> convertResultSet(ResultSet set) throws SQLException;
}
