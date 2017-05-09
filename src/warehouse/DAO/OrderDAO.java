package warehouse.DAO;

import warehouse.shared.model.RemoteOrder;
import warehouse.shared.model.Box;
import warehouse.shared.model.BoxType;
import warehouse.shared.model.RemoteBox;
import warehouse.utils.DatabaseManager;

import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.*;

/**
 * Created by Theo on 5/9/17.
 */
public class OrderDAO {

    private static DatabaseManager db;
    private static String nextId;

    static {
        db = new DatabaseManager();
    }

    public static RemoteOrder createOrder(Date created_at, Date processed_at, List<Box> boxes) {
        String id;
        RemoteOrder remoteOrder = null;

        synchronized (OrderDAO.class) {
            id = String.valueOf(Integer.valueOf(nextId) + 1);
        }

        try {
            remoteOrder = new RemoteOrder(id);
            db.insertOrder(remoteOrder, boxes);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return remoteOrder;
    }

    public static RemoteOrder getOrder(String id) {
        RemoteOrder remoteOrder = null;
        Map<String, Object> row = new HashMap<>();

        try {
             row = db.getOrderById(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        remoteOrder = new RemoteOrder(id, (Date) row.get("created_at"), (Date) row.get("processed_at"));
        ArrayList<Box> boxes = new ArrayList<>();
        ArrayList<HashMap<String, Object>> rows = new ArrayList<>();

        try {
            rows = db.getBoxesByOrderId(remoteOrder.getId());
        } catch (SQLException e) {
            e.printStackTrace();
        }

        for(HashMap<String, Object> boxRow : rows) {
            try {
                boxes.add(new RemoteBox((String) boxRow.get("id"), (int) row.get("items_qty"), (BoxType) row.get("type")));
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }

        remoteOrder.addBoxes(boxes);

        return remoteOrder;
    }

    public static ArrayList<RemoteOrder> getOrders() {
        ArrayList<RemoteOrder> remoteOrders = new ArrayList<>();
        ArrayList<HashMap<String, Object>> rows = new ArrayList<>();

        try {
            rows = db.getAllOrders();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        for(HashMap<String, Object> row : rows) {
            remoteOrders.add(new RemoteOrder((String) row.get("id"), (Date) row.get("created_at"), (Date) row.get("processed_at")));
        }

        return remoteOrders;
    }

    public static RemoteOrder deleteOrder(String id) {
        RemoteOrder remoteOrder = getOrder(id);

        try {
            db.deleteOrder(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return remoteOrder;
    }
}
