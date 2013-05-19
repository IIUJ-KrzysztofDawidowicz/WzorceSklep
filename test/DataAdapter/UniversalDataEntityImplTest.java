package DataAdapter;

import org.junit.Test;

import java.sql.ResultSet;
import java.util.List;

import static org.easymock.EasyMock.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class UniversalDataEntityImplTest
{

    private final String tableName = "Test Table";
    private final String[] columnNames = new String[] {"ID", "Name"};
    private final Class[] columnTypes = new Class[] {Integer.class, String.class};

    @Test
    public void testConvertToUniversal() throws Exception
    {
        ResultSet resultSet = createMock(ResultSet.class);
        expect(resultSet.getMetaData())
                .andStubReturn(TableInfoTest.getMockResultSetMetaData(tableName, columnNames, columnTypes));
        int[] idColumn = new int[]{1,2,3};
        String[] nameColumn = new String[] {"First", "Second", "Third"};

        for (int i = 0; i < idColumn.length; i++) {
            expect(resultSet.next()).andReturn(true);
            expect(resultSet.getObject(0)).andReturn(idColumn[i]);
            expect(resultSet.getObject(1)).andReturn(nameColumn[i]);
        }
        expect(resultSet.next()).andReturn(false);

        replay(resultSet);

        List<UniversalDataEntity> wynik = UniversalDataEntityImpl.convertToUniversal(resultSet);
        int i = 0;
        for(UniversalDataEntity entity: wynik)
        {
            assertEquals(idColumn[i], entity.getValue("ID"));
            assertEquals(idColumn[i], entity.getValue(0));
            assertEquals(nameColumn[i], entity.getValue(1));
            assertEquals(nameColumn[i], entity.getValue("Name"));
        }
    }

    @Test
    public void testGetValue() throws Exception
    {
        TableInfoTest.createTableInfo(tableName, columnNames, columnTypes);
        UniversalDataEntity entity = new UniversalDataEntityImpl(tableName);
        entity.setValue("ID", 1);
        entity.setValue("Name", "First");
        assertEquals(1, entity.getValue("ID"));
        assertEquals("First", entity.getValue("Name"));
        assertEquals(columnTypes[0], entity.getValue(0).getClass());
        assertEquals(columnTypes[1], entity.getValue(1).getClass());
    }

    @Test
    public void testValueChecking() throws Exception
    {
        TableInfoTest.createTableInfo(tableName, columnNames, columnTypes);
        UniversalDataEntity entity = new UniversalDataEntityImpl(tableName);
        boolean exceptionCaught = false;
        //Kolumna ID, typ int
        try
        {
            entity.setValue("ID", "Nie int");
        }
        catch (IllegalArgumentException e)
        {
            exceptionCaught = true;
        }
        assertTrue(exceptionCaught);
        exceptionCaught = false;
        try {
            entity.setValue("Name", 1);
        }
        catch (IllegalArgumentException e)
        {
            exceptionCaught = true;
        }
        assertTrue(exceptionCaught);
    }

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
