package WzorceSklep.Data.DataAdapter;

import java.sql.SQLException;
import java.util.Properties;

public class DataAdapterFactory
{
    private final static String defaultDB = "WzorceSklep";
    private final static String dbName = "WzorceSklepDB";

    private static DatabaseAdapter getDatabaseAdapter(String dbFileName) throws SQLException {
        Properties properties = new Properties();
        properties.put("user", "SA");
        properties.put("password", "");
        //properties.put("create", true);
        return new JavaDBAdapter(dbFileName, properties);
    }

    public static DatabaseAdapter getDatabaseAdapter() throws SQLException {
        Properties properties = new Properties();
        properties.put("user", "SA");
        properties.put("password", "");
        try {
            return new HSQLDBAdapter(dbName, properties);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        return null;
    }
}
