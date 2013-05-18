package DataAdapter;

import main.FactoryFactory;

import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * Udostępnia informacje o tabeli w bazie danych. Używane przez UniversalDataEntity i DatabaseAdapter.
 */
class TableInfo
{

    public TableInfo(String tableName) {
        this.tableName = tableName;
        this.valueTypes = new HashMap<String, Class>();
    }

    String[] getColumns()
    {
        return columns.clone();
    }

    Class getValueType(String columnName) throws IllegalArgumentException
    {
        Class wynik = valueTypes.get(columnName);
        if(wynik==null)
        {
            throw new IllegalArgumentException("W tabeli " + tableName + " nie istnieje kolumna o nazwie " + columnName);
        }
        return wynik;
    }

    static TableInfo getTableInfo(String tableName) throws IllegalArgumentException {
        TableInfo wynik = cache.get(tableName);
        if(wynik==null)
        {
            wynik = adapter.getTableInfo(tableName);
            if(wynik==null)
            {
                throw new IllegalArgumentException("Nie istnieje tabela o tej nazwie.");
            }
            cache.put(tableName,wynik);
        }
        return wynik;
    }
    //</editor-fold>

    //<editor-fold desc="Pola">
    public final String tableName;
    private String[] columns;
    private Map<String,Class> valueTypes;
    private static Map<String,TableInfo> cache = new HashMap<String, TableInfo>();
    /**
     * Mapuje nazwy do typów prymitywnych - wszystkie inne klasy można dostać z Class.forName.
     */
    private final static Map<String, Class> primitives;
    static
    {
        primitives = new HashMap<String, Class>();
        primitives.put(boolean.class.toString(),boolean.class);
        primitives.put(byte.class.toString(),byte.class);
        primitives.put(char.class.toString(),char.class);
        primitives.put(int.class.toString(),int.class);
        primitives.put(short.class.toString(),short.class);
        primitives.put(long.class.toString(),long.class);
        primitives.put(float.class.toString(),float.class);
        primitives.put(double.class.toString(),double.class);
        primitives.put(String.class.toString(),String.class);
    }
    final static DatabaseAdapter adapter = FactoryFactory.getInstance().getDatabaseAdapterFactory().getDatabaseAdapter();

    public static TableInfo getTableInfo(ResultSetMetaData metaData) throws SQLException {
        TableInfo wynik;
        String tableName = metaData.getTableName(0);
        if(cache.containsKey(tableName))
        {
            wynik = cache.get(tableName);
        }
        else
        {
            wynik = new TableInfo(tableName);
            wynik.columns = new String[metaData.getColumnCount()];
            Class columnType = null;
            String columnTypeName;
            for (int i = 0;i< wynik.columns.length;i++)
            {
                wynik.columns[i] = metaData.getColumnName(i);
                columnTypeName = metaData.getColumnClassName(i);
                if(primitives.containsKey(columnTypeName))
                    columnType = primitives.get(columnTypeName);
                else
                {
                    try
                    {
                        columnType = Class.forName(metaData.getColumnClassName(i));
                    }
                    catch (ClassNotFoundException ignored)
                    {
                        ignored.printStackTrace();  //Zasadniczo nie może się zdarzyć, chyba że oficjalne klasy javy przstały działać.
                    }
                }
                wynik.valueTypes.put(wynik.columns[i], columnType);
            }
            cache.put(tableName,wynik);
        }
        return wynik;
    }
    //</editor-fold>
}
