package web.modele;

import fr.insalyon.dasi.java_app.model.AstralProfile;
import fr.insalyon.dasi.java_app.model.Client;
import fr.insalyon.dasi.java_app.service.Service;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;

public class SInscrire extends Action {

    public SInscrire(Service service) {
        super(service);
    }

    @Override
    public void execute(HttpServletRequest request) {
        String nom = request.getParameter("nom");
        String prenom = request.getParameter("prenom");
        String naissance = request.getParameter("naissance");
        String genre = request.getParameter("genre");
        String adresse = request.getParameter("adresse");
        String cp = request.getParameter("cp");
        String ville = request.getParameter("ville");
        String email = request.getParameter("email");
        String telephone = request.getParameter("telephone");
        String mdp = request.getParameter("mdp");

        String fullAdresse = adresse + ", " + cp + " " + ville;
        Date dateNaissance = null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            dateNaissance = sdf.parse(naissance);
        } catch (ParseException ex) {
            Logger.getLogger(SInscrire.class.getName()).log(Level.SEVERE, null, ex);
        }
        Client newClient = new Client(prenom, nom, dateNaissance, fullAdresse, telephone, email, mdp, new AstralProfile(), new ArrayList());
        Client client = null;
        try {
            client = service.createAccount(newClient);
        } catch (Exception ex) {
            Logger.getLogger(SInscrire.class.getName()).log(Level.SEVERE, null, ex);
        }

        request.setAttribute("nouveauClient", client);
    }
}
