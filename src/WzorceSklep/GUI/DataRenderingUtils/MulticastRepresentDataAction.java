package WzorceSklep.GUI.DataRenderingUtils;

import WzorceSklep.GUI.RepresentDataAction;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
* Created with IntelliJ IDEA.
* User: KrzysztofD
* Date: 29.05.13
* Time: 14:06
* To change this template use File | Settings | File Templates.
*/
public class MulticastRepresentDataAction implements RepresentDataAction {

    private final List<RepresentDataAction> actions;

    public MulticastRepresentDataAction(RepresentDataAction... dataActions) {
        actions = new ArrayList<RepresentDataAction>(dataActions.length);
        Collections.addAll(actions, dataActions);
    }

    @Override
    public void execute() throws SQLException {
        try {
            for(RepresentDataAction action: actions)
                action.execute();
        } catch (SQLException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }
}
