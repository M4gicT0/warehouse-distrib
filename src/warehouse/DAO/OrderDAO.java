package warehouse.DAO;

import warehouse.model.Order;
import warehouse.shared.model.Box;
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

    public static Order createOrder(Date created_at, Date processed_at, List<Box> boxes) {
        String id;
        Order order = null;

        synchronized (OrderDAO.class) {
            id = String.valueOf(Integer.valueOf(nextId) + 1);
        }

        try {
            order = new Order(id);
            db.insertOrder(order, boxes);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return order;
    }

    public static Order getOrder(String id) {
        Order order = null;

        Map<String, Object> row = db.getOrderById(id);

        //TODO: parse row fields and create order Object from them

        //order = new Order(stuff from above);

        return order;
    }

    public static List<Order> getOrders() {
        List<Order> orders = new LinkedList<>();

        ArrayList<Map<String, Object>> rows = db.getAllOrders();

        //TODO: parse rows and create order Object from them
        //orders.push(new Order(stuff from above));

        return orders;
    }

    public static Order deleteOrder(String id) {
        Order order = getOrder(id);
        db.deleteOrder(id);

        return order;
    }
}
