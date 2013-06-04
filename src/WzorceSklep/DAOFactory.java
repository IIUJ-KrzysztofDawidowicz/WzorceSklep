package WzorceSklep;

import WzorceSklep.Data.DataAdapter.DataAdapterFactory;
import WzorceSklep.Data.DataAdapter.DatabaseAdapter;
import WzorceSklep.Data.Hurtownia.Hurtownia;
import WzorceSklep.Data.Hurtownia.HurtowniaDAO;
import WzorceSklep.Data.Klient.Klient;
import WzorceSklep.Data.Klient.KlientDAO;
import WzorceSklep.Data.ResultSetConverter;
import WzorceSklep.Data.Pracownik.Pracownik;
import WzorceSklep.Data.Pracownik.PracownikDAO;
import WzorceSklep.Data.Produkt.Produkt;
import WzorceSklep.Data.Produkt.ProduktyDAO;
import WzorceSklep.Data.SingleTableDataGetter;
import WzorceSklep.Data.Statystyki.*;
import WzorceSklep.Data.TableDataGetter;
import WzorceSklep.Data.Zamowienie.*;

import java.sql.SQLException;

/**
 * Created with IntelliJ IDEA.
 * User: KrzysztofD
 * Date: 24.05.13
 * Time: 22:57
 * To change this template use File | Settings | File Templates.
 */
public class DAOFactory {


    public static final String STATYSTYKA_PRODUKTY = "StatystykaProdukty";
    public static final String ZAMOWIENIA_KLIENTA_VIEW = "ZamowieniaKlientaView";
    public static final String ZAMOWIENIA_HURTOWNI_VIEW = "ZamowieniaHurtowniView";
    public static final String STATYSTYKA_HURTOWNIA = "StatystykaHurtownia";
    public static final String STATYSTYKA_KLIENT = "StatystykaKlient";

    public static DatabaseAdapter getDatabaseAdapter() throws SQLException {
        return DataAdapterFactory.getDatabaseAdapter();
    }

    public static DataAccessObject<Produkt> getProduktyDAO() throws SQLException {
        return new ProduktyDAO(getDatabaseAdapter());
    }

    public DataAccessObject<Hurtownia> getHurtowniaDAO() throws SQLException {
        return new HurtowniaDAO(getDatabaseAdapter());
    }

    public DataAccessObject<ZamowienieKlienta> getZamowieniaKllientaDAO() throws SQLException {
        return new ZamowienieKlientaDAO(getDatabaseAdapter());
    }

    public DataAccessObject<Pracownik> getPracownikDAO() throws SQLException {
        return new PracownikDAO(getDatabaseAdapter());
    }

    public DataAccessObject<Klient> getKlientDAO() throws SQLException {
        return new KlientDAO(getDatabaseAdapter());
    }

    public DataAccessObject<ZamowienieHurtowni> getZamownieniaHurtowniDAO() throws SQLException {
        return new ZamownieniaHurtowniDAO(getDatabaseAdapter(),
                getHurtowniaDAO(), getProduktyDAO());
    }

    public TableDataGetter<? extends Statystyki> getStatystykiGetter(String tableName) throws SQLException {
        ResultSetConverter dataConverter;
        if(tableName.startsWith(STATYSTYKA_PRODUKTY))
            dataConverter = new StatystykiProduktyDataConverter(tableName);
        else if(tableName.startsWith(STATYSTYKA_HURTOWNIA))
            dataConverter = new StatystykiHurtowniaDataConverter(tableName);
        else if(tableName.startsWith(STATYSTYKA_KLIENT))
            dataConverter = new StatystykaKlientDataConverter(tableName);
        else
            throw new IllegalArgumentException(tableName + " nie jest rozpoznawaną tabelą.");
        return new SingleTableDataGetter(dataConverter, getDatabaseAdapter(), tableName);
    }

    public TableDataGetter<ZamowienieKlienta> getZamowieniaKllientaGetter() throws SQLException {
        return new SingleTableDataGetter<ZamowienieKlienta>(new ZamowienieKlientaResultSetConverter(), getDatabaseAdapter(), ZAMOWIENIA_KLIENTA_VIEW);
    }

    public TableDataGetter<ZamowienieHurtowni> getZamownieniaHurtowniGetter() throws SQLException {
        return new SingleTableDataGetter<ZamowienieHurtowni>(new ZamowienieHurtowniViewResultSetConverter(), getDatabaseAdapter(), ZAMOWIENIA_HURTOWNI_VIEW);
/*
        return new ZamownieniaHurtowniDAO(getDatabaseAdapter(),
                getHurtowniaDAO(), getProduktyDAO());
*/
    }

    public TableDataGetter<Klient> getKlientGetter() throws SQLException {
        return new KlientDAO(getDatabaseAdapter());
    }

    public static TableDataGetter<Produkt> getProduktyGetter() throws SQLException {
        return new ProduktyDAO(getDatabaseAdapter());
    }

    public TableDataGetter<Pracownik> getPracownikGetter() throws SQLException {
        return new PracownikDAO(getDatabaseAdapter());
    }

    public static TableDataGetter<Hurtownia> getHurtowniaGetter() throws SQLException {
        return new HurtowniaDAO(getDatabaseAdapter());
    }

    public TableDataGetter<Produkt> getProduktGetter() throws SQLException {
        return new ProduktyDAO(getDatabaseAdapter());
    }

    public DataAccessObject<Produkt> getProduktDAO() throws SQLException {
        return new ProduktyDAO(getDatabaseAdapter());
    }

}
