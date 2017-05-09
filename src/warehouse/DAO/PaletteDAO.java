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

        try {
            Map<String, Object> row = db.getPaletteById(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        //TODO: parse row fields and create palette Object from them

        //palette = new RemotePalette(stuff from above);

        return palette;
    }

    public static List<Palette> getPalettes() {
        List<Palette> palettes = new LinkedList<>();

        try {
            ArrayList<HashMap<String, Object>> rows = db.getAllPalettes();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        //TODO: parse rows and create palette Object from them
        //palettes.push(new RemotePalette(stuff from above));

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
