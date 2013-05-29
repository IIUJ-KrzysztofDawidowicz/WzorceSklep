package WzorceSklep.GUI.DataRenderingUtils;

import WzorceSklep.DataAccessObject;
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

    protected final DataAccessObject<T>       dao;
    protected final TableAccessors            tableAccessors;
    protected final AbstractTableConverter<T> tableConverter;

    public RefreshTableAction(AbstractTableConverter<T> tableConverter,
                              DataAccessObject<T> dao,
                              TableAccessors tableAccessors)
    {
        this.dao = dao;
        this.tableAccessors = tableAccessors;
        this.tableConverter = tableConverter;
    }

    @Override
    public void execute() throws InvalidDataException, SQLException, ClassNotFoundException {
        String order_by= tableAccessors.getOrderBy();
        String lookFor = tableAccessors.getLookFor();
        List<T> list;
        if (lookFor.isEmpty())
            list = dao.select(order_by);
        else
            list = dao.select(lookFor, order_by);

        TableModel model = tableConverter.getTableModel(new ArrayList<T>(list));
        tableAccessors.setTableModel(model);
        tableAccessors.emptyFields();
    }

}
