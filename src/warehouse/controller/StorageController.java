package warehouse.controller;

import warehouse.model.BoxType;
import warehouse.model.Palette;

/**
 * Created by Theo on 4/23/17.
 */
public abstract class StorageController {

    private int freeSlots;
    private int [][]slotsMatrix; //slotsMatrix[AisleNumber][cell]

    private void store(Palette palette) {

    }

    private void retreive(String id) {

    }

    static void load(Palette palette) {
        // TODO: Implement algorithm (not required by assignment)
    }

    static Palette unload(BoxType type, int qty) {
        // TODO: Implement algorithm not (required by assignment)

        return null;
    }
}
