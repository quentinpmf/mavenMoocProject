package fr.utbm.mavenproject.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/*
 * @author quentinboudinot
*/
@WebServlet(name = "LogoutServlet", urlPatterns = "/logout" )
public class LogoutServlet extends HttpServlet {
    public static final String VUE_HOME     = "/home.jsp";
    public static final String VUE_LOGOUT   = "/logout.jsp";
    public static final String CHAMP_EMAIL  = "email";

    @Override
    public void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException 
    {
        System.out.println("doGet dans LogoutServlet"); //TODO QUENTIN : à désactiver lors de la mise en prod
        HttpSession userSession = request.getSession(true);
        Object email = userSession.getAttribute(CHAMP_EMAIL);
        // si l'utilisateur était connecté
        if(email != null) {
            userSession.invalidate(); // on le déconnecte
        }
        response.sendRedirect(VUE_HOME); // on le redirige vers l'acceuil
    }
    
    @Override
    public void doPost( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException 
    {
        System.out.println("doPost dans LogoutServlet"); //TODO QUENTIN : à désactiver lors de la mise en prod
        this.getServletContext().getRequestDispatcher( VUE_LOGOUT ).forward( request, response );
    }
}