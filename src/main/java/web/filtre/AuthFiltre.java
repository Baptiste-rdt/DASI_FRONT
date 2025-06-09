import java.io.IOException;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebFilter(filterName = "AuthFilter", urlPatterns = {
    "/accueil-client.html",
    "/profil.html",
    "/dashboard-employe.html",
    "/statistiques.html",
    "/consultation.html",
    "/mediums.html"
})
public class AuthFiltre implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;

        HttpSession session = request.getSession(false);
        Object user = (session != null) ? session.getAttribute("authentication") : null;

        if (user != null) {
            chain.doFilter(req, res);
        } else {
            response.sendRedirect("index.html");
        }
    }
}