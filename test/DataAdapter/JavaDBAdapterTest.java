package DataAdapter;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.sql.SQLException;
import java.util.List;

import static DataAdapter.DBTestUtils.*;
import static org.junit.Assert.*;

/**
 * Created with IntelliJ IDEA.
 * User: KrzysztofD
 * Date: 21.05.13
 * Time: 19:22
 * To change this template use File | Settings | File Templates.
 */
public class JavaDBAdapterTest {

    private DatabaseAdapter adapter;

    //@Test
    public void testContsructor() throws Exception {
        String statement = "CREATE table APP.TestTable (\n" +
                "    ID          INTEGER NOT NULL \n" +
                "                PRIMARY KEY GENERATED ALWAYS AS IDENTITY \n" +
                "                (START WITH 1, INCREMENT BY 1),\n" +
                "    NAME    VARCHAR(30)) ";
        adapter.executeArbitraryStatement(statement);
    }

    @Before
    public void setUp()
    {
        adapter = DataAdapterFactory.getDatabaseAdapter();
    }

    @Test
    public void testSelect() throws Exception {
        List<UniversalDataEntity> entityList = getTestEntities();
        compareEntityLists(entityList);
        adapter.delete(tableName, 1);
        entityList.remove(0);
        compareEntityLists(entityList);
    }

    private void compareEntityLists(List<UniversalDataEntity> entityList) throws SQLException, ClassNotFoundException {
        int i = 0;
        for(UniversalDataEntity entity: adapter.select(tableName,"",""))
        {
            UniversalDataEntity entity2 = entityList.get(i);
            assertTrue(String.format("Różnica między\n%s\n%s", entity, entity2), equalValues(entity, entity2));
//            assertEquals(entity.toString(), entityList.get(i).toString());
            i++;
        }
    }

    private List<UniversalDataEntity> getTestEntities() throws SQLException {
        int[] idColumn = new int[]{1,2,3};
        String[] nameColumn = new String[] {"First", "Second", "Third"};
        List<UniversalDataEntity> entityList = UniversalDataEntityFactory.convertToUniversal(getMockResultSet(idColumn, nameColumn));
        adapter.insert(entityList);
        return entityList;
    }

    @After
    public void deleteAll() throws Exception
    {
        for(UniversalDataEntity entity: adapter.selectAll(tableName))
        {
            adapter.delete(tableName,(Integer) entity.getValue("ID"));
        }
    }

    //@Test
    public void testUpdate() throws Exception {
        fail("Not implemented.");
    }

}
