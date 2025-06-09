package web.modele;

import javax.servlet.http.HttpServletRequest;

public class SeDeconnecter extends Action {

    public SeDeconnecter() {
        super(null); // Pas besoin de service ici
    }

    @Override
    public void execute(HttpServletRequest request) {
        request.getSession().invalidate(); // Supprime toute la session
        request.setAttribute("success", true);
    }
}
