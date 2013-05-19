package DataAdapter;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static DataAdapter.DBTestUtils.*;

public class TableInfoTest
{

    @Test
    public void testGetTableInfo() throws Exception
    {
        TableInfo tableInfo = DBTestUtils.createTableInfo(tableName, columnNames, columnTypes);
        String[] gotColumns = tableInfo.getColumns();
        for (int i = 0; i < columnNames.length; i++)
        {
            assertEquals(columnNames[i],gotColumns[i]);
        }

        for (int i = 0; i < columnNames.length; i++)
        {
            assertEquals(columnTypes[i], tableInfo.getValueType(columnNames[i]));
        }

        assertNotNull("Zawartość nie została odłożona do cache.", TableInfo.getTableInfo(tableName));
    }

}

