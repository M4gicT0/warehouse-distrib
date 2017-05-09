package warehouse.controller;

import warehouse.shared.model.BoxType;
import warehouse.shared.model.Palette;

/**
 * Created by Theo on 4/23/17.
 * This is where the main algorithm is implemented:
 * load() and unload().
 */
public class StorageController {

    private int freeSlots;
    private int [][]slotsMatrix; //slotsMatrix[AisleNumber][cell]

    public StorageController() {
        //freeSlots = db.getPaletteCount(0); //Repeat for each aisle, and substract its capacity
    }

    public void load(Palette palette) {
        // TODO: Implement algorithm (not required by assignment)
        //PaletteDAO.createPalette();
    }

    public Palette unload(BoxType type, int qty) {
        // TODO: Implement algorithm not (required by assignment)

        return null;
    }
}
