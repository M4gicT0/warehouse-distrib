package warehouse.shared.model;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Date;
import java.util.HashMap;

/**
 * Created by transpalette on 4/30/17.
 */
public class RemoteOrderManifest extends UnicastRemoteObject implements OrderManifest {

    private Order order;
    private HashMap<BoxType, Integer> list;
    private Date dueDate;
    private Date createdAt;

    public RemoteOrderManifest(HashMap<BoxType, Integer> list, Date dueDate) throws RemoteException {
        super();
        order = null;
        this.list = list;
        this.dueDate = dueDate;
        createdAt = new Date();
    }

    @Override
    public void setOrder(Order order) throws RemoteException {
        this.order = order;
    }

    @Override
    public Order getOrder() {
        return order;
    }

    @Override
    public HashMap<BoxType, Integer> getList() {
        return list;
    }
}
