package DataAdapter;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Reprezentuje dowolny obiekt trzymany w wierszu tabeli.
 */
public class UniversalDataEntityImpl implements UniversalDataEntity
{
    //<editor-fold desc="Metody publiczne">

    public UniversalDataEntityImpl(String tableName)
    {
        this.tableInfo = TableInfo.getTableInfo(tableName);
        values = new HashMap<String, Object>();
    }

    @Override
    public String[] getColumns() {
        return tableInfo.getColumns();
    }

    @Override
    public Object[] getValues()
    {
        String[] columns = getColumns();
        Object[] wynik = new Object[columns.length];
        for (int i = 0; i< columns.length; ++i)
        {
            wynik[i] = getValue(columns[i]);
        }
        return wynik;
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
    //</editor-fold>

    private final TableInfo tableInfo;
    private final Map<String,Object> values;

    public static List<UniversalDataEntity> convertToUniversal(ResultSet resultSet) throws SQLException {

        TableInfo info = TableInfo.getTableInfo(resultSet.getMetaData());
        List<UniversalDataEntity> wynik = new LinkedList<UniversalDataEntity>();
        UniversalDataEntity entity;
        while (resultSet.next())
        {
            entity = new UniversalDataEntityImpl(info.tableName);
            for (int i = 0; i < entity.getColumnCount(); i++) {
                entity.setValue(i, resultSet.getObject(i));
            }
        }

        return wynik;
    }
}
