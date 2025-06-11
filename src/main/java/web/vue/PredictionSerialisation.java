package web.vue;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class PredictionSerialisation extends Serialisation {

    @Override
    public void appliquer(HttpServletRequest request, HttpServletResponse response) throws IOException {
        List<String> predictions = (List<String>) request.getAttribute("predictions");

        JsonObject container = new JsonObject();

        if (predictions != null && predictions.size() == 3) {
            JsonArray array = new JsonArray();
            for (String pred : predictions) {
                array.add(pred);
            }
            container.add("predictions", array);
        } else {
            container.add("predictions", new JsonArray()); // vide
        }

        response.setContentType("application/json;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.print(new Gson().toJson(container));
        out.flush();
    }
}
