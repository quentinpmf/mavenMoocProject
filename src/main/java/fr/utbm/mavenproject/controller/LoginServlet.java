package fr.utbm.mavenproject.controller;

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
import javax.servlet.http.HttpSession;
import javax.ws.rs.core.Response;

/*
 * @author quentinboudinot
*/
public class LoginServlet extends HttpServlet {
    public static final String VUE          = "/login.jsp";
    public static final String VUE_HOME     = "/home.jsp";
    public static final String CHAMP_EMAIL  = "email";
    public static final String CHAMP_PASS   = "motdepasse";
    public static final String ATT_ERREURS  = "erreurs";
    public static final String ATT_RESULTAT = "resultat";

    @Override
    public void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException 
    {
        /* Affichage de la page de login */
        System.out.println("doGet dans LoginServlet");
        this.getServletContext().getRequestDispatcher( VUE ).forward( request, response );
    }

    @Override
    public void doPost( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException 
    {        
        String resultat;
        Map<String, String> erreurs = new HashMap<String, String>();

        /* Récupération des champs du formulaire. */
        String email = request.getParameter( CHAMP_EMAIL );
        String motDePasse = request.getParameter( CHAMP_PASS );

        /* Validation du champ email. */
        try {
            validationEmail( email );
        } catch ( Exception e ) {
            erreurs.put( CHAMP_EMAIL, e.getMessage() );
        }

        /* Validation du champ mot de passe . */
        try {
            validationMotsDePasse( motDePasse );
        } catch ( Exception e ) {
            erreurs.put( CHAMP_PASS, e.getMessage() );
        }

        /* Initialisation du résultat global de la validation. */
        if ( erreurs.isEmpty() ) {
            DefaultClientController dclc = new DefaultClientController();
            boolean emailExists = dclc.checkEmail(email); //je regarde si l'email est déja dans la BDD
            
            if(emailExists) //si oui, alors on vérifie le mot de passe saisi par l'utilisateur
            {
                boolean verifMotDePasse = dclc.comparePasswords(email, motDePasse);
                
                if(verifMotDePasse) //si le mot de passe est OK.
                {
                    resultat = "Succès de la connexion.";
                    request.setAttribute( ATT_RESULTAT, resultat );
                    
                    /* création de la session utilisateur */
                    HttpSession userSession = request.getSession(true);
                    userSession.setAttribute("email",email);
                    
                    //get others infos
                    Client cli = dclc.getClientByEmail(email);
                    userSession.setAttribute("id",cli.getId());
                    userSession.setAttribute("lastname",cli.getLastname());
                    userSession.setAttribute("firstname",cli.getFirstname());
                    userSession.setAttribute("address",cli.getAddress());
                    userSession.setAttribute("phone",cli.getPhone());
                    
                    this.getServletContext().getRequestDispatcher( VUE_HOME ).forward( request, response );
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
            /* Transmission de la paire d'objets request/response à notre JSP */
            this.getServletContext().getRequestDispatcher( VUE ).forward( request, response );
        } 
        else 
        {
            resultat = "Échec de la connexion.";
            
            request.setAttribute( ATT_ERREURS, erreurs );
            request.setAttribute( ATT_RESULTAT, resultat );
            /* Transmission de la paire d'objets request/response à notre JSP */
            this.getServletContext().getRequestDispatcher( VUE ).forward( request, response );
        }
    }

    /**
    * Valide l'adresse mail saisie.
    * @author quentinboudinot
    */
    private void validationEmail( String email ) throws Exception {
       if ( email != null && email.trim().length() != 0 ) {
           if ( !email.matches( "([^.@]+)(\\.[^.@]+)*@([^.@]+\\.)+([^.@]+)" ) ) {
               throw new Exception( "Merci de saisir une adresse mail valide." );
           }
       } else {
           throw new Exception( "Merci de saisir une adresse mail." );
       }
    }

    /**
     * Valide le mot de passe saisi.
     * @author quentinboudinot
     */
    private void validationMotsDePasse( String motDePasse ) throws Exception{
       if (motDePasse == null && motDePasse.trim().length() == 0 ) {
           throw new Exception("Merci de saisir votre mot de passe.");
       }
    }
}