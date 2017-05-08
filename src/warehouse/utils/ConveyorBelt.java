package warehouse.utils;

import warehouse.model.Palette;

import java.util.ArrayList;
import java.util.Observable;

/**
 * Created by Theo on 4/23/17.
 */
public class ConveyorBelt extends Observable {

    private static ConveyorBelt instance = new ConveyorBelt();
    private ArrayList<Palette> palettes;

    public static ConveyorBelt getInstance() {
        return instance;
    }

    private ConveyorBelt() {
        palettes = new ArrayList<>();
    }

    public void put(Palette palette) {
        palettes.add(palette);
        this.notifyObservers();
    }

    public Palette get(int i) {
        return palettes.get(i);
    }

    public Palette remove(int i) {
        return palettes.remove(i);
    }

    public int getPalettesNumber() {
        return palettes.size();
    }
}
