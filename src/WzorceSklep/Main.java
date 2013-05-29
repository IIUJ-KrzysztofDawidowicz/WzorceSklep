package WzorceSklep;

import WzorceSklep.Data.DataAdapter.DataAdapterFactory;
import WzorceSklep.Data.DataAdapter.DatabaseAdapter;
import WzorceSklep.Data.Pracownik.Pracownik;
import WzorceSklep.GUI.Admin;
import WzorceSklep.GUI.LoginAction;
import WzorceSklep.GUI.Sprzedawca;

import java.awt.*;
import java.io.Serializable;
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
        String sql = "";
        try {
            DatabaseAdapter adapter = DataAdapterFactory.getDatabaseAdapter();
            adapter.executeArbitraryStatement(sql);
        } catch (SQLException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }

    public static class LoginAdminAction implements Serializable, LoginAction {
        public LoginAdminAction() {
        }

        public final void Login(Pracownik pracownik) {
            EventQueue.invokeLater(new Runnable() {
                public void run() {
                    new Admin().setVisible(true);
                }
            });
        }
    }

    public static class LoginHurtownikAction implements Serializable, LoginAction {
        public LoginHurtownikAction() {
        }

        @Override
        public void Login(Pracownik pracownik) throws Exception {
            throw new Exception("Nie ma Hurtownika.");
        }
    }

    public static class LoginSprzedawcaAction implements Serializable, LoginAction {
        public LoginSprzedawcaAction() {
        }

        public void Login(final Pracownik pracownik) {
            EventQueue.invokeLater(new Runnable() {
                public void run() {
                    new Sprzedawca(pracownik).setVisible(true);
                }
            });
        }
    }
}
