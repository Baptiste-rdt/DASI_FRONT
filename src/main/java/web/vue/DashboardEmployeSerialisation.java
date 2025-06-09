
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import web.vue.Serialisation;

public class DashboardEmployeSerialisation extends Serialisation {

    @Override
    public void appliquer(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Gson gson = new Gson();
        JsonObject json = new JsonObject();

        if (request.getAttribute("unauthorized") != null) {
            json.addProperty("connected", false);
        } else {
            Map<String, Object> data = (Map<String, Object>) request.getAttribute("dashboardData");
            JsonElement dataJson = gson.toJsonTree(data);
            json = dataJson.getAsJsonObject();
        }

        response.setContentType("application/json;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.print(gson.toJson(json));
        out.flush();
    }
}
