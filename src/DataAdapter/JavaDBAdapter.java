package DataAdapter;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;

/**
 * Created with IntelliJ IDEA.
 * User: KrzysztofD
 * Date: 21.05.13
 * Time: 16:00
 * To change this template use File | Settings | File Templates.
 */
public class JavaDBAdapter implements DatabaseAdapter {
    private final String dbName;
    private final Properties properties;
    private final String connectionUrl;

    public JavaDBAdapter(String dbName, Properties properties) {
        this.dbName = dbName;
        this.properties = properties;
        try {
            Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        setDBSystemDir();

        connectionUrl = String.format("jdbc:derby:%s;", dbName);
        try {
            DriverManager.getConnection(connectionUrl, properties);
        } catch (SQLException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }

    private void setDBSystemDir() {
        // Decide on the db system directory: <userhome>/.addressbook/
        //String userHomeDir = System.getProperty("user.home", ".");
        String systemDir = "C:\\Users\\KrzysztofD\\Documents\\InteliJ Projects\\WzorceSklep\\";

        // Set the db system directory.
        System.setProperty("derby.system.home", systemDir);
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
