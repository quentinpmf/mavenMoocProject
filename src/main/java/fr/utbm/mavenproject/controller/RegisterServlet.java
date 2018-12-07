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


public class RegisterServlet extends HttpServlet {
    public static String VUE          = "/register.jsp";
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
        /* Affichage de la page d'inscription */
        this.getServletContext().getRequestDispatcher( VUE ).forward( request, response );
    }

    public void doPost( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException 
    {
        String resultat;
        Map<String, String> erreurs = new HashMap<String, String>();

        /* Récupération des champs du formulaire. */
        String email = request.getParameter( CHAMP_EMAIL );
        String motDePasse = request.getParameter( CHAMP_PASS );
        String confirmation = request.getParameter( CHAMP_CONF );
        String lastname = request.getParameter( LASTNAME );
        String firstname = request.getParameter( FIRSTNAME );
        String address = request.getParameter( ADDRESS );
        String phone = request.getParameter( PHONE );

        /* Validation du champ email. */
        try {
            validationEmail( email );
        } catch ( Exception e ) {
            erreurs.put( CHAMP_EMAIL, e.getMessage() );
        }

        /* Validation des champs mot de passe et confirmation. */
        try {
            validationMotsDePasse( motDePasse, confirmation );
        } catch ( Exception e ) {
            erreurs.put( CHAMP_PASS, e.getMessage() );
        }

        /* Validation du champ lastname. */
        try {
            validationLastName( lastname );
        } catch ( Exception e ) {
            erreurs.put( LASTNAME, e.getMessage() );
        }
        
        /* Validation du champ nom. */
        try {
            validationFirstName( firstname );
        } catch ( Exception e ) {
            erreurs.put( FIRSTNAME, e.getMessage() );
        }
        
        /* Validation du champ nom. */
        try {
            validationAddress( address );
        } catch ( Exception e ) {
            erreurs.put( ADDRESS, e.getMessage() );
        }
        
        /* Validation du champ nom. */
        try {
            validationPhone( phone );
        } catch ( Exception e ) {
            erreurs.put( PHONE, e.getMessage() );
        }

        /* Initialisation du résultat global de la validation. */
        if ( erreurs.isEmpty() ) {
            //insertion du client
            DefaultClientController dclc = new DefaultClientController();
            dclc.createClient(lastname,firstname,address,phone,email,motDePasse);
            resultat = "Succès de l'inscription.";
            VUE = "/login.jsp";
            
        } else {
            resultat = "Échec de l'inscription.";
            VUE = "/register.jsp";
        }

        /* Stockage du résultat et des messages d'erreur dans l'objet request */
        request.setAttribute( ATT_ERREURS, erreurs );
        request.setAttribute( ATT_RESULTAT, resultat );
        
        /* Transmission de la paire d'objets request/response à notre JSP */
        this.getServletContext().getRequestDispatcher( VUE ).forward( request, response );
    }

    /**
    * Valide l'adresse mail saisie.
    */
    private void validationEmail( String email ) throws Exception {
       if ( email != null && email.trim().length() != 0 ) {
           if ( !email.matches( "([^.@]+)(\\.[^.@]+)*@([^.@]+\\.)+([^.@]+)" ) ) {
               throw new Exception( "Merci de saisir une adresse mail valide." );
           }
           else
           {
               /* vérification email unique dans la BDD */
               DefaultClientController dclc = new DefaultClientController();
               boolean emailExists = dclc.checkEmail(email); //je regarde si l'email est déja dans la BDD

               if(emailExists) //si oui, alors on vérifie le mot de passe saisi par l'utilisateur
               {
                    throw new Exception( "L'email existe déja dans la bdD." );
               }
           }
       } else {
           throw new Exception( "Merci de saisir une adresse mail." );
       }
    }

    /**
     * Valide les mots de passe saisis.
     */
    private void validationMotsDePasse( String motDePasse, String confirmation ) throws Exception{
       if (motDePasse != null && motDePasse.trim().length() != 0 && confirmation != null && confirmation.trim().length() != 0) {
           if (!motDePasse.equals(confirmation)) {
               throw new Exception("Les mots de passe entrés sont différents, merci de les saisir à nouveau.");
           } else if (motDePasse.trim().length() < 3) {
               throw new Exception("Les mots de passe doivent contenir au moins 3 caractères.");
           }
       } else {
           throw new Exception("Merci de saisir et confirmer votre mot de passe.");
       }
    }

    /**
     * Valide le lastname d'utilisateur saisi.
     */
    private void validationLastName( String lastname ) throws Exception {
       if ( lastname != null && lastname.trim().length() < 3 ) {
           throw new Exception( "Le nom de famille de l'utilisateur doit contenir au moins 3 caractères." );
       }
    }
    
    /**
     * Valide le nom d'utilisateur saisi.
     */
    private void validationFirstName( String firstname ) throws Exception {
       if ( firstname != null && firstname.trim().length() < 3 ) {
           throw new Exception( "Le prénom de l'utilisateur doit contenir au moins 3 caractères." );
       }
    }
    
    /**
     * Valide l'adresse saisie.
     */
    private void validationAddress( String address ) throws Exception {
       if ( address != null && address.trim().length() < 5 ) {
           throw new Exception( "L'adresse doit contenir au moins 5 caractères." );
       }
    }
    
    /**
     * Valide le téléphone saisi.
     */
    private void validationPhone( String phone ) throws Exception {
       if ( phone != null && phone.trim().length() != 10 ) {
           throw new Exception( "Le téléphone doit contenir 10 chiffres." );
       }
    }
    
}