package web.modele;

import fr.insalyon.dasi.java_app.model.Client;
import fr.insalyon.dasi.java_app.service.Service;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import javax.servlet.http.HttpServletRequest;
import java.time.format.DateTimeParseException;
import static java.time.temporal.TemporalQueries.localDate;
import java.util.Date;

public class ModifierProfil extends Action {

    public ModifierProfil(Service service) {
        super(service);
    }

    @Override
    public void execute(HttpServletRequest request) {
        Client client = (Client) request.getSession().getAttribute("authentication");

        if (client == null) {
            request.setAttribute("success", false);
            request.setAttribute("message", "Utilisateur non connecté.");
            return;
        }

        String nom = request.getParameter("nom");
        String prenom = request.getParameter("prenom");
        String email = request.getParameter("email");
        String adresse = request.getParameter("adresse");
        String telephone = request.getParameter("telephone");
        String mdp = request.getParameter("mdp");
        String naissance = request.getParameter("naissance");

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate localDate = LocalDate.parse(naissance, formatter);
        Date date = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());

        // Mise à jour locale de l'objet client
        client.setLastName(nom);
        client.setFirstName(prenom);
        client.setEmail(email);
        client.setAddress(adresse);
        client.setPhone(telephone);
        client.setBirthDate(date);

        if (!"********".equals(mdp)) {
            client.setPassword(mdp);
        }

        boolean updated = service.updateClientInfo(client);

        request.setAttribute("success", updated);
        request.setAttribute("message", updated ? "Mise à jour réussie." : "Échec de la mise à jour.");
    }
}
