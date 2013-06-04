package WzorceSklep.Data;

import WzorceSklep.Data.DataAdapter.TableRow;
import WzorceSklep.DataEntity;

import java.sql.SQLException;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: KrzysztofD
 * Date: 30.05.13
 * Time: 10:48
 * To change this template use File | Settings | File Templates.
 */
public interface TableDataConverter<T extends DataEntity> extends ResultSetConverter<T> {
    List<TableRow> convertToTableRows(T dataEntity) throws SQLException;

    TableRow convertToTableRow(T dataEntity) throws SQLException;

}
