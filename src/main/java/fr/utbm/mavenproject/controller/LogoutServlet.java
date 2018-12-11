package fr.utbm.mavenproject.controller;

import fr.utbm.mavenproject.entity.Client;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.ws.rs.core.Response;

/*
 * @author quentinboudinot
*/
public class LogoutServlet extends HttpServlet {
    public static final String VUE_HOME     = "/home.jsp";
    public static final String CHAMP_EMAIL  = "email";
    public static final String CHAMP_PASS   = "motdepasse";
    public static final String ATT_ERREURS  = "erreurs";
    public static final String ATT_RESULTAT = "resultat";

    @Override
    public void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException 
    {
        //Déconnexion et renvoi à la page d'acceuil.
        
        HttpSession userSession = request.getSession(true);
        Object email = userSession.getAttribute("email");
        if(email != null)
        {
            userSession.invalidate();
        }
        this.getServletContext().getRequestDispatcher( VUE_HOME ).forward( request, response );
    }
}