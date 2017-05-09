package warehouse.shared.model;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Created by transpalette on 5/4/17.
 */
public interface Box extends Remote {

    String getId() throws RemoteException;
    BoxType getType() throws RemoteException;
    int getItemsQty() throws Exception;

}
