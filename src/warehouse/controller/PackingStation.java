package warehouse.controller;

import warehouse.shared.model.Box;
import warehouse.shared.model.BoxType;
import warehouse.shared.model.Palette;
import warehouse.shared.model.RemotePalette;
import warehouse.utils.ConveyorBelt;
import warehouse.utils.Destination;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Observable;

/**
 * Created by Theo on 4/23/17.
 */
public class PackingStation implements Station {

    private ArrayList<Box> boxes;
    private ConveyorBelt belt;

    public PackingStation() {
        boxes = new ArrayList<>();
        belt = ConveyorBelt.getInstance();
    }

    public void receiveTruck(ArrayList<Box> load) {
        boxes.addAll(load);
    }

    @Override
    public void process() {
        Palette palette = null;
        try {
            palette = new RemotePalette("0", Destination.CRANE);
        } catch (RemoteException e) {
            e.printStackTrace();
        }

        BoxType previousType = null;

        /* TODO: Find an alternative solution to create palettes without using a capacity. Verify that type is the same. Crappy algorithm */
        for (Box box : boxes) {
            try {
                if (palette.getCapacity() > (palette.getBoxesQty() + 1) && (box.getType() == previousType || previousType == null)) {
                    try {
                        previousType = box.getType();
                        palette.addBox(box);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    belt.put(palette);
                    try {
                        palette = new RemotePalette(palette.getId().concat("0"), Destination.CRANE);
                        palette.addBox(box);
                        previousType = box.getType();
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                    log("New palette sent to the crane !");
                }
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }

        //In case the last one hasn't been sent
        try {
            if (palette.getBoxesQty() > 0)
                belt.put(palette);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void log(String message) {
        System.out.println(this.getClass().getName() + ": " + message);
    }

    @Override
    public void update(Observable observable, Object o) {
        // Nothing happens here because it shouldn't receive any palette
        // from the conveyor belt
    }
}
