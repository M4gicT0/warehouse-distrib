package warehouse.shared;

import warehouse.model.Box;
import warehouse.model.BoxType;
import warehouse.model.Palette;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

/**
 * Created by transpalette on 5/2/17.
 */
public interface RemoteInterface extends Remote {

    String sayHello() throws RemoteException;
    void receive(ArrayList<Box> truck) throws RemoteException;
    Palette unload(BoxType type, int quantity) throws RemoteException;
}
