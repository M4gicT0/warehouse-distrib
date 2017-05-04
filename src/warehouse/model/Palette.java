package warehouse.model;

import warehouse.controller.Station;
import warehouse.utils.Destination;

import java.util.ArrayList;

/**
 * Created by Theo on 4/23/17.
 */
public class Palette {

    private String id;
    private int boxesQty;
    private final int CAPACITY = 16;
    private Destination destination;
    private ArrayList<Box> boxes;

    public Palette(String id, Destination destination) {
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

    public boolean has(Box.Type type, int qty) {
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

    public void addBox(Box box) throws Exception {
        if ((boxesQty + 1) >= CAPACITY)
            throw new Exception("Palette full !");

        boxes.add(box);
        boxesQty++;
    }

    public int getCapacity() {
        return CAPACITY;
    }

    public ArrayList<Box> removeBoxes(Box.Type type, int number) throws Exception {
        if (!has(type, number))
            throw new Exception("Not enough boxes on palette !");

        ArrayList<Box> order = new ArrayList<>();

        for (int i = 0; i < number; i++) {
            if (boxes.get(i).getType() == type)
                order.add(boxes.remove(i));
        }

        return order;
    }
}
