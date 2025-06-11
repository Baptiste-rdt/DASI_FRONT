package web.vue;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import fr.insalyon.dasi.java_app.model.Client;
import fr.insalyon.dasi.java_app.model.Consultation;
import fr.insalyon.dasi.java_app.model.Medium;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.List;

public class DashboardEmployeSerialisation extends Serialisation {

    @Override
    public void appliquer(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Boolean disponible = (Boolean) request.getAttribute("disponible");
        Consultation consultation = (Consultation) request.getAttribute("consultation");
        List<Consultation> historique = (List<Consultation>) request.getAttribute("historique");

        JsonObject container = new JsonObject();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        container.addProperty("disponible", disponible != null && disponible);
        container.addProperty("consultationEnCours", consultation != null);

        if (consultation != null && consultation.getClient() != null) {
            container.addProperty("clientEnCours", consultation.getClient().getFirstName() + " " + consultation.getClient().getLastName());
        }

        JsonArray historiqueArray = new JsonArray();
        if (historique != null) {
            for (Consultation c : historique) {
                JsonObject obj = new JsonObject();
                Medium m = c.getMedium();
                Client client = c.getClient();

                obj.addProperty("medium", m.getName());
                obj.addProperty("denomination", m.getDenomination());
                obj.addProperty("date", sdf.format(c.getDebut()));
                obj.addProperty("clientNom", client.getLastName()+ " " + client.getFirstName());
                obj.addProperty("clientId", client.getId());
                historiqueArray.add(obj);
            }
        }

        container.add("historique", historiqueArray);

        response.setContentType("application/json;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.print(container.toString());
        out.flush();
    }
}
