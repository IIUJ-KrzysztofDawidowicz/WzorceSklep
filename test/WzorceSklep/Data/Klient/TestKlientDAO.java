package WzorceSklep.Data.Klient;

import WzorceSklep.Data.DataAdapter.TableRow;
import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;

/**
 * Created with IntelliJ IDEA.
 * User: KrzysztofD
 * Date: 24.05.13
 * Time: 22:55
 * To change this template use File | Settings | File Templates.
 */
public class TestKlientDAO {

    private final static String tableName = "KLIENT";
    private final String adresTableName = "ADRESKLIENTA";
    private TableDataConverter<Klient> tableDataConverter = new KlientTableDataConverter(tableName, adresTableName);

/*
    @Test
    public void testSelect() throws Exception
    {
        DatabaseAdapter adapter = getDatabaseAdapter();
        KlientDAO klientDAO = new KlientDAO(adapter);
        List<Klient> klientList = klientDAO.getAll();
        Assert.assertEquals(1,klientList.size());
        Klient klient = klientList.get(0);
        Assert.assertEquals(1, klient.ID);
        Assert.assertEquals("Jan", klient.imie);
        Assert.assertEquals("Kowalski", klient.nazwisko);
        Assert.assertEquals("mail@mail.com", klient.mail);
        Assert.assertEquals(new BigDecimal("123456"), klient.telefon);
    }
*/

    @Test
    public void testConvertToDataRow() throws Exception
    {
        Klient klient = new Klient();
        klient.setID(1);
        klient.setMail("mail");
        klient.setTelefon(new BigDecimal("1234556"));
        klient.setImie("Jan");
        klient.setNazwisko("Kowalski");
        TableRow tableRow = tableDataConverter.convertToTableRow(klient);
        Assert.assertEquals(klient.getID(), (int)(Integer) tableRow.getValue("ID"));
        Assert.assertEquals(klient.getImie(), tableRow.getValue("Imie").toString());
        Assert.assertEquals(klient.getNazwisko(), tableRow.getValue("Nazwisko").toString());
        Assert.assertEquals(klient.getMail(), tableRow.getValue("Mail").toString());
    }

    /*private static DatabaseAdapter getDatabaseAdapter() throws SQLException {
        DatabaseAdapter adapter = EasyMock.createMock(DatabaseAdapter.class);
        List<TableRow> tableRows = new LinkedList<TableRow>();
        TableRow row = TableRowFactory.createTableRow("KLIENT");
        row.setValue("ID", 1);
        row.setValue("Imie", "Jan");
        row.setValue("Nazwisko", "Kowalski");
        row.setValue("Mail", "mail@mail.com");
        row.setValue("Telefon", new BigDecimal("123456"));
        tableRows.add(row);
        EasyMock.expect(adapter.selectAll("KLIENT")).andReturn(tableRows);
        EasyMock.replay(adapter);
        return adapter;
    }*/
}