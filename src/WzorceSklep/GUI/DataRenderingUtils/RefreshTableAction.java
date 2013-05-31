package WzorceSklep.GUI.DataRenderingUtils;

import WzorceSklep.Data.TableDataGetter;
import WzorceSklep.DataEntity;
import WzorceSklep.GUI.RepresentDataAction;
import com.sun.media.sound.InvalidDataException;

import javax.swing.table.TableModel;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: KrzysztofD
 * Date: 28.05.13
 * Time: 16:48
 * To change this template use File | Settings | File Templates.
 */
public class RefreshTableAction<T extends DataEntity> implements RepresentDataAction {

    private final TableDataGetter<T> tableDataGetter;
    private final TableAccessors            tableAccessors;
    private final AbstractTableConverter<T> tableConverter;

    public RefreshTableAction(AbstractTableConverter<T> tableConverter,
                              TableDataGetter<T> tableDataGetter,
                              TableAccessors tableAccessors)
    {
        this.tableDataGetter = tableDataGetter;
        this.tableAccessors = tableAccessors;
        this.tableConverter = tableConverter;
    }

    @Override
    public void execute() throws InvalidDataException, SQLException, ClassNotFoundException {
        String order_by= tableAccessors.getOrderBy();
        String lookFor = tableAccessors.getLookFor();
        List<T> list;
        if (lookFor.isEmpty())
            list = tableDataGetter.select(order_by);
        else
            list = tableDataGetter.select(lookFor, order_by);

        TableModel model = tableConverter.getTableModel(new ArrayList<T>(list));
        tableAccessors.setTableModel(model);
        tableAccessors.emptyFields();
    }

}
