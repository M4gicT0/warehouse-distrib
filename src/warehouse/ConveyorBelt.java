package warehouse;

import warehouse.model.Palette;

import java.util.LinkedList;
import java.util.Observable;

/**
 * Created by Theo on 4/23/17.
 */
public class ConveyorBelt extends Observable {

    private static ConveyorBelt instance = new ConveyorBelt();
    private LinkedList<Palette> palettes;

    public static ConveyorBelt getInstance() {
        return instance;
    }

    private ConveyorBelt() {
        palettes = new LinkedList<>();
    }

    public void put(Palette palette) {
        palettes.add(palette);
        this.notifyObservers();
    }

    public Palette pop() {
        return palettes.pop();
    }

    public Palette peek() {
        return palettes.peek();
    }
}
