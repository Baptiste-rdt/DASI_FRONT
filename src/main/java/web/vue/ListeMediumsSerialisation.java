/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web.vue;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import fr.insalyon.dasi.java_app.model.Medium;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author breydet
 */
public class ListeMediumsSerialisation extends Serialisation {

    @Override
    public void appliquer(HttpServletRequest request, HttpServletResponse response) throws IOException {
        List<Medium> mediums = (List<Medium>) request.getAttribute("mediums");
        
        Gson gson = new Gson();
        JsonObject container = new JsonObject();
        JsonArray array = new JsonArray();
        
        for (Medium unMedium : mediums) {
            JsonObject object = new JsonObject();
            object.addProperty("name", unMedium.getName());
            object.addProperty("description", unMedium.getDescription());
            object.addProperty("gender", unMedium.getGender());
            object.addProperty("type", unMedium.getDenomination());
            array.add(object);
        }
        
        container.add("mediums", array);
        
        String json = gson.toJson(container);
        
        response.setContentType("application/json;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.print(json);
        out.flush();
    }
    
}
