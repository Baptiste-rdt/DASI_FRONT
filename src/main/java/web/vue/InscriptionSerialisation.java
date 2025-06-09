package web.vue;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import fr.insalyon.dasi.java_app.model.Client;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class InscriptionSerialisation extends Serialisation {

    @Override
    public void appliquer(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Object user = request.getAttribute("nouveauClient");

        Gson gson = new Gson();
        JsonObject container = new JsonObject();

        if (user instanceof Client) {
            container.addProperty("success", true);
        } else {
            container.addProperty("success", false);
            container.addProperty("message", "L’inscription a échoué.");
        }

        response.setContentType("application/json;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.print(gson.toJson(container));
        out.flush();
    }
}
