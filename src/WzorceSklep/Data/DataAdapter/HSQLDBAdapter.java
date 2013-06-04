package WzorceSklep.Data.DataAdapter;

import WzorceSklep.Util;
import org.apache.commons.lang3.StringUtils;

import java.sql.*;
import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: KrzysztofD
 * Date: 31.05.13
 * Time: 20:35
 * To change this template use File | Settings | File Templates.
 */
public class HSQLDBAdapter implements DatabaseAdapter {

    private final Properties properties;
    private final String url;

    public HSQLDBAdapter(String dbName, Properties properties) throws ClassNotFoundException, SQLException {
        this.properties = properties;
        Class.forName("org.hsqldb.jdbcDriver");
        url = "jdbc:hsqldb:file:" + Util.getAbsolutePathOfCurrentDirectory();
    }

    @Override
    public ResultSet select(String tableName, String lookFor, String orderBy) throws SQLException {
        orderBy = validateOrderBy(tableName, orderBy);
        String selectCommand = String.format("SELECT * from %s WHERE %s ORDER BY %s", tableName, createWhereClause(tableName, lookFor), orderBy);

        return getResultSet(selectCommand);
    }

    private static String createWhereClause(String tableName, String lookFor) throws SQLException {
        TableInfo tableInfo = TableInfo.getTableInfo(tableName);
        String[] columns = tableInfo.getColumns();
        List<String> clauses = new ArrayList<String>();
        for (String column : columns) {
            if (tableInfo.getValueType(column) == String.class)
                clauses.add(String.format("%s like '%%%s%%'", column, lookFor));
        }
        return StringUtils.join(clauses, " or ");
    }

    @Override
    public ResultSet select(String tableName, String orderBy) throws SQLException {
        if(orderBy.equals(""))
            return selectAll(tableName);
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
        Map<String, Object> valueMap = removeColumnsWithNullValues(toMatch);
        String[] values = proceesValuesForStatement(valueMap.values().toArray());
        String[] columns = valueMap.keySet().toArray(new String[valueMap.size()]);
        List<String> clauses = new LinkedList<String>();
        for (int i = 0; i < columns.length; i++) {
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
            Connection conn = DriverManager.getConnection(url, properties);
            rs = conn.prepareStatement(selectCommand).executeQuery();
        } catch (SQLException e) {
            throw new IllegalArgumentException("Błąd przy próbie odczytu danych z tabeli (komenda " + selectCommand
                    + "), prawdopodobnie nieprawidłowa nazwa.", e);
        }
        return rs;
    }

    @Override
    public void insert(TableRow nowy) throws SQLException {
        Map<String, Object> valueMap = removeColumnsWithNullValues(nowy);
        String[] values = proceesValuesForStatement(valueMap.values().toArray());
        String[] columns = valueMap.keySet().toArray(new String[valueMap.size()]);
        String sql = String.format("INSERT INTO %s (%s) VALUES (%s)",
                nowy.getTableName(),
                StringUtils.join(columns, ", "),
                StringUtils.join(values, ", ")
        );
        executeArbitraryStatement(sql);
    }

    private static String[] proceesValuesForStatement(Object[] values) throws UnsupportedOperationException {
        String[] wynik = new String[values.length];
        for (int i = 0; i < wynik.length; i++) {
            if(values[i] == null)
                wynik[i] = "NULL";
            else if(values[i].getClass()==String.class || values[i].getClass()==java.sql.Date.class)
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
        executeArbitraryStatement(command);
    }

    private static String createSetClause(TableRow entity) {
        Map<String, Object> valueMap = removeColumnsWithNullValues(entity);
        String[] values = proceesValuesForStatement(valueMap.values().toArray());
        String[] columns = valueMap.keySet().toArray(new String[valueMap.size()]);
        List<String> clauses = new LinkedList<String>();
        for (int i = 0; i < columns.length; i++) {
            if(!columns[i].equals("ID"))
                clauses.add(String.format("%s = %s", columns[i], values[i]));
        }
        return StringUtils.join(clauses, ", ");
    }

    private static Map<String, Object> removeColumnsWithNullValues(TableRow entity) {
        Map<String, Object> valueMap = entity.getValueMap();
        for(Iterator<Map.Entry<String,Object>> iterator = valueMap.entrySet().iterator();
            iterator.hasNext();)
        {
            Map.Entry<String,Object> entry = iterator.next();
            if(entry.getValue()==null)
                iterator.remove();
        }
        return valueMap;
    }

    @Override
    public void delete(String tableName, int id) throws SQLException {
        String command = String.format("DELETE from %s WHERE ID = %s", tableName, id);
        getStatement().execute(command);
    }

    private Statement getStatement() throws SQLException {
        Connection conn = DriverManager.getConnection(url, properties);
        return conn.createStatement();
    }


    private String validateOrderBy(String tableName, String orderBy) throws SQLException {
        if(!TableInfo.getTableInfo(tableName).hasColumn(orderBy)) {
            if(TableInfo.getTableInfo(tableName).hasColumn(orderBy.toUpperCase()))
                return orderBy.toUpperCase();
            throw new IllegalArgumentException(String.format("Próba sortowania po kolumnie %s która nie istinieje w tabeli %s", orderBy, tableName));
        }
        return orderBy;
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
        try {
            statement.execute(command);
        } catch (SQLException e) {
            throw new SQLException("Błąd przy próbie wykonania polecenia " + command, e);
        }
    }
}
