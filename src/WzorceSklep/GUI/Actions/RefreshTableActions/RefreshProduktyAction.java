package WzorceSklep.GUI.Actions.RefreshTableActions;

import WzorceSklep.GUI.Admin;
import WzorceSklep.GUI.RefreshTableAction;

import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.Statement;

public class RefreshProduktyAction implements Serializable, RefreshTableAction {
    private final Admin admin;

    public RefreshProduktyAction(Admin admin) {
        this.admin = admin;
    }

    public void refresh() //Rysuje klienci_tabela
    {
        String lookFor = admin.getProdukty_szukaj_co().getText();
/*        //String order_by=produkty_sortuj.getSelectedItem().toString();
        String search_by = admin.getProdukty_szukaj_co().getText();
        *//*if(order_by.equals("ID")) order_by="ID_Produktu";*//*
        if (!search_by.isEmpty())
            search_by = " where Typ like '%" + search_by + "%' or Nazwa like '%" + search_by + "%'";
        int indeks_tabeli = 0;
        int rowcount = 0;
        try {
            Statement stat = admin.getConnection().createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = stat.executeQuery("use Sklep;select ID_Produktu, Typ, Cena, Nazwa, Ilosc, Specyfikacja from Produkty" + search_by);
            if (rs.last()) {
                rowcount = rs.getRow();
                rs.beforeFirst();
            }
            TableModel model = new DefaultTableModel(new String[]{"ID", "Typ", "Cena", "Nazwa", "Ilosc", "Specyfikacja"}, rowcount) {
                @Override
                public boolean isCellEditable(int rowIndex, int colIndex) {
                    return false;   //Disallow the editing of any cell
                }
            };

            while (rs.next()) {
                model.setValueAt(rs.getInt("ID_Produktu"), indeks_tabeli, 0);
                model.setValueAt(rs.getString("Typ"), indeks_tabeli, 1);
                model.setValueAt(rs.getString("Cena"), indeks_tabeli, 2);
                model.setValueAt(rs.getString("Nazwa"), indeks_tabeli, 3);
                model.setValueAt(rs.getInt("Ilosc"), indeks_tabeli, 4);
                model.setValueAt(rs.getString("Specyfikacja"), indeks_tabeli, 5);
                indeks_tabeli++;
            }
            admin.getProdukty_tabela().setModel(model);
            admin.getProdukty_szukaj_co().setText("");
        } catch (Exception e) {
            e.printStackTrace();*/
//        }
    }
}