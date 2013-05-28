package WzorceSklep.GUI.Actions.RefreshTableActions;

import WzorceSklep.DataAccessObject;
import WzorceSklep.DataEntity;
import WzorceSklep.GUI.Admin;
import WzorceSklep.GUI.RefreshTableAction;

import javax.swing.*;
import javax.swing.table.TableModel;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: KrzysztofD
 * Date: 28.05.13
 * Time: 16:48
 * To change this template use File | Settings | File Templates.
 */
public abstract class AbstractRefreshTableAction<T extends DataEntity> implements RefreshTableAction {
    protected final Admin admin;

    public AbstractRefreshTableAction(Admin admin) {
        this.admin = admin;
    }

    @Override
    public void refresh() //Rysuje hurtownia_tabela
    {
        String order_by=getOrderBy();
        String lookFor = getSzukajTextField().getText();
        // if(order_by.equals("ID")) order_by="ID_Hurtowni";
        DataAccessObject<T> dao;
        List<T> list;
        try {
            dao = getDataAccessObject();
            if (lookFor.isEmpty())
                list = dao.select(order_by);
            else
                list = dao.select(lookFor, order_by);
        } catch (SQLException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            return;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            return;
        }

        TableModel model = getTableConverter().getTableModel(new ArrayList<T>(list));
        getTable().setModel(model);
        setButtonColumns();
        getSzukajTextField().setText("");
/*            lookFor = " where Nazwa like '%" + lookFor + "%' or Osoba_kontaktowa like '%" + lookFor + "%' or Telefon like '%" + lookFor + "%' or Mail like '%" + lookFor + "%'";
        int indeks_tabeli = 0;
        int rowcount = 0;
        try {
            Statement stat = admin.getConnection().createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = stat.executeQuery("use Sklep;select ID_Hurtowni, Nazwa, Osoba_kontaktowa, Telefon, Mail from Hurtownie" + lookFor);
            if (rs.last()) {
                rowcount = rs.getRow();
                rs.beforeFirst();
            }
            TableModel model = new DefaultTableModel(new String[]{"ID", "Nazwa", "Osoba kontaktowa", "Telefon", "mail"}, rowcount) {
                @Override
                public boolean isCellEditable(int rowIndex, int colIndex) {
                    return false;   //Disallow the editing of any cell
                }
            };

            while (rs.next()) {
                model.setValueAt(rs.getInt("ID_Hurtowni"), indeks_tabeli, 0);
                model.setValueAt(rs.getString("Nazwa"), indeks_tabeli, 1);
                model.setValueAt(rs.getString("Osoba_kontaktowa"), indeks_tabeli, 2);
                model.setValueAt(rs.getString("Telefon"), indeks_tabeli, 3);
                model.setValueAt(rs.getString("Mail"), indeks_tabeli, 4);
                indeks_tabeli++;
            }
            admin.getHurtownia_tabela().setModel(model);
            admin.getHurtownia_szukaj_co().setText("");
        } catch (Exception e) {
            e.printStackTrace();
        }*/
    }


    protected abstract String getOrderBy();

    protected abstract AbstractTableConverter<T> getTableConverter();

    protected abstract JTextField getSzukajTextField();

    protected abstract JTable getTable();

    protected abstract DataAccessObject<T> getDataAccessObject() throws SQLException;

    protected abstract void setButtonColumns();

}
