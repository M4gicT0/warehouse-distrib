package warehouse.DAO;

import warehouse.shared.model.RemoteBox;
import warehouse.shared.model.Box;
import warehouse.shared.model.BoxType;
import warehouse.shared.model.Palette;
import warehouse.utils.DatabaseManager;

import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.*;

/**
 * Created by Theo on 5/8/17.
 */
public class BoxDAO {

    private static DatabaseManager db;
    private static String nextId;

    static {
        db = new DatabaseManager();
    }

    public static Box getBox(String id) {
        Box box = null;

        try {
            Map<String, Object> row = db.getBoxById(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        //TODO: parse row fields and create box Object from them

        //box = new RemoteBox(stuff from above);

        return box;
    }

    public static List<Box> getBoxes() {
        List<Box> boxes = new LinkedList<>();

        try {
            ArrayList<HashMap<String, Object>> rows = db.getAllBoxes();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        //TODO: parse rows and create box Object from them
        //boxes.push(new RemoteBox(stuff from above));

        return boxes;
    }

    public static Box deleteBox(String id) {
        Box box = getBox(id);
        try {
            db.deleteBox(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return box;
    }
}
