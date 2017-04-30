package warehouse.controller;

import warehouse.ConveyorBelt;
import warehouse.model.Palette;

import java.util.Observable;

/**
 * Created by Theo on 4/23/17.
 */
public class Crane implements Destination {

    private Palette palette;
    private ConveyorBelt belt = ConveyorBelt.getInstance();

    @Override
    public void process() {
        StorageController.load(palette);
        palette = null;
        log("Storing palette");
    }

    @Override
    public void log(String message) {
        //TODO : log in the system
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
}
