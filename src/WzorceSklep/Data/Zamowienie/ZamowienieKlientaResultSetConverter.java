package WzorceSklep.Data.Zamowienie;

import WzorceSklep.Data.ResultSetConverter;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: KrzysztofD
 * Date: 03.06.13
 * Time: 19:10
 * To change this template use File | Settings | File Templates.
 */
public class ZamowienieKlientaResultSetConverter implements ResultSetConverter<ZamowienieKlienta> {

    @Override
    public List<ZamowienieKlienta> convertResultSet(ResultSet set) throws SQLException {
        List<ZamowienieKlienta> wynik = new ArrayList<ZamowienieKlienta>();

        while (set.next())
        {
            ZamowienieKlienta element = new ZamowienieKlienta();

            element.zamawiajacy.setImie(set.getString("Imie"));
            element.zamawiajacy.setNazwisko(set.getString("Nazwisko"));
            element.tworzacy.setImie(set.getString("ImiePracownika"));
            element.tworzacy.setNazwisko(set.getString("NazwiskoPracownika"));
            element.getProduktZamowiony().nazwa = set.getString("NazwaProduktu");
            element.getProduktZamowiony().typ = set.getString("Typ");
            element.setDataZamowienia(set.getDate("DataZamowienia"));
            element.setDataOdebrania(set.getDate("DataOdebrania"));
            element.setIlosc(set.getInt("Ilosc"));
            element.setKwota(set.getBigDecimal("Kwota"));

            wynik.add(element);
        }

        return wynik;
    }
}
