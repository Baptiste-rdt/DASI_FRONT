/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web.modele;

import fr.insalyon.dasi.java_app.service.Service;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author breydet
 */
public class SeConnecter extends Action {

    public SeConnecter(Service service) {
        super(service);
    }

    @Override
    public void execute(HttpServletRequest request) {
        Object user = this.service.authenticatePerson(request.getParameter("email"), request.getParameter("password"));
        request.setAttribute("utilisateur", user);
    }

}
