package warehouse.server;

import warehouse.model.Box;
import warehouse.model.Palette;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 * Created by transpalette on 5/2/17.
 */
public class Server extends UnicastRemoteObject implements RemoteInterface {


    protected Server() throws RemoteException {
    }

    public static void main(String args[]) {
        try {
            Server warehouse = new Server();
            Naming.rebind("WarehouseDistrib", warehouse);


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
