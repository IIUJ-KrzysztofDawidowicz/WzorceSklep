package WzorceSklep.GUI.Actions.RefreshTableActions;

import WzorceSklep.GUI.Admin;
import WzorceSklep.GUI.RefreshTableAction;

import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.Statement;

public class RefreshZamowieniaHurtowniAction implements Serializable, RefreshTableAction {
    private final Admin admin;

    public RefreshZamowieniaHurtowniAction(Admin admin) {
        this.admin = admin;
    }//-------------------------------------------------------------------------------------------------------------------

    public void refresh() {
        String szukaj, sortuj;

        szukaj = admin.getZam_szukaj_co_hurt().getText();
        if (!szukaj.equals("")) {
            szukaj = " where Hurtownia like '%" + szukaj + "%' or Nazwa like '%" + szukaj + "%' or Typ like '%" + szukaj
                    + "%' or Ilosc like '%" + szukaj + "%' or Cena like '%" + szukaj
                    + "%' or Data_zamowienia like '%" + szukaj + "%' or Data_odebrania like '%" + szukaj + "%'";
        }

        //"Hurtownia","Produkt","Typ","Ilość","Kwota","Data zamowienia","Data odebrania"
        sortuj = admin.getZam_sortuj_hurt().getSelectedItem().toString();
        if (sortuj.equals("Produkt")) {
            sortuj = "Nazwa";
        } else if (sortuj.equals("Ilość")) {
            sortuj = "Ilosc";
        } else if (sortuj.equals("Kwota")) {
            sortuj = "Cena";
        } else if (sortuj.equals("Data zamowienia")) {
            sortuj = "Data_zamowienia";
        } else if (sortuj.equals("Data odebrania")) {
            sortuj = "Data_odebrania";
        }

        int i = 0;
        try {
            Statement stat = admin.getConnection().createStatement();

            ResultSet rs = stat.executeQuery("use Sklep;select count(*) from v_zamow_hurtownia" + szukaj);
            rs.next();

            TableModel model = new DefaultTableModel(new String[]{
                    "Hurtownia", "Produkt", "Typ", "Ilość", "Kwota", "Data zamowienia", "Data odebrania"
            }, rs.getInt(1)) {
                @Override
                public boolean isCellEditable(int rowIndex, int colIndex) {
                    return false;   //Disallow the editing of any cell
                }
            };


            rs = stat.executeQuery("use Sklep;select * from v_zamow_hurtownia" + szukaj + " order by " + sortuj);
            while (rs.next()) {
                model.setValueAt(rs.getString("Hurtownia"), i, 0);
                model.setValueAt(rs.getString("Nazwa"), i, 1);
                model.setValueAt(rs.getString("Typ"), i, 2);
                model.setValueAt(rs.getInt("Ilosc"), i, 3);
                model.setValueAt(rs.getInt("Cena"), i, 4);
                model.setValueAt(rs.getString("Data_zamowienia"), i, 5);
                model.setValueAt(rs.getString("Data_odebrania"), i, 6);
                i++;
            }
            admin.getZam_hurt_tab().setModel(model);
            admin.getZam_szukaj_co_hurt().setText("");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}