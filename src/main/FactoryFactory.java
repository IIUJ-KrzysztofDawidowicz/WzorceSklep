package main;

import DataAdapter.DataAdapterFactory;
import DataAdapter.SqliteJDBCDataAdapterFactory;

/**
 * Created with IntelliJ IDEA.
 * User: KrzysztofD
 * Date: 16.05.13
 * Time: 13:45
 * To change this template use File | Settings | File Templates.
 */
public abstract class FactoryFactory
{

    public static FactoryFactory getInstance() {
        return ourInstance;
    }

    public abstract DataAdapterFactory getDatabaseAdapterFactory();

    private static FactoryFactory ourInstance = new FactoryFactory() {
        @Override
        public DataAdapterFactory getDatabaseAdapterFactory() {
            return new SqliteJDBCDataAdapterFactory("testDB.db");
        }
    };
    private FactoryFactory() {}
}
