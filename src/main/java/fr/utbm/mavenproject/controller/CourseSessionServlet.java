package fr.utbm.mavenproject.controller;

import static fr.utbm.mavenproject.controller.LoginServlet.VUE;
import fr.utbm.mavenproject.entity.Client;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import fr.utbm.mavenproject.entity.CourseSession;
import java.util.ArrayList;

/*
 * @author quentinboudinot
*/
public class CourseSessionServlet extends HttpServlet {
    public static final String VUE          = "/course_sessions.jsp";
    public static final String VUE_CONNEXION = "/login.jsp";
    public static final String CHAMP_EMAIL  = "email";
    public static final String CHAMP_PASS   = "motdepasse";
    public static final String ATT_ERREURS  = "erreurs";
    public static final String ATT_RESULTAT = "resultat";

    @Override
    public void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException 
    {
        System.out.println("doGet dans CourseSession");
        
        //filtres
        String param_title = request.getParameter("title");
        String param_date = request.getParameter("date");
        String param_location = request.getParameter("location");
        //inscription
        String param_inscription = request.getParameter("inscription");
        
        //on teste les paramètres dans l'URL :
        //filtrage des formations (via barre de recherche)
        if(param_title != null || param_date != null || param_location != null)
        {
            DefaultCourseSessionController dcsc = new DefaultCourseSessionController();
            List<CourseSession> csList = dcsc.selectAllWithFilters(param_title,param_date,param_location);
            System.out.println("csList = "+csList);
        }
        else //inscription à une formation
        {
            if(param_inscription != null)
            {
                HttpSession userSession = request.getSession(true);
                Object clientId = userSession.getAttribute("id");
                System.out.println("clientId = "+clientId);

                if(clientId != null)
                {
                    System.out.println("id user = "+clientId);

                    //on regarde dans la table COURSE_SESSION_CLIENT si on à déja un ID client avec ce compte.
                    DefaultCourseClientController dcscc = new DefaultCourseClientController();
                    boolean ifClientIsPreRegistered = dcscc.checkIfClientIsInCs(clientId.toString(), param_inscription);
                    System.out.println("ifClientIsPreRegistered = "+ifClientIsPreRegistered);
                }
                else
                {
                    //redirection vers connexion.
                    this.getServletContext().getRequestDispatcher( VUE_CONNEXION ).forward( request, response );
                }

                /*
                if(!ifClientIsPreRegistered)
                {
                    //création d'un enregistrement dans COURSE_SESSION_CLIENT avec les mêmes paramêtres
                    boolean success = dcscc.registerClientInCs(clientId, param_inscription);
                    if(success)
                    {
                        //alors le client est inscrit
                    }
                    else
                    {
                        //alors le client n'est pas inscrit
                    }
                }
                */
            }
            else
            {
                //affichage de la page normal sans parametres
            }
        }
        
        this.getServletContext().getRequestDispatcher( VUE ).forward( request, response );
    }

    @Override
    public void doPost( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException 
    {
        System.out.println("doPost dans CourseSession");
        String id = request.getParameter("id");
        System.out.println("id = "+id);
    }
}