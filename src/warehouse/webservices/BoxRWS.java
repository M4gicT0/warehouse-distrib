package warehouse.webservices;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import warehouse.DAO.BoxDAO;
import warehouse.shared.model.Box;

import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;

/**
 * Created by Theo on 5/8/17.
 */
@WebServlet({ "/boxes", "/boxes/*" })
public class BoxRWS extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private static Gson gson = new GsonBuilder().create();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String output = "";
        ServletOutputStream out = response.getOutputStream();

        if (request.getContextPath().equals("/boxes") || request.getContextPath().equals("/boxes/")) {
            ArrayList<Box> boxes = BoxDAO.getBoxes();

            for (Box box : boxes)
                output += gson.toJson(box);

        } else if (request.getContextPath().startsWith("/boxes/")) {
            Box box = BoxDAO.getBox(request.getContextPath().substring(request.getContextPath().lastIndexOf("/")));

            output = gson.toJson(box);
        }

        out.println(output);
        out.flush();
    }
}
