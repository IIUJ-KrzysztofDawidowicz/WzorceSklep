package WzorceSklep.GUI.Actions.AbstractActions;

import WzorceSklep.GUI.Admin;
import WzorceSklep.Data.Pracownik.DialogPracownikSzczegoly;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
* Created with IntelliJ IDEA.
* User: KrzysztofD
* Date: 26.05.13
* Time: 23:00
* To change this template use File | Settings | File Templates.
*/
public class ActionPracownikSczegoly extends AbstractAction
{
    private final Admin admin;

    public ActionPracownikSczegoly(Admin admin) {
        this.admin = admin;
    }

    public void actionPerformed(ActionEvent e)
    {
        final int id=Integer.valueOf(admin.getPracownicy_tabela().getValueAt(Integer.valueOf(e.getActionCommand()), 0).toString()) ;

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run()
            {
                admin.setDialog(new DialogPracownikSzczegoly(admin.getThisframe(), true, admin.getConnection(), id));
                admin.getDialog().setVisible(true);
            }
        });
    }
}
