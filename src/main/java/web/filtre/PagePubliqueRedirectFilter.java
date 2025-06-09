package web.filtre;

import fr.insalyon.dasi.java_app.model.Client;
import fr.insalyon.dasi.java_app.model.Employee;
import java.io.IOException;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebFilter(filterName = "PagePubliqueRedirectFilter", urlPatterns = {
    "/connexion.html",
    "/inscription.html"
})
public class PagePubliqueRedirectFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {}

    @Override
    public void destroy() {}

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;

        HttpSession session = request.getSession(false);
        Object user = (session != null) ? session.getAttribute("authentication") : null;

        if (user instanceof Client) {
            response.sendRedirect("accueil-client.html");
        } else if (user instanceof Employee) {
            response.sendRedirect("dashboard-employe.html");
        } else {
            chain.doFilter(req, res); // Utilisateur non connecté : on laisse accéder
        }
    }
}
