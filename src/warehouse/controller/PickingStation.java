package warehouse.controller;

import warehouse.ConveyorBelt;
import warehouse.model.Box;
import warehouse.model.OrderManifest;
import warehouse.model.Palette;
import java.util.Observable;
import java.util.Set;

/**
 * Created by Theo on 4/23/17.
 */
public class PickingStation implements Destination {

    private Palette palette;
    private ConveyorBelt belt = ConveyorBelt.getInstance();
    private Crane crane;

    public PickingStation(Crane crane) {
        this.crane = crane;
        this.crane.setPickingStation(this);
    }

    @Override
    public void process() {
        //Process each received palette: take the desired boxes and put them in a truck
        //palette.removeBoxes();
    }

    @Override
    public void log(String message) {

    }

    @Override
    public void update(Observable observable, Object o) {
        for (int i = 0; i < belt.getPalettesNumber(); i++) {
            if (belt.get(i).getDestination() == this) {
                palette = belt.remove(i);
                process();
            }
        }
    }

    public void processOrder(OrderManifest manifest) {
        Set<Box.Type> types = manifest.getList().keySet();

        for (Box.Type boxType : types) {
            int quantity = manifest.getList().get(boxType);

            for (int i = 0; i < quantity; i++)
                crane.fetch(boxType, quantity);
        }
    }
}
