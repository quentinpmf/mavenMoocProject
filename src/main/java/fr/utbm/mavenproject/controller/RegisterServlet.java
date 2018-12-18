package fr.utbm.mavenproject.controller;

import fr.utbm.mavenproject.service.ClientService;
import fr.utbm.mavenproject.entity.Client;
import java.io.IOException;
import java.util.HashMap;
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
@WebServlet(name = "RegisterServlet", urlPatterns = "/register")
public class RegisterServlet extends HttpServlet {
    public static String VUE                = "/register.jsp";
    public static final String CHAMP_EMAIL  = "email";
    public static final String CHAMP_PASS   = "motdepasse";
    public static final String CHAMP_CONF   = "confirmation";
    public static final String LASTNAME     = "lastname";
    public static final String FIRSTNAME    = "firstname";
    public static final String ADDRESS      = "address";
    public static final String PHONE        = "phone";
    public static final String CHAMP_NOM    = "nom";
    public static final String ATT_ERREURS  = "erreurs";
    public static final String ATT_RESULTAT = "resultat";

    public void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException 
    {
        System.out.println("doGet dans RegisterServlet"); //TODO : à désactiver lors de la mise en prod
        this.getServletContext().getRequestDispatcher( VUE ).forward( request, response );
    }

    public void doPost( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException 
    {
        System.out.println("doPost dans RegisterServlet"); //TODO : à désactiver lors de la mise en prod
        
        String resultat;
        Map<String, String> erreurs = new HashMap<String, String>();

        // Récupération des champs saisis via le formulaire
        String email = request.getParameter( CHAMP_EMAIL );
        String motDePasse = request.getParameter( CHAMP_PASS );
        String confirmation = request.getParameter( CHAMP_CONF );
        String lastname = request.getParameter( LASTNAME );
        String firstname = request.getParameter( FIRSTNAME );
        String address = request.getParameter( ADDRESS );
        String phone = request.getParameter( PHONE );

        ClientService cs = new ClientService();
        
        // Vérification du champ email
        try {
            cs.verifEmailFromForm(email);
            // vérification si l'email est déja présent en base (pour éviter les doublons)
            boolean emailExists = cs.checkEmail(email);
            if(emailExists)
            {
                throw new Exception( "L'email existe déja dans la bdD." );
            }
        } catch ( Exception e ) {
            erreurs.put( CHAMP_EMAIL, e.getMessage() );
        }
        
        // Vérification du champ mot de passe
        try {
            cs.verifMdpFromForm(motDePasse);
        } catch ( Exception e ) {
            erreurs.put( CHAMP_PASS, e.getMessage() );
        }
        
        // Vérification des conditions du champ mot de passe
        try {
            cs.verifMdpConditions(motDePasse, confirmation);
        } catch ( Exception e ) {
            erreurs.put( CHAMP_PASS, e.getMessage() );
        }

        // Vérification du champ lastname
        try {
            cs.verifLNFromForm(lastname);
        } catch ( Exception e ) {
            erreurs.put( LASTNAME, e.getMessage() );
        }
        
        // Vérification du champ nom.
        try {
            cs.verifFNFromForm(firstname );
        } catch ( Exception e ) {
            erreurs.put( FIRSTNAME, e.getMessage() );
        }
        
        // Vérification du champ nom.
        try {
            cs.verifAddressFromForm(address );
        } catch ( Exception e ) {
            erreurs.put( ADDRESS, e.getMessage() );
        }
        
        // Vérification du champ nom.
        try {
            cs.verifPhoneFromForm(phone );
        } catch ( Exception e ) {
            erreurs.put( PHONE, e.getMessage() );
        }

        // Initialisation du résultat global de la validation
        if ( erreurs.isEmpty() ) {
            
            //insertion du client
            Client cli = new Client();
            cli.setLastname(lastname);     
            cli.setFirstname(firstname);
            cli.setAddress(address); 
            cli.setPhone(phone); 
            cli.setEmail(email); 
            cli.setPassword(motDePasse); 
            cli.setRole("0"); //role de base à 0 = client normal
            cs.create(cli);
            
            resultat = "Succès de l'inscription.";
            VUE = "/login.jsp";
            
        } else {
            resultat = "Échec de l'inscription.";
            VUE = "/register.jsp";
        }

        request.setAttribute( ATT_ERREURS, erreurs );
        request.setAttribute( ATT_RESULTAT, resultat );
        this.getServletContext().getRequestDispatcher( VUE ).forward( request, response );
    }
    
}