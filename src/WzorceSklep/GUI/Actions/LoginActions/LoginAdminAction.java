package WzorceSklep.GUI.Actions.LoginActions;

import WzorceSklep.Data.Pracownik.Pracownik;
import WzorceSklep.GUI.Admin;
import WzorceSklep.GUI.LoginAction;

import java.awt.*;
import java.io.Serializable;

public class LoginAdminAction implements Serializable, LoginAction {
    public LoginAdminAction() {
    }

    public final void Login(Pracownik pracownik) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Admin().setVisible(true);
            }
        });
    }
}