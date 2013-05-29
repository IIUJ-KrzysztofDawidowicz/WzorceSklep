package WzorceSklep;

import WzorceSklep.Data.Klient.Klient;
import WzorceSklep.Data.Klient.KlientDAO;

import java.sql.SQLException;

public class KlientDAOFactory {
    public KlientDAOFactory() {
    }

    public DataAccessObject<Klient> getKlientDAO() throws SQLException {
        return new KlientDAO(DAOFactory.getDatabaseAdapter());
    }
}