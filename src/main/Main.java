package main;

import DataAdapter.SqliteJDBCDatabaseAdapter;

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
        SqliteJDBCDatabaseAdapter adapter = new SqliteJDBCDatabaseAdapter("testDB.db");
        String sMakeTable = "";//"CREATE TABLE dummy (id numeric, response text)";
        String sMakeInsert = "INSERT INTO dummy VALUES(1,'Hello from the database')";
        String sMakeSelect = "SELECT response from dummy";

        try {
            System.out.println(adapter.createTable(sMakeTable, sMakeInsert, sMakeSelect));
        } catch (SQLException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }
}
