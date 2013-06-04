package WzorceSklep.Data.Zamowienie;

import WzorceSklep.Data.DataAdapter.TableRow;
import WzorceSklep.Data.TableDataConverter;

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
public class ZamowienieKlientaViewTableDataConverter implements TableDataConverter<ZamowienieKlienta> {
    @Override
    public List<TableRow> convertToTableRows(ZamowienieKlienta dataEntity) throws SQLException {
        throw new UnsupportedOperationException("Not implemented.");  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public TableRow convertToTableRow(ZamowienieKlienta dataEntity) throws SQLException {
        throw new UnsupportedOperationException("Not implemented.");  //To change body of implemented methods use File | Settings | File Templates.
    }

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
            element.setIlosc(set.getInt("Ilosc"));
            element.setKwota(set.getBigDecimal("Kwota"));

            wynik.add(element);
        }

        return wynik;
    }
}
