/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.utbm.mavenproject.repository;

import fr.utbm.mavenproject.entity.Client;
import java.math.BigInteger;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

/**
 *
 * @author quentinboudinot
 */
public class FileClientDao 
{
    private static final String JPA_UNIT_NAME = "fr.utbm_mavenproject_jar_1.0-SNAPSHOTPU";
    private EntityManager entityManager;

    protected EntityManager getEntityManager() 
    {
        if (entityManager == null) {
            entityManager = Persistence.createEntityManagerFactory(JPA_UNIT_NAME).createEntityManager();
        }
        return entityManager;
    }
    
    /**
    * L'opération Read
    * @return tous les clients présents dans la base de données.
    * @author quentinboudinot
    */
    public List<Client> selectAll() {
        List<Client> clients = getEntityManager().createQuery("select c from Client c").getResultList();
        return clients;
    }
    
    /**
    * L'opération checkIfEmailExists
    * @param email
    * @return boolean true si email existe / false sinon.
    * @author quentinboudinot
    */
    public boolean checkIfEmailExists(String email) {
        Query query = getEntityManager().createQuery("SELECT COUNT(*) FROM Client c WHERE c.email =:email");
        query.setParameter("email", email);
        String count = query.getSingleResult().toString();
        
        if(count.equals("0"))
        {
            return false;
        }
        else
        {
            return true;
        }
    }
    
    /**
    * L'opération getClientByEmail
    * @param email
    * @return le client avec l'email entré.
    * @author quentinboudinot
    */
    public Client getClientByEmail(String email) {
        TypedQuery<Client> query = getEntityManager().createQuery("SELECT c FROM Client c WHERE c.email = :email", Client.class);
        Client result = query.setParameter("email", email).getSingleResult();
        return result;
    }
    
    /**
    * L'opération Create
    * @param c Le client à insérer dans la base de données.
    * @return Le client insérée
    * @author quentinboudinot
    */
    public Client insert(Client c) {
           getEntityManager().getTransaction().begin();
           getEntityManager().persist(c);
           getEntityManager().getTransaction().commit();
           return c;
    }
    
    /**
    * L'opération Delete
    * @param c Le client à supprimer de la base de données
    * @author quentinboudinot
    */
    public void delete(Client c) {
        getEntityManager().getTransaction().begin();
        c = getEntityManager().merge(c);//<-Important
        getEntityManager().remove(c);
        getEntityManager().getTransaction().commit();
    }
    
    /**
    * L'opération Update
    * @param c Le client à mettre à jour dans la base de données.
    * @return Le client mise à jour
    * @author quentinboudinot
    */
    public Client update(Client c) {
        getEntityManager().getTransaction().begin();
        c = getEntityManager().merge(c);
        getEntityManager().getTransaction().commit();
        return c;
    }
}