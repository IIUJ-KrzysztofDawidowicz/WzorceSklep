package WzorceSklep.Data.DataAdapter;


import org.apache.commons.lang3.StringUtils;

import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Reprezentuje dowolny obiekt trzymany w wierszu tabeli.
 */
public class BasicTableRow implements TableRow
{
    //<editor-fold desc="Metody publiczne">

    BasicTableRow(String tableName) throws SQLException {
        tableInfo = TableInfo.getTableInfo(tableName);
        if(tableInfo==null)
            throw new SQLException(String.format("Table %s not found.", tableName));
        values = new LinkedHashMap<String, Object>();
        for(String columnName: getColumns())
            values.put(columnName, null);
    }

    @Override
    public String[] getColumns() {
        return tableInfo.getColumns();
    }

    @Override
    public Object[] getValues()
    {
        return values.values().toArray();
/*        Set<Map.Entry<String,Object>> entrySet = values.entrySet();
        Object[] wynik = new Object[entrySet.size()];
        int i = 0;
        for(Object o: entrySet)
            wynik[i++] = o;
        return wynik;*/
    }

    /**
     * Zwraca zawartość dla danej kolumny.
     * @param columnName Nazwa kolumny.
     * @return Zawartość dla danej kolumny. Null w przypadku nieistniejącej kolumny
     * (null może być także poprawną wartością kolumny!)
     */
    @Override
    public Object getValue(String columnName)
    {
        if(!values.containsKey(columnName))
            return new IllegalArgumentException(String.format("W tabeli %s nie ma kolumny o nazwie %s", getTableName(), columnName));
        return values.get(columnName);
    }

    @Override
    public void setValue(String columnName, Object value)
    {
//        try
//        {
//            value.getClass().cast(tableInfo.getValueType(columnName).newInstance());
//        }
//        catch (ClassCastException e)
        if(!tableInfo.getValueType(columnName).equals(value.getClass()))
        {
            throw new IllegalArgumentException("Próba przypisania wartości typu " + value.getClass()
            + " do kolumny " + columnName + " o typie " + tableInfo.getValueType(columnName));
        }
        values.put(columnName,value);
    }

    @Override
    public Object getValue(int columnIndex)
    {
        return getValue(tableInfo.getColumns()[columnIndex]);
    }

    @Override
    public void setValue(int columnIndex, Object value)
    {
        setValue(tableInfo.getColumns()[columnIndex], value);
    }

    @Override
    public int getColumnCount() {
        return tableInfo.getColumnCount();
    }

    @Override
    public String toString()
    {
        StringUtils.join(getColumns(), ", ");
        return String.format("Wiersz tabeli: %s\n%s\n%s",
                getTableName(),
                StringUtils.join(getColumns(), ", "),
                StringUtils.join(getValues(), ", "));
    }
    //</editor-fold>

    final TableInfo tableInfo;
    final Map<String,Object> values;

    public String getTableName() {
        return tableInfo.tableName;
    }

    @Override
    public TableInfo getTableInfo() {
        return tableInfo;
    }

}
