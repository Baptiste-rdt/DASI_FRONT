package web.vue;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import fr.insalyon.dasi.java_app.model.Client;
import fr.insalyon.dasi.java_app.model.Consultation;
import fr.insalyon.dasi.java_app.model.AstralProfile;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class FaireConsultationSerialisation extends Serialisation {

    @Override
    public void appliquer(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Consultation consultation = (Consultation) request.getAttribute("consultation");

        JsonObject container = new JsonObject();

        if (consultation != null && consultation.getClient() != null) {
            Client client = consultation.getClient();
            AstralProfile profil = client.getAstralProfile();

            container.addProperty("enCours", true);
            container.addProperty("nom", client.getLastName());
            container.addProperty("prenom", client.getFirstName());
            container.addProperty("naissance", client.getBirthDate().toString());

            if (profil != null) {
                container.addProperty("signeZodiaque", profil.getAstrologicalSign());
                container.addProperty("signeChinois", profil.getChineseSign());
                container.addProperty("couleur", profil.getLuckyColor());
                container.addProperty("animal", profil.getAnimalTotem());
            }
        } else {
            container.addProperty("enCours", false);
        }

        response.setContentType("application/json;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.print(new Gson().toJson(container));
        out.flush();
    }
}
