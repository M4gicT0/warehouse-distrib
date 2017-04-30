package warehouse.model;

import java.util.Date;
import java.util.HashMap;

/**
 * Created by transpalette on 4/30/17.
 */
public class OrderManifest {

    private Order order;
    private HashMap<Box.Type, Integer> manifest;
    private Date dueDate;
    private Date createdAt;

    public OrderManifest(HashMap<Box.Type, Integer> manifest, Date dueDate) {
        order = null;
        this.manifest = manifest;
        this.dueDate = dueDate;
        createdAt = new Date();
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Order getOrder() {
        return order;
    }

    public HashMap<Box.Type, Integer> getManifest() {
        return manifest;
    }
}
