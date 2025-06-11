/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web.modele;

import fr.insalyon.dasi.java_app.model.Client;
import fr.insalyon.dasi.java_app.model.Employee;
import fr.insalyon.dasi.java_app.service.Service;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author breydet
 */
public class LancerConsultation extends Action {

    public LancerConsultation(Service service) {
        super(service);
    }

    @Override
    public void execute(HttpServletRequest request) {
        Boolean success;
        Object userLogged = request.getSession().getAttribute("authentication");

        if (userLogged instanceof Client) {
            Client client = (Client) userLogged;
            success = this.service.consultationRequest(client, request.getParameter("mediumName"));
        } else {
            success = false;
        }
        request.setAttribute("success", success);
    }

}
