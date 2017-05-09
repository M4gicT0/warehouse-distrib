package warehouse.webservices;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import warehouse.DAO.OrderDAO;
import warehouse.shared.model.RemoteOrder;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Theo on 5/9/17.
 */
@WebServlet({ "/orders", "/orders/*" })
public class OrderRWS extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private static Gson gson = new GsonBuilder().create();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String output = "";
        ServletOutputStream out = response.getOutputStream();

        if (request.getRequestURI().equals("/orders") || request.getRequestURI().equals("/orders/")) {
            ArrayList<RemoteOrder> remoteOrders = OrderDAO.getOrders();

            for (RemoteOrder remoteOrder : remoteOrders)
                output += gson.toJson(remoteOrder);

        } else if (request.getRequestURI().startsWith("/orders/")) {
            RemoteOrder remoteOrder = OrderDAO.getOrder(request.getRequestURI().substring(request.getRequestURI().lastIndexOf("/")));

            output = gson.toJson(remoteOrder);
        }

        out.println(output);
        out.flush();
    }
}
