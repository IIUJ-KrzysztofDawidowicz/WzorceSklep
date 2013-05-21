package main;

import DataAdapter.DataAdapterFactory;
import DataAdapter.DatabaseAdapter;
import DataAdapter.UniversalDataEntity;

import java.sql.SQLException;

/**
 * Created with IntelliJ IDEA.
 * User: KrzysztofD
 * Date: 18.05.13
 * Time: 17:33
 * To change this template use File | Settings | File Templates.
 */
public class Main
{
    public static void main(String[] args)
    {
        DatabaseAdapter adapter = DataAdapterFactory.getDatabaseAdapter("testDB.db");
        String sMakeTable = "";//"CREATE TABLE dummy (id numeric, response text)";
        String sMakeInsert = "INSERT INTO dummy VALUES(1,'Hello from the database')";
        String sMakeSelect = "SELECT response from dummy";

        try {
            for(UniversalDataEntity entity: adapter.select("dummy", null, null))
                System.out.println(entity);
        } catch (SQLException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (ClassNotFoundException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }
}
