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
 * Time: 12:14
 * To change this template use File | Settings | File Templates.
 */
public class RealizujZamowienieKlientaAction extends AbstractAction {
    private DataAccessObject<ZamowienieKlienta> zamowienieDao;
    private JTable tabela;
    private RefreshableJFrame parent;
    private DataAccessObject<Produkt> produktDao;

    public RealizujZamowienieKlientaAction(RefreshableJFrame parent, JTable tabela, DataAccessObject<ZamowienieKlienta> zamowienieDao, DataAccessObject<Produkt> produktDao) {
        this.zamowienieDao = zamowienieDao;
        this.tabela = tabela;
        this.parent = parent;
        this.produktDao = produktDao;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            ZamowienieKlienta zamowienie = zamowienieDao.getById(Util.getIDFromTable(e, tabela));
            Produkt produkt = produktDao.getById(zamowienie.getProduktZamowiony().ID);
            zamowienie.setProduktZamowiony(produkt);
            String[] opcje = new String[] {"Tak", "Nie"};
            if(JOptionPane.YES_OPTION == JOptionPane.showOptionDialog(parent,
                    "Potwierdź realizację zamówienia", "",
                    JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE, null,
                    opcje, opcje[1]))
            {
                if(zamowienie.getIlosc()>produkt.ilosc) {
                    JOptionPane.showMessageDialog(parent,
                            String.format("Błąd: brak towaru\nZamówienie na %d dostępne %d",
                                    zamowienie.getIlosc(), produkt.ilosc));
                    return;
                }
                zamowienie.setZrealizowane(true);
                zamowienie.setDataOdebrania(new Date(new DateTime().getMillis()));
                produkt.ilosc-=zamowienie.getIlosc();
                zamowienieDao.update(zamowienie);
                produktDao.update(produkt);

            }
        } catch (SQLException e1) {
            e1.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            Util.showErrorDialog(parent, e1);
        }
    }
}
