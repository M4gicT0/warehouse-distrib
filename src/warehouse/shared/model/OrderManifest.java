package warehouse.shared.model;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.HashMap;

/**
 * Created by transpalette on 5/9/17.
 */
public interface OrderManifest extends Remote {

    void setOrder(Order order) throws RemoteException;
    Order getOrder() throws RemoteException;
    HashMap<BoxType, Integer> getList() throws RemoteException;
}
