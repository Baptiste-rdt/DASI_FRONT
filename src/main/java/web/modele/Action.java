package web.modele;

import fr.insalyon.dasi.java_app.service.Service;
import javax.servlet.http.HttpServletRequest;

public abstract class Action {
    protected Service service;
    public Action(Service service) {
        this.service = service;
    }

    public abstract void execute(HttpServletRequest request);
}