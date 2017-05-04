package warehouse.server;

import warehouse.model.Box;
import warehouse.model.Palette;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Created by transpalette on 5/2/17.
 */
public interface RemoteInterface extends Remote {

    String sayHello() throws RemoteException;
    void load(Palette palette) throws RemoteException;
    Palette unload(Box.Type type, int quantity) throws RemoteException;
}
