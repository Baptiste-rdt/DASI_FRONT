/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web.vue;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import fr.insalyon.dasi.java_app.model.Client;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author breydet
 */
public class AccueilClientSerialisation extends Serialisation {

    @Override
    public void appliquer(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Client c = (Client) request.getAttribute("client");

        Gson gson = new Gson();
        JsonObject object = new JsonObject();

        object.addProperty("nom", c.getLastName());
        object.addProperty("prenom", c.getFirstName());
        object.addProperty("email", c.getEmail());
        object.addProperty("naissance", c.getBirthDate().getTime());
        object.addProperty("adresse", c.getAddress());
        object.addProperty("telephone", c.getPhone());
        object.addProperty("mdp", c.getPassword());
        object.addProperty("zodiaque", c.getAstralProfile().getAstrologicalSign());
        object.addProperty("chinois", c.getAstralProfile().getChineseSign());
        object.addProperty("couleur", c.getAstralProfile().getLuckyColor());
        object.addProperty("animal", c.getAstralProfile().getAnimalTotem());
        String json = gson.toJson(object);

        response.setContentType("application/json;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.print(json);
        out.flush();
    }

}
