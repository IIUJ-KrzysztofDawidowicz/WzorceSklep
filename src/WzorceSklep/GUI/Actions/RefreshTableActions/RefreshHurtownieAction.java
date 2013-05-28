package WzorceSklep.GUI.Actions.RefreshTableActions;

import WzorceSklep.DAOFactory;
import WzorceSklep.Data.Hurtownia.Hurtownia;
import WzorceSklep.DataAccessObject;
import WzorceSklep.GUI.Actions.RefreshTableActions.TableConverters.HurtowniaTableConverter;
import WzorceSklep.GUI.Admin;

import javax.swing.*;
import java.io.Serializable;
import java.sql.SQLException;

public class RefreshHurtownieAction extends AbstractRefreshTableAction<Hurtownia> implements Serializable {

    public RefreshHurtownieAction(Admin admin) {
        super(admin);
    }

    @Override
    protected String getOrderBy() {
        return "ID";
    }

    protected JTextField getSzukajTextField() {
        return admin.getHurtownia_szukaj_co();
    }

    protected JTable getTable() {
        return admin.getHurtownia_tabela();
    }

    protected DataAccessObject<Hurtownia> getDataAccessObject() throws SQLException {
        return DAOFactory.getHurtowniaDAO();
    }

    /**
     * Tabela Hurtownie nie ma guzików.
     *
     */
    @Override
    protected void setButtonColumns() {}

    protected HurtowniaTableConverter getTableConverter() {
        return new HurtowniaTableConverter();
    }
}