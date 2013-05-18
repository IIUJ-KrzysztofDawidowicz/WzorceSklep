package DataAccessObject;

public interface DAOFactory
{
    public DataAccessObject getDAO(Class entityClass);
}
