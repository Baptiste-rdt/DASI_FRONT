package web.modele;

import fr.insalyon.dasi.java_app.dao.ConsultationDao;
import fr.insalyon.dasi.java_app.model.Consultation;
import fr.insalyon.dasi.java_app.model.Employee;
import fr.insalyon.dasi.java_app.service.Service;

import javax.servlet.http.HttpServletRequest;

public class ValiderConsultation extends Action {

    public ValiderConsultation(Service service) {
        super(service);
    }

    @Override
    public void execute(HttpServletRequest request) {
        Object user = request.getSession().getAttribute("authentication");

        if (user instanceof Employee) {
            String contenu = request.getParameter("contenu");
            Employee employe = (Employee) user;

            Consultation consultation = employe.getCurrentConsultation();
            if (consultation != null && contenu != null && !contenu.isEmpty()) {
                service.finishConsultation(consultation, contenu);
                request.setAttribute("success", true);
                return;
            }
        }

        request.setAttribute("success", false);
    }
}
