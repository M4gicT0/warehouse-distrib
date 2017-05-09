package warehouse.controller;

import warehouse.DAO.BoxDAO;
import warehouse.DAO.PaletteDAO;
import warehouse.shared.model.BoxType;
import warehouse.shared.model.Palette;
import warehouse.utils.DatabaseManager;

import java.rmi.RemoteException;
import java.sql.SQLException;

/**
 * Created by Theo on 4/23/17.
 * This is where the main algorithm is implemented:
 * load() and unload().
 */
public class StorageController {

    private int freeSlots;
    private int [][]slotsMatrix; //slotsMatrix[AisleNumber][cell]
    private DatabaseManager db;
    private BoxDAO boxDAO;
    private PaletteDAO paletteDAO;

    public StorageController() {
        db = new DatabaseManager();
        try {
            db.dropTables();
            db.createTables();
            db.addAisle();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //freeSlots = db.getPaletteCount(0); //Repeat for each aisle, and substract its capacity
    }

    public void load(Palette palette) {
        // TODO: Implement algorithm (not required by assignment)
        paletteDAO.createPalette();
    }

    public Palette unload(BoxType type, int qty) {
        // TODO: Implement algorithm not (required by assignment)

        return null;
    }
}
