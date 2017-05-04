package warehouse.controller;

import warehouse.model.RemoteBox;
import warehouse.model.RemotePalette;

/**
 * Created by Theo on 4/23/17.
 */
public abstract class StorageController {

    private int freeSlots;
    private int [][]slotsMatrix; //slotsMatrix[AisleNumber][cell]

    private void store(RemotePalette palette) {

    }

    private void retreive(String id) {

    }

    static void load(RemotePalette palette) {
        // TODO: Implement algorithm (not required by assignment)
    }

    static RemotePalette unload(RemoteBox.Type type, int qty) {
        // TODO: Implement algorithm not (required by assignment)

        return null;
    }
}
