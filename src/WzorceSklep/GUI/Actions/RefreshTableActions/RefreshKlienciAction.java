package WzorceSklep.GUI.Actions.RefreshTableActions;

import WzorceSklep.DAOFactory;
import WzorceSklep.Data.Klient.Klient;
import WzorceSklep.DataAccessObject;
import WzorceSklep.GUI.Actions.RefreshTableActions.TableConverters.KlienciTableConverter;
import WzorceSklep.GUI.Admin;

import javax.swing.*;
import javax.swing.table.TableModel;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class RefreshKlienciAction extends AbstractRefreshTableAction<Klient> implements Serializable {

    public RefreshKlienciAction(Admin admin) {
        super(admin);
    }//-------------------------------------------------------------------------------------------------------------------

    public void refresh() //Rysuje klienci_tabela
    {
        String orderBy = getOrderBy();
        String lookFor = getSzukajTextField().getText();
        DataAccessObject<Klient> dataAccessObject = null;
        try {
            dataAccessObject = getDataAccessObject();
        } catch (SQLException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            return;
        }
        List<Klient> list;
        try {
            if (lookFor.isEmpty())
                list = dataAccessObject.select(orderBy);
            else
                list = dataAccessObject.select(lookFor, orderBy);
        } catch (SQLException e) {
            e.printStackTrace();
            list = new LinkedList<Klient>();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            list = new LinkedList<Klient>();
        }
        Klient[] klients = new Klient[list.size()];
        list.toArray(klients);
        TableModel model = getTableConverter().getTableModel(klients);
        getTable().setModel(model);
        getSzukajTextField().setText("");
    }

    protected JTable getTable() {
        return admin.getKlienci_tabela();
    }

    protected KlienciTableConverter getTableConverter() {
        return new KlienciTableConverter();
    }

    protected DataAccessObject<Klient> getDataAccessObject() throws SQLException {
        return DAOFactory.getKlientDAO();
    }

    protected JTextField getSzukajTextField() {
        return admin.getKlienci_szukaj_co();
    }

    /**
     * Tabela Klieni nie ma guzików.
     */
    @Override
    protected void setButtonColumns() {}

    protected String getOrderBy() {
        return admin.getKlienci_sortuj().getSelectedItem().toString();
    }
}