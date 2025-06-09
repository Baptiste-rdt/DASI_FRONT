
import fr.insalyon.dasi.java_app.model.Consultation;
import fr.insalyon.dasi.java_app.model.Employee;
import fr.insalyon.dasi.java_app.service.Service;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import web.modele.Action;

public class ActionDashboardEmploye extends Action {

    public ActionDashboardEmploye(Service service) {
        super(service);
    }

    @Override
    public void execute(HttpServletRequest request) {
        Employee employe = (Employee) request.getSession().getAttribute("authentication");

        if (employe == null) {
            request.setAttribute("unauthorized", true);
            return;
        }

        Map<String, Object> data = new HashMap<>();
/*        data.put("disponible", employe.isDisponible());
        data.put("consultationEnCours", employe.getConsultationEnCours() != null);

        if (employe.getConsultationEnCours() != null) {
            data.put("clientEnCours", employe.getConsultationEnCours().getClient().getPrenom());
        }

        List<Map<String, Object>> historique = new ArrayList<>();
        for (Consultation c : employe.getConsultations()) {
            Map<String, Object> line = new HashMap<>();
            line.put("medium", c.getMedium().getType());
            line.put("denomination", c.getMedium().getDenomination());
            line.put("date", c.getDate().toString());
            line.put("clientNom", c.getClient().getPrenom() + " " + c.getClient().getNom());
            line.put("clientId", c.getClient().getId());
            historique.add(line);
        }

        data.put("historique", historique);
*/
        request.setAttribute("dashboardData", data);
    }
}
