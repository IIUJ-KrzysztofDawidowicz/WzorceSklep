package DataAdapter;

import java.util.Properties;

public class DataAdapterFactory
{
    private static String defaultDB = "WzorceSklep";

    public static DatabaseAdapter getDatabaseAdapter(String dbFileName) {
        Properties properties = new Properties();
//        properties.put("user", "dbuser");
//        properties.put("password", "dbuserpwd");
        //properties.put("create", true);
        return new JavaDBAdapter(dbFileName, properties);
    }

    public static DatabaseAdapter getDatabaseAdapter()
    {
        return getDatabaseAdapter(defaultDB);
    }
}
