package DataAdapter;

import org.junit.Test;

import java.sql.ResultSet;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static DataAdapter.DBTestUtils.*;

public class UniversalDataEntityImplTest
{

    @Test
    public void testConvertToUniversal() throws Exception
    {
        int[] idColumn = new int[]{1,2,3};
        String[] nameColumn = new String[] {"First", "Second", "Third"};
        ResultSet resultSet = getMockResultSet(idColumn, nameColumn);

        List<UniversalDataEntity> wynik = UniversalDataEntityFactory.convertToUniversal(resultSet);
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
        createTableInfo(tableName, columnNames, columnTypes);
        UniversalDataEntity entity = UniversalDataEntityFactory.createUniversalDataEntity(tableName);
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
        DBTestUtils.createTableInfo(tableName, columnNames, columnTypes);
        UniversalDataEntity entity = UniversalDataEntityFactory.createUniversalDataEntity(tableName);
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
        DBTestUtils.createTableInfo(tableName, columnNames, columnTypes);
        UniversalDataEntity entity = UniversalDataEntityFactory.createUniversalDataEntity(tableName);
        String[] gotColumns = entity.getColumns();
        for (int i = 0; i < columnNames.length; i++)
        {
            assertEquals(columnNames[i], gotColumns[i]);

        }
    }

    @Test
    public void testToString() throws Exception
    {
        createTableInfo(tableName,columnNames,columnTypes);
        UniversalDataEntity entity = UniversalDataEntityFactory.createUniversalDataEntity(tableName);
        entity.setValue(0,1);
        entity.setValue(1,"First");
        String expected = String.format("Wiersz tabeli: %s\n%s, %s\n1, First", tableName, columnNames[0], columnNames[1]);
        assertEquals(expected, entity.toString());
    }
}
