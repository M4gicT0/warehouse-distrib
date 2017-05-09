package warehouse.shared.model;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 * Created by Theo on 4/23/17.
 */
public class RemoteBox extends UnicastRemoteObject implements Box {

    private String id;
    private int itemsQty;
    private BoxType type;

    public RemoteBox(String id, int itemsQty, BoxType type) throws RemoteException {
        super();
        this.id = id;
        this.itemsQty = itemsQty;
        this.type = type;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public BoxType getType() {
        return type;
    }

    @Override
    public int getItemsQty() {
        return itemsQty;
    }
}
