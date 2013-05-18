package DataManager;

import DataAccessObject.DataAccessObject;
import DataEntity.Pracownik;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: KrzysztofD
 * Date: 17.05.13
 * Time: 08:44
 * To change this template use File | Settings | File Templates.
 */
public class PracownicyManagerImpl implements PracownicyManager
{
    public PracownicyManagerImpl(DataAccessObject<Pracownik> dataAccessObject) {
        this.dataAccessObject = dataAccessObject;
    }

    @Override
    public Pracownik getSzczgoly(int id)
    {
        return dataAccessObject.select(id);
    }

    @Override
    public void zmienDanePracownika(Pracownik noweDane)
    {
        dataAccessObject.update(noweDane);
    }

    @Override
    public TableModel getTable(String lookFor, String orderBy)
    {
        List<Pracownik> list = dataAccessObject.select(lookFor, orderBy);
        TableModel wynik = new TableModel() {
            @Override
            public int getRowCount() {
                return 0;  //To change body of implemented methods use File | Settings | File Templates.
            }

            @Override
            public int getColumnCount() {
                return 0;  //To change body of implemented methods use File | Settings | File Templates.
            }

            @Override
            public String getColumnName(int columnIndex) {
                return null;  //To change body of implemented methods use File | Settings | File Templates.
            }

            @Override
            public Class<?> getColumnClass(int columnIndex) {
                return null;  //To change body of implemented methods use File | Settings | File Templates.
            }

            @Override
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return false;  //To change body of implemented methods use File | Settings | File Templates.
            }

            @Override
            public Object getValueAt(int rowIndex, int columnIndex) {
                return null;  //To change body of implemented methods use File | Settings | File Templates.
            }

            @Override
            public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
                //To change body of implemented methods use File | Settings | File Templates.
            }

            @Override
            public void addTableModelListener(TableModelListener l) {
                //To change body of implemented methods use File | Settings | File Templates.
            }

            @Override
            public void removeTableModelListener(TableModelListener l) {
                //To change body of implemented methods use File | Settings | File Templates.
            }
        };

        throw new NotImplementedException();
    }

    @Override
    public int dodaj(Pracownik nowy) {
        return dataAccessObject.insert(nowy);
    }

    @Override
    public void usun(int id) {
        dataAccessObject.delete(id);
    }

    final DataAccessObject<Pracownik> dataAccessObject;
}
