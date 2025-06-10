/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web.vue;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import fr.insalyon.dasi.java_app.model.Client;
import fr.insalyon.dasi.java_app.model.Employee;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author breydet
 */
public class VerifierAuthentificationSerialisation extends Serialisation {

    @Override
    public void appliquer(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Object user = request.getSession().getAttribute("authentication");

        Gson gson = new Gson();
        JsonObject object = new JsonObject();

        if (user instanceof Client) {
            Client c = (Client) user;
            object.addProperty("connected", true);
            object.addProperty("role", "client");
            object.addProperty("nom", c.getLastName());
            object.addProperty("prenom", c.getFirstName());
            object.addProperty("email", c.getEmail());
            object.addProperty("naissance", c.getBirthDate().getTime());
            object.addProperty("adresse", c.getAddress());
            object.addProperty("telephone", c.getPhone());
            object.addProperty("adresse", c.getPassword());

        } else if (user instanceof Employee) {
            Employee e = (Employee) user;
            object.addProperty("connected", true);
            object.addProperty("role", "employe");
            object.addProperty("nom", e.getLastName());
            object.addProperty("prenom", e.getFirstName());
        } else {
            object.addProperty("connected", false);
        }

        String json = gson.toJson(object);

        response.setContentType("application/json;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.print(json);
        out.flush();
    }

}
