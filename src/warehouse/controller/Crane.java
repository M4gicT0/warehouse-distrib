package warehouse.controller;

import warehouse.model.Palette;

import java.util.Observable;

/**
 * Created by Theo on 4/23/17.
 */
public class Crane implements Destination {

    private Palette palette;

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
        Palette p = (Palette) o;

        if (p.getDestination() == this) {
            palette = p;
            process();
        }
    }
}
