package fr.utbm.mavenproject.controller;

import fr.utbm.mavenproject.entity.Client;
import fr.utbm.mavenproject.entity.ClientSession;
import fr.utbm.mavenproject.entity.CourseSession;
import fr.utbm.mavenproject.service.ClientSessionService;
import fr.utbm.mavenproject.service.CourseSessionService;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author quentinboudinot
 */
public class CourseSessionRegisterServlet extends HttpServlet {
    public static final String VUE_CONNEXION = "/login.jsp";
    public static final String VUE_CS        = "course_sessions";
    public static final String VUE_RED_CS    = "/course_sessions.jsp";
    public static final String CHAMP_EMAIL   = "email";
    public static final String CHAMP_ID      = "id";
    public static final String ATT_ERREURS   = "erreurs";
    public static final String ATT_RESULTAT  = "resultat";

    public void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException 
    {
        System.out.println("doGet dans CourseSessionRegisterServlet"); //TODO QUENTIN : à désactiver lors de la mise en 
        
        String resultat;
        Map<String, String> erreurs = new HashMap<String, String>();
        
        //parametres
        String param_courseId = request.getParameter("courseId");
        
        //on regarde si l'utilisateur est bien connecté
        HttpSession userSession = request.getSession(true);
        Object email = userSession.getAttribute(CHAMP_EMAIL);
        System.out.println("email = "+email);
        Object id = userSession.getAttribute(CHAMP_ID);
        System.out.println("id = "+id);
        
        // si l'utilisateur est connecté
        if(email != null) 
        {
            System.out.println("dans le if");
            //on teste les paramètres dans l'URL :
            if(param_courseId != null && !param_courseId.isEmpty())
            {
                System.out.println("param_courseId = "+param_courseId);
                Integer courseId = Integer.valueOf(param_courseId);	

                //update bdd de la course sessions avec nombre de places libres = nbplaces libres -1
                //récupération de la course session
                CourseSessionService csserv = new CourseSessionService();
                CourseSession activeCs = csserv.getCsById(courseId);
                
                String strPlacesLibres = activeCs.getPlacesLibres();
                strPlacesLibres = strPlacesLibres.replaceAll("\\s","");
                int intPlacesLibres = Integer.valueOf(strPlacesLibres);
                if(intPlacesLibres > 0)
                {
                    
                    try {
                        //création de l'objet client
                        Client cli = new Client();
                        cli.setId((int) id);
                        resultat = "Création du client OK";
                        
                        //création de l'objet course session
                        CourseSession cos = new CourseSession();
                        cos.setId(courseId);
                        resultat = "Création de courseSession OK";
                        
                        //inscription du client dans la course session
                        ClientSession cls = new ClientSession();
                        cls.setClientId(cli);
                        cls.setSessionId(cos);
                        resultat = "Création de courseSession OK";
                        
                        //insertion du clientsession dans la BDD
                        ClientSessionService css = new ClientSessionService();
                        css.create(cls);

                        //update bdd de la course sessions avec nombre de places libres = nbplaces libres -1
                        //récupération de la course session
                        int newPlacesLibres = intPlacesLibres-1;
                        activeCs.setPlacesLibres(Integer.toString(newPlacesLibres));
                        csserv.update(activeCs);
                        
                    } catch ( Exception e ) {
                        resultat = "Echec de l'inscription à la session de cours";
                        erreurs.put( "register_session", e.getMessage() );
                    }
                    
                    request.setAttribute( ATT_ERREURS, erreurs );
                    request.setAttribute( ATT_RESULTAT, resultat );
                }
                
                //redirection vers les sessions de cours
                response.sendRedirect(VUE_CS);
                return;
            }
        }
        else // sinon on le redirige vers la page d'acceuil pour éviter la fraude
        {
            this.getServletContext().getRequestDispatcher( VUE_CONNEXION ).forward( request, response );
        }
    }
}