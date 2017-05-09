package warehouse.DAO;

import warehouse.model.RemotePalette;
import warehouse.shared.model.Palette;
import warehouse.utils.DatabaseManager;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by Theo on 5/8/17.
 */
public class PaletteDAO {

    private static DatabaseManager db;
    private static Map<String, Palette> palettes;
    private static String nextId;

    static {
        db = new DatabaseManager();
        palettes = new ConcurrentHashMap<>();
        initPalettes();
    }

    private static void initPalettes() {
        //Fetch all palettes from DB
    }

    public static Palette getPalette(String id) {
        Palette palette = null;

        if (palettes.containsKey(id))
            palette = palettes.get(id);

        return palette;
    }

    public static List<Palette> getPalettes() {
        return new LinkedList<Palette>(palettes.values());
    }

    public static Palette deletePalette(String id) {
        db.deletePalette(id);

        return palettes.remove(id);
    }
}
