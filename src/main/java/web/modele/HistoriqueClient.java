package web.modele;

import fr.insalyon.dasi.java_app.model.Client;
import fr.insalyon.dasi.java_app.model.Consultation;
import fr.insalyon.dasi.java_app.service.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class HistoriqueClient extends Action {

    public HistoriqueClient(Service service) {
        super(service);
    }

    @Override
    public void execute(HttpServletRequest request) {
        Client client = (Client) request.getSession().getAttribute("authentication");

        if (client != null) {
            List<Consultation> historique = service.getClientConsultationHistory(client);
            request.setAttribute("historique", historique);
        } else {
            request.setAttribute("historique", null);
        }
    }
}
