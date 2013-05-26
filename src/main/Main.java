package main;

import DataAdapter.DataAdapterFactory;
import DataAdapter.DatabaseAdapter;
import GUI.Admin;

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
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Admin(null).setVisible(true);
            }
        });
/*        DatabaseAdapter adapter = DataAdapterFactory.getDatabaseAdapter();
        String sql = "";
        try {
            adapter.executeArbitraryStatement(sql);
        } catch (SQLException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }*/
    }
}
