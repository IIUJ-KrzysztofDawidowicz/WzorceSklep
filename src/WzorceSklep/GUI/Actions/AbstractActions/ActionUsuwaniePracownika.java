package WzorceSklep.GUI.Actions.AbstractActions;

import WzorceSklep.GUI.Admin;
import WzorceSklep.Data.Pracownik.DialogUsunPracownika;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
* Created with IntelliJ IDEA.
* User: KrzysztofD
* Date: 26.05.13
* Time: 23:04
* To change this template use File | Settings | File Templates.
*/
public class ActionUsuwaniePracownika extends AbstractAction
{
    private final Admin admin;

    public ActionUsuwaniePracownika(Admin admin) {
        this.admin = admin;
    }

    public void actionPerformed(ActionEvent e)
    {
        final int id=Integer.valueOf(admin.getPracownicy_tabela().getValueAt(Integer.valueOf(e.getActionCommand()), 0).toString()) ;
        final String nazwisko= admin.getPracownicy_tabela().getValueAt(Integer.valueOf(e.getActionCommand()), 1).toString();
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                admin.setDialog(new DialogUsunPracownika(admin.getThisframe(), true, admin.getConnection(), id, nazwisko));
                admin.getDialog().setVisible(true);
                admin.getDialog().addWindowListener(new WindowAdapter()//Sprawdza, czy okno dialogowe zostało zamknięte
                {
                    @Override
                    public void windowClosed(WindowEvent e) {
                        admin.refreshCurrentWindow();
                    }
                });
            }
        });
    }
}
