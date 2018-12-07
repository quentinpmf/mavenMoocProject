package fr.utbm.mavenproject.controller;

import static fr.utbm.mavenproject.controller.LoginServlet.VUE;
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

/*
 * @author quentinboudinot
*/
public class CourseSessionServlet extends HttpServlet {
    public static final String VUE          = "/course_sessions.jsp";
    public static final String CHAMP_EMAIL  = "email";
    public static final String CHAMP_PASS   = "motdepasse";
    public static final String ATT_ERREURS  = "erreurs";
    public static final String ATT_RESULTAT = "resultat";

    @Override
    public void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException 
    {
        /* Affichage de la page de login */
        String param_id = request.getParameter("id");
        System.out.println("url param = "+param_id);
        this.getServletContext().getRequestDispatcher( VUE ).forward( request, response );
    }

    @Override
    public void doPost( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException 
    {
        String id = request.getParameter("id");
        System.out.println("id = "+id);
    }
}