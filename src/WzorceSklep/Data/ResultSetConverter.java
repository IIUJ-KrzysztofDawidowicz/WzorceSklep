package WzorceSklep.Data;

import WzorceSklep.DataEntity;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: KrzysztofD
 * Date: 04.06.13
 * Time: 09:54
 * To change this template use File | Settings | File Templates.
 */
public interface ResultSetConverter<T extends DataEntity> {
    List<T> convertResultSet(ResultSet set) throws SQLException;
}
