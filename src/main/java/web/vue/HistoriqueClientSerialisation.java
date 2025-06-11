package web.vue;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import fr.insalyon.dasi.java_app.model.Consultation;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.List;

public class HistoriqueClientSerialisation extends Serialisation {

    @Override
    public void appliquer(HttpServletRequest request, HttpServletResponse response) throws IOException {
        List<Consultation> consultations = (List<Consultation>) request.getAttribute("historique");

        JsonArray historiqueArray = new JsonArray();

        if (consultations != null) {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

            for (Consultation c : consultations) {
                JsonObject obj = new JsonObject();
                obj.addProperty("mediumNom", c.getMedium().getName());
                obj.addProperty("mediumType", c.getMedium().getDenomination());
                obj.addProperty("mediumDescription", c.getMedium().getDescription());
                obj.addProperty("date", sdf.format(c.getDebut()));
                historiqueArray.add(obj);
            }
        }

        JsonObject container = new JsonObject();
        container.add("historique", historiqueArray);

        response.setContentType("application/json;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.print(new Gson().toJson(container));
        out.flush();
    }
}
