package web.vue;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import fr.insalyon.dasi.java_app.model.Employee;
import fr.insalyon.dasi.java_app.model.Medium;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

public class StatistiquesSerialisation extends Serialisation {

    @Override
    public void appliquer(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Gson gson = new Gson();
        JsonObject container = new JsonObject();

        JsonArray mediumsArray = new JsonArray();
        List<Map.Entry<Medium, Long>> topMediums = (List<Map.Entry<Medium, Long>>) request.getAttribute("topMediums");
        for (Map.Entry<Medium, Long> entry : topMediums) {
            JsonObject obj = new JsonObject();
            obj.addProperty("nom", entry.getKey().getName());
            obj.addProperty("denomination", entry.getKey().getClass().getSimpleName());
            obj.addProperty("count", entry.getValue());
            mediumsArray.add(obj);
        }
        container.add("topMediums", mediumsArray);

        JsonArray employeesArray = new JsonArray();
        List<Map.Entry<Employee, Long>> topEmployees = (List<Map.Entry<Employee, Long>>) request.getAttribute("topEmployees");
        for (Map.Entry<Employee, Long> entry : topEmployees) {
            JsonObject obj = new JsonObject();
            obj.addProperty("nom", entry.getKey().getLastName() + " " + entry.getKey().getFirstName());
            obj.addProperty("count", entry.getValue());
            employeesArray.add(obj);
        }
        container.add("topEmployees", employeesArray);

        response.setContentType("application/json;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.print(gson.toJson(container));
        out.flush();
    }
}
