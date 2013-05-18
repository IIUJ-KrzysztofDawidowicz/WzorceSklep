package DataAdapter;

import org.junit.Test;

import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import static org.easymock.EasyMock.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class TableInfoTest {

    @Test
    public void testGetTableInfo() throws Exception
    {
        TableInfo tableInfo;
        final String tableName = "Test Table";
        final String[] columnNames = new String[] {"ID", "Name"};
        final Class[] columnTypes = new Class[] {int.class, String.class};
        tableInfo = createTableInfo(tableName, columnNames, columnTypes);
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

    private TableInfo createTableInfo(String tableName, String[] columnNames, Class[] columnTypes) throws SQLException {
        if(columnNames.length!=columnTypes.length)
            throw new IllegalArgumentException("Nie zgadzają się długości tablic typów danych i nazw kolumn.");

        TableInfo wynik;
        ResultSetMetaData metaData = createMock(ResultSetMetaData.class);
        expect(metaData.getTableName(0)).andReturn(tableName);
        expect(metaData.getColumnCount()).andStubReturn(columnNames.length);
        for(int i = 0; i<columnNames.length; i++)
        {
            expect(metaData.getColumnClassName(i)).andStubReturn(columnTypes[i].toString());
            expect(metaData.getColumnName(i)).andStubReturn(columnNames[i]);
        }
        replay(metaData);

        wynik = TableInfo.getTableInfo(metaData);
        return wynik;
    }
}

