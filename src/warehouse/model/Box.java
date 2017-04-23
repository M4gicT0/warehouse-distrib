package warehouse.model;

/**
 * Created by Theo on 4/23/17.
 */
public class Box {

    public enum Type {
        COTTON, FOOD, WOOD;
    }

    private String id;
    private int itemsQty;
    private Type type;

    public Box(String id, int itemsQty, Type type) {
        this.id = id;
        this.itemsQty = itemsQty;
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public Type getType() {
        return type;
    }

    public int getItemsQty() {
        return itemsQty;
    }
}
