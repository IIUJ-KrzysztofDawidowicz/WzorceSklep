package DataAdapter;

public class SqliteJDBCDataAdapterFactory implements DataAdapterFactory {

    private String databaseFileName;

    public SqliteJDBCDataAdapterFactory(String databaseFileName) {
        this.databaseFileName = databaseFileName;
    }

    @Override
    public DatabaseAdapter getDatabaseAdapter() {
        return new SqliteJDBCDatabaseAdapter(databaseFileName);
    }
}
