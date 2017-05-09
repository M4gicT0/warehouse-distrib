package warehouse.controller;

import warehouse.shared.model.BoxType;
import warehouse.shared.model.Palette;
import warehouse.utils.ConveyorBelt;
import warehouse.utils.Destination;

import java.rmi.RemoteException;
import java.util.Observable;

/**
 * Created by Theo on 4/23/17.
 */
public class Crane implements Station {

    private Palette palette;
    private ConveyorBelt belt;
    private StorageController storageController;

    public Crane() {
         belt = ConveyorBelt.getInstance();
         storageController = new StorageController();
    }

    @Override
    public void process() {
        storageController.load(palette);
        palette = null;
        log("Storing palette");
    }

    @Override
    public void log(String message) {
        System.out.println(this.getClass().getName() + ": " + message);
    }

    @Override
    public void update(Observable observable, Object o) {
        log("There is something on the conveyor belt !");
        for (int i = 0; i < belt.getPalettesNumber(); i++) {
            try {
                if (belt.get(i).getDestination() == Destination.CRANE) {
                    log("It is for me.");
                    palette = belt.remove(i);
                    process();
                }
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    public void fetch(BoxType type, int qty) throws RemoteException {
        Palette palette = storageController.unload(type, qty);
        palette.setDestination(Destination.PICKING_STATION);
        belt.put(palette);
    }
}
