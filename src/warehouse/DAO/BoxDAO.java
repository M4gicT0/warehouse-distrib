package warehouse.DAO;

import warehouse.shared.model.RemoteBox;
import warehouse.shared.model.Box;
import warehouse.shared.model.BoxType;
import warehouse.shared.model.Palette;
import warehouse.utils.DatabaseManager;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Created by Theo on 5/8/17.
 */
public class BoxDAO {

    private static DatabaseManager db;
    private static String nextId;

    static {
        db = new DatabaseManager();
    }

    public static Box createBox(Palette palette, BoxType type) {
        String id;
        Box box = null;

        synchronized (BoxDAO.class) {
            id = String.valueOf(Integer.valueOf(nextId) + 1);
        }

        try {
            box = new RemoteBox(id, 0, type);
            db.insertBox(box, palette.getId());
        } catch (RemoteException e) {
            e.printStackTrace();
        }

        return box;
    }

    public static Box getBox(String id) {
        Box box = null;

        Map<String, Object> row = db.getBoxById(id);

        //TODO: parse row fields and create box Object from them

        //box = new RemoteBox(stuff from above);

        return box;
    }

    public static List<Box> getBoxes() {
        List<Box> boxes = new LinkedList<>();

        ArrayList<Map<String, Object>> rows = db.getAllBoxes();

        //TODO: parse rows and create box Object from them
        //boxes.push(new RemoteBox(stuff from above));

        return boxes;
    }

    public static Box deleteBox(String id) {
        Box box = getBox(id);
        db.deleteBox(id);

        return box;
    }
}
