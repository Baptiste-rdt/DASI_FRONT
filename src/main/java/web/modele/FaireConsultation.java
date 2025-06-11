package web.modele;

import fr.insalyon.dasi.java_app.model.Consultation;
import fr.insalyon.dasi.java_app.model.Employee;
import fr.insalyon.dasi.java_app.service.Service;
import javax.servlet.http.HttpServletRequest;

public class FaireConsultation extends Action {

    public FaireConsultation(Service service) {
        super(service);
    }

    @Override
    public void execute(HttpServletRequest request) {
        Object user = request.getSession().getAttribute("authentication");

        if (user instanceof Employee) {
            Employee employe = (Employee) user;
            Consultation consultation = service.getPendingConsultationForEmployee(employe);
            request.setAttribute("consultation", consultation);
        } else {
            request.setAttribute("consultation", null);
        }
    }
}
