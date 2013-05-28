package WzorceSklep.GUI.Actions.LoginActions;

import WzorceSklep.Data.Pracownik.Pracownik;
import WzorceSklep.GUI.LoginAction;

import java.io.Serializable;

public class LoginHurtownikAction implements Serializable, LoginAction {
    public LoginHurtownikAction() {
    }

    @Override
    public void Login(Pracownik pracownik) throws Exception {
        throw new Exception("Nie ma Hurtownika.");
    }
}