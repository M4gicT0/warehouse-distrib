package warehouse.controller;

import warehouse.model.Box;
import warehouse.model.Palette;

/**
 * Created by Theo on 4/23/17.
 */
public abstract class StorageController {

    private int freeSlots;
    private int [][]slotsMatrix; //slotsMatrix[AisleNumber][cell]

    static void load(Palette palette) {
        // TODO: Implement algorithm (not required by assignment)
    }

    static Palette unload(Box.Type type, int qty) {
        // TODO: Implement algorithm not (required by assignment)

        return null;
    }
}
