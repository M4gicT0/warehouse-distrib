package warehouse.DAO;

import warehouse.shared.model.Box;
import warehouse.shared.model.BoxType;
import warehouse.shared.model.RemoteBox;
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
        Map<String, Object> row = new HashMap<>();

        try {
            row = db.getBoxById(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            box = new RemoteBox((String) row.get("id"), (int) row.get("items_qty"), (BoxType) row.get("type"));
        } catch (RemoteException e) {
            e.printStackTrace();
        }

        return box;
    }

    public static ArrayList<Box> getBoxes() {
        ArrayList<Box> boxes = new ArrayList<>();
        ArrayList<HashMap<String, Object>> rows = new ArrayList<>();

        try {
            rows = db.getAllBoxes();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        for(HashMap<String, Object> row : rows) {
            try {
                boxes.add(new RemoteBox((String) row.get("id"), (int) row.get("items_qty"), (BoxType) row.get("type")));
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }

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
