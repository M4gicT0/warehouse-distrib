package warehouse.model;

import warehouse.controller.Destination;

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

    public Destination getDestination() {
        return destination;
    }

    public void addBox(Box box) throws Exception {
        if ((boxesQty + 1) >= CAPACITY)
            throw new Exception("Palette full !");

        boxes.add(box);
        boxesQty++;
    }

    public ArrayList<Box> removeBoxes(int number) throws Exception {
        if ((number > boxesQty))
            throw new Exception("Not enough boxes on palette !");

        ArrayList<Box> order = new ArrayList<>();

        for (int i = 0; i < number; i++)
            order.add(boxes.remove(1)); //Probably wrong

        return order;
    }
}
