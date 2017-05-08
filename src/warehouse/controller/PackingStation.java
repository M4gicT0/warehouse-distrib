package warehouse.controller;

import warehouse.model.Box;
import warehouse.model.Palette;
import warehouse.model.RemotePalette;
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
        Palette palette = null;
        try {
            palette = new RemotePalette("0", Destination.CRANE);
        } catch (RemoteException e) {
            e.printStackTrace();
        }

        /* TODO: Find an alternative solution to create palettes without using a capacity */
        for (Box box : boxes) {
            try {
                if (palette.getCapacity() < (palette.getBoxesQty() + 1)) {
                    try {
                        palette.addBox(box);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    belt.put(palette);
                    try {
                        palette = new RemotePalette(palette.getId().concat("0"), Destination.CRANE);
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                    log("New palette sent to the crane !");
                }
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void log(String message) {
        System.out.println(message);
    }

    @Override
    public void update(Observable observable, Object o) {
        // Nothing happens here because it shouldn't receive any palette
        // from the conveyor belt
    }
}
