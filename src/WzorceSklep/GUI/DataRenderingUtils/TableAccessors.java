package WzorceSklep.GUI.DataRenderingUtils;

import javax.swing.table.TableModel;

/**
 * Created with IntelliJ IDEA.
 * User: KrzysztofD
 * Date: 29.05.13
 * Time: 10:27
 * To change this template use File | Settings | File Templates.
 */
public interface TableAccessors {
    String getOrderBy();

    void emptyFields();

    void setTableModel(TableModel model);

    String getLookFor();
}
