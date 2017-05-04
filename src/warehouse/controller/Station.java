package warehouse.controller;

import java.util.Observer;

/**
 * Created by Theo on 4/23/17.
 */
public interface Station extends Observer {

    void process();
    void log(String message);
}
