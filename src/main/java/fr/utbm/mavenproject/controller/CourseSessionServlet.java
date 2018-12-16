package fr.utbm.mavenproject.controller;

import fr.utbm.mavenproject.entity.CourseSession;
import fr.utbm.mavenproject.entity.Location;
import fr.utbm.mavenproject.entity.Client;
import fr.utbm.mavenproject.service.ClientService;
import fr.utbm.mavenproject.service.CourseSessionService;
import fr.utbm.mavenproject.service.LocationService;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author quentinboudinot
 */
public class CourseSessionServlet extends HttpServlet {
    public static final String VUE          = "/course_sessions.jsp";
    public static final String VUE_CONNEXION = "/login.jsp";
    public static final String CHAMP_EMAIL  = "email";
    public static final String CHAMP_PASS   = "motdepasse";
    public static final String ATT_ERREURS  = "erreurs";
    public static final String ATT_RESULTAT = "resultat";

    public void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException 
    {
        System.out.println("doGet dans CourseSession"); //TODO QUENTIN : à désactiver lors de la mise en prod
        
        //affichage de la page normal sans parametres
        CourseSessionService css = new CourseSessionService();
        List<CourseSession> courseSessions = css.getAllCs();

        LocationService ls = new LocationService();
        List<Location> locations = ls.getLocations();

        HttpSession userSession = request.getSession(true);
        Object email = userSession.getAttribute("email");
        String strEmail = (String) email;
        if(strEmail != null && !strEmail.isEmpty())
        {
            ClientService cs = new ClientService();
            Client client = cs.getClientByEmail(email.toString().trim());
            request.setAttribute("client",client);
        }
        
        request.setAttribute("courseSessions", courseSessions);
        request.setAttribute("locations",locations);
        
        
        this.getServletContext().getRequestDispatcher( VUE ).forward( request, response );
    }
    
    public void doPost( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException 
    {        
        System.out.println("doPost dans HomeServlet"); //TODO QUENTIN : à désactiver lors de la mise en prod
        this.getServletContext().getRequestDispatcher( VUE ).forward( request, response );
    }
}