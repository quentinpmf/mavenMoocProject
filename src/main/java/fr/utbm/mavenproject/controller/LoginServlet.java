package fr.utbm.mavenproject.controller;

import fr.utbm.mavenproject.entity.Client;
import fr.utbm.mavenproject.service.ClientService;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author quentinboudinot
 */
@WebServlet( name="LoginServlet", urlPatterns = "/login" )
public class LoginServlet extends HttpServlet {
    public static final String VUE          = "/login.jsp";
    public static final String VUE_HOME     = "home";
    public static final String CHAMP_EMAIL  = "email";
    public static final String CHAMP_PASS   = "motdepasse";
    public static final String ATT_ERREURS  = "erreurs";
    public static final String ATT_RESULTAT = "resultat";

    @Override
    public void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException 
    {
        System.out.println("doGet dans LoginServlet"); //TODO QUENTIN : à désactiver lors de la mise en prod
        this.getServletContext().getRequestDispatcher( VUE ).forward( request, response );
    }

    @Override
    public void doPost( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException 
    {        
        System.out.println("doPost dans LoginServlet"); //TODO QUENTIN : à désactiver lors de la mise en prod
        
        String resultat;
        Map<String, String> erreurs = new HashMap<String, String>();

        // Récupération des champs du formulaire.
        String email = request.getParameter( CHAMP_EMAIL );
        String motDePasse = request.getParameter( CHAMP_PASS );

        ClientService cs = new ClientService();
            
        // Vérification du champ email. 
        try {
            cs.verifEmailFromForm(email);
        } catch ( Exception e ) {
            erreurs.put( CHAMP_EMAIL, e.getMessage() );
        }

        // Vérification du champ mot de passe
        try {
            cs.verifMdpFromForm(motDePasse);
        } catch ( Exception e ) {
            erreurs.put( CHAMP_PASS, e.getMessage() );
        }

        // Initialisation du résultat global de la validation
        if ( erreurs.isEmpty() ) {
            boolean emailExists = cs.checkEmail(email); //je regarde si l'email est déja dans la BDD
            
            if(emailExists) //si oui, alors on vérifie le mot de passe saisi par l'utilisateur
            {
                boolean verifMotDePasse = cs.comparePasswords(email, motDePasse);
                
                if(verifMotDePasse) //si le mot de passe est OK.
                {
                    resultat = "Succès de la connexion.";
                    request.setAttribute( ATT_RESULTAT, resultat );
                    
                    // création de la session utilisateur
                    HttpSession userSession = request.getSession(true);
                    userSession.setAttribute("email",email);
                    
                    // récupération des informations utilisateurs autres qu'email
                    Client cli = cs.getClientByEmail(email);
                    userSession.setAttribute("id",cli.getId());
                    userSession.setAttribute("lastname",cli.getLastname());
                    userSession.setAttribute("firstname",cli.getFirstname());
                    userSession.setAttribute("address",cli.getAddress());
                    userSession.setAttribute("phone",cli.getPhone());
                    
                    //redirection vers la home
                    response.sendRedirect(VUE_HOME);
                    return;
                }
                else
                {
                    erreurs.put( CHAMP_PASS, "Le mot de passe saisi est incorrect.");
                    resultat = "Échec de la connexion.";
                }
            }
            else
            {
                erreurs.put( CHAMP_EMAIL, "Aucun client avec l'email saisi.");
                resultat = "Échec de la connexion";
            }
            
            request.setAttribute( ATT_ERREURS, erreurs );
            request.setAttribute( ATT_RESULTAT, resultat );
            this.getServletContext().getRequestDispatcher( VUE ).forward( request, response );
        } 
        else 
        {
            resultat = "Échec de la connexion.";
            request.setAttribute( ATT_ERREURS, erreurs );
            request.setAttribute( ATT_RESULTAT, resultat );
            this.getServletContext().getRequestDispatcher( VUE ).forward( request, response );
        }
    }
}