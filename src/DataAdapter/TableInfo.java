package DataAdapter;

import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * Udostępnia informacje o tabeli w bazie danych. Używane przez TableRow i DatabaseAdapter.
 */
class TableInfo
{

    TableInfo(ResultSetMetaData metaData) throws SQLException  {
        tableName = metaData.getTableName(1);
        this.columns = new String[metaData.getColumnCount()];
        valueTypes = new HashMap<String, Class>();
        Class columnType = null;
        String columnTypeName;
        for (int i = 0;i< this.columns.length;i++)
        {
            this.columns[i] = metaData.getColumnName(i+1); //Zwracany string zaczyna się od "class " i dopiero jest nazwa klasy.
            columnTypeName = metaData.getColumnClassName(i+1);
            if(columnTypeName.startsWith("class "))
                columnTypeName=columnTypeName.substring("class ".length());
            if(primitives.containsKey(columnTypeName))
                columnType = primitives.get(columnTypeName);
            else
            {
                try {
                    columnType = Class.forName(columnTypeName);
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                }
            }
            valueTypes.put(columns[i], columnType);
        }
        cache.put(tableName,this);
    }

    String[] getColumns()
    {
        return columns.clone();
    }

    Class getValueType(String columnName) throws IllegalArgumentException
    {
        if(!valueTypes.containsKey(columnName))
        {
            throw new IllegalArgumentException("W tabeli " + tableName + " nie istnieje kolumna o nazwie " + columnName);
        }
        return valueTypes.get(columnName);
    }

    static TableInfo getTableInfo(String tableName) throws SQLException {
        if(!cache.containsKey(tableName))
            DataAdapterFactory.getDatabaseAdapter().createTableInfo(tableName);
        return cache.get(tableName);
    }

    /**
     * Tworzy obiekt TableInfo na podstawie podanych metadanych.
     * Jeśli obiekt dla danej tabeli jest już w cache, jest on zwracany - nie jest .
     * @param metaData
     * @return
     * @throws SQLException Jeśli w bazie danych zaszedł błąd.
     */
    public static TableInfo getTableInfo(ResultSetMetaData metaData) throws SQLException {
        String tableName = metaData.getTableName(1);
        if(cache.containsKey(tableName))
            return cache.get(tableName);
        TableInfo tableInfo = new TableInfo(metaData);
        cache.put(tableName,tableInfo);
        return tableInfo;
    }
    //</editor-fold>

    //<editor-fold desc="Pola">
    public final String tableName;
    private String[] columns;
    private final Map<String,Class> valueTypes;
    private final static Map<String,TableInfo> cache = new HashMap<String, TableInfo>();
    /**
     * Mapuje nazwy do typów prymitywnych - wszystkie inne klasy można dostać z Class.forName.
     * Używany do tworzenia valueTypes.
     */
    private final static Map<String, Class> primitives;
    static
    {
        primitives = new HashMap<String, Class>();
        primitives.put(boolean.class.toString(),Boolean.class);
        primitives.put(byte.class.toString(),Byte.class);
        primitives.put(char.class.toString(),Character.class);
        primitives.put(int.class.toString(),Integer.class);
        primitives.put(short.class.toString(),Short.class);
        primitives.put(long.class.toString(),Long.class);
        primitives.put(float.class.toString(),Float.class);
        primitives.put(double.class.toString(),Double.class);
        primitives.put(String.class.toString(),String.class);
    }

    public int getColumnCount() {
        return columns.length;
    }

    public static boolean hasInfo(String tableName) {
        return cache.containsKey(tableName);
    }
    //</editor-fold>
}
