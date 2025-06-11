package web.modele;

import fr.insalyon.dasi.java_app.model.Client;
import fr.insalyon.dasi.java_app.model.Employee;
import fr.insalyon.dasi.java_app.service.Service;
import java.io.IOException;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

public class ObtenirPrediction extends Action {

    public ObtenirPrediction(Service service) {
        super(service);
    }

    @Override
    public void execute(HttpServletRequest request) {
        Object user = request.getSession().getAttribute("authentication");

        if (user instanceof Employee) {
            Client client = ((Employee) user).getCurrentConsultation().getClient();
            int love = Integer.parseInt(request.getParameter("love"));
            int health = Integer.parseInt(request.getParameter("health"));
            int work = Integer.parseInt(request.getParameter("work"));

            try {
                List<String> predictions = service.getPrediction(client, love, health, work);
                request.setAttribute("predictions", predictions);
            } catch (IOException e) {
                request.setAttribute("predictions", null);
            }
        } else {
            request.setAttribute("predictions", null);
        }
    }
}
