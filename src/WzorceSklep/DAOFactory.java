package WzorceSklep;

import WzorceSklep.Data.DataAdapter.DataAdapterFactory;
import WzorceSklep.Data.Hurtownia.Hurtownia;
import WzorceSklep.Data.Hurtownia.HurtowniaDAO;
import WzorceSklep.Data.Klient.Klient;
import WzorceSklep.Data.Klient.KlientDAO;
import WzorceSklep.Data.Pracownik.Pracownik;
import WzorceSklep.Data.Pracownik.PracownikDAO;

import java.sql.SQLException;

/**
 * Created with IntelliJ IDEA.
 * User: KrzysztofD
 * Date: 24.05.13
 * Time: 22:57
 * To change this template use File | Settings | File Templates.
 */
public class DAOFactory {


    public static DataAccessObject<Klient> getKlientDAO() throws SQLException {
        return new KlientDAO(DataAdapterFactory.getDatabaseAdapter());
    }

    public static DataAccessObject<Pracownik> getPracownikDAO() throws SQLException {
        return new PracownikDAO(DataAdapterFactory.getDatabaseAdapter());
    }

    public static DataAccessObject<Hurtownia> getHurtowniaDAO() throws SQLException {
        return new HurtowniaDAO(DataAdapterFactory.getDatabaseAdapter());
    }
}
