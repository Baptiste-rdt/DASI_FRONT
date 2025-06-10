/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web.controleur;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import fr.insalyon.dasi.java_app.dao.JpaUtil;
import fr.insalyon.dasi.java_app.model.Client;
import fr.insalyon.dasi.java_app.model.Employee;
import fr.insalyon.dasi.java_app.service.Service;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import web.modele.Action;
import web.modele.HistoriqueClient;
import web.modele.InitialiserAccueil;
import web.modele.ModifierProfil;
import web.modele.SInscrire;
import web.modele.SeConnecter;
import web.modele.SeDeconnecter;
import web.vue.AccueilSerialisation;
import web.vue.ConnexionSerialisation;
import web.vue.DeconnexionSerialisation;
import web.vue.HistoriqueClientSerialisation;
import web.vue.InscriptionSerialisation;
import web.vue.ModifierProfilSerialisation;
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

        response.setContentType("application/json;charset=UTF-8");

        String action = request.getParameter("action");

        switch (action) {
            case "initHome": {
                Action a = new InitialiserAccueil(new Service());
                a.execute(request);
                new AccueilSerialisation().appliquer(request, response);
                break;
            }

            case "connexion": {
                Action a = new SeConnecter(new Service());
                a.execute(request);
                new ConnexionSerialisation().appliquer(request, response);
                break;
            }

            case "inscription": {
                Action a = new SInscrire(new Service());
                a.execute(request);
                new InscriptionSerialisation().appliquer(request, response);
                break;
            }

            case "checkAuth": {
                new VerifierAuthentificationSerialisation().appliquer(request, response);
                break;
            }

            case "deconnexion": {
                request.getSession().invalidate();
                JsonObject res = new JsonObject();
                res.addProperty("disconnected", true);
                response.getWriter().print(new Gson().toJson(res));
                break;
            }

            case "redirigerSiConnecte": {
                Object user = request.getSession().getAttribute("authentication");
                JsonObject res = new JsonObject();
                if (user instanceof Client) {
                    res.addProperty("connected", true);
                    res.addProperty("role", "client");
                } else if (user instanceof Employee) {
                    res.addProperty("connected", true);
                    res.addProperty("role", "employe");
                } else {
                    res.addProperty("connected", false);
                }
                response.getWriter().print(new Gson().toJson(res));
                break;
            }

            case "historiqueClient": {
                Action a = new HistoriqueClient(new Service());
                a.execute(request);
                new HistoriqueClientSerialisation().appliquer(request, response);
                break;
            }
            
            case "modifierProfil": {
                Action a = new ModifierProfil(new Service());
                a.execute(request);
                new ModifierProfilSerialisation().appliquer(request, response);
                break;
            }

            default: {
                JsonObject res = new JsonObject();
                res.addProperty("error", "Action inconnue.");
                response.getWriter().print(new Gson().toJson(res));
            }
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
