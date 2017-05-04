package warehouse.model;

import warehouse.utils.Destination;

import java.rmi.RemoteException;
import java.util.ArrayList;

/**
 * Created by transpalette on 5/4/17.
 */
public interface Palette {

    String getId() throws RemoteException;
    int getBoxesQty() throws RemoteException;
    boolean has(BoxType type, int qty) throws RemoteException;
    Destination getDestination() throws RemoteException;
    void setDestination(Destination destination) throws RemoteException;
    void addBox(Box box) throws RemoteException;
    int getCapacity() throws RemoteException;
    ArrayList<RemoteBox> removeBoxes(BoxType type, int number) throws RemoteException;

}
