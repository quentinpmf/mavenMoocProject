package fr.utbm.mavenproject.service;

import fr.utbm.mavenproject.repository.ClientDao;
import fr.utbm.mavenproject.entity.Client;

import java.util.List;

/**
 *
 * @author quentinboudinot
 */
public class ClientService {
    
    private static ClientDao objClientDao;

    public ClientService() {
        objClientDao = new ClientDao();
    }

    public void create(Client entity) {
        objClientDao.insert(entity);
    }

    public void update(Client entity) {
        objClientDao.update(entity);
    }

    public Client getClientByEmail(String email) {
        return objClientDao.getClientByEmail(email);
    }

    public void delete(String email) {
        Client client = objClientDao.getClientByEmail(email);
        objClientDao.delete(client);
    }

    public List<Client> getClients() {
        return objClientDao.getClients();
    }

    public ClientDao objClientDao() {
        return objClientDao;
    }
    
    //################# Verifications from forms #######################
    
    // vérifie l'adresse email saisie dans le formulaire
    public void verifEmailFromForm( String email ) throws Exception {
       if ( email != null && email.trim().length() != 0 ) {
           if ( !email.matches( "([^.@]+)(\\.[^.@]+)*@([^.@]+\\.)+([^.@]+)" ) ) {
               throw new Exception( "Merci de saisir une adresse mail valide." );
           }
       } else {
           throw new Exception( "Merci de saisir une adresse mail." );
       }
    }

    // vérifie le mot de passe saisi dans le formulaire
    public void verifMdpFromForm( String motDePasse ) throws Exception{
       if (motDePasse == null && motDePasse.trim().length() == 0 ) {
           throw new Exception("Merci de saisir votre mot de passe.");
       }
    }
    
    // vérifie les conditions de mot de passe sur le formulaire d'enregistrement
    public void verifMdpConditions( String motDePasse, String confirmation ) throws Exception{
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
    
    // vérifie le lastname saisi dans le formulaire
    public void verifLNFromForm( String lastname ) throws Exception {
       if ( lastname != null && lastname.trim().length() < 3 ) {
           throw new Exception( "Le nom de famille de l'utilisateur doit contenir au moins 3 caractères." );
       }
    }
    
    // vérifie le firstname saisi dans le formulaire
    public void verifFNFromForm( String firstname ) throws Exception {
       if ( firstname != null && firstname.trim().length() < 3 ) {
           throw new Exception( "Le prénom de l'utilisateur doit contenir au moins 3 caractères." );
       }
    }
    
    // vérifie l'adresse saisie dans le formulaire
    public void verifAddressFromForm( String address ) throws Exception {
       if ( address != null && address.trim().length() < 5 ) {
           throw new Exception( "L'adresse doit contenir au moins 5 caractères." );
       }
    }
    
    // vérifie le téléphone saisi dans le formulaire
    public void verifPhoneFromForm( String phone ) throws Exception {
       if ( phone != null && phone.trim().length() != 10 ) {
           throw new Exception( "Le téléphone doit contenir 10 chiffres." );
       }
    }
    
    //##################################################################
    
    // renvoie true si l'email est présent dans la base de données
    public boolean checkEmail(String email) {
        return objClientDao.checkIfEmailExists(email);
    }
    
    // compare le mot de passe donné en parametre avec celui de la BDD.
    public boolean comparePasswords(String email, String passwordFromForm)
    {
        Client cli = objClientDao.getClientByEmail(email); //récupération du client
        String passwordFromDB = cli.getPassword(); //récupération du mot de passe stocké en base
        passwordFromDB = passwordFromDB.replaceAll("\\s",""); //on supprime les espaces
        return (passwordFromDB.equals(passwordFromForm)); //on return true ou false en fonction de la différence de mot de passe
    }
    
}
