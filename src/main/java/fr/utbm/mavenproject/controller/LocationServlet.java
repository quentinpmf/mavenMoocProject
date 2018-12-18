package fr.utbm.mavenproject.controller;

import fr.utbm.mavenproject.entity.Location;
import fr.utbm.mavenproject.service.LocationService;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author quentinboudinot
 */
@WebServlet(name = "LocationServlet", urlPatterns = "/location")
public class LocationServlet extends HttpServlet {
    public static final String VUE          = "/location.jsp";
    public static final String CHAMP_CITY   = "city"; //TODO Quentin : ajouter dans la vue
    public static final String ATT_ERREURS  = "erreurs";
    public static final String ATT_RESULTAT = "resultat";
    
    @Override
    public void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException 
    {
        System.out.println("doGet dans LocationServlet"); //TODO : à désactiver lors de la mise en prod
        
        String resultat;
        Map<String, String> erreurs = new HashMap<String, String>();
        
        String param_city = request.getParameter("city");
        if(param_city != null)
        {
            // Création de la ville
            try {
                Location loc = new Location();
                loc.setCity(param_city);
                LocationService ls = new LocationService();
                ls.create(loc);
                resultat = "Succès de l ajout de la nouvelle ville.";
            } catch ( Exception e ) {
                resultat = "Echec de l ajout de la nouvelle ville.";
                erreurs.put( CHAMP_CITY, e.getMessage() );
            }
            request.setAttribute( ATT_ERREURS, erreurs );
            request.setAttribute( ATT_RESULTAT, resultat );
        }
        this.getServletContext().getRequestDispatcher( VUE ).forward( request, response );
    }

    @Override
    public void doPost( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException 
    {        
        System.out.println("doPost dans LocationServlet"); //TODO : à désactiver lors de la mise en prod
        LocationService ls = new LocationService();
        List<Location> locations = ls.getLocations();
        request.setAttribute("locations", locations);
        this.getServletContext().getRequestDispatcher( VUE ).forward( request, response );
    }
}