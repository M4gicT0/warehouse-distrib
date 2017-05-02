package warehouse.server;

import warehouse.model.Box;
import warehouse.model.Palette;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

/**
 * Created by transpalette on 5/2/17.
 */
public class RemoteServer extends UnicastRemoteObject implements RemoteInterface {

    public RemoteServer() throws RemoteException {
    }

    public static void main(String args[]) {
        try {
            RemoteServer obj = new RemoteServer();
            RemoteInterface stub = (RemoteInterface) UnicastRemoteObject.exportObject(obj, 0);

            // Bind the remote object's stub in the registry
            Registry registry = LocateRegistry.getRegistry();
            registry.bind("RemoteInterface", stub);

            System.err.println("Server ready");
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
