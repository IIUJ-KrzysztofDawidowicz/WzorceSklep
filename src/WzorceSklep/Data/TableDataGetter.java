package WzorceSklep.Data;

import WzorceSklep.Data.Klient.Klient;
import WzorceSklep.DataEntity;

import java.sql.SQLException;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: KrzysztofD
 * Date: 30.05.13
 * Time: 10:58
 * To change this template use File | Settings | File Templates.
 */
public interface TableDataGetter<T extends DataEntity> {

    List<T> select(String orderBy) throws SQLException;

    List<T> select(String lookFor, String orderBy) throws SQLException;

    List<T> select() throws SQLException;
}
