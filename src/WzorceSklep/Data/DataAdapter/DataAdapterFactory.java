package WzorceSklep.Data.DataAdapter;

import java.sql.SQLException;
import java.util.Properties;

public class DataAdapterFactory
{
    private final static String defaultDB = "WzorceSklep";

    private static DatabaseAdapter getDatabaseAdapter(String dbFileName) throws SQLException {
        Properties properties = new Properties();
//        properties.put("user", "dbuser");
//        properties.put("password", "dbuserpwd");
        //properties.put("create", true);
        return new JavaDBAdapter(dbFileName, properties);
    }

    public static DatabaseAdapter getDatabaseAdapter() throws SQLException {
        return getDatabaseAdapter(defaultDB);
    }
}
