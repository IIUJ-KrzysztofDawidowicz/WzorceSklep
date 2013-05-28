package WzorceSklep.GUI.Actions.RefreshTableActions;

import WzorceSklep.DataEntity;

import javax.swing.table.TableModel;
import java.util.List;

/**
 * Konwertuje tablicę obiektów DataEntity na TableModel do wyświetlenia.
 */
public abstract class AbstractTableConverter<T extends DataEntity> {

    public TableModel getTableModel(T[] data)
    {
        TableModel model = getEmptyTableModel(data.length);
        fillTableModel(model, data);
        return model;
    }

    public TableModel getTableModel(List<T> data)
    {
        TableModel model = getEmptyTableModel(data.size());
        fillTableModel(model, data);
        return model;
    }

    private void fillTableModel(TableModel model, T[] data) {
        for (int row = 0; row < data.length; row++) {
            Object[] dataInColumns = mapDataToColumns(data[row]);
            for (int column = 0; column < dataInColumns.length; column++) {
                model.setValueAt(dataInColumns[column], row, column);
            }
        }
    }

    private void fillTableModel(TableModel model, List<T> data) {
        for (int row = 0; row < data.size(); row++) {
            Object[] dataInColumns = mapDataToColumns(data.get(row));
            for (int column = 0; column < dataInColumns.length; column++) {
                model.setValueAt(dataInColumns[column], row, column);
            }
        }
    }

    protected abstract TableModel getEmptyTableModel(int length);

    protected abstract Object[] mapDataToColumns(T object);
}
