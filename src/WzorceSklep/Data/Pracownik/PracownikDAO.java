package WzorceSklep.Data.Pracownik;

import WzorceSklep.Data.TableDataGetter;
import WzorceSklep.Data.ZAdresem;
import WzorceSklep.DataAccessObject;
import WzorceSklep.Data.DataAdapter.DatabaseAdapter;
import WzorceSklep.Data.DataAdapter.TableInfo;
import WzorceSklep.Data.DataAdapter.TableRow;
import WzorceSklep.Data.DataAdapter.TableRowFactory;
import WzorceSklep.Data.AdresOsoby;
import com.sun.media.sound.InvalidDataException;
import javafx.util.Pair;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: KrzysztofD
 * Date: 25.05.13
 * Time: 19:03
 * To change this template use File | Settings | File Templates.
 */
public class PracownikDAO implements DataAccessObject<Pracownik>, TableDataGetter<Pracownik> {
    private final DatabaseAdapter adapter;
    private final static String tableNamePracownik = "PRACOWNIK";
    private final static String tableNameAdres = "ADRESPRACOWNIKA";
    private final PracownikTableDataConverter tableDataConverter = new PracownikTableDataConverter(tableNamePracownik, tableNameAdres);

    public PracownikDAO(DatabaseAdapter databaseAdapter) {
        adapter = databaseAdapter;
    }

    @Override
    public List<Pracownik> select(String orderBy) throws SQLException {
        orderBy = TableInfo.getColumnNameWithCheckedCase(tableNamePracownik, orderBy);
        return tableDataConverter.convertResultSet(adapter.select(tableNamePracownik, orderBy));
    }

    @Override
    public List<Pracownik> select(String lookFor, String orderBy) throws SQLException {
        orderBy = TableInfo.getColumnNameWithCheckedCase(tableNamePracownik, orderBy);
        return tableDataConverter.convertResultSet(adapter.select(tableNamePracownik, lookFor, orderBy));
    }

    @Override
    public List<Pracownik> select() throws SQLException {
        return tableDataConverter.convertResultSet(adapter.selectAll(tableNamePracownik));
    }

    @Override
    public void insert(Pracownik nowy) throws SQLException {
        nowy.setID(adapter.getMaxValueForColumn(tableNamePracownik, "ID")+1);
        for (TableRow row: tableDataConverter.convertToTableRows(nowy)) {
            adapter.insert(row);
        }
//        TableRow row = tableDataConverter.convertToTableRow(nowy);
//        adapter.insert(row);
//        row = tableDataConverter.convertAdresToTableRow(nowy.adres, nowy.ID);
//        adapter.insert(row);
    }

    @Override
    public void update(Pracownik nowy) throws SQLException {
        adapter.update(tableDataConverter.convertToTableRow(nowy));
    }

    @Override
    public void delete(int id) throws SQLException {
        List<Pair<String,Integer>> toDelete = new ArrayList<Pair<String, Integer>>(2);
        toDelete.add(new Pair<String, Integer>(tableNameAdres,id));
        toDelete.add(new Pair<String, Integer>(tableNamePracownik,id));
        delete(toDelete);
//        adapter.delete(tableNameAdres, id);
//        adapter.delete(tableNamePracownik, id);
    }

    private void delete(List<Pair<String, Integer>> toDelete) throws SQLException {
        for(Pair<String,Integer> item:toDelete)
            adapter.delete(item.getKey(),item.getValue());
    }

    @Override
    public Pracownik getById(int id) throws SQLException {
        Pracownik pracownik;
        {
            TableRow row = TableRowFactory.createTableRow(tableNamePracownik);
            row.setValue("ID", id);
            List<Pracownik> list = tableDataConverter.convertResultSet(adapter.selectExactMatch(row));
            if(list.isEmpty())
                return null;
            pracownik = list.get(0);
        }
        {
            pracownik.setAdres(getAdresByID(pracownik));
        }
        return pracownik;
    }

    private AdresOsoby getAdresByID(ZAdresem zAdresem) throws SQLException {
        TableRow row = TableRowFactory.createTableRow(tableNameAdres);
        row.setValue("ID", zAdresem.getID());
        List<AdresOsoby> list = tableDataConverter.convertToAdres(adapter.selectExactMatch(row));
        if(list.isEmpty())
            return null;
        return list.get(0);
    }

    public Pracownik getByLogin(String login, String haslo) throws SQLException, InvalidDataException {
        TableRow row = TableRowFactory.createTableRow(tableNamePracownik);
        row.setValue("Login", login);
        row.setValue("Password", haslo);
        List<Pracownik> list = tableDataConverter.convertResultSet(adapter.selectExactMatch(row));
        if(list.size()<1)
            return null;
        Pracownik pracownik = list.get(0);
        pracownik.setAdres(getAdresByID(pracownik));

        return pracownik;
    }

}
