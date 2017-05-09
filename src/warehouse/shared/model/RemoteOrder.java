package warehouse.shared.model;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Theo on 4/23/17.
 */
public class RemoteOrder extends UnicastRemoteObject implements Order {

    private String id;
    private Date created_at;
    private Date processed_at;
    private ArrayList<Box> boxes;

    public RemoteOrder(String id) throws RemoteException {
        super();
        this.id = id;
        created_at = new Date();
        processed_at = null;
        boxes = new ArrayList<>();
    }

    public RemoteOrder(String id, Date created_at, Date processed_at) throws RemoteException {
        super();
        this.id = id;
        this.created_at = created_at;
        this.processed_at = processed_at;
        boxes = new ArrayList<>();
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public Date created_at() {
        return created_at;
    }

    @Override
    public Date processed_at() {
        return processed_at;
    }

    @Override
    public void addBoxes(ArrayList<Box> boxes) {
        this.boxes.addAll(boxes);
    }
}
