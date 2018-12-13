package fr.utbm.mavenproject.controller;

import static fr.utbm.mavenproject.controller.LogoutServlet.CHAMP_EMAIL;
import fr.utbm.mavenproject.entity.Course;
import fr.utbm.mavenproject.entity.CourseSession;
import fr.utbm.mavenproject.entity.Location;
import fr.utbm.mavenproject.repository.CourseDao;
import fr.utbm.mavenproject.service.ClientService;
import fr.utbm.mavenproject.service.CourseService;
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
public class CreateCourseServlet extends HttpServlet {
    public static final String VUE           = "/createcourse.jsp";
    public static final String VUE_CONNEXION = "/login.jsp";
    public static final String VUE_CS        = "course_sessions";

    public void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException 
    {
        System.out.println("doGet dans CreateCourseServlet"); //TODO QUENTIN : à désactiver lors de la mise en 
        
        //filtres
        String param_title = request.getParameter("title");
        
        //on regarde si l'utilisateur est bien connecté
        HttpSession userSession = request.getSession(true);
        Object email = userSession.getAttribute(CHAMP_EMAIL);
        
        // si l'utilisateur est connecté
        if(email != null) 
        {
            //on teste les paramètres dans l'URL :
            //filtrage des formations (via barre de recherche)
            if(param_title != null && !param_title.isEmpty())
            {
                System.out.println("param_title = "+param_title);

                //création du cours
                Course cou = new Course();
                cou.setTitle(param_title);

                //insertion du cours dans la BDD
                CourseService cs = new CourseService();
                cs.create(cou);

                //redirection vers les sessions de cours
                response.sendRedirect(VUE_CS);
                return;
            }
            else
            {
                //affichage de la page normal sans parametres
                this.getServletContext().getRequestDispatcher( VUE ).forward( request, response );
            }
        }
        else // sinon on le redirige vers la page d'acceuil pour éviter la fraude
        {
            this.getServletContext().getRequestDispatcher( VUE_CONNEXION ).forward( request, response );
        }
    }
    
    public void doPost( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException 
    {        
        System.out.println("doPost dans CreateCourseServlet"); //TODO QUENTIN : à désactiver lors de la mise en prod
        this.getServletContext().getRequestDispatcher( VUE ).forward( request, response );
    }
}