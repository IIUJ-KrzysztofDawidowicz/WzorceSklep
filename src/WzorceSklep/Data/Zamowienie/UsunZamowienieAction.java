package WzorceSklep.Data.Zamowienie;

import WzorceSklep.DataAccessObject;
import WzorceSklep.GUI.RefreshableJFrame;
import WzorceSklep.Util;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.sql.SQLException;

/**
 * Created with IntelliJ IDEA.
 * User: KrzysztofD
 * Date: 05.06.13
 * Time: 11:26
 * To change this template use File | Settings | File Templates.
 */
public class UsunZamowienieAction extends AbstractAction {
    private JTable tabela;
    private final DataAccessObject dao;
    private RefreshableJFrame parent;

    public UsunZamowienieAction(JTable tabela, DataAccessObject dao, RefreshableJFrame parent) {
        this.tabela = tabela;
        this.dao = dao;
        this.parent = parent;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        int id = Util.getIDFromTable(e, tabela);
        try {
            if(JOptionPane.YES_OPTION== Util.showDeleteConfirmationDialog(parent, "zamowienie"))
            {
                dao.delete(id);
                parent.refreshCurrentWindow();
            }
        } catch (SQLException e1) {
            e1.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }
}
