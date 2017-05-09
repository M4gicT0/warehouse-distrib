package warehouse;

import warehouse.controller.Crane;
import warehouse.controller.PackingStation;
import warehouse.controller.PickingStation;
import warehouse.shared.model.RemoteOrder;
import warehouse.model.OrderManifest;
import warehouse.shared.model.Box;
import warehouse.shared.RemoteInterface;
import warehouse.utils.ConveyorBelt;
import warehouse.utils.DatabaseManager;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.sql.SQLException;
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
        packingStation = new PackingStation();
        pickingStation = new PickingStation(crane);
        ConveyorBelt.getInstance().addObserver(crane);
        ConveyorBelt.getInstance().addObserver(packingStation);
        ConveyorBelt.getInstance().addObserver(pickingStation);
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
    public void receive(ArrayList<Box> truck) {
        //Receive at packing station
        packingStation.receiveTruck(truck);
        packingStation.process();
    }

    @Override
    public RemoteOrder order(OrderManifest orderManifest) throws RemoteException {
        return pickingStation.processOrder(orderManifest);
    }

    @Override
    public void resetDatabase() throws RemoteException {
        DatabaseManager db = new DatabaseManager();
        try {
            db.dropTables();
            db.createTables();
            db.addAisle();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
