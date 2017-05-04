package warehouse.model;

import java.util.Date;
import java.util.HashMap;

/**
 * Created by transpalette on 4/30/17.
 */
public class OrderManifest {

    private Order order;
    private HashMap<RemoteBox.Type, Integer> list;
    private Date dueDate;
    private Date createdAt;

    public OrderManifest(HashMap<RemoteBox.Type, Integer> list, Date dueDate) {
        order = null;
        this.list = list;
        this.dueDate = dueDate;
        createdAt = new Date();
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Order getOrder() {
        return order;
    }

    public HashMap<RemoteBox.Type, Integer> getList() {
        return list;
    }
}
