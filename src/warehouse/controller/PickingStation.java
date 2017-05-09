package warehouse.controller;

import warehouse.model.*;
import warehouse.shared.model.BoxType;
import warehouse.shared.model.Palette;
import warehouse.shared.model.RemoteOrder;
import warehouse.utils.ConveyorBelt;
import warehouse.utils.Destination;

import java.rmi.RemoteException;
import java.util.Observable;
import java.util.Set;

/**
 * Created by Theo on 4/23/17.
 */
public class PickingStation implements Station {

    private Palette palette;
    private BoxType processingType;
    private int processingQty;
    private ConveyorBelt belt;
    private Crane crane;
    private RemoteOrder remoteOrder;

    public PickingStation(Crane crane) {
        this.crane = crane;
        belt = ConveyorBelt.getInstance();
    }

    @Override
    public void process() {
        //Process each received palette: take the desired boxes and put them in a truck
        try {
            remoteOrder.addBoxes(palette.removeBoxes(processingType, processingQty)); //Process [TYPE -> QTY]

            if (palette.getBoxesQty() > 0) { //Send it back to the crane if not empty
                palette.setDestination(Destination.CRANE);
                belt.put(palette);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void log(String message) {
        System.out.println(this.getClass().getName() + ": " + message);
    }

    @Override
    public void update(Observable observable, Object o) {
        for (int i = 0; i < belt.getPalettesNumber(); i++) {
            try {
                if (belt.get(i).getDestination() == Destination.PICKING_STATION) {
                    palette = belt.remove(i);
                    process();
                }
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    public RemoteOrder processOrder(OrderManifest manifest) throws RemoteException {
        Set<BoxType> types = manifest.getList().keySet();
        remoteOrder = new RemoteOrder("randomId");

        for (BoxType boxType : types) {
            int quantity = manifest.getList().get(boxType);
            processingType = boxType;
            processingQty = quantity;
            crane.fetch(boxType, quantity);
        }

        return remoteOrder;
    }
}
