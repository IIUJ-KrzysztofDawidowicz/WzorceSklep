package DataAdapter;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.sql.*;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: KrzysztofD
 * Date: 17.05.13
 * Time: 21:54
 * To change this template use File | Settings | File Templates.
 */
public class SqliteJDBCDatabaseAdapter implements DatabaseAdapter
{
    static final String driverName = "org.sqlite.JDBC";
    String databaseFileName = "testDB.db";
    static final String jdbc = "jdbc:sqlite";
    private final String url = String.format("%s:%s",jdbc,databaseFileName);
    private final int timeout = 30;

    public SqliteJDBCDatabaseAdapter(String databaseFileName){
        this.databaseFileName = databaseFileName;
        try {
            Class.forName(driverName);
        } catch (ClassNotFoundException e) {
            System.out.println(driverName + " nie jest poprawną nazwą drivera.");
            e.printStackTrace();
            System.exit(-1); //Ustalamy to jako static, więc może się kompletnie wywalić.
        }
    }

    public String createTable(String createTableCommand, String insertCommand, String selectCommand) throws SQLException {
        Connection conn = DriverManager.getConnection(url);
        StringBuilder wynik = new StringBuilder();
        try {
            Statement stmt = conn.createStatement();
            try {
                stmt.setQueryTimeout(timeout);
                if (!createTableCommand.isEmpty()) {
                    stmt.executeUpdate( createTableCommand );
                }
                stmt.executeUpdate( insertCommand );
                ResultSet rs = stmt.executeQuery(selectCommand);
                try {
                    while(rs.next())
                    {
                        wynik.append(rs.getString("response"));
                    }
                } finally {
                    try { rs.close(); } catch (Exception ignore) {}
                }
            } finally {
                try { stmt.close(); } catch (Exception ignore) {}
            }
        } finally {
            try { conn.close(); } catch (Exception ignore) {}
        }
        return wynik.toString();
    }

    @Override
    public List<UniversalDataEntity> select(String tableName, String lookFor, String orderBy) throws SQLException, ClassNotFoundException {
        Connection conn = DriverManager.getConnection(url);
        Statement stmt = conn.createStatement();
        String selectCommand = String.format("SELECT * from %s", tableName);

        ResultSet rs;
        try {
            rs = stmt.executeQuery(selectCommand);
        } catch (SQLException e) {
            throw new IllegalArgumentException("Błąd przy próbie odczytu danych z tabeli " + tableName
                    + ", prawdopodobnie nieprawidłowa nazwa.", e);
        }

        return UniversalDataEntityFactory.convertToUniversal(rs);
    }

    @Override
    public void insert(UniversalDataEntity nowy) {

        throw new NotImplementedException();
    }

    @Override
    public void update(UniversalDataEntity nowy) {

        throw new NotImplementedException();

    }

    @Override
    public void delete(String tableName, int id) {

        throw new NotImplementedException();
    }

    @Override
    public void createTableInfo(String tableName) throws SQLException {
        Connection conn = DriverManager.getConnection(url);
        Statement stmt = conn.createStatement();
        String selectCommand = String.format("SELECT * from %s", tableName);

        ResultSet rs;
        try {
            rs = stmt.executeQuery(selectCommand);
        } catch (SQLException e) {
            throw new IllegalArgumentException("Błąd przy próbie odczytu danych z tabeli " + tableName
                    + ", prawdopodobnie nieprawidłowa nazwa.", e);
        }
        TableInfo.getTableInfo(rs.getMetaData());
    }

    @Override
    public void executeArbitraryStatement(String statement) throws SQLException {
        throw new UnsupportedOperationException("Not implemented.");  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void insert(List<UniversalDataEntity> entityList) {
        throw new UnsupportedOperationException("Not implemented.");  //To change body of implemented methods use File | Settings | File Templates.
    }
}
