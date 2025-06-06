/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web.controleur;

import fr.insalyon.dasi.java_app.dao.JpaUtil;
import fr.insalyon.dasi.java_app.service.Service;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import web.modele.Action;
import web.modele.InitialiserAccueil;
import web.modele.SeConnecter;
import web.vue.AccueilSerialisation;
import web.vue.ConnexionSerialisation;
import web.vue.Serialisation;
import web.vue.VerifierAuthentificationSerialisation;

/**
 *
 * @author breydet
 */
@WebServlet(name = "ActionServlet", urlPatterns = {"/ActionServlet"})
public class ActionServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        switch (request.getParameter("action")) {
            case ("initHome"): {
                Action action = new InitialiserAccueil(new Service());
                action.execute(request);

                Serialisation serial = new AccueilSerialisation();
                serial.appliquer(request, response);
            }
            break;
            case ("connexion"): {
                Action action = new SeConnecter(new Service());
                action.execute(request);

                Serialisation serial = new ConnexionSerialisation();
                serial.appliquer(request, response);
            }
            break;
            case ("checkAuth"): {
                Serialisation serial = new VerifierAuthentificationSerialisation();
                serial.appliquer(request, response);
            }
            break;
            case ("deconnexion"): {
                request.getSession().removeAttribute("authentication");
            }
            break;
        }

    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    @Override
    public void init() throws ServletException {
        super.init(); //To change body of generated methods, choose Tools | Templates.
        JpaUtil.creerFabriquePersistance();
    }

    @Override
    public void destroy() {
        super.destroy(); //To change body of generated methods, choose Tools | Templates.
        JpaUtil.fermerFabriquePersistance();
    }

}
