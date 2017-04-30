package warehouse.controller;

import warehouse.ConveyorBelt;
import warehouse.model.Box;
import warehouse.model.Order;
import warehouse.model.OrderManifest;
import warehouse.model.Palette;
import java.util.Observable;
import java.util.Set;

/**
 * Created by Theo on 4/23/17.
 */
public class PickingStation implements Destination {

    private Palette palette;
    private Box.Type processingType;
    private int processingQty;
    private ConveyorBelt belt = ConveyorBelt.getInstance();
    private Crane crane;
    private Order order;

    public PickingStation(Crane crane) {
        this.crane = crane;
        this.crane.setPickingStation(this);
    }

    @Override
    public void process() {
        //Process each received palette: take the desired boxes and put them in a truck
        try {
            order.addBoxes(palette.removeBoxes(processingType, processingQty)); //Process [TYPE -> QTY]

            if (palette.getBoxesQty() > 0) { //Send it back to the crane if not empty
                palette.setDestination(crane);
                belt.put(palette);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void log(String message) {
        System.out.println(message);
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
        order = new Order("randomId");

        for (Box.Type boxType : types) {
            int quantity = manifest.getList().get(boxType);
            processingType = boxType;
            processingQty = quantity;
            crane.fetch(boxType, quantity);
        }
    }
}
