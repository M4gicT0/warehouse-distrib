package warehouse.model;

import warehouse.utils.Destination;

import java.rmi.RemoteException;
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
    private ArrayList<Box> boxes;

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

    public boolean has(BoxType type, int qty) throws RemoteException {
        for (Box box : boxes) {
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

    public void addBox(Box box) throws RemoteException {
        if ((boxesQty + 1) >= CAPACITY)
            throw new RemoteException("RemotePalette full !");

        boxes.add(box);
        boxesQty++;
    }

    public int getCapacity() {
        return CAPACITY;
    }

    public ArrayList<Box> removeBoxes(BoxType type, int number) throws RemoteException {
        if (!has(type, number))
            throw new RemoteException("Not enough boxes on palette !");

        ArrayList<Box> order = new ArrayList<>();

        for (int i = 0; i < number; i++) {
            if (boxes.get(i).getType() == type)
                order.add(boxes.remove(i));
        }

        return order;
    }
}
