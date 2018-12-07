/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.utbm.mavenproject.controller;

import fr.utbm.mavenproject.entity.Client;
import fr.utbm.mavenproject.repository.FileClientDao;
import java.util.List;
import java.util.Objects;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author quentinboudinot
 */
public class DefaultClientController 
{
    private static EntityManager em;
    
    //Ajoute un client dans la base de données.
    public void createClient(String lastname, String firstname, String address, String phone, String email, String password) 
    {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("fr.utbm_mavenproject_jar_1.0-SNAPSHOTPU");
        em = emf.createEntityManager();
        em.getTransaction().begin();
        Client cli = new Client();
        cli.setLastname(lastname);
        cli.setFirstname(firstname);
        cli.setAddress(address);
        cli.setPhone(phone);
        cli.setEmail(email);
        cli.setPassword(password);
        em.persist(cli);
        em.getTransaction().commit();
    }
    
    //Récupération d'un client en BDD avec son email.
    public boolean checkEmail(String email) 
    {
        FileClientDao fcd = new FileClientDao();
        boolean emailExists = fcd.checkIfEmailExists(email);
        return emailExists;
    }
    
    //Compare le mot de passe donné en parametre avec celui de la BDD.
    public boolean comparePasswords(String email, String passwordFromForm)
    {
        FileClientDao fcd = new FileClientDao();
        Client cli = fcd.getClientByEmail(email); //récupération du client
        String passwordFromDB = cli.getPassword(); //récupération du mot de passe stocké en base
        passwordFromDB = passwordFromDB.replaceAll("\\s",""); //on supprime les espaces
        return (passwordFromDB.equals(passwordFromForm)); //on return true ou false en fonction de la différence de mot de passe
    }
}
