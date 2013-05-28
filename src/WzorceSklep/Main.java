package WzorceSklep;

import WzorceSklep.Data.DataAdapter.DataAdapterFactory;
import WzorceSklep.Data.DataAdapter.DatabaseAdapter;

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
        startProgram();
//        executeStatement();
    }

    private static void startProgram() {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new WzorceSklep.GUI.Login().setVisible(true);
            }
        });
    }

    private static void executeStatement() {
        String sql = "CREATE TABLE Produkt\n(\n    ID int PRIMARY KEY,\n\tTyp varchar(50) NOT NULL,\n\tCena int NOT NULL,\n\tNazwa varchar(50) NOT NULL,\n\tIlosc int NOT NULL,\n\tSpecyfikacja varchar(50) NOT NULL\n)";
        try {
            DatabaseAdapter adapter = DataAdapterFactory.getDatabaseAdapter();
            adapter.executeArbitraryStatement(sql);
        } catch (SQLException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }
}
