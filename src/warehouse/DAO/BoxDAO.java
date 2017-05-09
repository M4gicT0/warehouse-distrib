package warehouse.DAO;

import warehouse.shared.model.Box;
import warehouse.utils.DatabaseManager;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by Theo on 5/8/17.
 */
public class BoxDAO {

    private static DatabaseManager db;
    private static Map<String, Box> boxes;
    private static String nextId;

    static {
        db = new DatabaseManager();
        boxes = new ConcurrentHashMap<>();
        initBoxes();
    }

    private static void initBoxes() {
        //Fetch all boxes from DB
    }

    public static Box getBox(String id) {
        Box box = null;

        if (boxes.containsKey(id))
            box = boxes.get(id);

        return box;
    }

    public static List<Box> getBoxes() {
        return new LinkedList<Box>(boxes.values());
    }

    public static Box deleteBox(String id) {
        db.deleteBox(id);

        return boxes.remove(id);
    }
}
