/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web.modele;

import fr.insalyon.dasi.java_app.model.Client;
import fr.insalyon.dasi.java_app.service.Service;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author breydet
 */
public class InitialiserAccueilClient extends Action {

    public InitialiserAccueilClient(Service service) {
        super(service);
    }

    @Override
    public void execute(HttpServletRequest request) {
        Client client = this.service.getClientById(Integer.parseInt(request.getParameter("idClient")));
        request.setAttribute("client", client);
    }
    
}
