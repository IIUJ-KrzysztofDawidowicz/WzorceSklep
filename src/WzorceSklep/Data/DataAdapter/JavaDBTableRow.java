package WzorceSklep.Data.DataAdapter;

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
        if (value == null) throw new IllegalArgumentException(
                String.format("Jako wartość dla kolumny %s został przekazany null.", columnName));

        Class type;
//        try {
        if (!tableInfo.hasColumn(columnName)) {
            if(tableInfo.hasColumn(columnName.toUpperCase())) {
                columnName=columnName.toUpperCase();
            }
            else
                throw new IllegalArgumentException(columnName + " nie jest prawidłową kolumną tablicy " + getTableName());
        }
//        } catch (IllegalArgumentException e) {
        type = tableInfo.getValueType(columnName);

//        }
        if(!type.equals(value.getClass()))
        {
            throw new IllegalArgumentException(
                    String.format(
                            "Próba przypisania wartości typu %s do kolumny %s o typie %s",
                            value.getClass(), columnName, type));
        }
        super.values.put(columnName,value);
    }
}
