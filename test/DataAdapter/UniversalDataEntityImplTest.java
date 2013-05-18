package DataAdapter;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class UniversalDataEntityImplTest
{
    @Test
    public void testUDEConstructor() throws Exception
    {
        final String tableName = "Test Table";
        final String[] columnNames = new String[] {"ID", "Name"};
        final Class[] columnTypes = new Class[] {int.class, String.class};

        TableInfoTest.createTableInfo(tableName,columnNames,columnTypes);
        UniversalDataEntity entity = new UniversalDataEntityImpl(tableName);
        String[] gotColumns = entity.getColumns();
        for (int i = 0; i < columnNames.length; i++)
        {
            assertEquals(columnNames[i], gotColumns[i]);
        }
    }
}
