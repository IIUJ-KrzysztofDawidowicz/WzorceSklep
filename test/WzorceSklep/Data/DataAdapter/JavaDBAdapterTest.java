/*
package WzorceSklep.DataAccessObject.DataAdapter;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.sql.SQLException;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Properties;

import static WzorceSklep.DataAccessObject.DataAdapter.DBTestUtils.*;
import static org.junit.Assert.*;

*/
/**
 * Created with IntelliJ IDEA.
 * User: KrzysztofD
 * Date: 21.05.13
 * Time: 19:22
 * To change this template use File | Settings | File Templates.
 *//*

public class JavaDBAdapterTest {

    private DatabaseAdapter adapter;

    @Before
    public void setUp()
    {
        adapter = DataAdapterFactory.getDatabaseAdapter("testDB");
    }

    @Test
    public void testSelect() throws Exception {
        List<TableRow> entityList = getTestEntities();
        compareEntityLists(entityList);
        adapter.delete(tableName, 1);
        entityList.remove(0);
        compareEntityLists(entityList);
    }

*/
/*
    @Test
    public void testSelectWithSort() throws Exception
    {
        List<TableRow> entityList = getTestEntities();
        Collections.sort(entityList, new Comparator<TableRow>() {
            @Override
            public int compare(TableRow o1, TableRow o2) {
                return ((String)o1.getValue("Name")).compareTo((String)o2.getValue("Name"));
            }
        });
        int i = 0;
        for(TableRow entity: adapter.select(tableName,"Name"))
        {
            TableRow entity2 = entityList.get(i);
            assertTrue(String.format("Różnica między\n%s\n%s", entity, entity2), equalValues(entity, entity2));
//            assertEquals(entity.toString(), entityList.get(i).toString());
            i++;
        }
    }
*//*


*/
/*
    @Test
    public void testSelectWithWhere() throws Exception
    {
        List<TableRow> entityList = getTestEntities();
        entityList.remove(1);
        int i = 0;
        for(TableRow entity: adapter.select(tableName,"F","ID"))
        {
            TableRow entity2 = entityList.get(i);
            assertTrue(String.format("Różnica między\n%s\n%s", entity, entity2), equalValues(entity, entity2));
            i++;
        }
    }
*//*


    */
/*private void compareEntityLists(List<TableRow> entityList) throws SQLException, ClassNotFoundException {
        int i = 0;
        for(TableRow entity: adapter.selectAll(tableName))
        {
            TableRow entity2 = entityList.get(i);
            assertTrue(String.format("Różnica między\n%s\n%s", entity, entity2), equalValues(entity, entity2));
//            assertEquals(entity.toString(), entityList.get(i).toString());
            i++;
        }
    }*//*


    private List<TableRow> getTestEntities() throws SQLException {
        int[] idColumn = new int[]{1,2,3};
        String[] nameColumn = new String[] {"First", "Second", "Third"};
        List<TableRow> entityList = TableRowFactory.convertToTableRow(getMockResultSet(idColumn, nameColumn));
        adapter.insert(entityList);
        return entityList;
    }

    @After
    public void deleteAll() throws Exception
    {
        for(TableRow entity: adapter.selectAll(tableName))
        {
            adapter.delete(tableName,(Integer) entity.getValue("ID"));
        }
    }

    @Test
    public void testUpdate() throws Exception {
        List<TableRow> entityList = getTestEntities();
        TableRow entity = entityList.get(0);
        entity.setValue("Name", "Different name");
        adapter.update(entity);
        compareEntityLists(entityList);
    }

}
*/
