package WzorceSklep;

import WzorceSklep.Data.TableDataGetter;

import java.sql.SQLException;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: KrzysztofD
 * Date: 17.05.13
 * Time: 08:47
 * To change this template use File | Settings | File Templates.
 */
public interface DataAccessObject<T extends DataEntity> extends TableDataGetter<T>
{
    public List<T> select(String orderBy) throws SQLException;
    public List<T> select(String lookFor, String orderBy) throws SQLException;

    public void insert(T nowy) throws SQLException;
    public void update(T nowy) throws SQLException;
    public void delete(int id) throws SQLException;

    T getById(int id) throws SQLException;
}
