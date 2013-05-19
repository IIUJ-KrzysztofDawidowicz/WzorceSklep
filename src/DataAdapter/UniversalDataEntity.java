package DataAdapter;

/**
 * Reprezentuje dowolny obiekt trzymany w wierszu tabeli.
 */
public interface UniversalDataEntity
{
    public String[] getColumns();
    public Object[] getValues();
    public Object getValue(String columnName);
    public void setValue(String columnName, Object value);
    public Object getValue(int columnIndex);
    public void setValue(int columnIndex, Object value);

    int getColumnCount();
}
