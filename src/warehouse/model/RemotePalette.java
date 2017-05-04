package warehouse.model;

import warehouse.utils.Destination;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.RemoteObject;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

/**
 * Created by Theo on 4/23/17.
 */
public class RemotePalette extends UnicastRemoteObject implements Palette {

    private String id;
    private int boxesQty;
    private final int CAPACITY = 16;
    private Destination destination;
    private ArrayList<RemoteBox> boxes;

    public RemotePalette(String id, Destination destination) throws RemoteException {
        super();
        this.id = id;
        this.destination = destination;
        boxes = new ArrayList<>();
    }

    public String getId() {
        return id;
    }

    public int getBoxesQty() {
        return boxesQty;
    }

    public boolean has(RemoteBox.Type type, int qty) {
        for (RemoteBox box : boxes) {
            if (box.getType() == type)
                qty--;
        }

        return (qty <= 0);
    }

    public Destination getDestination() {
        return destination;
    }

    public void setDestination(Destination destination) {
        this.destination = destination;
    }

    public void addBox(RemoteBox box) throws Exception {
        if ((boxesQty + 1) >= CAPACITY)
            throw new Exception("RemotePalette full !");

        boxes.add(box);
        boxesQty++;
    }

    public int getCapacity() {
        return CAPACITY;
    }

    public ArrayList<RemoteBox> removeBoxes(RemoteBox.Type type, int number) throws Exception {
        if (!has(type, number))
            throw new Exception("Not enough boxes on palette !");

        ArrayList<RemoteBox> order = new ArrayList<>();

        for (int i = 0; i < number; i++) {
            if (boxes.get(i).getType() == type)
                order.add(boxes.remove(i));
        }

        return order;
    }
}
