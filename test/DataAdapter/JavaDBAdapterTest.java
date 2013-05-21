package DataAdapter;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static DataAdapter.DBTestUtils.getMockResultSet;
import static DataAdapter.DBTestUtils.tableName;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

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
        int[] idColumn = new int[]{1,2,3};
        String[] nameColumn = new String[] {"First", "Second", "Third"};
        List<UniversalDataEntity> entityList = UniversalDataEntityFactory.convertToUniversal(getMockResultSet(idColumn,nameColumn));
        adapter.insert(entityList);
        int i = 0;
        for(UniversalDataEntity entity: adapter.select(tableName,"",""))
        {
            assertEquals(entity.toString(), entityList.get(i).toString());
            i++;
        }

/*        adapter.delete(tableName, 3);
        List<UniversalDataEntity> fromDB = adapter.select(tableName,"","");
        assertEquals(entityList.get(0), fromDB.get(0));
        assertEquals(entityList.get(1), fromDB.get(1));
        assertEquals(2, fromDB.size());*/
    }

    @Test
    public void testUpdate() throws Exception {
        fail("Not implemented.");
    }

    @Test
    public void testCreateTableInfo() throws Exception {
        fail("Not implemented.");
    }
}
