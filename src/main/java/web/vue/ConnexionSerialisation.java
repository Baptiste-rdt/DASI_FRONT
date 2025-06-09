/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web.vue;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import fr.insalyon.dasi.java_app.model.Client;
import fr.insalyon.dasi.java_app.model.Employee;
import fr.insalyon.dasi.java_app.model.Medium;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author breydet
 */
public class ConnexionSerialisation extends Serialisation {

    @Override
    public void appliquer(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Object user = request.getAttribute("utilisateur");

        Gson gson = new Gson();
        JsonObject object = new JsonObject();

        if (user instanceof Client) {
            object.addProperty("success", true);
            object.addProperty("role", "client");
            request.getSession().setAttribute("authentication", user);
        } else if (user instanceof Employee) {
            object.addProperty("success", true);
            object.addProperty("role", "employe");
            request.getSession().setAttribute("authentication", user);
        } else {
            object.addProperty("success", false);
        }

        String json = gson.toJson(object);

        response.setContentType("application/json;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.print(json);
        out.flush();

    }

}
