package WzorceSklep.GUI.DataRenderingUtils.TableConverters;

import WzorceSklep.Data.Zamowienie.ZamowienieHurtowni;
import WzorceSklep.GUI.DataRenderingUtils.AbstractTableConverter;

import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

/**
 * Created with IntelliJ IDEA.
 * User: KrzysztofD
 * Date: 29.05.13
 * Time: 13:17
 * To change this template use File | Settings | File Templates.
 */
public class ZamowieniaHurtowniTableConverter extends AbstractTableConverter<ZamowienieHurtowni> {

    public static final String ID_COLUMN = "ID";
    public static final String DOSTAWCA_COLUMN = "Dostawca";
    public static final String PRODUKT_COLUMN = "Produkt";
    public static final String TYP_COLUMN = "Typ";
    public static final String ILOSC_COLUMN = "Ilość";
    public static final String KWOTA_COLUMN = "Kwota";
    public static final String DATA_ZAMOWIENIA_COLUMN = "Data zamówienia";
    public static final String DATA_ODEBRANIA_COLUMN = "Data odebrania";
    public static final String ZREALIZUJ_COLUMN = "Zrealizuj";
    public static final String USUN_COLUMN = "Usuń";
    private final static String[] columnNames = new String[]{
            ID_COLUMN, DOSTAWCA_COLUMN, PRODUKT_COLUMN, TYP_COLUMN, ILOSC_COLUMN, KWOTA_COLUMN,
            DATA_ZAMOWIENIA_COLUMN, DATA_ODEBRANIA_COLUMN, ZREALIZUJ_COLUMN, USUN_COLUMN
    };

    @Override
    protected TableModel getEmptyTableModel(int length) {
        return new DefaultTableModel(columnNames,length){
            @Override
            public boolean isCellEditable(int row, int column) {
                return column==index.get(USUN_COLUMN) || column==index.get(ZREALIZUJ_COLUMN);
            }
        };
    }

    @Override
    protected Object[] mapDataToColumns(ZamowienieHurtowni zamowienie) {
        Object[] wynik = new Object[columnNames.length];

        wynik[index.get(ID_COLUMN)] = zamowienie.getID();
        wynik[index.get(DOSTAWCA_COLUMN)] = zamowienie.getZamawiajacy().getNazwa();
        wynik[index.get(PRODUKT_COLUMN)] = zamowienie.getProduktZamowiony().nazwa;
        wynik[index.get(TYP_COLUMN)] = zamowienie.getProduktZamowiony().typ;
        wynik[index.get(ILOSC_COLUMN)] = zamowienie.getIlosc();
        wynik[index.get(KWOTA_COLUMN)] = zamowienie.getKwota();
        wynik[index.get(DATA_ZAMOWIENIA_COLUMN)] = zamowienie.getDataZamowienia();
        wynik[index.get(DATA_ODEBRANIA_COLUMN)] = zamowienie.getDataOdebrania();
        wynik[index.get(USUN_COLUMN)] = "Usuń";
        wynik[index.get(ZREALIZUJ_COLUMN)] = "Zrealizuj";

        return wynik;
    }

    @Override
    protected String[] getColumnNames() {
        return columnNames;
    }
}
