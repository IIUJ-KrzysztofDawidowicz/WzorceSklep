package WzorceSklep.Data.DataAdapter;

import org.apache.commons.lang3.StringUtils;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;

/**
 * Łączy się z JavaDB z użyciem JDBC.
 */
public class JavaDBAdapter implements DatabaseAdapter {
    private final Properties properties;
    private final String url;

    public JavaDBAdapter(String dbName, Properties properties) throws SQLException {
        this.properties = properties;
        try {
            Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        setDBSystemDir();

        url = String.format("jdbc:derby:%s;", dbName);
        DriverManager.getConnection(url, properties);
    }

    private void setDBSystemDir() {
        // Decide on the db system directory: <userhome>/.addressbook/
        //String userHomeDir = System.getProperty("user.home", ".");
        String systemDir = "C:\\Users\\KrzysztofD\\Documents\\InteliJ Projects\\WzorceSklep\\";

        // Set the db system directory.
        System.setProperty("derby.system.home", systemDir);
    }

    @Override
    public ResultSet select(String tableName, String lookFor, String orderBy) throws SQLException {
        validateOrderBy(tableName, orderBy);
        String selectCommand = String.format("SELECT * from %s WHERE %s ORDER BY %s", tableName, createWhereClause(tableName, lookFor), orderBy);

        return getResultSet(selectCommand);
    }

    private static String createWhereClause(String tableName, String lookFor) throws SQLException {
        TableInfo tableInfo = TableInfo.getTableInfo(tableName);
        String[] columns = tableInfo.getColumns();
        List<String> clauses = new LinkedList<String>();
        for (String column : columns) {
            if (tableInfo.getValueType(column) == String.class)
                clauses.add(String.format("%s like '%%%s%%'", column, lookFor));
        }
        return StringUtils.join(clauses, " or ");
    }

    @Override
    public ResultSet select(String tableName, String orderBy) throws SQLException {
        validateOrderBy(tableName, orderBy);
        String selectCommand = String.format("SELECT * from %s ORDER BY %s", tableName, orderBy);

        return getResultSet(selectCommand);

    }

    @Override
    public int getMaxValueForColumn(String tableName, String columnName) throws SQLException {
        String selectCommand = String.format("SELECT MAX(%s) from %s", columnName, tableName);

        ResultSet rs = getResultSet(selectCommand);
        rs.next();
        return rs.getInt(1);
    }

    @Override
    public ResultSet selectExactMatch(TableRow toMatch) throws SQLException {
        String tableName = toMatch.getTableName();
        String selectCommand = String.format("SELECT * from %s WHERE %s", tableName, createExactMatchClause(toMatch));
        return getResultSet(selectCommand);
    }

    private String createExactMatchClause(TableRow toMatch) {
        String[] columns = toMatch.getColumns();
        Object[] values = toMatch.getValues();
        List<String> clauses = new LinkedList<String>();
        for (int i = 0; i < columns.length; i++) {
            if(values[i]==null)
                continue;
            if(values[i].getClass()==String.class)
                clauses.add(String.format("%s = '%s'", columns[i], values[i]));
            else
                clauses.add(String.format("%s = %s", columns[i], values[i]));
        }
        return StringUtils.join(clauses, " AND ");
    }

    public ResultSet selectAll(String tableName) throws SQLException {
        String selectCommand = String.format("SELECT * from %s", tableName);

        return getResultSet(selectCommand);
    }

    private ResultSet getResultSet(String selectCommand) throws SQLException {
        ResultSet rs;
        try {
            rs = getStatement().executeQuery(selectCommand);
        } catch (SQLException e) {
            throw new IllegalArgumentException("Błąd przy próbie odczytu danych z tabeli (komenda " + selectCommand
                    + "), prawdopodobnie nieprawidłowa nazwa.", e);
        }
        return rs;
    }

    @Override
    public void insert(TableRow nowy) throws SQLException {
        Connection connection = DriverManager.getConnection(url,properties);
        String[] processedValues = proceesValuesForStatement(nowy.getValues(), nowy.getColumns());
        String sql = String.format("INSERT INTO %s (%s) VALUES (%s)",
                nowy.getTableName(),
                StringUtils.join(nowy.getColumns(), ", "),
                StringUtils.join(processedValues, ", ")
        );
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.execute();
    }

    private static String[] proceesValuesForStatement(Object[] values, String[] columns) throws UnsupportedOperationException {
        String[] wynik = new String[values.length];
        for (int i = 0; i < wynik.length; i++) {
            if(values[i] == null)
                throw new UnsupportedOperationException("TableRow nie jest wypełniony. Brakuje wartości dla " + columns[i]);
            if(values[i].getClass()==String.class)
                wynik[i] = String.format("'%s'", values[i]);
            else
                wynik[i] = values[i].toString();
        }
        return wynik;
    }

    @Override
    public void update(TableRow nowy) throws SQLException {
        String command = String.format(
                "UPDATE %s SET %s WHERE ID = %s",
                nowy.getTableName(),
                createSetClause(nowy),
                nowy.getValue("ID"));
        getStatement().execute(command);
    }

    private static String createSetClause(TableRow entity) {
        String[] columns = entity.getColumns();
        String[] values = proceesValuesForStatement(entity.getValues(), columns);
        List<String> clauses = new LinkedList<String>();
        for (int i = 0; i < columns.length; i++) {
            if(!columns[i].equals("ID"))
                clauses.add(String.format("%s = %s", columns[i], values[i]));
        }
        return StringUtils.join(clauses, ", ");
    }

    @Override
    public void delete(String tableName, int id) throws SQLException {
        String command = String.format("DELETE from %s WHERE ID = %s", tableName, id);
        getStatement().execute(command);
    }

    private Statement getStatement() throws SQLException {
        Connection conn = DriverManager.getConnection(url);
        return conn.createStatement();
    }


    private void validateOrderBy(String tableName, String orderBy) throws SQLException {
        if(!TableInfo.getTableInfo(tableName).hasColumn(orderBy))
            throw new IllegalArgumentException(String.format("Próba sortowania po kolumnie %s która nie istinieje w tabeli %s", orderBy, tableName));
    }

    @Override
    public void createTableInfo(String tableName) throws SQLException {
        String selectCommand = String.format("SELECT * from %s WHERE 0=1", tableName);

        ResultSet rs = getResultSet(selectCommand);
        TableInfo.getTableInfo(rs.getMetaData());
    }

    @Override
    public void executeArbitraryStatement(String command) throws SQLException {
        Connection connection = DriverManager.getConnection(url, properties);
        Statement statement = connection.createStatement();
        statement.execute(command);
    }

}
