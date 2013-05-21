package DataAdapter;

import java.sql.SQLException;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: KrzysztofD
 * Date: 21.05.13
 * Time: 16:00
 * To change this template use File | Settings | File Templates.
 */
public class JavaDBAdapter implements DatabaseAdapter {
    private final String dbFileName;

    public JavaDBAdapter(String dbFileName) {
        this.dbFileName = dbFileName;
    }

    @Override
    public List<UniversalDataEntity> select(String tableName, String lookFor, String orderBy) throws SQLException, ClassNotFoundException {
        throw new UnsupportedOperationException("Not implemented.");
    }

    @Override
    public int insert(UniversalDataEntity nowy) {
        throw new UnsupportedOperationException("Not implemented.");
    }

    @Override
    public void update(UniversalDataEntity nowy) {
        throw new UnsupportedOperationException("Not implemented.");
    }

    @Override
    public void delete(String tableName, int id) {
        throw new UnsupportedOperationException("Not implemented.");
    }

    @Override
    public void createTableInfo(String tableName) throws SQLException {
        throw new UnsupportedOperationException("Not implemented.");
    }
}
