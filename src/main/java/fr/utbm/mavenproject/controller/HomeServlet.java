package fr.utbm.mavenproject.controller;

import fr.utbm.mavenproject.entity.Course;
import fr.utbm.mavenproject.entity.CourseSession;
import fr.utbm.mavenproject.service.CourseService;
import fr.utbm.mavenproject.service.CourseSessionService;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author quentinboudinot
 */
@WebServlet(name = "HomeServlet", urlPatterns = "/home")
public class HomeServlet extends HttpServlet {
    public static final String VUE = "/home.jsp";

    public void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException 
    {
        System.out.println("doGet dans HomeServlet"); //TODO : à désactiver lors de la mise en prod
        CourseSessionService css = new CourseSessionService();
        List<CourseSession> courseSessions = css.getFourFirstCs();
        CourseService cs = new CourseService();
        List<Course> courses = cs.getCourses();
        
        request.setAttribute("courseSessions", courseSessions);
        request.setAttribute("courses", courses);
        
        this.getServletContext().getRequestDispatcher( VUE ).forward( request, response );
    }

    public void doPost( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException 
    {        
        System.out.println("doPost dans HomeServlet"); //TODO : à désactiver lors de la mise en prod
        this.getServletContext().getRequestDispatcher( VUE ).forward( request, response );
    }
}