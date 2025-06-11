package web.modele;

import fr.insalyon.dasi.java_app.model.Employee;
import fr.insalyon.dasi.java_app.model.Medium;
import fr.insalyon.dasi.java_app.service.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;
import java.util.AbstractMap;

public class StatistiquesAction extends Action {

    public StatistiquesAction(Service service) {
        super(service);
    }

    @Override
    public void execute(HttpServletRequest request) {
        int limit = 8; // ou récupérer un paramètre request.getParameter("max") si besoin

        List<Map.Entry<Medium, Long>> topMediums = service.getTopXMediums(limit);
        List<Map.Entry<Employee, Long>> topEmployees = service.getNbClientsByEmployee(limit);

        request.setAttribute("topMediums", topMediums);
        request.setAttribute("topEmployees", topEmployees); // nom mis à jour ici aussi
    }
}
