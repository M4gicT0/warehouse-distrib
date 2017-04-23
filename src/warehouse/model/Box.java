package warehouse.model;

/**
 * Created by Theo on 4/23/17.
 */
public class Box {

    private String id;
    private int itemsQty;
    private String type;

    public Box(String id, int itemsQty, String type) {
        this.id = id;
        this.itemsQty = itemsQty;
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public int getItemsQty() {
        return itemsQty;
    }
}
