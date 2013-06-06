package WzorceSklep.Data.Zamowienie;

import WzorceSklep.Data.Produkt.Produkt;
import WzorceSklep.DataAccessObject;
import WzorceSklep.GUI.RefreshableJFrame;
import WzorceSklep.Util;
import org.joda.time.DateTime;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.sql.Date;
import java.sql.SQLException;

/**
 * Created with IntelliJ IDEA.
 * User: KrzysztofD
 * Date: 05.06.13
 * Time: 21:44
 * To change this template use File | Settings | File Templates.
 */
public class ZrealizujZamowienieHurtowniAction extends AbstractAction {
    private DataAccessObject<ZamowienieHurtowni> zamowienieDao;
    private JTable tabela;
    private RefreshableJFrame parent;
    private DataAccessObject<Produkt> produktDao;

    public ZrealizujZamowienieHurtowniAction(DataAccessObject<ZamowienieHurtowni> zamowienieDao, JTable tabela, RefreshableJFrame parent, DataAccessObject<Produkt> produktDao) {
        this.zamowienieDao = zamowienieDao;
        this.tabela = tabela;
        this.parent = parent;
        this.produktDao = produktDao;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            ZamowienieHurtowni zamowienie = zamowienieDao.getById(Util.getIDFromTable(e, tabela));
            if(zamowienie.getDataOdebrania()!=null)
            {
                JOptionPane.showMessageDialog(parent, "" +
                        "Zamówienie już zostało zrealizowane " +zamowienie.getDataOdebrania(), "",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }
            if(uzytkownikPotwierdzil())
            {
                final Produkt produktZamowiony = zamowienie.getProduktZamowiony();
                zamowienie.setDataOdebrania(new Date(new DateTime().getMillis()));
                produktZamowiony.ilosc+=zamowienie.getIlosc();
                zamowienieDao.update(zamowienie);
                produktDao.update(produktZamowiony);
                parent.refreshCurrentWindow();

            }
        } catch (SQLException e1) {
            e1.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            Util.showErrorDialog(parent, e1);
        }
    }

    private boolean uzytkownikPotwierdzil() {
        String[] opcje = new String[] {"Tak", "Nie"};
        return JOptionPane.YES_OPTION == JOptionPane.showOptionDialog(parent,
                "Potwierdź realizację zamówienia", "",
                JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE, null,
                opcje, opcje[1]);
    }
}
