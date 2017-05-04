package warehouse.shared;

import warehouse.model.RemoteBox;
import warehouse.model.RemotePalette;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

/**
 * Created by transpalette on 5/2/17.
 */
public interface RemoteInterface extends Remote {

    String sayHello() throws RemoteException;
    void receive(ArrayList<RemoteBox> truck) throws RemoteException;
    RemotePalette unload(RemoteBox.Type type, int quantity) throws RemoteException;
}
