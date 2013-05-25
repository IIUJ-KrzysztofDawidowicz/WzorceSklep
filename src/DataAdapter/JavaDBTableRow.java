package DataAdapter;

import java.sql.SQLException;

/**
 * Created with IntelliJ IDEA.
 * User: KrzysztofD
 * Date: 24.05.13
 * Time: 18:18
 * To change this template use File | Settings | File Templates.
 */
public class JavaDBTableRow extends BasicTableRow {
    JavaDBTableRow(String tableName) throws SQLException {
        super(tableName);
    }

    @Override
    public Object getValue(String columnName)
    {
        if(!values.containsKey(columnName)) {
            if (!values.containsKey(columnName.toUpperCase()))
                throw new IllegalArgumentException(String.format("W tabeli %s nie ma kolumny o nazwie %s", getTableName(), columnName));
            return values.get(columnName.toUpperCase());
        }
        return values.get(columnName);
    }


    @Override
    public Object getValue(int columnIndex)
    {
        return this.getValue(tableInfo.getColumns()[columnIndex]);
    }

    @Override
    public void setValue(int columnIndex, Object value)
    {
        this.setValue(tableInfo.getColumns()[columnIndex], value);
    }


    @Override
    public void setValue(String columnName, Object value) {

        Class type;
        try {
            type = tableInfo.getValueType(columnName);
        } catch (IllegalArgumentException e) {
            type = tableInfo.getValueType(columnName.toUpperCase());
        }
        if(!type.equals(value.getClass()))
        {
            throw new IllegalArgumentException("Próba przypisania wartości typu " + value.getClass()
                    + " do kolumny " + columnName + " o typie " + type);
        }
        super.values.put(columnName,value);
    }
}
