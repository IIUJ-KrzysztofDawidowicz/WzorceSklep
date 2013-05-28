package WzorceSklep.GUI.Actions.LoginActions;

import WzorceSklep.Data.Pracownik.Pracownik;
import WzorceSklep.GUI.LoginAction;
import WzorceSklep.GUI.Sprzedawca;

import java.awt.*;
import java.io.Serializable;

public class LoginSprzedawcaAction implements Serializable, LoginAction {
    public LoginSprzedawcaAction() {
    }

    public void Login(final Pracownik pracownik) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Sprzedawca(null, pracownik.login).setVisible(true);
            }
        });
    }
}