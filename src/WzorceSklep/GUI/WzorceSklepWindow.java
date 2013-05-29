package WzorceSklep.GUI;

import javafx.scene.control.TextInputControl;

import javax.swing.*;

/**
 * Created with IntelliJ IDEA.
 * User: KrzysztofD
 * Date: 28.05.13
 * Time: 21:23
 * To change this template use File | Settings | File Templates.
 */
public interface WzorceSklepWindow {
    JTextField getZam_szukaj_co_klient();

    JTable getZam_klient_tab();

    JTextField getZam_szukaj_co_hurt();

    JComboBox getZam_sortuj_hurt();

    JTable getZam_hurt_tab();

    JTable getKlienci_tabela();

    JTextField getKlienci_szukaj_co();

    JComboBox getKlienci_sortuj();

    JTextField getProdukty_szukaj_co();

    JTable getProdukty_tabela();
}
