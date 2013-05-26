package TableConverter;

import DataEntity.DataEntity;

import javax.swing.table.TableModel;

/**
 * Konwertuje tablicę obiektów DataEntity na TableModel do wyświetlenia.
 */
public abstract class TableConverter<T extends DataEntity> {

    public TableModel getTableModel(T[] data)
    {
        TableModel model = getEmptyTableModel(data.length);
        fillTableModel(data, model);
        return model;
    }

    protected abstract TableModel getEmptyTableModel(int length);

    protected abstract Object[] mapDataToColumns(T object);

    protected void fillTableModel(T[] data, TableModel model) {
        for (int row = 0; row < data.length; row++) {
            Object[] dataInColumns = mapDataToColumns(data[row]);
            for (int column = 0; column < dataInColumns.length; column++) {
                model.setValueAt(dataInColumns[column], row, column);
            }
        }
    }
}
