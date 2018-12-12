package fr.utbm.mavenproject.controller;

import fr.utbm.mavenproject.entity.CourseSession;
import fr.utbm.mavenproject.service.CourseSessionService;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
//import net.sf.jasperreports.view.*;
//import net.sf.jasperreports.engine.*;

/**
 *
 * @author quentinboudinot
 */
@WebServlet(name = "HomeServlet", urlPatterns = "/home")
public class HomeServlet extends HttpServlet {
    public static final String VUE = "/home.jsp";

    public void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException 
    {
        System.out.println("doGet dans HomeServlet"); //TODO QUENTIN : à désactiver lors de la mise en prod
        CourseSessionService css = new CourseSessionService();
        List<CourseSession> courseSessions = css.getFourFirstCs();
        request.setAttribute("courseSessions", courseSessions);
        System.out.println("courseSessions = "+courseSessions);
        this.getServletContext().getRequestDispatcher( VUE ).forward( request, response );
    }

    public void doPost( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException 
    {        
        System.out.println("doPost dans HomeServlet"); //TODO QUENTIN : à désactiver lors de la mise en prod
        this.getServletContext().getRequestDispatcher( VUE ).forward( request, response );
    }
}