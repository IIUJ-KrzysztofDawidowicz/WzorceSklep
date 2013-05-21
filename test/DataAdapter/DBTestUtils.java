package DataAdapter;

import org.easymock.EasyMock;

import java.sql.ResultSetMetaData;
import java.sql.SQLException;

class DBTestUtils {
    static final String tableName = "Test Table";
    static final String[] columnNames = new String[] {"ID", "Name"};
    static final Class[] columnTypes = new Class[] {Integer.class, String.class};

    public static TableInfo createTableInfo(String tableName, String[] columnNames, Class[] columnTypes) throws SQLException, ClassNotFoundException {
        return TableInfo.getTableInfo(getMockResultSetMetaData(tableName, columnNames, columnTypes));
    }

    public static ResultSetMetaData getMockResultSetMetaData(String tableName, String[] columnNames, Class[] columnTypes) throws SQLException {
        if (columnNames.length != columnTypes.length)
            throw new IllegalArgumentException("Nie zgadzają się długości tablic typów danych i nazw kolumn.");

        ResultSetMetaData metaData = EasyMock.createMock(ResultSetMetaData.class);
        EasyMock.expect(metaData.getTableName(1)).andStubReturn(tableName);
        EasyMock.expect(metaData.getColumnCount()).andStubReturn(columnNames.length);
        for (int i = 0; i < columnNames.length; i++) {
            EasyMock.expect(metaData.getColumnClassName(i+1)).andStubReturn(columnTypes[i].toString());
            EasyMock.expect(metaData.getColumnName(i+1)).andStubReturn(columnNames[i]);
        }
        EasyMock.replay(metaData);
        return metaData;
    }
}