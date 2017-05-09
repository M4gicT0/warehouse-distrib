package warehouse.shared.model;

import java.rmi.Remote;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by transpalette on 5/9/17.
 */
public interface Order extends Remote {

    String getId();
    Date created_at();
    Date processed_at();
    void addBoxes(ArrayList<Box> boxes);
}
