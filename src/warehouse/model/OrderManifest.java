package warehouse.model;

import warehouse.shared.model.BoxType;
import warehouse.shared.model.RemoteOrder;

import java.util.Date;
import java.util.HashMap;

/**
 * Created by transpalette on 4/30/17.
 */
public class OrderManifest {

    private RemoteOrder remoteOrder;
    private HashMap<BoxType, Integer> list;
    private Date dueDate;
    private Date createdAt;

    public OrderManifest(HashMap<BoxType, Integer> list, Date dueDate) {
        remoteOrder = null;
        this.list = list;
        this.dueDate = dueDate;
        createdAt = new Date();
    }

    public void setOrder(RemoteOrder remoteOrder) {
        this.remoteOrder = remoteOrder;
    }

    public RemoteOrder getOrder() {
        return remoteOrder;
    }

    public HashMap<BoxType, Integer> getList() {
        return list;
    }
}
