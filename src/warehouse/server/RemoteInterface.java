package warehouse.server;

import warehouse.model.Box;
import warehouse.model.Palette;

import java.rmi.Remote;

/**
 * Created by transpalette on 5/2/17.
 */
public interface RemoteInterface extends Remote {

    String sayHello();
    void load(Palette palette);
    Palette unload(Box.Type type, int quantity);
}
