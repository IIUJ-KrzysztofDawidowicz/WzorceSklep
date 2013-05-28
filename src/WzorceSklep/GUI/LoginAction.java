package WzorceSklep.GUI;

import WzorceSklep.Data.Pracownik.Pracownik;

/**
 * Created with IntelliJ IDEA.
 * User: KrzysztofD
 * Date: 27.05.13
 * Time: 23:02
 * To change this template use File | Settings | File Templates.
 */
public interface LoginAction {
    void Login(Pracownik pracownik) throws Exception;
}
