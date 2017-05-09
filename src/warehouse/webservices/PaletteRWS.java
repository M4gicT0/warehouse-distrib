package warehouse.webservices;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import warehouse.DAO.PaletteDAO;
import warehouse.model.RemotePalette;
import warehouse.shared.model.Palette;
import warehouse.utils.Destination;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Theo on 5/8/17.
 */
@WebServlet({ "/palettes", "/palettes/*" })
public class PaletteRWS extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private static Gson gson = new GsonBuilder().create();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /*
        *   Check if palettes/ or palettes/id
        *   if palettes/ -> PaletteDAO.all(): foreach: JSONize and print
        *   else if palettes/id -> PaletteDAO.getById(id): foreach: JSONize and print
         */
        String output = "";
        ServletOutputStream out = response.getOutputStream();

        if (request.getRequestURI().equals("/palettes") || request.getRequestURI().equals("/palettes/")) {
            ArrayList<Palette> palettes = PaletteDAO.getPalettes();

            for (Palette palette : palettes) {
                //JSONize and add to result
                output += gson.toJson(palette);
            }

        } else if (request.getRequestURI().startsWith("/palettes/")) {
            Palette p = new RemotePalette("p9p99", Destination.NONE);

            output = gson.toJson(p);
        }

        out.println(output);
        out.flush();
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
