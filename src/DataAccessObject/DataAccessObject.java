package DataAccessObject;

import DataEntity.DataEntity;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: KrzysztofD
 * Date: 17.05.13
 * Time: 08:47
 * To change this template use File | Settings | File Templates.
 */
public interface DataAccessObject<T extends DataEntity>
{
    public List<T> select(String lookFor, String orderBy);
    public T select(int id);
    public int insert(T nowy);
    public void update(T nowy);
    public void delete(int id);
}
