package warehouse.webservices;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import warehouse.DAO.PaletteDAO;
import warehouse.shared.model.Palette;

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

        String output = "";
        ServletOutputStream out = response.getOutputStream();

        if (request.getContextPath().equals("/palettes") || request.getContextPath().equals("/palettes/")) {
            ArrayList<Palette> palettes = PaletteDAO.getPalettes();

            for (Palette palette : palettes) {
                //JSONize and add to result
                output += gson.toJson(palette);
            }

        } else if (request.getContextPath().startsWith("/palettes/")) {
            Palette p = PaletteDAO.getPalette(request.getContextPath().substring(request.getContextPath().lastIndexOf("/")));

            output = gson.toJson(p);
        }

        out.println(output);
        out.flush();
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
