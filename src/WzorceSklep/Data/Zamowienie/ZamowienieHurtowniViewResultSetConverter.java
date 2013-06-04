package WzorceSklep.Data.Zamowienie;

import WzorceSklep.Data.ResultSetConverter;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: KrzysztofD
 * Date: 04.06.13
 * Time: 09:59
 * To change this template use File | Settings | File Templates.
 */
public class ZamowienieHurtowniViewResultSetConverter implements ResultSetConverter<ZamowienieHurtowni> {
    @Override
    public List<ZamowienieHurtowni> convertResultSet(ResultSet set) throws SQLException {
        List<ZamowienieHurtowni> wynik = new ArrayList<ZamowienieHurtowni>();

        while (set.next())
        {
            ZamowienieHurtowni zamowienieHurtowni = new ZamowienieHurtowni();

            zamowienieHurtowni.getZamawiajacy().setNazwa(set.getString("NazwaHurtowni"));
            zamowienieHurtowni.getProduktZamowiony().nazwa= set.getString("NazwaProduktu");
            zamowienieHurtowni.getProduktZamowiony().typ=set.getString("TypProduktu");
            zamowienieHurtowni.setIlosc(set.getInt("Ilosc"));
            zamowienieHurtowni.setDataZamowienia(set.getDate("DataZamowienia"));
            zamowienieHurtowni.setDataOdebrania(set.getDate("DataOdebrania"));

            wynik.add(zamowienieHurtowni);
        }

        return wynik;
    }
}
