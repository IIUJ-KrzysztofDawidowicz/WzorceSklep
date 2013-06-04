package WzorceSklep;

import WzorceSklep.Data.DataAdapter.DataAdapterFactory;
import WzorceSklep.Data.DataAdapter.DatabaseAdapter;

import javax.swing.*;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.File;
import java.sql.SQLException;

public class Util {
    public static Integer convertZeroToNullElseSameValue(Integer nrLokalu) {
        return ((nrLokalu == 0) ? null : nrLokalu);
    }

    public static String getAbsolutePathOfCurrentDirectory() {
        String path;
        path = new File(".").getAbsolutePath();
        path = path.substring(0,path.length()-2);
        return path;
    }

    private static void executeStatement() {
        String sql = "select * from Pracownik";
        try {
            DatabaseAdapter adapter = DataAdapterFactory.getDatabaseAdapter();
            adapter.selectAll("Pracownik");
//            adapter.executeArbitraryStatement(sql);
        } catch (SQLException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }

    public static void showErrorDialog(Component sender, Throwable e) {
        e.printStackTrace();
        String message = e.getMessage();
        while (e.getCause()!=null)
        {
            e = e.getCause();
            message += '\n' + e.getMessage();
        }
        JOptionPane.showMessageDialog(sender, message, "Error", JOptionPane.ERROR_MESSAGE);
    }

    public static int getColumnIndex(TableModel model, String columnName) throws IllegalArgumentException {
        final int columnCount = model.getColumnCount();
        for (int i = 0; i < columnCount; i++) {
            if(model.getColumnName(i).equals(columnName))
                return i;
        }
        throw new IllegalArgumentException(String.format("Kolumny %s nie ma w podanej tabeli.", columnName));
    }

    public static Integer getIDFromTable(ActionEvent e, JTable table) {
        return (Integer) table.getValueAt(Integer.valueOf(e.getActionCommand()),
                getColumnIndex(table.getModel(), "ID"));
    }
}