package warehouse.shared;

import warehouse.shared.model.RemoteOrder;
import warehouse.model.OrderManifest;
import warehouse.shared.model.Box;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

/**
 * Created by transpalette on 5/2/17.
 */
public interface RemoteInterface extends Remote {

    String sayHello() throws RemoteException;
    void receive(ArrayList<Box> truck) throws RemoteException;
    RemoteOrder order(OrderManifest orderManifest) throws RemoteException;
    void resetDatabase() throws RemoteException;
}
