package fr.utbm.mavenproject.controller;

import fr.utbm.mavenproject.entity.Course;
import fr.utbm.mavenproject.service.CourseService;
import java.io.IOException;
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
    public static final String VUE           = "/create_course.jsp";
    public static final String VUE_CONNEXION = "/login.jsp";
    public static final String VUE_CS        = "course_sessions";
    public static final String CHAMP_EMAIL   = "email";

    public void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException 
    {
        System.out.println("doGet dans CreateCourseServlet"); //TODO : à désactiver lors de la mise en 
        
        //parametres
        String param_title = request.getParameter("title");
        
        //on regarde si l'utilisateur est bien connecté
        HttpSession userSession = request.getSession(true);
        Object email = userSession.getAttribute(CHAMP_EMAIL);
        
        // si l'utilisateur est connecté
        if(email != null) 
        {
            //on teste les paramètres dans l'URL :
            if(param_title != null && !param_title.isEmpty())
            {
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
        System.out.println("doPost dans CreateCourseServlet"); //TODO : à désactiver lors de la mise en prod
        this.getServletContext().getRequestDispatcher( VUE ).forward( request, response );
    }
}