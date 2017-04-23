package warehouse.controller;

/**
 * Created by Theo on 4/23/17.
 */
public class Crane implements Destination {
    @Override
    public void process() {
        StorageController.load(null); //Example
    }

    @Override
    public void log() {

    }
}
