package DataManager;

import DataEntity.DataEntity;

import javax.swing.table.TableModel;

/**
 * Created with IntelliJ IDEA.
 * User: KrzysztofD
 * Date: 16.05.13
 * Time: 15:01
 * To change this template use File | Settings | File Templates.
 */
public interface DataManager<T extends DataEntity>
{
    public TableModel getTable(String lookFor, String orderBy);
    public int dodaj(T nowy);
    public void usun(int id);
}
