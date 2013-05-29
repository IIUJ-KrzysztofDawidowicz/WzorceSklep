package WzorceSklep;

import WzorceSklep.Data.Pracownik.Pracownik;
import WzorceSklep.Data.Pracownik.PracownikDAO;

import java.sql.SQLException;

public class PracownikDAOFactory {
    public PracownikDAOFactory() {
    }

    public DataAccessObject<Pracownik> getPracownikDAO() throws SQLException {
        return new PracownikDAO(DAOFactory.getDatabaseAdapter());
    }
}