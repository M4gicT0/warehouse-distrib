package warehouse.DAO;

import warehouse.model.RemotePalette;
import warehouse.shared.model.Palette;
import warehouse.utils.DatabaseManager;
import warehouse.utils.Destination;

import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.*;

/**
 * Created by Theo on 5/8/17.
 */
public class PaletteDAO {

    private static DatabaseManager db;
    private static String nextId;

    static {
        db = new DatabaseManager();
    }

    public static Palette createPalette(int aisleNumber, int x, int y) {
        String id;
        Palette palette = null;

        synchronized (PaletteDAO.class) {
            id = String.valueOf(Integer.valueOf(nextId) + 1);
            nextId = id;
        }

        try {
            palette = new RemotePalette(id, Destination.NONE);
            db.insertPalette(palette, aisleNumber, x, y);
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return palette;
    }


    public static Palette getPalette(String id) {
        Palette palette = null;
        Map<String, Object> row = new HashMap<>();

        try {
            row = db.getPaletteById(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            palette = new RemotePalette((String) row.get("id"), Destination.NONE);
        } catch (RemoteException e) {
            e.printStackTrace();
        }

        return palette;
    }

    public static ArrayList<Palette> getPalettes() {
        ArrayList<Palette> palettes = new ArrayList<>();
        ArrayList<HashMap<String, Object>> rows = new ArrayList<>();

        try {
            rows = db.getAllPalettes();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        for(HashMap<String, Object> row : rows) {
            try {
                palettes.add(new RemotePalette((String) row.get("id"), Destination.NONE));
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }

        return palettes;
    }

    public static Palette deletePalette(String id) {
        Palette palette = getPalette(id);
        try {
            db.deletePalette(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return palette;
    }
}
