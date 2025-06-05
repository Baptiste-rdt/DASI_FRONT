/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web.modele;

import fr.insalyon.dasi.java_app.model.Medium;
import fr.insalyon.dasi.java_app.service.Service;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author breydet
 */
public class InitialiserAccueil extends Action {

    public InitialiserAccueil(Service service) {
        super(service);
    }

    @Override
    public void execute(HttpServletRequest request) {
        List<Map.Entry<Medium, Long>> mediums = this.service.getTopXMediums(5);
        System.out.println(mediums);
        request.setAttribute("mediums", mediums);
    }
    
}
