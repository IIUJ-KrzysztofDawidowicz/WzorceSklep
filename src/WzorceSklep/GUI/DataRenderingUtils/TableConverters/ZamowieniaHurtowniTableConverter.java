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

    private final static String[] columnNames = new String[]{
            "ID", "Dostawca","Produkt","Typ","Ilość","Kwota","Data zamówienia",/*"Data odebrania",*/ "Usuń"
    };

    @Override
    protected TableModel getEmptyTableModel(int length) {
        return new DefaultTableModel(columnNames,length){
            @Override
            public boolean isCellEditable(int row, int column) {
                return column==index.get("Usuń");
            }
        };
    }

    @Override
    protected Object[] mapDataToColumns(ZamowienieHurtowni zamowienie) {
        Object[] wynik = new Object[columnNames.length];

        wynik[index.get("ID")] = zamowienie.getID();
        wynik[index.get("Dostawca")] = zamowienie.getZamawiajacy().getNazwa();
        wynik[index.get("Produkt")] = zamowienie.getProduktZamowiony().nazwa;
        wynik[index.get("Typ")] = zamowienie.getProduktZamowiony().typ;
        wynik[index.get("Ilość")] = zamowienie.getIlosc();
        wynik[index.get("Kwota")] = zamowienie.getKwota();
        wynik[index.get("Data zamówienia")] = zamowienie.getDataZamowienia();
//        wynik[index.get("Data odebrania")] = zamowienie.getDataOdebrania();
        wynik[index.get("Usuń")] = "Usuń";

        return wynik;
    }

    @Override
    protected String[] getColumnNames() {
        return columnNames;
    }
}
