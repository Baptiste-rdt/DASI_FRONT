package web.modele;

import fr.insalyon.dasi.java_app.model.Consultation;
import fr.insalyon.dasi.java_app.model.Employee;
import fr.insalyon.dasi.java_app.service.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class DashboardEmploye extends Action {

    public DashboardEmploye(Service service) {
        super(service);
    }

    @Override
    public void execute(HttpServletRequest request) {
        Employee employe = (Employee) request.getSession().getAttribute("authentication");

        if (employe != null) {
            boolean disponible = false;// ou service.isEmployeDisponible(employe)
            if(employe.getCurrentConsultation() == null) disponible = true;
            Consultation consultation = service.getPendingConsultationForEmployee(employe);
            List<Consultation> historique = service.getEmployeeConsultationHistory(employe);
            request.setAttribute("disponible", disponible);
            request.setAttribute("consultation", consultation);
            request.setAttribute("historique", historique);
        }
    }
}
