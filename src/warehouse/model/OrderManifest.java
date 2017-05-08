package warehouse.model;

import java.util.Date;
import java.util.HashMap;

/**
 * Created by transpalette on 4/30/17.
 */
public class OrderManifest {

    private Order order;
    private HashMap<BoxType, Integer> list;
    private Date dueDate;
    private Date createdAt;

    public OrderManifest(HashMap<BoxType, Integer> list, Date dueDate) {
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

    public HashMap<BoxType, Integer> getList() {
        return list;
    }
}
