/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.utbm.mavenproject.repository;

import fr.utbm.mavenproject.entity.Client;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

/**
 *
 * @author quentinboudinot
 */
public class ClientDao 
{
    private static final String JPA_UNIT_NAME = "fr.utbm_mavenproject_jar_1.0-SNAPSHOTPU";
    private EntityManager entityManager;

    protected EntityManager getEntityManager() {
        if (entityManager == null) {
            entityManager = Persistence.createEntityManagerFactory(JPA_UNIT_NAME).createEntityManager();
        }
        return entityManager;
    }

    public Client getClientByEmail(String email) {
        TypedQuery<Client> query = getEntityManager().createQuery("select c from Client c where c.email = :email", Client.class);
        Client result = query.setParameter("email", email).getSingleResult();
        return result;
    }
    
    public List<Client> getClients(){
        List<Client> result = getEntityManager().createQuery("select c from Client c", Client.class).getResultList();
        return result;
    }
    
    public Client insert(Client c) {
           getEntityManager().getTransaction().begin();
           getEntityManager().persist(c);
           getEntityManager().getTransaction().commit();
           return c;
    }

    public void delete(Client c) {
        getEntityManager().getTransaction().begin();
        c = getEntityManager().merge(c);//<-Important
        getEntityManager().remove(c);
        getEntityManager().getTransaction().commit();
    }
    
    public Client update(Client c) {
        getEntityManager().getTransaction().begin();
        c = getEntityManager().merge(c);
        getEntityManager().getTransaction().commit();
        return c;
    }
    
    //##################################################
    
    public boolean checkIfEmailExists(String email) {
        Query query = getEntityManager().createQuery("select count(*) from Client c where c.email =:email");
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
    
    public boolean checkIfClientIsInCs(String clientId, String csId)
    {
        CourseClientDao fcscd = new CourseClientDao();
        boolean exists = fcscd.checkIfClientIsInCs(clientId,csId); //récupération du client
        return exists;
    }
}