package DataAdapter;

import org.easymock.EasyMock;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import static org.easymock.EasyMock.createMock;
import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.replay;

class DBTestUtils {
    static final String     tableName = "TESTTABLE";
    static final String[]   columnNames = new String[] {"ID", "Name"};
    static final Class[]    columnTypes = new Class[] {Integer.class, String.class};

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

    static ResultSet getMockResultSet(int[] idColumn, String[] nameColumn) throws SQLException {
        ResultSet resultSet = createMock(ResultSet.class);
        expect(resultSet.getMetaData())
                .andStubReturn(DBTestUtils.getMockResultSetMetaData(tableName, columnNames, columnTypes));

        for (int i = 0; i < idColumn.length; i++) {
            expect(resultSet.next()).andReturn(true);
            expect(resultSet.getObject(1)).andReturn(idColumn[i]);
            expect(resultSet.getObject(2)).andReturn(nameColumn[i]);
        }
        expect(resultSet.next()).andReturn(false);

        replay(resultSet);
        return resultSet;
    }

    public static boolean equalValues(UniversalDataEntity entity1, UniversalDataEntity entity2) {
        if(entity1.getTableInfo()!= entity2.getTableInfo())
            return false;
        Object id = entity1.getValue("ID");
        Object[] values = entity1.getValues();
        Object[] otherValues = entity2.getValues();
        for (int i = 0; i < values.length; i++) {
            if(!values[i].equals(otherValues[i]) && !values[i].equals(id))
                return false;
        }


        return true;
    }
}