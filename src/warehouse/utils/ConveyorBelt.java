package warehouse.utils;

import warehouse.model.Palette;

import java.rmi.RemoteException;
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

    public void put(Palette palette) throws RemoteException {
        log("Sending palette to " + palette.getDestination().toString());
        palettes.add(palette);

        setChanged();
        notifyObservers();
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

    private void log(String message) {
        System.out.println(this.getClass().getName() + ": " + message);
    }
}
