package DataAdapter;

public class DataAdapterFactory
{
    private static final String defaultDB = "testDB.db";

    public static DatabaseAdapter getDatabaseAdapter(String dbFileName) {
        return new JavaDBAdapter(dbFileName);
    }

    public static DatabaseAdapter getDatabaseAdapter()
    {
        return getDatabaseAdapter(defaultDB);
    }
}
