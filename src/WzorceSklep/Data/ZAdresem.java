package WzorceSklep.Data;

import WzorceSklep.Data.Adres;

/**
 * Created with IntelliJ IDEA.
 * User: KrzysztofD
 * Date: 30.05.13
 * Time: 15:32
 * To change this template use File | Settings | File Templates.
 */
public interface ZAdresem<T extends Adres> {
    int getID();
    void setID(int id);

    T getAdres();

    void setAdres(T adres);

    String getName();
}
