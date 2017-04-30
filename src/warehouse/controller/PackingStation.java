package warehouse.controller;

import warehouse.ConveyorBelt;
import warehouse.model.Box;
import warehouse.model.Palette;

import java.util.ArrayList;
import java.util.Observable;

/**
 * Created by Theo on 4/23/17.
 */
public class PackingStation implements Destination {

    private ArrayList<Box> boxes;
    private Crane crane;
    private ConveyorBelt belt;

    public PackingStation(Crane crane) {
        boxes = new ArrayList<>();
        this.crane = crane;
        this.crane.setPackingStation(this);
        belt = ConveyorBelt.getInstance();
    }

    public void receiveTruck(ArrayList<Box> load) {
        boxes.addAll(load);
    }

    @Override
    public void process() {
        Palette palette = new Palette("0", crane);

        /* TODO: Find an alternative solution to create palettes without using a capacity */
        for (Box box : boxes) {
            if (palette.getCapacity() < (palette.getBoxesQty() + 1)) {
                try {
                    palette.addBox(box);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                belt.put(palette);
                palette = new Palette(palette.getId().concat("0"), crane);
                log("New palette");
            }
        }
    }

    @Override
    public void log(String message) {
        //TODO : log in the system
    }

    @Override
    public void update(Observable observable, Object o) {
        // Nothing happens here because it shouldn't receive any palette
        // from the conveyor belt
    }
}
