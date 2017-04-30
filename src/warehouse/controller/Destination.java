package warehouse.controller;

/**
 * Created by Theo on 4/23/17.
 */
public interface Destination {

    void process();
    void log(String message);
}
