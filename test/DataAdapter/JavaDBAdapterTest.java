package DataAdapter;

import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;
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
        for(UniversalDataEntity entity: adapter.select(tableName,"",""))
            System.out.print(entity);
    }

    @Test
    public void testInsert() throws Exception {

    }

    @Test
    public void testUpdate() throws Exception {
        fail("Not implemented.");
    }

    @Test
    public void testDelete() throws Exception {
        fail("Not implemented.");
    }

    @Test
    public void testCreateTableInfo() throws Exception {
        fail("Not implemented.");
    }
}
