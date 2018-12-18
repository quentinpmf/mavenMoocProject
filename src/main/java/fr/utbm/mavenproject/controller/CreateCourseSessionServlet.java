package fr.utbm.mavenproject.controller;

import fr.utbm.mavenproject.entity.Course;
import fr.utbm.mavenproject.entity.CourseSession;
import fr.utbm.mavenproject.entity.Location;
import fr.utbm.mavenproject.service.CourseService;
import fr.utbm.mavenproject.service.CourseSessionService;
import fr.utbm.mavenproject.service.LocationService;
import java.io.IOException;
import java.text.ParseException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.text.SimpleDateFormat;  
import java.util.Date;  
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author quentinboudinot
 */
public class CreateCourseSessionServlet extends HttpServlet {
    public static final String VUE           = "/create_course_session.jsp";
    public static final String VUE_CONNEXION = "/login.jsp";
    public static final String VUE_CS        = "course_sessions";
    public static final String CHAMP_EMAIL   = "email";
    public static final String IMAGE         = "http://image.noelshack.com/fichiers/2018/49/4/1544137044-formation8.jpg";

    public void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException 
    {
        System.out.println("doGet dans CreateCourseSessionServlet"); //TODO : à désactiver lors de la mise en 
        
        //parametres
        String param_course = request.getParameter("course");
        String param_startDate = request.getParameter("startDate");
        String param_endDate = request.getParameter("endDate");
        String param_maxi = request.getParameter("maxi");
        String param_location = request.getParameter("location");
        
        //on regarde si l'utilisateur est bien connecté
        HttpSession userSession = request.getSession(true);
        Object email = userSession.getAttribute(CHAMP_EMAIL);
        
        // si l'utilisateur est connecté
        if(email != null) 
        {
            //on teste les paramètres dans l'URL :
            if(param_course != null && !param_course.isEmpty() && param_startDate != null && !param_startDate.isEmpty() && param_endDate != null && !param_endDate.isEmpty()&& param_location != null && !param_location.isEmpty() && param_maxi != null && !param_maxi.isEmpty() && param_location != null && !param_location.isEmpty())
            {                
                Integer courseCode = Integer.valueOf(param_course);	
                Integer locationId = Integer.valueOf(param_location);	
                Integer maxi = Integer.valueOf(param_maxi);
                
                CourseService cserv = new CourseService();
                Course cou = cserv.getCourseByCode(courseCode);
                
                LocationService lserv = new LocationService();
                Location loca = lserv.getLocationById(locationId);
                
                //création de la session de cours
                CourseSession cs = new CourseSession();
                cs.setCourseCode(cou);
                cs.setLocationId(loca);
                
                Date nowDate = new Date(); //date par défaut si échec de formatage de la date ci dessous
                cs.setStartDate(nowDate);
                cs.setEndDate(nowDate);
                
                //date du formulaire
                Date startDate,endDate;
                try {
                    startDate = new SimpleDateFormat("yyyy-MM-dd").parse(param_startDate);
                    cs.setStartDate(startDate);
                } catch (ParseException ex) {
                    Logger.getLogger(CreateCourseSessionServlet.class.getName()).log(Level.SEVERE, null, ex);
                }
                try {
                    endDate = new SimpleDateFormat("yyyy-MM-dd").parse(param_endDate);
                    cs.setEndDate(endDate);
                } catch (ParseException ex) {
                    Logger.getLogger(CreateCourseSessionServlet.class.getName()).log(Level.SEVERE, null, ex);
                }
    
                cs.setMaxi(maxi);
                cs.setImage(IMAGE);
                cs.setPlacesLibres(param_maxi);

                //insertion de la session de cours dans la BDD
                CourseSessionService css = new CourseSessionService();
                css.create(cs);

                //redirection vers les sessions de cours
                response.sendRedirect(VUE_CS);
                return;
            }
            else
            {
                //affichage de la page normal sans parametres
                CourseService cs = new CourseService();
                List<Course> courses = cs.getCourses();
                
                LocationService ls = new LocationService();
                List<Location> locations = ls.getLocations();
       
                request.setAttribute("courses", courses);
                request.setAttribute("locations",locations);
                
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
        System.out.println("doPost dans CreateCourseSessionServlet"); //TODO : à désactiver lors de la mise en prod
        this.getServletContext().getRequestDispatcher( VUE ).forward( request, response );
    }
}