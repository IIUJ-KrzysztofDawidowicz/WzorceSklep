package WzorceSklep.GUI.DataRenderingUtils;

import WzorceSklep.DataEntity;

import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Konwertuje tablicę obiektów DataEntity na TableModel do wyświetlenia.
 */
public abstract class AbstractTableConverter<T extends DataEntity> {

    protected final Map<String,Integer> index;

    protected AbstractTableConverter() {
        String[] colmunNames = getColumnNames();
        index = new HashMap<String, Integer>();
        for (int i = 0; i < colmunNames.length; i++) {
            index.put(colmunNames[i], i);
        }
    }

    public TableModel getTableModel(List<T> data)
    {
        TableModel model = getEmptyTableModel(data.size());
        fillTableModel(model, data);
        return model;
    }

    private void fillTableModel(TableModel model, List<T> data) {
        for (int row = 0; row < data.size(); row++) {
            Object[] dataInColumns = mapDataToColumns(data.get(row));
            for (int column = 0; column < dataInColumns.length; column++) {
                model.setValueAt(dataInColumns[column], row, column);
            }
        }
    }

    protected TableModel getEmptyTableModel(final int length) {
        return new DefaultTableModel(getColumnNames(), length)
        {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;   //Disallow the editing of any cell
            }
        };
    }

    protected abstract Object[] mapDataToColumns(T object);

    protected abstract String[] getColumnNames();
}
