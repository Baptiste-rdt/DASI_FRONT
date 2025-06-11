/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web.modele;

import fr.insalyon.dasi.java_app.model.Medium;
import fr.insalyon.dasi.java_app.service.Service;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author breydet
 */
public class InitialiserListeMediums extends Action {

    public InitialiserListeMediums(Service service) {
        super(service);
    }

    @Override
    public void execute(HttpServletRequest request) {
        try {
            List<Medium> mediums = this.service.getAllMediums();
            request.setAttribute("mediums", mediums);
        } catch (Exception ex) {
            request.setAttribute("error", ex.getMessage());
        }
    }

}
