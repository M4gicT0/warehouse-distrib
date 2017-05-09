package warehouse.webservices;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import warehouse.DAO.OrderDAO;
import warehouse.model.Order;

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

        if (request.getContextPath().equals("/orders") || request.getContextPath().equals("/orders/")) {
            ArrayList<Order> orders = OrderDAO.getOrders();

            for (Order order : orders)
                output += gson.toJson(order);

        } else if (request.getContextPath().startsWith("/orders/")) {
            Order order = OrderDAO.getOrder(request.getContextPath().substring(request.getContextPath().lastIndexOf("/")));

            output = gson.toJson(order);
        }

        out.println(output);
        out.flush();
    }
}
