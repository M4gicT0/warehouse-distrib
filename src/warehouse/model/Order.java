package warehouse.model;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Theo on 4/23/17.
 */
public class Order {

    private String id;
    private Date created_at;
    private Date processed_at;
    private ArrayList<Box> boxes;

    public Order(String id) {
        this.id = id;
        created_at = new Date();
        processed_at = null;
        boxes = new ArrayList<>();
    }

    public String getId() {
        return id;
    }

    public Date created_at() {
        return created_at;
    }

    public Date processed_at() {
        return processed_at;
    }

    public void addBoxes(ArrayList<Box> boxes) {
        this.boxes.addAll(boxes);
    }
}
