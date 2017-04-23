package warehouse.controller;

import warehouse.model.Box;
import warehouse.model.Palette;

/**
 * Created by Theo on 4/23/17.
 */
public abstract class StorageController {

    private int freeSlots;
    private int [][]slotsMatrix;

    static void load(Palette palette) {

    }

    static Palette unload(Box.Type type) {
        return null;
    }
}
