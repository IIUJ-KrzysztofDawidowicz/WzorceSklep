package WzorceSklep.Data.DataAdapter;

import java.util.Map;
import java.util.Set;

/**
 * Reprezentuje dowolny obiekt trzymany w wierszu tabeli.
 */
public interface TableRow
{
    public String[] getColumns();
    public Object[] getValues();
    public Object getValue(String columnName);
    public void setValue(String columnName, Object value);
    public Object getValue(int columnIndex);
    public void setValue(int columnIndex, Object value);

    int getColumnCount();

    String getTableName();

    TableInfo getTableInfo();

    Map<String,Object> getValueMap();
}
