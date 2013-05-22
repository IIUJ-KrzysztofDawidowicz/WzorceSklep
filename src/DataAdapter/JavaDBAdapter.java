package DataAdapter;

import org.apache.commons.lang3.StringUtils;

import java.sql.*;
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
    private final Properties properties;
    private final String url;

    public JavaDBAdapter(String dbName, Properties properties) {
        this.properties = properties;
        try {
            Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        setDBSystemDir();

        url = String.format("jdbc:derby:%s;", dbName);
        try {
            DriverManager.getConnection(url, properties);
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
        return selectAll(tableName);
    }

    public List<UniversalDataEntity> selectAll(String tableName) throws SQLException {
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
    public void insert(UniversalDataEntity nowy) throws SQLException {
        Connection connection = DriverManager.getConnection(url,properties);
        String sql = String.format("INSERT INTO %s (%s) VALUES (%s)",
                nowy.getTableName(),
                StringUtils.join(nowy.getColumns(), ", "),
                StringUtils.join(proceesValuesForStatement(nowy.getValues()), ", ")
        );
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.execute();
    }

    private String[] proceesValuesForStatement(Object[] values) {
        String[] wynik = new String[values.length];
        for (int i = 0; i < wynik.length; i++) {
            if(values[i].getClass()==String.class)
                wynik[i] = String.format("'%s'", values[i]);
            else
                wynik[i] = values[i].toString();
        }
        return wynik;
    }

    @Override
    public void update(UniversalDataEntity nowy) {
        throw new UnsupportedOperationException("Not implemented.");
    }

    @Override
    public void delete(String tableName, int id) throws SQLException {
        Connection conn = DriverManager.getConnection(url);
        Statement stmt = conn.createStatement();
        String command = String.format("DELETE from %s WHERE ID = %s", tableName, id);
        stmt.execute(command);

    }

    @Override
    public void createTableInfo(String tableName) throws SQLException {
        throw new UnsupportedOperationException("Not implemented.");
    }

    @Override
    public void executeArbitraryStatement(String command) throws SQLException {
        Connection connection = DriverManager.getConnection(url, properties);
        Statement statement = connection.createStatement();
        statement.execute(command);
    }

    @Override
    public void insert(List<UniversalDataEntity> entityList) throws SQLException {
        for(UniversalDataEntity entity: entityList)
            insert(entity);
    }

}
