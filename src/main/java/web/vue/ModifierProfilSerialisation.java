package web.vue;

import com.google.gson.JsonObject;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class ModifierProfilSerialisation extends Serialisation {

    @Override
    public void appliquer(HttpServletRequest request, HttpServletResponse response) throws IOException {
        boolean success = (boolean) request.getAttribute("success");
        String message = (String) request.getAttribute("message");

        JsonObject json = new JsonObject();
        json.addProperty("success", success);
        json.addProperty("message", message);

        response.setContentType("application/json;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.print(json.toString());
        out.flush();
    }
}
