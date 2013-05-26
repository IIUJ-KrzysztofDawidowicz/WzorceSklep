package DataAccessObject;

import DataAdapter.DataAdapterFactory;
import DataEntity.*;

/**
 * Created with IntelliJ IDEA.
 * User: KrzysztofD
 * Date: 24.05.13
 * Time: 22:57
 * To change this template use File | Settings | File Templates.
 */
public class DAOFactory {


    public static DataAccessObject<Klient> getKlientDAO() {
        return new KlientDAO(DataAdapterFactory.getDatabaseAdapter());
    }

    public static DataAccessObject<Pracownik> getPracownikDAO() {
        return new PracownikDAO(DataAdapterFactory.getDatabaseAdapter());
    }
}
