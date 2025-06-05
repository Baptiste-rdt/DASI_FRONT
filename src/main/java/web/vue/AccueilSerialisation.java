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
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author breydet
 */
public class AccueilSerialisation extends Serialisation {

    @Override
    public void appliquer(HttpServletRequest request, HttpServletResponse response) throws IOException {
        
        List<Map.Entry<Medium, Long>> mediums = (List<Map.Entry<Medium, Long>>) request.getAttribute("mediums");
        
        Gson gson = new Gson();
        JsonObject container = new JsonObject();
        JsonArray array = new JsonArray();
        
        for (Map.Entry<Medium, Long> unMedium : mediums) {
            JsonObject object = new JsonObject();
            object.addProperty("name", unMedium.getKey().getName());
            object.addProperty("description", unMedium.getKey().getDescription());
            object.addProperty("gender", unMedium.getKey().getGender());
            object.addProperty("position", unMedium.getValue());
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
