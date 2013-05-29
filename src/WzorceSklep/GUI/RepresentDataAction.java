package WzorceSklep.GUI;

import com.sun.media.sound.InvalidDataException;

import java.sql.SQLException;

/**
 * Created with IntelliJ IDEA.
 * User: KrzysztofD
 * Date: 26.05.13
 * Time: 22:36
 * To change this template use File | Settings | File Templates.
 */
public interface RepresentDataAction {
    void execute() throws InvalidDataException, SQLException, ClassNotFoundException;
}
