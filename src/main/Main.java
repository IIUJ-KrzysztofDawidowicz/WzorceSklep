package main;

import DataAdapter.DataAdapterFactory;
import DataAdapter.DatabaseAdapter;

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
        DatabaseAdapter adapter = DataAdapterFactory.getDatabaseAdapter();
        String sql =
                "CREATE table APP.TestTable (\n" +
                "    ID          INTEGER NOT NULL,\n" +
                "    NAME    VARCHAR(30))";
        try {
            adapter.executeArbitraryStatement(sql);
        } catch (SQLException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }
}
