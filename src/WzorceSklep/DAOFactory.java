package WzorceSklep;

import WzorceSklep.Data.DataAdapter.DataAdapterFactory;
import WzorceSklep.Data.DataAdapter.DatabaseAdapter;
import WzorceSklep.Data.Hurtownia.Hurtownia;
import WzorceSklep.Data.Hurtownia.HurtowniaDAO;
import WzorceSklep.Data.Klient.Klient;
import WzorceSklep.Data.Klient.KlientDAO;
import WzorceSklep.Data.Pracownik.Pracownik;
import WzorceSklep.Data.Pracownik.PracownikDAO;
import WzorceSklep.Data.Produkt.Produkt;
import WzorceSklep.Data.Produkt.ProduktyDAO;

import java.sql.SQLException;

/**
 * Created with IntelliJ IDEA.
 * User: KrzysztofD
 * Date: 24.05.13
 * Time: 22:57
 * To change this template use File | Settings | File Templates.
 */
public class DAOFactory {


    private static DatabaseAdapter getDatabaseAdapter() throws SQLException {
        return DataAdapterFactory.getDatabaseAdapter();
    }

    public static DataAccessObject<Klient> getKlientDAO() throws SQLException {
        return new KlientDAO(getDatabaseAdapter());
    }

    public static DataAccessObject<Pracownik> getPracownikDAO() throws SQLException {
        return new PracownikDAO(getDatabaseAdapter());
    }

    public static DataAccessObject<Hurtownia> getHurtowniaDAO() throws SQLException {
        return new HurtowniaDAO(getDatabaseAdapter());
    }

    public static DataAccessObject<Produkt> getProduktyDAO() throws SQLException {
        return new ProduktyDAO(getDatabaseAdapter());
    }
}
