package warehouse;

import warehouse.model.Box;
import warehouse.model.Palette;
import warehouse.shared.RemoteInterface;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

/**
 * Created by transpalette on 5/2/17.
 */
public class Server extends UnicastRemoteObject implements RemoteInterface {

    protected Server() throws RemoteException {
        super();
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
    public void load(Palette palette) {

    }

    @Override
    public Palette unload(Box.Type type, int quantity) {
        return null;
    }
}
