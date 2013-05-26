package DataAdapter;

import org.apache.commons.lang3.StringUtils;

import java.sql.*;
import java.util.LinkedList;
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
    public ResultSet select(String tableName, String lookFor, String orderBy) throws SQLException, ClassNotFoundException {
        validateOrderBy(tableName, orderBy);
        Connection conn = DriverManager.getConnection(url);
        Statement stmt = conn.createStatement();
        String selectCommand = String.format("SELECT * from %s WHERE %s ORDER BY %s", tableName, createWhereClause(tableName, lookFor), orderBy);

        ResultSet rs;
        try {
            rs = stmt.executeQuery(selectCommand);
        } catch (SQLException e) {
            throw new IllegalArgumentException("Błąd przy próbie odczytu danych z tabeli " + tableName
                    + ", prawdopodobnie nieprawidłowa nazwa.", e);
        }

        return rs;
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
        Connection conn = DriverManager.getConnection(url);
        Statement stmt = conn.createStatement();
        String selectCommand = String.format("SELECT * from %s ORDER BY %s", tableName, orderBy);

        ResultSet rs;
        try {
            rs = stmt.executeQuery(selectCommand);
        } catch (SQLException e) {
            throw new IllegalArgumentException("Błąd przy próbie odczytu danych z tabeli " + tableName
                    + ", prawdopodobnie nieprawidłowa nazwa.", e);
        }

        return rs;

    }

    @Override
    public int getMaxValueForColumn(String tableName, String columnName) throws SQLException {
        Connection conn = DriverManager.getConnection(url);
        Statement stmt = conn.createStatement();
        String selectCommand = String.format("SELECT MAX(%s) from %s", columnName, tableName);

        ResultSet rs;
        try {
            rs = stmt.executeQuery(selectCommand);
        } catch (SQLException e) {
            throw new IllegalArgumentException("Błąd przy próbie odczytu danych z tabeli " + tableName
                    + ", prawdopodobnie nieprawidłowa nazwa.", e);
        }
        rs.next();
        return rs.getInt(1);
    }

    @Override
    public TableRow selectOne(String tableName, int id) throws SQLException {
        Connection conn = DriverManager.getConnection(url);
        Statement stmt = conn.createStatement();
        String selectCommand = String.format("SELECT * from %s WHERE ID = %s", tableName, id);

        ResultSet rs;
        try {
            rs = stmt.executeQuery(selectCommand);
        } catch (SQLException e) {
            throw new IllegalArgumentException("Błąd przy próbie odczytu danych z tabeli " + tableName
                    + ", prawdopodobnie nieprawidłowa nazwa.", e);
        }

        return TableRowFactory.convertToUniversal(rs).get(0);
    }

    public ResultSet selectAll(String tableName) throws SQLException {
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

        return rs;
    }

    @Override
    public void insert(TableRow nowy) throws SQLException {
        Connection connection = DriverManager.getConnection(url,properties);
        String[] processedValues = proceesValuesForStatement(nowy.getValues());
        String sql = String.format("INSERT INTO %s (%s) VALUES (%s)",
                nowy.getTableName(),
                StringUtils.join(nowy.getColumns(), ", "),
                StringUtils.join(processedValues, ", ")
        );
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.execute();
    }

    private static String[] proceesValuesForStatement(Object[] values) throws UnsupportedOperationException {
        String[] wynik = new String[values.length];
        for (int i = 0; i < wynik.length; i++) {
            if(values[i] == null)
                throw new UnsupportedOperationException("TableRow nie jest wypełniony.");
            if(values[i].getClass()==String.class)
                wynik[i] = String.format("'%s'", values[i]);
            else
                wynik[i] = values[i].toString();
        }
        return wynik;
    }

    @Override
    public void update(TableRow nowy) throws SQLException {
        Connection conn = DriverManager.getConnection(url);
        Statement stmt = conn.createStatement();
        String command = String.format(
                "UPDATE %s SET %s WHERE ID = %s",
                nowy.getTableName(),
                createSetClause(nowy),
                nowy.getValue("ID"));
        stmt.execute(command);
    }

    private static String createSetClause(TableRow entity) {
        String[] columns = entity.getColumns();
        String[] values = proceesValuesForStatement(entity.getValues());
        List<String> clauses = new LinkedList<String>();
        for (int i = 0; i < columns.length; i++) {
            if(!columns[i].equals("ID"))
                clauses.add(String.format("%s = %s", columns[i], values[i]));
        }
        return StringUtils.join(clauses, ", ");
    }

    @Override
    public void delete(String tableName, int id) throws SQLException {
        Connection conn = DriverManager.getConnection(url);
        Statement stmt = conn.createStatement();
        String command = String.format("DELETE from %s WHERE ID = %s", tableName, id);
        stmt.execute(command);

    }


    private void validateOrderBy(String tableName, String orderBy) throws SQLException {
        String[] columnNames = TableInfo.getTableInfo(tableName).getColumns();
        boolean isOrderByValid = false;
        for(String column: columnNames)
            if(column.equals(orderBy))
            {
                isOrderByValid = true;
                break;
            }
        if(!isOrderByValid)
            throw new IllegalArgumentException(String.format("Próba sortowania po kolumnie %s która nie istinieje w tabeli %s", orderBy, tableName));
    }

    @Override
    public void createTableInfo(String tableName) throws SQLException {
        Connection conn = DriverManager.getConnection(url);
        Statement stmt = conn.createStatement();
        String selectCommand = String.format("SELECT * from %s WHERE 0=1", tableName);

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
    public void executeArbitraryStatement(String command) throws SQLException {
        Connection connection = DriverManager.getConnection(url, properties);
        Statement statement = connection.createStatement();
        statement.execute(command);
    }

    @Override
    public void insert(List<TableRow> entityList) throws SQLException {
        for(TableRow entity: entityList)
            insert(entity);
    }

}
