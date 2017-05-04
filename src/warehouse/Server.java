package warehouse;

import warehouse.controller.Crane;
import warehouse.controller.PackingStation;
import warehouse.controller.PickingStation;
import warehouse.model.RemoteBox;
import warehouse.model.RemotePalette;
import warehouse.shared.RemoteInterface;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

/**
 * Created by transpalette on 5/2/17.
 */
public class Server extends UnicastRemoteObject implements RemoteInterface {

    PackingStation packingStation;
    PickingStation pickingStation;
    Crane crane;

    protected Server() throws RemoteException {
        super();

        crane = new Crane();
        packingStation = new PackingStation(crane);
        pickingStation = new PickingStation(crane);
    }

    public static void main(String args[]) {

        try {
            System.out.println("Starting shared...");
            RemoteInterface rmiServer = new Server();
            Registry registry = LocateRegistry.createRegistry(1099);
            registry.rebind("WarehouseDistrib", rmiServer);
            System.out.println("Server started.");
        } catch (Exception e) {
            System.err.println("Server exception: " + e.toString());
            e.printStackTrace();
        }
    }

    @Override
    public String sayHello() {
        return "Hello fellow !";
    }

    @Override
    public void receive(ArrayList<RemoteBox> truck) {
        //Receive at packing station

        packingStation.receiveTruck(truck);
        packingStation.process();
    }

    @Override
    public RemotePalette unload(RemoteBox.Type type, int quantity) {
        return null;
    }
}
